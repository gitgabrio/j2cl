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

import com.google.common.base.Preconditions;
import com.google.j2cl.ast.processors.Visitable;

/**
 * Class for catch clause.
 */
@Visitable
public class CatchClause extends Node {
  @Visitable Block body;
  @Visitable Variable exceptionVar;

  public CatchClause(Block body, Variable exceptionVar) {
    Preconditions.checkNotNull(body);
    Preconditions.checkNotNull(exceptionVar);
    this.body = body;
    this.exceptionVar = exceptionVar;
  }

  public Block getBody() {
    return body;
  }

  public Variable getExceptionVar() {
    return exceptionVar;
  }

  public void setBody(Block body) {
    this.body = body;
  }

  public void setExceptionVar(Variable exceptionVar) {
    this.exceptionVar = exceptionVar;
  }

  @Override
  public Node accept(Processor processor) {
    return Visitor_CatchClause.visit(processor, this);
  }
}