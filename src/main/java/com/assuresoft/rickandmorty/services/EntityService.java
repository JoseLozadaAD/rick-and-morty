package com.assuresoft.rickandmorty.services;

import java.util.List;

/**
 * The EntityService<T> interface specifies
 * the methods with which a generic type object will interact with database
 *
 * @author Jose Lozada
 */
public interface EntityService<T> {
  /**
   * Gets a list of T objects
   *
   * @return This returns a list of T objects
   */
  List<T> findAll();
  /**
   * Gets a T object found by its id
   *
   * @param id This is the id of T object
   * @return This returns Optional of a T object after find by its id
   */
  T findById(String id);
  /**
   * Saves an object in database
   *
   * @param entityToSave This is the object to save
   * @return This returns a saved T object
   */
  T create(T entityToSave);
  /**
   * Updates an object existent in database
   *
   * @param id This is the id of object
   * @param entityToUpdate This is the object to update
   * @return This returns an updated T object
   */
  T update(String id, T entityToUpdate);
  /**
   * Deletes an object existent in database
   *
   * @param id This is the id of object
   * @return This returns a deleted T object
   */
  T delete(String id);
}
