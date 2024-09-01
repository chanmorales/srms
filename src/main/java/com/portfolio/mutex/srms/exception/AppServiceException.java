package com.portfolio.mutex.srms.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppServiceException extends RuntimeException {

  /**
   * The optional field where the validation failure occurs
   */
  private final String field;
  /**
   * The response status code for the exception
   */
  private final int statusCode;

  public AppServiceException(String message) {
    this(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public AppServiceException(String message, HttpStatus status) {
    this(message, null, status);
  }

  public AppServiceException(String message, String field, HttpStatus status) {
    super(message);
    this.field = field;
    this.statusCode = status.value();
  }
}
