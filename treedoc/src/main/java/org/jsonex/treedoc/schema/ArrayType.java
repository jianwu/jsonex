package org.jsonex.treedoc.schema;

import lombok.Data;

@Data
public class ArrayType extends Schema {
  public final Schema item;
}
