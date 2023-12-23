package org.jsonex.treedoc.schema;

import lombok.Data;

@Data
public class BooleanType extends Schema {
  public final static BooleanType instance = new BooleanType();
  private BooleanType() {}
}
