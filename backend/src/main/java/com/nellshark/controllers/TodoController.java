package com.nellshark.controllers;

import com.nellshark.models.Todo;
import com.nellshark.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
    Todo todo = todoService.getTodoById(id);
    return ResponseEntity.ok(todo);
  }
}
