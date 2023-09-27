package com.nellshark.services;

import com.nellshark.exceptions.TodoNotFoundException;
import com.nellshark.models.Todo;
import com.nellshark.repositories.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo getTodoById(Long id) {
    return todoRepository
        .findById(id)
        .orElseThrow(() -> new TodoNotFoundException("Todo with id %s not found".formatted(id)));
  }
}
