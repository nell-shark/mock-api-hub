package com.nellshark.repositories;

import com.nellshark.models.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  private final List<User> users;

  public UserRepository() {
    this.users =  List.of(
        new User(1L, "Nelly")
    );
  }

  public List<User> findAll(){
    return users;
  }
}
