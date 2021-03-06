/*************************************************************
 Copyright 2018-2019 eBay Inc.
 Author/Developer: Jianwu Chen

 Use of this source code is governed by an MIT-style
 license that can be found in the LICENSE file or at
 https://opensource.org/licenses/MIT.
 ************************************************************/

package org.jsonex.jsoncoder.fieldTransformer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jsonex.jsoncoder.BeanCoderContext;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UnusedReturnValue")
@Setter @Getter @Accessors(chain=true) @RequiredArgsConstructor(staticName = "of")
public class MaskFilterByName implements FieldTransformer {
  private Map<String, MaskStrategy> fieldToStrategyMap = new HashMap<>();

  public MaskFilterByName add(String name, MaskStrategy strategy) {
    fieldToStrategyMap.put(name, strategy);
    return this;
  }

  @Override
  public FieldInfo apply(FieldInfo fieldInfo, BeanCoderContext beanCoderContext) {
    MaskStrategy strategy = fieldToStrategyMap.get(fieldInfo.getName());
    if (strategy == null)
      return fieldInfo;

    return fieldInfo.setObj(strategy.apply(fieldInfo.obj)).setType(String.class);
  }
}
