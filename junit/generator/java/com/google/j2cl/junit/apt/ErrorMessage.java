/*
 * Copyright 2016 Google Inc.
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
package com.google.j2cl.junit.apt;

import javax.tools.Diagnostic.Kind;

enum ErrorMessage {
  NON_VOID_RETURN("Method %s has a non void return type."),
  NON_PROMISE_RETURN(
      "Method %s has a non void return type that is not a promise-like. "
          + "A promise-like type is a type that is annotated with @JsType "
          + "and has a 'then' method. 'then' method should have a 'success' callback parameter"
          + "and an optional 'failure' callback parameter where both are"
          + "@JsFunction or @FunctionalInterface."),
  HAS_ARGS("Method %s cannot have arguments."),
  IS_STATIC("Method %s cannot be static."),
  NON_PUBLIC("Method %s cannot be non-public."),
  HAS_TIMEOUT("Method %s has timeout attribute but doesn't return a promise-like type."),
  ASYNC_HAS_EXPECTED_EXCEPTION(
      "Method %s has expectedException attribute but returns a promise-like type."),
  ASYNC_NO_TIMEOUT("Method %s is missing @Test timeout attribute."),
  NON_TOP_LEVEL_TYPE("Type %s is not a top level class."),
  SKIPPED_TYPE("Type %s is not a JUnit test or a JUnit4 style suite. Skipped.", Kind.WARNING),
  JUNIT3_SUITE("Type %s is a JUnit3 style suite. j2cl_test supports only JUnit4 style suites."),
  NO_TEST(
      "No tests found.\n"
          + " This usually means that the name of your test does not match your Java test class"
          + " or that you have an invalid value in test_class attribute."),
  CANNOT_WRITE_RESOURCE("Can not write jsunit test suite file: %s");

  private final String formattedMsg;
  private final Kind kind;

  private ErrorMessage(String formattedMsg, Kind kind) {
    this.formattedMsg = formattedMsg;
    this.kind = kind;
  }

  private ErrorMessage(String formattedMsg) {
    this(formattedMsg, Kind.ERROR);
  }

  public Kind kind() {
    return kind;
  }

  public String format(Object... args) {
    return String.format(formattedMsg, args);
  }
}
