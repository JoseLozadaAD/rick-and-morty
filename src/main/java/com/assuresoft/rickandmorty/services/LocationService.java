package com.assuresoft.rickandmorty.services;

import com.assuresoft.rickandmorty.exceptions.EntityNotFoundException;
import com.assuresoft.rickandmorty.models.Character;
import com.assuresoft.rickandmorty.models.Location;
import com.assuresoft.rickandmorty.repositories.CharacterJpaRepository;
import com.assuresoft.rickandmorty.repositories.LocationJpaRepository;
import com.assuresoft.rickandmorty.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Location service class handles all the request methods related to the Character entity. <br>
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
public class LocationService implements EntityService<Location> {
  private final LocationJpaRepository repository;

  @Override
  public List<Location> findAll() {
    return repository.findAll();
  }

  @Override
  public Location findById(UUID id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFoundException(String.format(ErrorMessages.LOCATION_NOT_FOUND, id));
    }

    return repository.findById(id).get();
  }

  @Override
  public Location create(Location entityToSave) {
    return repository.save(entityToSave);
  }

  @Override
  public Location update(UUID id, Location entityToUpdate) {
    Location locationFromDb = findById(id);

    entityToUpdate.setId(locationFromDb.getId());
    entityToUpdate.setCreationDate(locationFromDb.getCreationDate());

    return repository.save(entityToUpdate);
  }

  @Override
  public Location delete(UUID id) {
    Location locationToDelete = findById(id);
    repository.delete(locationToDelete);

    return locationToDelete;
  }
}
