package com.google.j2cl.transpiler.readable.simpletrycatchthrow;

public class SimpleTryCatchThrow {
  public void main() {
    try {
      throw new ClassCastException();
    } catch (NullPointerException | ClassCastException e) {
      // expected empty body.
    } finally {
    }
  }
}