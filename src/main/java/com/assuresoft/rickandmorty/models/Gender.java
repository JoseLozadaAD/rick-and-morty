package com.assuresoft.rickandmorty.models;

public enum Gender {
  MALE("Male"),
  FEMALE("Female"),
  GENDERLESS("Genderless");

  private final String name;
  private Gender(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
