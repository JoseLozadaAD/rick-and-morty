package com.assuresoft.rickandmorty.controllers;

import com.assuresoft.rickandmorty.models.Location;
import com.assuresoft.rickandmorty.services.LocationService;
import com.assuresoft.rickandmorty.utils.HelperMethods;
import com.assuresoft.rickandmorty.utils.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private LocationService service;
  private final UUID ID = UUID.randomUUID();
  private Location location;

  @BeforeEach
  void setup() {
    // To initialize the mocks before every test
    MockitoAnnotations.openMocks(this);
    location = Location.builder()
        .id(ID)
        .name("Test name")
        .build();
  }

  @Test
  void testGetAll() throws Exception {
    final List<Location> characterList = Arrays.asList(location);

    when(service.findAll()).thenReturn(characterList);

    mockMvc.perform(get(Path.LOCATION))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", is(characterList.get(0).getName())));
  }

  @Test
  void testGetById() throws Exception {
    when(service.findById(any(UUID.class))).thenReturn(location);

    mockMvc.perform(get(Path.LOCATION + "/" + ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(location.getName())));
  }

  @Test
  void testCreate() throws Exception {
    when(service.create(any(Location.class))).thenReturn(location);

    mockMvc.perform(MockMvcRequestBuilders
          .post(Path.LOCATION)
          .content(HelperMethods.asJsonString(location))
          .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is(location.getName())));
  }

  @Test
  void testUpdate() throws Exception {
    when(service.update(any(UUID.class), any(Location.class))).thenReturn(location);

    mockMvc.perform(MockMvcRequestBuilders
            .put(Path.LOCATION + "/" + ID)
            .content(HelperMethods.asJsonString(location))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.name", is(location.getName())));
  }

  @Test
  void testDelete() throws Exception {
    when(service.delete(any(UUID.class))).thenReturn(location);

    mockMvc.perform(MockMvcRequestBuilders
            .delete(Path.LOCATION + "/" + ID))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.name", is(location.getName())));
  }
}
