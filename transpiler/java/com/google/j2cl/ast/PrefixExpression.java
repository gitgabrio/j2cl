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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.j2cl.ast.annotations.Visitable;

/**
 * Class for prefix unary expressions.
 */
@Visitable
public class PrefixExpression extends UnaryExpression {
  private PrefixOperator operator;

  private PrefixExpression(Expression operand, PrefixOperator operator) {
    super(operand);
    this.operator = checkNotNull(operator);
  }

  @Override
  public PrefixOperator getOperator() {
    return operator;
  }

  @Override
  public Node accept(Processor processor) {
    return Visitor_PrefixExpression.visit(processor, this);
  }

  @Override
  public PrefixExpression clone() {
    return new PrefixExpression(getOperand().clone(), operator);
  }

  @Override
  Builder createBuilder() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * A Builder for prefix unary expressions.
   */
  public static class Builder extends UnaryExpression.Builder {

    public static Builder from(UnaryExpression expression) {
      return (Builder)
          newBuilder()
              .setOperand(expression.getOperand())
              .setOperator(expression.getOperator());
    }

    @Override
    PrefixExpression doBuild(Expression operand, Operator operator) {
      return new PrefixExpression(operand, (PrefixOperator) operator);
    }
  }
}
