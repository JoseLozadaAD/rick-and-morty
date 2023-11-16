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
@Document(collection = "weapons")
@EqualsAndHashCode(of = { "id", "name" })
public class Weapon {
    @Id
    private String id;
    private String name;
    private String type;
    private float damage;
    private String skin;
    private Date creationDate;
    private Date lastUpdateDate;
}

