package org.jsonex.treedoc.schema;

import lombok.Data;

import java.util.List;

@Data
public class MixTypeArrayType extends Schema {
  public final List<Schema> items;
}
