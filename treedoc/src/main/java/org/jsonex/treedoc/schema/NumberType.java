package org.jsonex.treedoc.schema;

import lombok.Data;

@Data
public class NumberType extends Schema {
  public final static NumberType instance = new NumberType();
  private NumberType() {}
}
