package edu.grinnell.csc207.util;

import java.math.BigInteger;

public class BigFraction {
  // Fields
  private BigInteger num;

  private BigInteger denom;

  // Constructors
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  }

  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  }

  public BigFraction(BigInteger wholeNumb) {
    this.num = wholeNumb;
    this.denom = BigInteger.ONE;
  }
  
  public BigFraction(String str) {
    String[] terms = str.split("/");

    this.num = BigInteger.valueOf(Integer.parseInt(terms[0]));
    this.denom = BigInteger.valueOf(Integer.parseInt(terms[1]));
  }

  // Methods
  public void reduce() {
    BigInteger gcd = this.denom.gcd(this.num);

    this.denom = this.denom.divide(gcd);
    this.num = this.num.divide(gcd);
  }

  public BigInteger numerator() {
    return this.num;
  }

  public void setNum(BigInteger num) {
    this.num = num;
  }

  public BigInteger denominator() {
    return this.denom;
  }

  public void setDenom(BigInteger denom) {
    this.denom = denom;
  }

  public String toString() {
    if (this.denom == BigInteger.valueOf(1)) {
      return this.num.toString();
    }
    return this.num.toString() + "/" + this.denom.toString();
  }

  private void equalBase(BigFraction val) {
    val.reduce();
    // finding common denominator
    BigInteger newDenom = this.denom.multiply(val.denom);

    // setting numerators
    this.num = this.num.multiply(val.denom);
    val.num = val.num.multiply(this.denom);

    // setting denominators
    this.denom = newDenom;
    val.denom = newDenom;
  }

  // New Shit
  public BigFraction add(BigFraction val) {
    BigFraction newFrac = new BigFraction(this.num, this.denom);
    BigFraction passedFrac = new BigFraction(val.num, val.denom);
    newFrac.equalBase(passedFrac);
    newFrac.num = newFrac.num.add(passedFrac.num);
    newFrac.reduce();
    return newFrac;
  }

  public BigFraction subtract(BigFraction val) {
    BigFraction newFrac = new BigFraction(this.num, this.denom);
    BigFraction passedFrac = new BigFraction(val.num, val.denom);
    newFrac.equalBase(passedFrac);
    newFrac.num = newFrac.num.subtract(passedFrac.num);
    newFrac.reduce();
    return newFrac;
  }

  public BigFraction multiply(BigFraction val) {
    BigFraction newFrac = new BigFraction(this.num, this.denom);

    newFrac.num = newFrac.num.multiply(val.num);
    newFrac.denom = newFrac.denom.multiply(val.denom);
    newFrac.reduce();
    return newFrac;
  }

  public BigFraction divide(BigFraction val) {
    return multiply(new BigFraction(val.denom, val.num));
  }
}
