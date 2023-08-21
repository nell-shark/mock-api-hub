package com.nellshark.controllers;

import com.nellshark.models.User;
import com.nellshark.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends AbstractGenericController<User> {

  public UserController(UserService service) {
    super(service);
  }
}
