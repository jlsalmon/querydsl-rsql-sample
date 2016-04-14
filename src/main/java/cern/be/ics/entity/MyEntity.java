package cern.be.ics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Justin Lewis Salmon
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyEntity {

  @Id
  private Long id;
  private String name;
  private NestedEntity nestedEntity;
}
