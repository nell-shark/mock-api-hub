package com.nellshark.services;

import com.nellshark.models.User;
import com.nellshark.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractGenericService<User> {

  public UserService(UserRepository userRepository) {
    super(userRepository);
  }
}
