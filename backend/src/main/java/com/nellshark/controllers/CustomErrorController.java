package com.nellshark.controllers;

import com.nellshark.exceptions.HandlerNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

  @GetMapping("/error")
  public void handleError(HttpServletRequest request) {
    String originalUrl = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    throw new HandlerNotFoundException("Error processing the request: " + originalUrl);
  }
}
