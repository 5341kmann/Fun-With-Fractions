package edu.grinnell.csc207.util;

public class BFRegisterSet {

  // fields
  private BigFraction[] register;

  private static final int BASE = 97;

  // Constructors
  public BFRegisterSet() {
    this.register = new BigFraction[26];
  }
  // Methods
  private int getIndex(char val) {
    int index = (int) (val) - BASE;
    return index;
  }

  public void store(char var, BigFraction val) {
    register[getIndex(var)] = val;
  }

  public BigFraction get(char var) {
    return register[getIndex(var)];
  }

  public boolean isInRegister(char var) {
    if (register[getIndex(var)] == null) {
      return false;
    }
    return true;
  }
}