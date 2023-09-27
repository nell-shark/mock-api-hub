package com.nellshark.services;

import com.nellshark.models.Todo;
import com.nellshark.repositories.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService extends AbstractGenericService<Todo, Long> {

  public TodoService(TodoRepository todoRepository) {
    super(todoRepository);
  }
}
