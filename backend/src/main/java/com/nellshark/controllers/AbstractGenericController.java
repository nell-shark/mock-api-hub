package com.nellshark.controllers;

import com.nellshark.services.AbstractGenericService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class AbstractGenericController<T, ID> {

  private final AbstractGenericService<T, ID> service;

  public AbstractGenericController(AbstractGenericService<T, ID> service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<T>> getEntities(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) String sort,
      @RequestParam(required = false) Integer limit
  ) {
    List<T> entities = service.getEntities(page, sort, limit);
    return ResponseEntity.ok(entities);
  }

  @GetMapping("/{id}")
  public ResponseEntity<T> getEntityById(@PathVariable("id") ID id) {
    T entity = service.getEntityById(id);
    return ResponseEntity.ok(entity);
  }
}
