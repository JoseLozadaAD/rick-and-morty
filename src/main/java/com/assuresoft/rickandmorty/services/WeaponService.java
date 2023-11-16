package com.assuresoft.rickandmorty.services;

import com.assuresoft.rickandmorty.exceptions.EntityNotFoundException;
import com.assuresoft.rickandmorty.models.Weapon;
import com.assuresoft.rickandmorty.repositories.WeaponMongoRepository;
import com.assuresoft.rickandmorty.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Weapon service class handles all the request methods related to the Character entity. <br>
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
 * @author Josue Veliz
 */
@Service
@Transactional
@RequiredArgsConstructor
public class WeaponService implements EntityService<Weapon> {
  private final WeaponMongoRepository repository;

  @Override
  public List<Weapon> findAll() {
    return repository.findAll();
  }

  @Override
  public Weapon findById(String id) {
    if (!repository.existsById(id)) {
      throw new EntityNotFoundException(String.format(ErrorMessages.WEAPON_NOT_FOUND, id));
    }

    return repository.findById(id).get();
  }

  @Override
  public Weapon create(Weapon entityToSave) {
    return repository.save(entityToSave);
  }

  @Override
  public Weapon update(String id, Weapon entityToUpdate) {
    final Weapon weaponFromDb = findById(id);

    entityToUpdate.setId(weaponFromDb.getId());
    entityToUpdate.setCreationDate(weaponFromDb.getCreationDate());

    return repository.save(entityToUpdate);
  }

  @Override
  public Weapon delete(String id) {
    final Weapon weaponToDelete = findById(id);
    repository.delete(weaponToDelete);

    return weaponToDelete;
  }
}
