package com.assuresoft.rickandmorty.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
  private final UUID ID = UUID.randomUUID();
  private final String NAME = "Test name";
  private final String TYPE = "Planet";

  private final String DIMENSION = "c137";
  private Location location;

  @BeforeEach
  public void setup() {
    location = Location.builder()
        .id(ID)
        .name(NAME)
        .type(TYPE)
        .dimension(DIMENSION)
        .build();
  }

  @Test
  public void testNoArgsConstructor() {
    final Location noArgsLocation = new Location();

    assertNotNull(noArgsLocation);
    assertNull(noArgsLocation.getName());
  }

  @Test
  public void testGetters() {
    assertEquals(ID, location.getId());
    assertEquals(NAME, location.getName());
    assertEquals(TYPE, location.getType());
    assertEquals(DIMENSION, location.getDimension());
    assertNull(location.getCreationDate());
    assertNull(location.getLastUpdateDate());
  }

  @Test
  public void testSetters() {
    final String newName = "New name";
    final String newType = "Gas Station";
    final String newDimension = "b25";

    location.setName(newName);
    location.setType(newType);
    location.setDimension(newDimension);

    assertEquals(newName, location.getName());
    assertEquals(newType, location.getType());
    assertEquals(newDimension, location.getDimension());
    assertNull(location.getCreationDate());
    assertNull(location.getLastUpdateDate());
  }

  @Test
  public void testEquals() {
    final Location location1 = Location.builder().id(ID).name(NAME).build();
    final Location location2 = Location.builder().id(ID).name(NAME).build();

    assertTrue(location1.equals(location2));
  }
}
