package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * BigFraction type calculator util.
 * @author Grant Sackmann
 *
 *
 */
public class BFCalculator {

  /**
   * Stores recently computed value.
   */
  private BigFraction currentVal;

  /**
   * Constructs BFCalculator with initial value of zero.
   */
  public BFCalculator() {
    currentVal = new BigFraction(BigInteger.valueOf(0), BigInteger.valueOf(1));
  } // end BFCalculator()

  /**
   * Constructs BFCalculator with givin initial value.
   *
   * @param initialVal
   */
  public BFCalculator(BigFraction initialVal) {
    this.currentVal = initialVal;
  } // end BFCalculator()

  /**
   * Returns the most recently computed value.
   *
   * @return BigFraction
   */
  public BigFraction get() {
    return currentVal;
  } // end get()

  /**
   * Updates the most recently computed value.
   *
   * @param val value to replace most recent value with
   */
  public void setValue(BigFraction val) {
    currentVal = val;
  } // end setValue()

  /**
   * Adds val to most recently computed value.
   *
   * @param val value to be added
   */
  public void add(BigFraction val) {
    currentVal = currentVal.add(val);
  } // end add()

  /**
   * Subtracts val from most recently computed value.
   *
   * @param val value to be subtracted
   */
  public void subtract(BigFraction val) {
    currentVal = currentVal.subtract(val);
  } // end subtract()

  /**
   * Multiplies most recently computed value with val.
   *
   * @param val value to be multiplied by
   */
  public void multiply(BigFraction val) {
    currentVal = currentVal.multiply(val);
  } // end multiply()

  /**
   * Divides most recently computed value by val.
   *
   * @param val value to be divided by
   */
  public void divide(BigFraction val) {
    currentVal = currentVal.divide(val);
  } // end divide()

  /**
   * Resets most recently computed value to zero.
   */
  public void clear() {
    currentVal.setDenom(BigInteger.valueOf(1));
    currentVal.setNum(BigInteger.valueOf(0));
  } // end clear()
} // end BFCalculator()
