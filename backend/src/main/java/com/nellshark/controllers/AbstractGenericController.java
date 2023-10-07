package com.nellshark.controllers;

import com.nellshark.services.AbstractGenericService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class AbstractGenericController<T, ID> {

  private final AbstractGenericService<T, ID> service;

  public AbstractGenericController(AbstractGenericService<T, ID> service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<T>> getEntities(
      @RequestParam(required = false) Map<String, String> filterParams) {
    List<T> entities = service.getEntities(filterParams);
    return ResponseEntity.ok(entities);
  }

  @GetMapping("/{id}")
  public ResponseEntity<T> getEntityById(@PathVariable("id") ID id) {
    T entity = service.getEntityById(id);
    return ResponseEntity.ok(entity);
  }

  // The server won't actually update the resource, but it will simulate the update as if it did
  @PostMapping
  public ResponseEntity<T> postEntity(@RequestBody T t) {
    return ResponseEntity.ok(t);
  }

  // The server won't actually update the resource, but it will simulate the update as if it did
  @PutMapping("/{id}")
  public ResponseEntity<T> putEntity(@PathVariable("id") ID id, @RequestBody T t) {
    return ResponseEntity.ok(t);
  }

  // The server won't actually update the resource, but it will simulate the update as if it did
  @PatchMapping("/{id}")
  public ResponseEntity<T> patchEntity(@PathVariable("id") ID id, @RequestBody T t) {
    return ResponseEntity.ok(t);
  }

  // The server won't actually update the resource, but it will simulate the update as if it did
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEntity(@PathVariable("id") ID id) {
    return ResponseEntity.ok().build();
  }
}
