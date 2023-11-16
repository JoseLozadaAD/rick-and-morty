package com.assuresoft.rickandmorty.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenderTest {
  @Test
  public void testEnumValues() {
    assertEquals("Male", Gender.MALE.getName());
    assertEquals("Female", Gender.FEMALE.getName());
    assertEquals("Genderless", Gender.GENDERLESS.getName());
  }
}
