package edu.grinnell.csc207.util;

import java.math.BigInteger;

public class BFCalculator {
  // Fields
  private BigFraction currentVal;

  // Constructors
  public BFCalculator() {
    currentVal = new BigFraction(BigInteger.valueOf(0), BigInteger.valueOf(1));
  }

  public BFCalculator(BigFraction initialVal) {
    this.currentVal = initialVal;
  }

  // Methods
  public BigFraction get() {
    return currentVal;
  }

  public void setValue(BigFraction val) {
    currentVal = val;
  }
  
  public void add(BigFraction val) {
    currentVal = currentVal.add(val);
  }

  public void subtract(BigFraction val) {
    currentVal = currentVal.subtract(val);
  }

  public void multiply(BigFraction val) {
   currentVal = currentVal.multiply(val);
  }

  public void divide(BigFraction val) {
    currentVal = currentVal.divide(val);
  }

  public void clear() {
    currentVal.setDenom(BigInteger.valueOf(1));
    currentVal.setNum(BigInteger.valueOf(0));
  }
}
