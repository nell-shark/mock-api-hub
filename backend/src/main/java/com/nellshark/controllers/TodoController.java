package com.nellshark.controllers;

import com.nellshark.models.Todo;
import com.nellshark.services.TodoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController extends AbstractGenericController<Todo> {

  public TodoController(TodoService service) {
    super(service);
  }
}
