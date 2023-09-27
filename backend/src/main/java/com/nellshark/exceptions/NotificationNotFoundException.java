package com.nellshark.exceptions;

public class NotificationNotFoundException extends RuntimeException {

  public NotificationNotFoundException(String message) {
    super(message);
  }
}
