package com.nellshark.exceptions;

public class ResourceLoadException extends RuntimeException {

  public ResourceLoadException(String message, Throwable cause) {
    super(message, cause);
  }
}
