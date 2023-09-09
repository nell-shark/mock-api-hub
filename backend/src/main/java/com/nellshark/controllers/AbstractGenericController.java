package com.nellshark.controllers;

import com.nellshark.services.AbstractGenericService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public abstract class AbstractGenericController<T> {

  private final AbstractGenericService<T> service;

  public AbstractGenericController(AbstractGenericService<T> service) {
    this.service = service;
  }

  public AbstractGenericService<T> getService() {
    return service;
  }

  @GetMapping
  public ResponseEntity<List<T>> getAllEntities() {
    return ResponseEntity.ok(service.getAllEntities());
  }
}
