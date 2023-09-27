package com.nellshark.exceptions;

public class TodoNotFoundException extends RuntimeException {

  public TodoNotFoundException(String message) {
    super(message);
  }
}
