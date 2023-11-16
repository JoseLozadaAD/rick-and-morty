package com.assuresoft.rickandmorty.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
  private final UUID ID = UUID.randomUUID();
  private final String NAME = "Test name";
  private final String STATUS = "Test name";
  private final Gender GENDER = Gender.MALE;
  private final String SPECIE = "Human";
  private final String DIMENSION = "c137";
  private Character character;
  private Location location;

  @BeforeEach
  void setup() {
    location = new Location();
    character = Character.builder()
        .id(ID)
        .name(NAME)
        .status(STATUS)
        .location(location)
        .gender(GENDER)
        .specie(SPECIE)
        .dimension(DIMENSION)
        .build();
  }

  @Test
  void testNoArgsConstructor() {
    final Character noArgsCharacter = new Character();

    assertNotNull(noArgsCharacter);
    assertNull(noArgsCharacter.getName());
  }

  @Test
  void testGetters() {
    assertEquals(ID, character.getId());
    assertEquals(NAME, character.getName());
    assertEquals(STATUS, character.getStatus());
    assertEquals(SPECIE, character.getSpecie());
    assertEquals(GENDER, character.getGender());
    assertEquals(DIMENSION, character.getDimension());
    assertEquals(location, character.getLocation());
    assertNull(character.getCreationDate());
    assertNull(character.getLastUpdateDate());
  }

  @Test
  void testSetters() {
    final String newName = "New name";
    final String newStatus = "Dead";
    final String newSpecie = "New specie";
    final String newDimension = "b23";
    final Gender newGender = Gender.GENDERLESS;
    final Location newLocation = new Location();

    character.setName(newName);
    character.setStatus(newStatus);
    character.setSpecie(newSpecie);
    character.setDimension(newDimension);
    character.setGender(newGender);
    character.setLocation(newLocation);

    assertEquals(newName, character.getName());
    assertEquals(newStatus, character.getStatus());
    assertEquals(newSpecie, character.getSpecie());
    assertEquals(newGender, character.getGender());
    assertEquals(newDimension, character.getDimension());
    assertNull(character.getCreationDate());
    assertNull(character.getLastUpdateDate());
  }

  @Test
  void testEquals() {
    final Gender gender = Gender.FEMALE;

    Character character1 = Character.builder().id(ID).name(NAME).gender(gender).build();
    Character character2 = Character.builder().id(ID).name(NAME).build();

    assertTrue(character1.equals(character2));
  }
}
