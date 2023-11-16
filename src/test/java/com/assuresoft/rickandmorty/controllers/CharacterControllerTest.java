package com.assuresoft.rickandmorty.controllers;

import com.assuresoft.rickandmorty.models.Character;
import com.assuresoft.rickandmorty.services.CharacterService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CharacterService service;
  private final UUID ID = UUID.randomUUID();
  private Character character;

  @BeforeEach
  void setup() {
    // To initialize the mocks before every test
    MockitoAnnotations.openMocks(this);
    character = Character.builder()
        .id(ID)
        .name("Test name")
        .build();
  }

  @Test
  void testGetAll() throws Exception {
    final List<Character> characterList = Arrays.asList(character);

    when(service.findAll()).thenReturn(characterList);

    mockMvc.perform(get(Path.CHARACTER))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", is(characterList.get(0).getName())));
  }

  @Test
  void testGetById() throws Exception {
    when(service.findById(any(UUID.class))).thenReturn(character);

    mockMvc.perform(get(Path.CHARACTER + "/" + ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(character.getName())));
  }

  @Test
  void testCreate() throws Exception {
    when(service.create(any(Character.class))).thenReturn(character);

    mockMvc.perform(MockMvcRequestBuilders
          .post(Path.CHARACTER)
          .content(HelperMethods.asJsonString(character))
          .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is(character.getName())));
  }

  @Test
  void testUpdate() throws Exception {
    when(service.update(any(UUID.class), any(Character.class))).thenReturn(character);

    mockMvc.perform(MockMvcRequestBuilders
            .put(Path.CHARACTER + "/" + ID)
            .content(HelperMethods.asJsonString(character))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.name", is(character.getName())));
  }

  @Test
  void testDelete() throws Exception {
    when(service.delete(any(UUID.class))).thenReturn(character);

    mockMvc.perform(MockMvcRequestBuilders
            .delete(Path.CHARACTER + "/" + ID))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.name", is(character.getName())));
  }
}
