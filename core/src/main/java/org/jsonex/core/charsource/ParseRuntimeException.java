/*************************************************************
 Copyright 2018-2019 eBay Inc.
 Author/Developer: Jianwu Chen

 Use of this source code is governed by an MIT-style
 license that can be found in the LICENSE file or at
 https://opensource.org/licenses/MIT.
 ************************************************************/

package org.jsonex.core.charsource;

import lombok.Getter;
import lombok.Setter;
import org.jsonex.core.type.Nullable;

public class ParseRuntimeException extends RuntimeException {
  final Bookmark bookmark;
  final String digest;
  @Getter @Setter
  final private @Nullable Object partialObject;

  public ParseRuntimeException(Throwable cause, String message, Bookmark bookmark, String digest, Object partialObject) {
    super(message, cause);
    this.bookmark = bookmark;
    this.digest = digest;
    this.partialObject = partialObject;
  }

  public String getMessage() {
    return super.getMessage() + ", " + bookmark + ", digest:" + digest;
  }
}
