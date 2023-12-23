package org.jsonex.treedoc.schema;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Set;

@Data
public class ObjectType extends Schema {
  LinkedHashMap<String, Schema> properties;

  @Override
  public boolean isSimilarTo(Schema other) {
    if (! (other instanceof ObjectType))
      return false;
    ObjectType otherObj = (ObjectType) other;
    properties.keySet().removeAll(otherObj.properties.keySet());
    for(String key : properties.keySet()) {
      if (!otherObj.properties.containsKey(key))
        return false;
      if (!properties.get(key).isSimilarTo(otherObj.properties.get(key)))
        return false;
    }

  }
}
