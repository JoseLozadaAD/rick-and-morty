package com.assuresoft.rickandmorty.repositories;

import com.assuresoft.rickandmorty.models.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterJpaRepository extends MongoRepository<Character, String> {
}
