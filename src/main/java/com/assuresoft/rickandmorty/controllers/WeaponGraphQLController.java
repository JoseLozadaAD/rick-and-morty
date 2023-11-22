package com.assuresoft.rickandmorty.controllers;

import com.assuresoft.rickandmorty.models.Weapon;
import com.assuresoft.rickandmorty.repositories.WeaponMongoRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WeaponGraphQLController {
    private final WeaponMongoRepository repository;

    public WeaponGraphQLController(WeaponMongoRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    Iterable<Weapon> weapons() {
        return repository.findAll();
    }
}
