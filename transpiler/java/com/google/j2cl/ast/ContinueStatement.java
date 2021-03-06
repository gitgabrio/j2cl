/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.j2cl.ast;

import com.google.j2cl.ast.annotations.Visitable;
import com.google.j2cl.common.SourcePosition;
import javax.annotation.Nullable;

/** Continue Statement. */
@Visitable
public class ContinueStatement extends Statement {

  @Nullable private final String label;

  private ContinueStatement(SourcePosition sourcePosition, String label) {
    super(sourcePosition);
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public ContinueStatement clone() {
    return new ContinueStatement(getSourcePosition(), label);
  }

  @Override
  public Node accept(Processor processor) {
    return Visitor_ContinueStatement.visit(processor, this);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /** Builder for ContinueStatement. */
  public static class Builder {
    private String label;
    private SourcePosition sourcePosition;

    public static Builder from(ContinueStatement continueStatement) {
      return newBuilder()
          .setSourcePosition(continueStatement.getSourcePosition())
          .setLabel(continueStatement.getLabel());
    }

    public Builder setSourcePosition(SourcePosition sourcePosition) {
      this.sourcePosition = sourcePosition;
      return this;
    }

    public Builder setLabel(String label) {
      this.label = label;
      return this;
    }

    public ContinueStatement build() {
      return new ContinueStatement(sourcePosition, label);
    }
  }
}
