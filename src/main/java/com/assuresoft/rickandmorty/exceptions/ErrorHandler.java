package com.assuresoft.rickandmorty.exceptions;

import com.assuresoft.rickandmorty.utils.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Map<String, Object>> HandlerEntityNotFoundException(EntityNotFoundException exception) {
    final Map<String, Object> error = new HashMap<>();
    error.put(ErrorMessages.ERROR_MESSAGE, exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
}
