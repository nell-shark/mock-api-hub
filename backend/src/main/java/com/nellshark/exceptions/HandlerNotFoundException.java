package com.nellshark.exceptions;

public class HandlerNotFoundException extends RuntimeException {

  public HandlerNotFoundException(String message) {
    super(message);
  }
}
