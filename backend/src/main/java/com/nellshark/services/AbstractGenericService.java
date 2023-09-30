package com.nellshark.services;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractGenericService<T, ID> {

  private static final Logger logger = LoggerFactory.getLogger(AbstractGenericService.class);

  private final JpaRepository<T, ID> repository;

  public AbstractGenericService(JpaRepository<T, ID> repository) {
    this.repository = repository;
  }

  public List<T> getEntities(Integer page, Integer size, String sort) {
    logger.info("Getting entities: page={}, size={}, sort={}", page, size, sort);

    Sort sorting = isNull(sort) ? Sort.unsorted() : Sort.by(sort);

    if (isNull(size) && isNull(page)) {
      return repository.findAll(sorting);
    }

    page = nonNull(page) && page > 1 ? page - 1 : 0;
    size = nonNull(size) && size > 0 ? size : 10;

    PageRequest pageRequest = PageRequest.of(page, size, sorting);
    return repository.findAll(pageRequest).getContent();
  }

  public T getEntityById(ID id) {
    return repository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Entity with id %s not found".formatted(id)));
  }
}
