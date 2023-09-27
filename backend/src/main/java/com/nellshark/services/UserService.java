package com.nellshark.services;

import com.nellshark.exceptions.UserNotFoundException;
import com.nellshark.models.User;
import com.nellshark.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUserById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id %s not found".formatted(id)));
  }
}
