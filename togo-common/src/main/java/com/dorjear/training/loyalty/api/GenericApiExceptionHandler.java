package com.dorjear.training.loyalty.api;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GenericApiExceptionHandler {

  private static final String MESSAGE_FEILD = "errorMessage";

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseBody
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public Map<String, String> handleException(ResourceNotFoundException e) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put(MESSAGE_FEILD, e.getLocalizedMessage());
    return errorResponse;
  }

  @ExceptionHandler({Exception.class, InternalServerException.class})
  @ResponseBody
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public Map<String, String> handleException(RuntimeException e) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put(MESSAGE_FEILD, e.getLocalizedMessage());
    return errorResponse;
  }
}
