package com.nellshark.repositories;

import com.nellshark.models.Todo;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public class TodoRepository extends AbstractGenericRepository<Todo> {

  public TodoRepository() {
    super(List.of(new Todo(1L, "Todo", false)));
  }
}
