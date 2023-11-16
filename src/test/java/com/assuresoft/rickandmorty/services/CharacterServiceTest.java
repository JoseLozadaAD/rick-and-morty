package com.assuresoft.rickandmorty.services;

import com.assuresoft.rickandmorty.exceptions.EntityNotFoundException;
import com.assuresoft.rickandmorty.models.Character;
import com.assuresoft.rickandmorty.repositories.CharacterJpaRepository;
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

public class CharacterServiceTest {
  @Mock
  private CharacterJpaRepository repository;
  @InjectMocks
  private CharacterService characterService;
  private final UUID ID = UUID.randomUUID();
  private Character character;

  @BeforeEach
  void setup() {
    // To initialize the mocks before every test
    MockitoAnnotations.openMocks(this);
    character = Character.builder()
        .id(ID)
        .name("Test name")
        .build();
  }

  @Test
  void testFindAll() {
    final List<Character> characterList = Arrays.asList(character);

    when(repository.findAll()).thenReturn(characterList);

    assertNotNull(characterService.findAll());
    assertEquals(characterList, characterService.findAll());
  }

  @Test
  void testFindByIdHappyPath() {
    final Optional<Character> characterOptional = Optional.of(character);

    when(repository.existsById(any(UUID.class))).thenReturn(true);
    when(repository.findById(any(UUID.class))).thenReturn(characterOptional);

    assertNotNull(characterService.findById(ID));
    assertEquals(characterOptional.get(), characterService.findById(ID));
  }

  @Test
  void testFindByIdThrowException() {
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      when(repository.existsById(any(UUID.class))).thenReturn(false);
      characterService.findById(ID);
    });

    assertEquals(exception.getMessage(), String.format(ErrorMessages.CHARACTER_NOT_FOUND, ID));
  }

  @Test
  void testCreate() {
    when(repository.save(any(Character.class))).thenReturn(character);

    assertNotNull(characterService.create(character));
    assertEquals(character, characterService.create(character));
  }

  @Test
  void testUpdate() {
    final Optional<Character> characterOptional = Optional.of(character);

    when(repository.existsById(any(UUID.class))).thenReturn(true);
    when(repository.findById(any(UUID.class))).thenReturn(characterOptional);
    when(repository.save(any(Character.class))).thenReturn(character);

    assertNotNull(characterService.update(ID, character));
    assertEquals(character, characterService.update(ID, character));
  }

  @Test
  void testDelete() {
    final Optional<Character> characterOptional = Optional.of(character);

    when(repository.existsById(any(UUID.class))).thenReturn(true);
    when(repository.findById(any(UUID.class))).thenReturn(characterOptional);

    assertNotNull(characterService.delete(ID));
    assertEquals(character, characterService.delete(ID));
  }
}
