package edu.grinnell.csc207.util;

import java.math.BigInteger;

public class BigFraction {
  // Fields
  private BigInteger num;

  private BigInteger denom;

  // Constructors
  BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  }

  BigFraction(BigInteger wholeNumb) {
    this.num = wholeNumb;
    this.denom = BigInteger.ONE;
  }

  // Methods 
  public void reduce() {
    BigInteger gcd = this.denom.gcd(this.num);

    this.denom = this.denom.divide(gcd);
    this.num = this.num.divide(gcd);
  }

  public BigInteger getNum() {
    return this.num;
  }
  public void setNum(BigInteger num) {
    this.num = num;
  }

  public BigInteger getDenom() {
    return this.denom;
  }
  public void setDenom(BigInteger denom) {
    this.denom = denom;
  }
}