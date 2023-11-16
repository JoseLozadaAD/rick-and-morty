package com.assuresoft.rickandmorty.repositories;

import com.assuresoft.rickandmorty.models.Weapon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeaponMongoRepository extends MongoRepository<Weapon, String> {
}
