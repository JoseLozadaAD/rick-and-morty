package com.assuresoft.rickandmorty.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {

    super(message);
  }
}
