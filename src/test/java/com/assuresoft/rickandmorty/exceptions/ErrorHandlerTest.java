package com.assuresoft.rickandmorty.exceptions;

import com.assuresoft.rickandmorty.utils.ErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorHandlerTest {
  @InjectMocks
  private ErrorHandler errorHandler;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testHandlerEntityNotFoundException() {
    final String errorMessage = "Entity not found";
    final EntityNotFoundException exception = new EntityNotFoundException(errorMessage);

    final ResponseEntity<Map<String, Object>> responseEntity = errorHandler.HandlerEntityNotFoundException(exception);
    final Map<String, Object> responseBody = responseEntity.getBody();

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals(errorMessage, responseBody.get(ErrorMessages.ERROR_MESSAGE));
  }
}
