package com.assuresoft.rickandmorty.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * This class represent the character entity in database
 *
 * @author Jose Lozada
 */
@Entity
@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "characters")
@EqualsAndHashCode(of = { "id", "name" })
public class Character {
  @Id
  @UuidGenerator
  private UUID id;
  private String name;
  private String status;
  @OneToOne
  @JoinColumn(name = "location_id")
  @JsonIgnoreProperties({"creationDate", "lastUpdateDate"})
  private Location location;
  private String specie;
  private Gender gender;
  private String dimension;
  @CreationTimestamp
  private Date creationDate;
  @UpdateTimestamp
  private Date lastUpdateDate;
}
