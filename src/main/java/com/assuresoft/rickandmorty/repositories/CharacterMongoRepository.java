package com.assuresoft.rickandmorty.repositories;

import com.assuresoft.rickandmorty.models.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacterMongoRepository extends MongoRepository<Character, String> {
}
