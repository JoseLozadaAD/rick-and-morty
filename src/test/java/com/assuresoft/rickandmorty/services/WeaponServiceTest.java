package com.assuresoft.rickandmorty.services;

import com.assuresoft.rickandmorty.exceptions.EntityNotFoundException;
import com.assuresoft.rickandmorty.models.Weapon;
import com.assuresoft.rickandmorty.repositories.WeaponMongoRepository;
import com.assuresoft.rickandmorty.utils.ErrorMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WeaponServiceTest {
    @Mock
    private WeaponMongoRepository repository;
    @InjectMocks
    private WeaponService weaponService;
    private final String ID = UUID.randomUUID().toString();
    private Weapon weapon;

    @BeforeEach
    void setup() {
        // To initialize the mocks before every test
        MockitoAnnotations.openMocks(this);
        weapon = weapon.builder()
                .id(ID)
                .name("Test name")
                .build();
    }

    @Test
    void testFindAll() {
        final List<Weapon> weaponList = Arrays.asList(weapon);

        when(repository.findAll()).thenReturn(weaponList);

        assertNotNull(weaponService.findAll());
        assertEquals(weaponList, weaponService.findAll());
    }

    @Test
    void testFindByIdHappyPath() {
        final Optional<Weapon> weaponOptional = Optional.of(weapon);

        when(repository.existsById(any(String.class))).thenReturn(true);
        when(repository.findById(any(String.class))).thenReturn(weaponOptional);

        assertNotNull(weaponService.findById(ID));
        assertEquals(weaponOptional.get(), weaponService.findById(ID));
    }

    @Test
    void testFindByIdThrowException() {
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            when(repository.existsById(any(String.class))).thenReturn(false);
            weaponService.findById(ID);
        });

        assertEquals(exception.getMessage(), String.format(ErrorMessages.WEAPON_NOT_FOUND, ID));
    }

    @Test
    void testCreate() {
        when(repository.save(any(Weapon.class))).thenReturn(weapon);

        assertNotNull(weaponService.create(weapon));
        assertEquals(weapon, weaponService.create(weapon));
    }

    @Test
    void testUpdate() {
        final Optional<Weapon> weaponOptional = Optional.of(weapon);

        when(repository.existsById(any(String.class))).thenReturn(true);
        when(repository.findById(any(String.class))).thenReturn(weaponOptional);
        when(repository.save(any(Weapon.class))).thenReturn(weapon);

        assertNotNull(weaponService.update(ID, weapon));
        assertEquals(weapon, weaponService.update(ID, weapon));
    }

    @Test
    void testDelete() {
        final Optional<Weapon> weaponOptional = Optional.of(weapon);

        when(repository.existsById(any(String.class))).thenReturn(true);
        when(repository.findById(any(String.class))).thenReturn(weaponOptional);

        assertNotNull(weaponService.delete(ID));
        assertEquals(weapon, weaponService.delete(ID));
    }
}