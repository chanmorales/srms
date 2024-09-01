package com.portfolio.mutex.srms.exception;

import com.portfolio.mutex.srms.dto.ExceptionDto;
import com.portfolio.mutex.srms.util.LocaleHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class AppControllerAdvice {

  private final LocaleHelper localeHelper;

  /**
   * Handles the {@link AppServiceException} if it occurs
   *
   * @param exception the exception
   * @return customized response based on the exception
   */
  @ExceptionHandler(AppServiceException.class)
  public ResponseEntity<ExceptionDto> handleAppServiceException(AppServiceException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity.status(exception.getStatusCode())
        .body(toExceptionDto(exception.getMessage(), exception.getField()));
  }

  /**
   * Handles the generic {@link Exception} if it occurs
   *
   * @param exception the exception
   * @return customized response based on the exception
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionDto> handleException(Exception exception) {
    log.error(exception.getMessage(), exception);
    if (exception instanceof BadCredentialsException) {
      // Invalid credentials
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(toExceptionDto(localeHelper.toLocale("invalid_credentials")));
    } else if (exception instanceof SignatureException ||
        exception instanceof ExpiredJwtException) {
      // Invalid JWT (bad format or expired)
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(toExceptionDto(localeHelper.toLocale("invalid_token")));
    } else if (exception instanceof AccountStatusException) {
      // Account is currently locked
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(toExceptionDto(localeHelper.toLocale("locked_account")));
    } else if (exception instanceof AccessDeniedException) {
      // Forbidden access
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(toExceptionDto(localeHelper.toLocale("forbidden_access")));
    } else {
      // Generic exception
      return ResponseEntity.internalServerError().body(toExceptionDto(exception.getMessage()));
    }
  }

  /**
   * Creates an exception dto based on message
   *
   * @param message the error message
   * @return the exception dto
   */
  private ExceptionDto toExceptionDto(String message) {
    return toExceptionDto(message, null);
  }

  /**
   * Creates an exception dto based on message and field
   *
   * @param message the error message
   * @param field   the error field
   * @return the exception dto
   */
  private ExceptionDto toExceptionDto(String message, String field) {
    return ExceptionDto.builder().message(message).field(field).build();
  }
}
