package cern.be.ics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author Justin Lewis Salmon
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NestedEntity {

  private String description;
}
