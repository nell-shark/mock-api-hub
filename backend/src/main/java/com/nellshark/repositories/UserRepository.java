package com.nellshark.repositories;

import com.nellshark.models.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractGenericRepository<User> {

  public UserRepository() {
    super(List.of());
  }
}
