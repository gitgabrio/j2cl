package com.google.j2cl.transpiler.integration.varargs;

/**
 * Class that has a constructor with var arg parameters.
 */
class Parent {
  public int value;

  public Parent(int... args) {
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        value += args[i];
      }
    }
  }

  public Parent(String f) {
    // call the vararg-constructor by this() call.
    this(1);
  }
}

class Child extends Parent {
  public Child() {
    // call the vararg-constructor by super() call.
    super(2);
  }
}

public class Main {
  public int field;

  public Main(int f) {
    field = f;
  }

  public int bar(Main a, Main... args) {
    int result = a.field;
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        result += args[i].field;
      }
    }
    return result;
  }

  public static void main(String... args) {
    Main m = new Main(0);
    Main m1 = new Main(1);
    Main m2 = new Main(2);
    Main m3 = new Main(3);
    Main m4 = new Main(4);
    int a = m.bar(m1, m2, m3, m4); // varargs with mulitple arguments.
    assert (a == 10);

    int b = m.bar(m1); // no argument for varargs.
    assert (b == 1);

    int c = m.bar(m1, m2); // varargs with one element.
    assert (c == 3);

    int d = m.bar(m1, new Main[] {m2, m3, m4}); // array argument for the varargs.
    assert (d == 10);

    int e = m.bar(m1, new Main[] {}); // empty array for the varargs.
    assert (e == 1);

    int f = m.bar(m1, null); // null for the varargs.
    assert (f == 1);

    Parent p = new Parent(1, 2, 3); // constructor call with varargs.
    assert (p.value == 6);

    Parent pp = new Parent(""); // constructor call with varargs is invoked by this() call.
    assert (pp.value == 1);

    Child cc = new Child(); // constructor call with varargs is invoked by super() call.
    assert (cc.value == 2);
  }
}