package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;

/** REPL Calculator program. */
public class InteractiveCalculator {
  /** Set of Valid Mathematical Operators. */
  static final Set<Character> OPERATORS = Set.of('*', '/', '+', '-');

  /** ASCII 'a' Value. */
  static final int ASCII_A_LOWER = 97;

  /** ASCII 'z' Value. */
  static final int ASCII_Z_LOWER = 122;

  /**
   * counts occurrences of given char in string.
   *
   * @param str String input
   * @param ch character to be counted
   * @return int number of occurrences
   */
  private static int countChar(String str, char ch) {
    int count = 0;
    for (Character val : str.toCharArray()) {
      if (ch == val) {
        count += 1;
      } // end if
    } // end for
    return count;
  } // end countChar()

  /**
   * checks if string is a valid integer representation.
   *
   * @param str String to be checked
   * @return boolean truth value of str as an integer.
   */
  private static boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
    } catch (Exception e) {
      return false;
    } // end try-catch
    return true;
  } // end isInteger()

  /**
   * Checks if String is a valid calculator operand with respect to register.
   *
   * @param register variable register
   * @param operand String representing mathematical operand
   * @return boolean truth value of valid operand
   */
  private static boolean isValidOperand(BFRegisterSet register, String operand) {
    if (countChar(operand, '/') == 1) {
      String[] terms = operand.split("/");
      // checks that each string is valid integer
      for (String termAsString : terms) {
        if (!isInteger(termAsString)) {
          return false;
        } // end if
      } // end for
      return true;

      // checks for stored variable
    } else if (isInteger(operand)) {
      return true;
    } else if (operand.length() == 1
        && isLowCaseAlpha(operand.charAt(0))
        && register.get(operand.charAt(0)) != null) {
      return true;
    } // end if
    return false;
  } // end if

  /**
   * Checks if character value is in range of a lower case ASCII value.
   *
   * @param ch character to checked
   * @return boolean truth value of ch against lower case range
   */
  private static boolean isLowCaseAlpha(char ch) {
    return ASCII_A_LOWER <= ch && ch <= ASCII_Z_LOWER;
  } // end isLowCaseAlpha()

  /**
   * Attempts to process String str using calculator and register objects.
   *
   * @param calculator object for processing values and storing result
   * @param register object for storing and accessing calculated variables
   * @param str String representation of expression to be computed
   * @return boolean truth value of whether String expression could be processed
   */
  public static boolean processExpression(
    BFCalculator calculator, BFRegisterSet register, String str) {

    String[] arguments = str.split(" ");

    if (arguments.length % 2 == 1 && isValidOperand(register, arguments[0])) {
      if (isLowCaseAlpha(arguments[0].charAt(0))) {
        arguments[0] = register.get(arguments[0].charAt(0)).toString();
      } // end if

      calculator.setValue(new BigFraction(arguments[0]));

      for (int i = 1; i < arguments.length; i += 2) {
        char operator = arguments[i].charAt(0);
        String operand = arguments[i + 1];

        if (OPERATORS.contains(operator) && isValidOperand(register, operand)) {
          if (isLowCaseAlpha(operand.charAt(0))) {
            operand = register.get(operand.charAt(0)).toString();
          } // end if

          if (operator == '+') {
            calculator.add(new BigFraction(operand));
          } else if (operator == '-') {
            calculator.subtract(new BigFraction(operand));
          } else if (operator == '*') {
            calculator.multiply(new BigFraction(operand));
          } else {
            calculator.divide(new BigFraction(operand));
          } // end if
        } else {

          return false;
        } // end if
      } // end for
      return true;
    } // end if
    // pen.printf("fail a: %b%n",arguments.length % 2 == 1);
    // pen.printf("fail b: %b%n",isValidOperand(register, arguments[0]));
    return false;
  } // end processExpression()

  /**
   * Checks if the String expression is a valid STORE command.
   *
   * @param str expression
   * @return boolean truth value of whether expression is a STORE command
   */
  public static boolean isStore(String str) {
    String[] arguments = str.split(" ");

    if (arguments.length == 2 && arguments[0].equals("STORE")) {
      return true;
    } // end if
    return false;
  } // end isStore()

  /**
   * Attempts to process String expression as a STORE command with respect to calculator
   * and register objects.
   *
   * @param calculator object for accessing calculated value to be stored
   * @param register object for storing variable
   * @param str String representation of STORE expression
   * @return boolean truth value of whether STORE command could be processed
   */
  public static boolean processStore(BFCalculator calculator, BFRegisterSet register, String str) {
    String stringVar = str.split(" ")[1];
    char charVar = stringVar.charAt(0);
    if (stringVar.length() == 1 && isLowCaseAlpha(charVar)) {
      register.store(charVar, calculator.get());
      return true;
    } // end if
    return false;
  } // end processStore()

  /**
   * Main method to executing InteractiveCalculator program.
   *
   * @param args arguments for user input.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scan = new Scanner(System.in);
    BFRegisterSet register = new BFRegisterSet();
    String inputLine = "";
    BFCalculator calculator = new BFCalculator();
    while (!(inputLine).equals("QUIT")) {
      inputLine = scan.nextLine();
      if (isStore(inputLine)) {
        if (processStore(calculator, register, inputLine)) {
          pen.println("STORED");
        } else {
          pen.println("*** ERROR [STORE command received invalid register] ***");
        } // end if
      } else if (processExpression(calculator, register, inputLine)) {
        pen.println("> " + calculator.get().toString());
      } else {
        pen.println("*** ERROR [Invalid expression] ***");
      } // end if
    } // end while
    scan.close();
  } // end main()
} // end InteractiveCalculator
