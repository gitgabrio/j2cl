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
package com.google.j2cl.transpiler.integration.instanceofclass;

/**
 * Test instanceof class type.
 */
public class Main {
  public static void main(String... args) {
    Object object = new Main();
    assert object instanceof Main;
    assert object instanceof Object;
    assert !(object instanceof String);
    assert "A String Literal" instanceof String;
    assert !(null instanceof Object);

    try {
      assert hasSideEffect() instanceof Object;
      assert false;
    } catch (IllegalArgumentException expected) {
    }

    try {
      assert hasSideEffect() instanceof ThreadLocal;
      assert false;
    } catch (IllegalArgumentException expected) {
    }
  }

  private static Object hasSideEffect() {
    throw new IllegalArgumentException();
  }
}
