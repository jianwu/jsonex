package org.jsonex.treedoc.schema;

import lombok.Data;
import org.jsonex.core.util.SetUtil;

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
    int diff = SetUtil.difference(properties.keySet(), otherObj.properties.keySet()).size();
    Set<String> commonKeys = SetUtil.intersection(properties.keySet(), otherObj.properties.keySet());
    if (diff > commonKeys.size())
      return false;

    for(String key : commonKeys) {
      if (!properties.get(key).isSimilarTo(otherObj.properties.get(key)))
        return false;
    }
    return true;
  }
}
