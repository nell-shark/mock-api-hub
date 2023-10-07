package com.nellshark.repositories;

import com.nellshark.models.Todo;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CustomGenericRepository<Todo, Long> {

}
