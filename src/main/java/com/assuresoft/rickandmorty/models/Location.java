package com.assuresoft.rickandmorty.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * This class represent the location entity in database
 *
 * @author Jose Lozada
 */
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "locations")
@EqualsAndHashCode(of = { "id", "name" })
public class Location {
  @Id
  private String id;
  private String name;
  private String type;
  private String dimension;
  private Date creationDate;
  private Date lastUpdateDate;
}
