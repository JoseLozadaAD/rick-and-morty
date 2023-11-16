package com.assuresoft.rickandmorty.controllers;

import com.assuresoft.rickandmorty.models.Weapon;
import com.assuresoft.rickandmorty.services.WeaponService;
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

@WebMvcTest(WeaponController.class)
public class WeaponControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private WeaponService service;
  private final String ID = UUID.randomUUID().toString();
  private Weapon weapon;

  @BeforeEach
  void setup() {
    // To initialize the mocks before every test
    MockitoAnnotations.openMocks(this);
    weapon = Weapon.builder()
        .id(ID)
        .name("Test name")
        .build();
  }

  @Test
  void testGetAll() throws Exception {
    final List<Weapon> weaponList = Arrays.asList(weapon);

    when(service.findAll()).thenReturn(weaponList);

    mockMvc.perform(get(Path.WEAPON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", is(weaponList.get(0).getName())));
  }

  @Test
  void testGetById() throws Exception {
    when(service.findById(any(String.class))).thenReturn(weapon);

    mockMvc.perform(get(Path.WEAPON + "/" + ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(weapon.getName())));
  }

  @Test
  void testCreate() throws Exception {
    when(service.create(any(Weapon.class))).thenReturn(weapon);

    mockMvc.perform(MockMvcRequestBuilders
          .post(Path.WEAPON)
          .content(HelperMethods.asJsonString(weapon))
          .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is(weapon.getName())));
  }

  @Test
  void testUpdate() throws Exception {
    when(service.update(any(String.class), any(Weapon.class))).thenReturn(weapon);

    mockMvc.perform(MockMvcRequestBuilders
            .put(Path.WEAPON + "/" + ID)
            .content(HelperMethods.asJsonString(weapon))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.name", is(weapon.getName())));
  }

  @Test
  void testDelete() throws Exception {
    when(service.delete(any(String.class))).thenReturn(weapon);

    mockMvc.perform(MockMvcRequestBuilders
            .delete(Path.WEAPON + "/" + ID))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.name", is(weapon.getName())));
  }
}
