package com.google.j2cl.transpiler.integration.arrayleafinsertion;

public class Person implements HasFullName {
  private String name;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}