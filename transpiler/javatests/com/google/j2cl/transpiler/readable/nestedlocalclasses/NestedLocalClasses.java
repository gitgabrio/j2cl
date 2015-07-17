package com.google.j2cl.transpiler.readable.nestedlocalclasses;

public class NestedLocalClasses {
  public void test(final int p) {
    final int localVar = 1;
    class InnerClass {
      public void fun() {
        final int localVarInInnerClass = 1;
        // local class with two nested levels.
        class InnerInnerClass {
          public int fieldInInnerInnerClass = localVar + localVarInInnerClass;
        }
        new InnerInnerClass();
      }
    }
  }
}