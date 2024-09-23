package edu.grinnell.csc207.util;

/** util class for storing variables and their BigFraction values. */
public class BFRegisterSet {

  /** Register storing values for given variable. */
  private BigFraction[] register;

  /** ASCII value of char 'a'. */
  private static final int BASE = 97;

  /** Alphabet length. */
  private static final int ALPHABET_LENGTH = 26;

  /** Constructor making a alphabet length BigInteger register. */
  public BFRegisterSet() {
    this.register = new BigFraction[ALPHABET_LENGTH];
  } // end BFRegisterSet()

  /**
   * Converts char value to proper index for BFRegisterSet.
   *
   * @param val character to be indexed
   * @return int returns index
   */
  private int getIndex(char val) {
    int index = (int) (val) - BASE;
    return index;
  } // end getIndex()

  /**
   * Stores the char variable as register key to the corresponding BigFraction val.
   *
   * @param var variable key for storage/access
   * @param val value to be stored
   */
  public void store(char var, BigFraction val) {
    register[getIndex(var)] = val;
  } // end store()

  /**
   * Returns the value at the given key, var.
   *
   * @param var key to retrieve
   * @return BigFraction
   */
  public BigFraction get(char var) {
    return register[getIndex(var)];
  } // end get()

  /**
   * Returns if var is contained in object register.
   *
   * @param var key to looked up
   * @return boolean whether var is in the register
   */
  public boolean isInRegister(char var) {
    if (register[getIndex(var)] == null) {
      return false;
    } // end if
    return true;
  } // end isInRegister()
} // end BFRegisterSet
