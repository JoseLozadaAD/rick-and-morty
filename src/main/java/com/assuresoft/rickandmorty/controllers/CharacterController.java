package com.assuresoft.rickandmorty.controllers;

import com.assuresoft.rickandmorty.models.Character;
import com.assuresoft.rickandmorty.services.CharacterService;
import com.assuresoft.rickandmorty.utils.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Character controller class for handling HTTP requests related to the Character entity.
 * <br> <br>
 * Request:
 * <ul>
 *   <li>GET all</li>
 *   <li>GET by its id</li>
 *   <li>POST</li>
 *   <li>PUT by its id</li>
 *   <li>DELETE by its id</li>
 * </ul>
 *
 * @author Jose Lozada
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Path.CHARACTER)
public class CharacterController {
  private final CharacterService characterService;

  @GetMapping
  public ResponseEntity<List<Character>> getAll() {
    return ResponseEntity.ok(characterService.findAll());
  }

  @GetMapping(Path.ID)
  public ResponseEntity<Character> getById(@PathVariable String id) {
    return ResponseEntity.ok(characterService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Character> save(@RequestBody Character character) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(characterService.create(character));
  }

  @PutMapping(Path.ID)
  public ResponseEntity<Character> update(@PathVariable String id, @RequestBody Character character) {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(characterService.update(id, character));
  }

  @DeleteMapping(Path.ID)
  public ResponseEntity<Character> delete(@PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(characterService.delete(id));
  }
}
