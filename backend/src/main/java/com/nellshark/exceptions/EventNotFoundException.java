package com.nellshark.exceptions;

public class EventNotFoundException extends RuntimeException {

  public EventNotFoundException(String message) {
    super(message);
  }
}
