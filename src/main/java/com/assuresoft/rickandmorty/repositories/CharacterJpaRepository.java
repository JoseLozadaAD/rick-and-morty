package com.assuresoft.rickandmorty.repositories;

import com.assuresoft.rickandmorty.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterJpaRepository extends JpaRepository<Character, UUID> {
}
