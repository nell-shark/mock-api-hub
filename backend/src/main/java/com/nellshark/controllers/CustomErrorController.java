package com.nellshark.controllers;

import static jakarta.servlet.RequestDispatcher.ERROR_REQUEST_URI;

import com.nellshark.exceptions.HandlerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

  @GetMapping("/error")
  public void handleError(HttpServletRequest request) {
    String originalUrl = (String) request.getAttribute(ERROR_REQUEST_URI);
    throw new HandlerNotFoundException("Error processing the request: " + originalUrl);
  }
}
