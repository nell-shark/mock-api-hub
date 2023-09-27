package com.nellshark.exceptions;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler({
      AddressNotFoundException.class,
      BookNotFoundException.class,
      CommentNotFoundException.class,
      CompanyNotFoundException.class,
      CourseNotFoundException.class,
      EmployeeNotFoundException.class,
      EventNotFoundException.class,
      MessageNotFoundException.class,
      NotificationNotFoundException.class,
      OrderNotFoundException.class,
      PostNotFoundException.class,
      PostNotFoundException.class,
      ProjectNotFoundException.class,
      RecipeNotFoundException.class,
      ReviewNotFoundException.class,
      TodoNotFoundException.class,
      UserNotFoundException.class
  })
  public ResponseEntity<ApiError> handleException(
      RuntimeException e,
      HttpServletRequest request
  ) {
    logger.warn("{} Occurred: {}", e.getClass().getSimpleName(), e.getMessage());

    ApiError apiError = new ApiError(
        NOT_FOUND,
        e.getMessage(),
        request.getRequestURI()
    );

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception e, HttpServletRequest request) {
    logger.warn("{} Occurred: {}", e.getClass().getSimpleName(), e.getMessage());

    ApiError apiError = new ApiError(
        INTERNAL_SERVER_ERROR,
        e.getMessage(),
        request.getRequestURI()
    );

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
