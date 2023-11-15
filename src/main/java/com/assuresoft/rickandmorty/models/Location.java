package com.assuresoft.rickandmorty.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * This class represent the location entity in database
 *
 * @author Jose Lozada
 */
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
@EqualsAndHashCode(of = { "id", "name" })
public class Location {
  @Id
  @UuidGenerator
  private UUID id;
  private String name;
  private String type;
  private String dimension;
  @JsonIgnore
  @OneToOne(mappedBy = "location")
  private Character character;
  @CreationTimestamp
  private Date creationDate;
  @UpdateTimestamp
  private Date lastUpdateDate;
}
