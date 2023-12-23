package org.jsonex.treedoc.schema;

public abstract class Schema {
  public boolean isSimilarTo(Schema other) {
    return this.getClass() == other.getClass();
  }
}
