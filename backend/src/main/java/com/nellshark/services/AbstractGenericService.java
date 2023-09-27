package com.nellshark.services;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public class AbstractGenericService<T, ID> {

  private static final Logger logger = LoggerFactory.getLogger(AbstractGenericService.class);

  private final JpaRepository<T, ID> repository;

  public AbstractGenericService(JpaRepository<T, ID> repository) {
    this.repository = repository;
  }

  public List<T> getEntities(Integer page, String sort, Integer limit) {
    logger.info("Getting entities: page={}, sort={}, limit={}", page, sort, limit);

    int pageSize = 10;

    Stream<T> stream = Objects.isNull(sort)
        ? repository.findAll().stream()
        : repository.findAll(Sort.by(sort)).stream();

    if (Objects.nonNull(page) && page >= 0) {
      stream = stream.skip(page * pageSize).limit(pageSize);
    }

    if (Objects.nonNull(limit) && limit >= 1) {
      stream = stream.limit(limit);
    }

    return stream.toList();
  }

  public T getEntityById(ID id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Entity with id %s not found".formatted(id)));
  }
}
