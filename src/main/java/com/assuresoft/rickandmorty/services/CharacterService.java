package com.assuresoft.rickandmorty.services;

import com.assuresoft.rickandmorty.exceptions.EntityNotFoundException;
import com.assuresoft.rickandmorty.models.Character;
import com.assuresoft.rickandmorty.repositories.CharacterJpaRepository;
import com.assuresoft.rickandmorty.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Character service class handles all the request methods related to the Character entity. <br>
 * It implements {@link EntityService} interface.
 * <br> <br>
 * Responsibilities:
 * <ul>
 *   <li>Retrieving all characters</li>
 *   <li>Finding a character by its ID</li>
 *   <li>Creating a new character</li>
 *   <li>Updating an existing character</li>
 *   <li>Deleting an existing character</li>
 * </ul>
 *
 * @author Jose Lozada
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CharacterService implements EntityService<Character> {
  private final CharacterJpaRepository repository;

  @Override
  public List<Character> findAll() {
    return repository.findAll();
  }

  @Override
  public Character findById(String id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFoundException(String.format(ErrorMessages.CHARACTER_NOT_FOUND, id));
    }

    return repository.findById(id).get();
  }

  @Override
  public Character create(Character entityToSave) {
    return repository.save(entityToSave);
  }

  @Override
  public Character update(String id, Character entityToUpdate) {
    final Character characterFromDb = findById(id);

    entityToUpdate.setId(characterFromDb.getId());
    entityToUpdate.setCreationDate(characterFromDb.getCreationDate());

    return repository.save(entityToUpdate);
  }

  @Override
  public Character delete(String id) {
    final Character characterToDelete = findById(id);
    repository.delete(characterToDelete);

    return characterToDelete;
  }
}
