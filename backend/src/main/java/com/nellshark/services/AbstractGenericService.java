package com.nellshark.services;

import static java.util.Objects.isNull;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

import com.nellshark.repositories.CustomGenericRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractGenericService<T, ID> {

  private static final Logger logger = LoggerFactory.getLogger(AbstractGenericService.class);

  private final CustomGenericRepository<T, ID> repository;
  private final Class<T> entityClass;

  // The cast is correct, because we take type of the first generic
  @SuppressWarnings("unchecked")
  public AbstractGenericService(CustomGenericRepository<T, ID> repository) {
    this.repository = repository;
    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
    this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
  }

  public T getEntityById(ID id) {
    logger.info("Getting entity of {} by id: {}", entityClass, id);
    return repository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Entity with id %s not found".formatted(id)));
  }

  public List<T> getEntities(Map<String, String> filterParams) {
    logger.info("Getting entities of {}: filterParams={}", entityClass, filterParams);

    Optional<String> directionOptional = Optional.ofNullable(filterParams.remove("direction"));
    Optional<String> sortOptional = Optional.ofNullable(filterParams.remove("sort"));
    Optional<String> pageOptional = Optional.ofNullable(filterParams.remove("page"));
    Optional<String> sizeOptional = Optional.ofNullable(filterParams.remove("size"));

    Direction direction = directionOptional
        .map(dir -> dir.equalsIgnoreCase("DESC") ? DESC : ASC)
        .orElse(ASC);

    Sort sort = sortOptional
        .map(sortBy -> Sort.by(direction, sortBy))
        .orElse(Sort.by(direction, "id"));

    Pageable pageable;

    if (pageOptional.isEmpty() && sizeOptional.isEmpty()) {
      pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    } else {
      int page = pageOptional
          .map(Integer::parseInt)
          .filter(p -> p > 1)
          .map(p -> p - 1)
          .orElse(0);

      int size = sizeOptional
          .map(Integer::parseInt)
          .filter(s -> s > 0)
          .orElse(10);

      pageable = PageRequest.of(page, size, sort);
    }

    Specification<T> specification = getSpecification(filterParams);

    return repository.findAll(specification, pageable).getContent();
  }

  public Specification<T> getSpecification(Map<String, String> filterParams) {
    return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

      Predicate[] predicates = filterParams.entrySet()
          .stream()
          .filter(Objects::nonNull)
          .map(entry -> criteriaBuilder.equal(
              root.get(entry.getKey()),
              castToRequiredType(root.get(entry.getKey()).getJavaType(), entry.getValue())))
          .toArray(Predicate[]::new);

      return criteriaBuilder.and(predicates);
    };
  }

  private Object castToRequiredType(Class<?> fieldType, String value) {
    if (isNull(fieldType) || isNull(value)) {
      throw new IllegalArgumentException("Cannot cast value to the required type: " + fieldType);
    }

    try {
      if (fieldType.isAssignableFrom(Boolean.class)) {
        return Boolean.valueOf(value);
      }

      if (fieldType.isAssignableFrom(Double.class)) {
        return Double.valueOf(value);
      }

      if (fieldType.isAssignableFrom(Long.class)) {
        return Long.valueOf(value);
      }

      if (fieldType.isAssignableFrom(Integer.class)) {
        return Integer.valueOf(value);
      }

      if (fieldType.isAssignableFrom(LocalDateTime.class)) {
        return LocalDateTime.parse(value);
      }

      if (fieldType.isAssignableFrom(LocalDate.class)) {
        return LocalDate.parse(value);
      }

      return value;
    } catch (NumberFormatException | DateTimeParseException e) {
      throw new IllegalArgumentException("Error casting value to the required type: " + fieldType);
    }
  }
}
