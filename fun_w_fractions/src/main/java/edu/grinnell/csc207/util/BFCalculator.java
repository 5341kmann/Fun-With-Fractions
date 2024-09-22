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

  private void baseTo(BigFraction val) {
    val.reduce();
    BigInteger newDenom = currentVal.getDenom().multiply(val.getDenom());
    // setting numerators
    currentVal.setNum(currentVal.getNum().multiply(val.getDenom()));
    val.setNum(val.getNum().multiply(currentVal.getDenom()));

    // setting denominators
    currentVal.setDenom(newDenom);
    val.setDenom(newDenom);
  }

  public void add(BigFraction val) {
    baseTo(val);
    currentVal.setDenom(currentVal.getDenom().add(val.getDenom()));
    currentVal.reduce();
  }

  public void subtract(BigFraction val) {
    baseTo(val);
    currentVal.setDenom(currentVal.getDenom().subtract(val.getDenom()));
    currentVal.reduce();
  }

  public void multiply(BigFraction val) {
    baseTo(val);
    currentVal.setDenom(currentVal.getDenom().multiply(val.getDenom()));
    currentVal.reduce();
  }
  
  public void divide(BigFraction val) {
    baseTo(val);
    currentVal.setDenom(currentVal.getDenom().divide(val.getDenom()));
    currentVal.reduce();
  }

  public void clear() {
    currentVal.setDenom(BigInteger.valueOf(0));
    currentVal.setNum(BigInteger.valueOf(1));
  }
}
