package org.jsonex.treedoc.schema;

import lombok.Data;

@Data
public class StringType extends Schema {
  public final static StringType instance = new StringType();
  private StringType() {}
}
