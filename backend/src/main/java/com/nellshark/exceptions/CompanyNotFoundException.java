package com.nellshark.exceptions;

public class CompanyNotFoundException extends RuntimeException {

  public CompanyNotFoundException(String message) {
    super(message);
  }
}
