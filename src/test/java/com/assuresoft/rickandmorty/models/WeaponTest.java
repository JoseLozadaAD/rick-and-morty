package com.assuresoft.rickandmorty.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {
  private final String ID = UUID.randomUUID().toString();
  private final String NAME = "Test name";
  private final String TYPE = "Ray Gun";
  private final float DAMAGE = 6.9f;
  private final String SKIN = "Original";
  private Weapon weapon;

  @BeforeEach
  void setup() {
    weapon = Weapon.builder()
        .id(ID)
        .name(NAME)
        .type(TYPE)
        .damage(DAMAGE)
        .skin(SKIN)
        .build();
  }

  @Test
  void testNoArgsConstructor() {
    final Weapon noArgsWeapon = new Weapon();

    assertNotNull(noArgsWeapon);
    assertNull(noArgsWeapon.getName());
  }

  @Test
  void testGetters() {
    assertEquals(ID, weapon.getId());
    assertEquals(NAME, weapon.getName());
    assertEquals(TYPE, weapon.getType());
    assertEquals(DAMAGE, weapon.getDamage());
    assertEquals(SKIN, weapon.getSkin());
    assertNull(weapon.getCreationDate());
    assertNull(weapon.getLastUpdateDate());
  }

  @Test
  void testSetters() {
    final String newName = "New name gun";
    final String newType = "Gas";
    final float newDamage = 9.8f;
    final String newSkin = "over";

    weapon.setName(newName);
    weapon.setType(newType);
    weapon.setDamage(newDamage);
    weapon.setSkin(newSkin);

    assertEquals(newName, weapon.getName());
    assertEquals(newType, weapon.getType());
    assertEquals(newDamage, weapon.getDamage());
    assertEquals(newSkin, weapon.getSkin());
    assertNull(weapon.getCreationDate());
    assertNull(weapon.getLastUpdateDate());
  }

  @Test
  void testEquals() {
    final Weapon weapon1 = Weapon.builder().id(ID).name(NAME).build();
    final Weapon weapon2 = Weapon.builder().id(ID).name(NAME).build();

    assertTrue(weapon1.equals(weapon2));
  }
}
