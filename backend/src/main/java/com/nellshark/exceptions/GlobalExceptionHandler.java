package com.nellshark.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiError> handleEntityNotFoundException(
      EntityNotFoundException e, HttpServletRequest request) {
    logger.warn("{} Occurred: {}", e.getClass().getSimpleName(), e.getMessage());

    ApiError apiError = new ApiError(
        NOT_FOUND,
        e.getMessage(),
        request.getRequestURI()
    );

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e, HttpServletRequest request) {
    logger.warn("{} Occurred: {}", e.getClass().getSimpleName(), e.getMessage());

    ApiError apiError = new ApiError(
        BAD_REQUEST,
        e.getMessage(),
        request.getRequestURI()
    );

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception e, HttpServletRequest request) {
    logger.error("{} Occurred: {}", e.getClass().getSimpleName(), e.getMessage());

    ApiError apiError = new ApiError(
        INTERNAL_SERVER_ERROR,
        e.getMessage(),
        request.getRequestURI()
    );

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
