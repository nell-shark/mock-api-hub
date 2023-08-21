package com.nellshark.repositories;

import java.util.List;

public abstract class AbstractGenericRepository<T> {

  private final List<T> list;

  public AbstractGenericRepository(List<T> list) {
    this.list = list;
  }

  public List<T> findAll() {
    return list;
  }
}
