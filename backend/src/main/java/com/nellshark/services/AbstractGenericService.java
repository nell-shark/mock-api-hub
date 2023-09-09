package com.nellshark.services;

import com.nellshark.repositories.AbstractGenericRepository;
import java.util.List;

public abstract class AbstractGenericService<T> {

  private final AbstractGenericRepository<T> repository;

  public AbstractGenericService(AbstractGenericRepository<T> repository) {
    this.repository = repository;
  }

  public AbstractGenericRepository<T> getRepository() {
    return repository;
  }

  public List<T> getAllEntities() {
    return repository.findAll();
  }
}
