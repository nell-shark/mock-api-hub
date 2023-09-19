package com.nellshark.exceptions;

public class JsonDeserializationException extends RuntimeException {

  public JsonDeserializationException(String message, Throwable cause) {
    super(message, cause);
  }
}
