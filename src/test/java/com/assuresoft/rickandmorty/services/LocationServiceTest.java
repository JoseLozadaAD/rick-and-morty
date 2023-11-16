package com.assuresoft.rickandmorty.services;

import com.assuresoft.rickandmorty.exceptions.EntityNotFoundException;
import com.assuresoft.rickandmorty.models.Character;
import com.assuresoft.rickandmorty.models.Location;
import com.assuresoft.rickandmorty.repositories.LocationJpaRepository;
import com.assuresoft.rickandmorty.utils.ErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LocationServiceTest {
  @Mock
  private LocationJpaRepository repository;
  @InjectMocks
  private LocationService locationService;
  private final UUID ID = UUID.randomUUID();
  private Location location;

  @BeforeEach
  public void setup() {
    // To initialize the mocks before every test
    MockitoAnnotations.openMocks(this);
    location = Location.builder()
        .id(ID)
        .name("Test name")
        .build();
  }

  @Test
  public void testFindAll() {
    final List<Location> locationList = Arrays.asList(location);

    when(repository.findAll()).thenReturn(locationList);

    assertNotNull(locationService.findAll());
    assertEquals(locationList, locationService.findAll());
  }

  @Test
  public void testFindByIdHappyPath() {
    final Optional<Location> locationOptional = Optional.of(location);

    when(repository.existsById(any(UUID.class))).thenReturn(true);
    when(repository.findById(any(UUID.class))).thenReturn(locationOptional);

    assertNotNull(locationService.findById(ID));
    assertEquals(locationOptional.get(), locationService.findById(ID));
  }

  @Test
  public void testFindByIdThrowException() {
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      when(repository.existsById(any(UUID.class))).thenReturn(false);
      locationService.findById(ID);
    });

    assertEquals(exception.getMessage(), String.format(ErrorMessages.LOCATION_NOT_FOUND, ID));
  }

  @Test
  public void testCreate() {
    when(repository.save(any(Location.class))).thenReturn(location);

    assertNotNull(locationService.create(location));
    assertEquals(location, locationService.create(location));
  }

  @Test
  public void testUpdate() {
    final Optional<Location> locationOptional = Optional.of(location);

    when(repository.existsById(any(UUID.class))).thenReturn(true);
    when(repository.findById(any(UUID.class))).thenReturn(locationOptional);
    when(repository.save(any(Location.class))).thenReturn(location);

    assertNotNull(locationService.update(ID, location));
    assertEquals(location, locationService.update(ID, location));
  }

  @Test
  public void testDelete() {
    final Optional<Location> locationOptional = Optional.of(location);

    when(repository.existsById(any(UUID.class))).thenReturn(true);
    when(repository.findById(any(UUID.class))).thenReturn(locationOptional);

    assertNotNull(locationService.delete(ID));
    assertEquals(location, locationService.delete(ID));
  }
}
