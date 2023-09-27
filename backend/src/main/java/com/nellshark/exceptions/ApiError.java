package com.nellshark.exceptions;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ApiError {

  private final HttpStatus status;
  private final String error;
  private final String path;
  @JsonFormat(shape = STRING)
  private final LocalDateTime timestamp;
  private final int statusCode;

  public ApiError(HttpStatus status, String error, String path) {
    this.status = status;
    this.error = error;
    this.path = path;
    this.timestamp = LocalDateTime.now();
    this.statusCode = status.value();
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getPath() {
    return path;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
