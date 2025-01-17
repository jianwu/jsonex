package org.jsonex.treedoc.schema;

public class Schema {
  public boolean isSimilarTo(Schema other) {
    return this instanceof NullType || other instanceof NullType || this.getClass() == other.getClass();
  }
}
