package com.dorjear.training.loyalty.api;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 4667090911027872931L;

  public InternalServerException(String message) {
    super(message);
  }

  public InternalServerException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalServerException(Throwable cause) {
    super(cause);
  }

  public InternalServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}