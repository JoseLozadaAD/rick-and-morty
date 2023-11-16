package com.assuresoft.rickandmorty.controllers;

import com.assuresoft.rickandmorty.models.Location;
import com.assuresoft.rickandmorty.services.LocationService;
import com.assuresoft.rickandmorty.utils.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Location controller class for handling HTTP requests related to the Location entity.
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
@RequestMapping(Path.LOCATION)
public class LocationController {
  private final LocationService locationService;

  @GetMapping
  public ResponseEntity<List<Location>> getAll() {
    return ResponseEntity.ok(locationService.findAll());
  }

  @GetMapping(Path.ID)
  public ResponseEntity<Location> getById(@PathVariable String id) {
    return ResponseEntity.ok(locationService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Location> save(@RequestBody Location location) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(locationService.create(location));
  }

  @PutMapping(Path.ID)
  public ResponseEntity<Location> update(@PathVariable String id, @RequestBody Location location) {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(locationService.update(id, location));
  }

  @DeleteMapping(Path.ID)
  public ResponseEntity<Location> delete(@PathVariable String id) {
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(locationService.delete(id));
  }
}
