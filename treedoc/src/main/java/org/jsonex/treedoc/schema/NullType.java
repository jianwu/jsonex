package org.jsonex.treedoc.schema;

import lombok.Data;

@Data
public final class NullType extends Schema {
  public final static NullType instance = new NullType();
  private NullType() {}
}
