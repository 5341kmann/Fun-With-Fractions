package edu.grinnell.csc207.util;

import java.math.BigInteger;

/** util classes to produces fraction objects composed of BigIntegers. */
public class BigFraction {

  /** Fraction object numerator. */
  private BigInteger num;

  /** Fraction object denominator. */
  private BigInteger denom;

  /**
   * Constructor for BigFraction object.
   *
   * @param numVal numerator
   * @param denomVal denominator
   */
  public BigFraction(BigInteger numVal, BigInteger denomVal) {
    this.num = numVal;
    this.denom = denomVal;
    this.reduce();
  } // end BigFraction()

  /**
   * Constructor for BigFraction object.
   *
   * @param numVal numerator
   * @param denomVal denominator
   */
  public BigFraction(int numVal, int denomVal) {
    this.num = BigInteger.valueOf(numVal);
    this.denom = BigInteger.valueOf(denomVal);
    this.reduce();
  } // end BigFraction()

  /**
   * Constructor for BigFraction object.
   *
   * @param wholeNumb value
   */
  public BigFraction(BigInteger wholeNumb) {
    this.num = wholeNumb;
    this.denom = BigInteger.ONE;
  } // end BigFraction()

  /**
   * Constructor for BigFraction object.
   *
   * @param str fraction
   */
  public BigFraction(String str) {
    String[] terms = str.split("/");
    if (terms.length == 1) {
      this.num = BigInteger.valueOf(Integer.parseInt(terms[0]));
      this.denom = BigInteger.valueOf(1);
    } else if (terms.length == 2) {
      this.num = BigInteger.valueOf(Integer.parseInt(terms[0]));
      this.denom = BigInteger.valueOf(Integer.parseInt(terms[1]));
      this.reduce();
    } else {
      this.num = BigInteger.valueOf(0);
      this.denom = BigInteger.valueOf(1);
    } // end if
  } // end BigFraction()

  /** reduces fraction object. */
  public void reduce() {
    BigInteger gcd = this.denom.gcd(this.num);

    this.denom = this.denom.divide(gcd);
    this.num = this.num.divide(gcd);

    if (this.denom.compareTo(BigInteger.valueOf(0)) == -1) {
      this.num = this.num.multiply(BigInteger.valueOf(-1));
      this.denom = this.denom.multiply(BigInteger.valueOf(-1));
    } // end if
  } // end reduce()

  /**
   * Returns the numerator of fraction.
   *
   * @return BigInteger numerator value
   */
  public BigInteger numerator() {
    return this.num;
  } // end numerator()

  /**
   * Assigns fraction numerator to num.
   *
   * @param numVal numerator value
   */
  public void setNum(BigInteger numVal) {
    this.num = numVal;
  } // end setNum()

  /**
   * Returns the denominator of fraction.
   *
   * @return BigInteger denominator value
   */
  public BigInteger denominator() {
    return this.denom;
  } // end denominator()

  /**
   * Assigns fraction denominator to denom.
   *
   * @param denomVal denominator value
   */
  public void setDenom(BigInteger denomVal) {
    this.denom = denomVal;
  } // end setDenom()

  /**
   * Returns the fraction as a String.
   *
   * @return String representation of fraction.
   */
  public String toString() {
    this.reduce();
    if (this.denom.equals(BigInteger.valueOf(1))) {
      return this.num.toString();
    } // end if
    return this.num.toString() + "/" + this.denom.toString();
  } // end toString()

  /**
   * Rebases fraction object with val to operate on.
   *
   * @param val fraction to base with
   */
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
  } // end equalBase()

  /**
   * Returns summation of val and fraction object.
   *
   * @param val fraction to add
   * @return BigFraction sum of fractions
   */
  public BigFraction add(BigFraction val) {
    BigFraction newFrac = new BigFraction(this.num, this.denom);
    BigFraction passedFrac = new BigFraction(val.num, val.denom);
    newFrac.equalBase(passedFrac);
    newFrac.num = newFrac.num.add(passedFrac.num);
    newFrac.reduce();
    return newFrac;
  } // end add()

  /**
   * Returns difference of val and fraction object.
   *
   * @param val fraction to subtract
   * @return BigFraction difference of fractions
   */
  public BigFraction subtract(BigFraction val) {
    BigFraction newFrac = new BigFraction(this.num, this.denom);
    BigFraction passedFrac = new BigFraction(val.num, val.denom);
    newFrac.equalBase(passedFrac);
    newFrac.num = newFrac.num.subtract(passedFrac.num);
    newFrac.reduce();
    return newFrac;
  } // end subtract()

  /**
   * Returns product of val and fraction object.
   *
   * @param val fraction to multiply with
   * @return BigFraction product of fractions
   */
  public BigFraction multiply(BigFraction val) {
    BigFraction newFrac = new BigFraction(this.num, this.denom);

    newFrac.num = newFrac.num.multiply(val.num);
    newFrac.denom = newFrac.denom.multiply(val.denom);
    newFrac.reduce();
    return newFrac;
  } // end multiply()

  /**
   * Returns quotient of val and fraction object.
   *
   * @param val fraction to divided by
   * @return BigFraction quotient of fractions
   */
  public BigFraction divide(BigFraction val) {
    return multiply(new BigFraction(val.denom, val.num));
  } // end divide()
} // end BigFraction
