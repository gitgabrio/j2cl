/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package b;

import a.A1;
import a.A2;
import a.A3;
import a.A4;
import a.A5;

public class B {
  static void main(String... args) {
    A1.newA1().getType().doSomething();
    A2.newA2().getType().get().doSomething();
    A3.newA3().getType().get().get().doSomething();
    A4.newA4().getType().doSomething();
    A5.newA5().getType().doSomething();
  }
}
