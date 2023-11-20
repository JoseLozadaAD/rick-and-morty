package com.assuresoft.rickandmorty.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * This class represent the character collection in database
 *
 * @author Jose Lozada
 */
@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "characters")
@EqualsAndHashCode(of = { "id", "name" })
public class Character {
  @Id
  private String id;
  private String name;
  private String status;
  @DBRef
  @JsonIgnoreProperties({"creationDate", "lastUpdateDate"})
  private Location location;
  @DBRef
  @JsonIgnoreProperties({"creationDate", "lastUpdateDate"})
  private List<Weapon> weapons;
  private String specie;
  private Gender gender;
  private String dimension;
  private Date creationDate;
  private Date lastUpdateDate;
}
