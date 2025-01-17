package org.jsonex.treedoc.schema;

import lombok.Data;
import org.jsonex.core.util.Assert;
import org.jsonex.treedoc.TDNode;

import java.util.List;

@Data
public class ArrayType extends Schema {
  public final List<Schema> items;

  @Override
  public boolean isSimilarTo(Schema other) {
    if (! (other instanceof ArrayType))
      return false;
    ArrayType otherArray = (ArrayType) other;
    if (items.size() > 1 || otherArray.items.size() > 1)
      return false;  // Don't support mixed type array merge yet due to complexity

    return items.get(0).isSimilarTo(otherArray.items.get(0));
  }

  public static ArrayType build(TDNode node) {
    Assert.isTrue(node.getType() == TDNode.Type.ARRAY);

  }

}
