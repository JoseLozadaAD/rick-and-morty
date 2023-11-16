package com.assuresoft.rickandmorty.repositories;

import com.assuresoft.rickandmorty.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationMongoRepository extends MongoRepository<Location, String> {
}
