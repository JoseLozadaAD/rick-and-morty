package com.assuresoft.rickandmorty.controllers;

import com.assuresoft.rickandmorty.models.Weapon;
import com.assuresoft.rickandmorty.services.WeaponService;
import com.assuresoft.rickandmorty.utils.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Weapon controller class for handling HTTP requests related to the Weapon collection.
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
 * @author Josue Veliz
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Path.WEAPON)
public class WeaponController {
  private final WeaponService weaponService;

  @GetMapping
  public ResponseEntity<List<Weapon>> getAll() {
    return ResponseEntity.ok(weaponService.findAll());
  }

  @GetMapping(Path.ID)
  public ResponseEntity<Weapon> getById(@PathVariable String id) {
    return ResponseEntity.ok(weaponService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Weapon> save(@RequestBody Weapon weapon) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(weaponService.create(weapon));
  }

  @PutMapping(Path.ID)
  public ResponseEntity<Weapon> update(@PathVariable String id, @RequestBody Weapon weapon) {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(weaponService.update(id, weapon));
  }

  @DeleteMapping(Path.ID)
  public ResponseEntity<Weapon> delete(@PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(weaponService.delete(id));
  }
}
