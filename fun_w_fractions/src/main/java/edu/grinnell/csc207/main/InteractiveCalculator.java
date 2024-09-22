package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.*;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;

public class InteractiveCalculator {

  static final Set<Character> operators = Set.of('*', '/', '+', '-');

  static final int ASCII_A_LOWER = 97;

  static final int ASCII_Z_LOWER = 122;

  private static int countChar(String str, char ch) {
    int count = 0;
    for (Character val : str.toCharArray()) {
      if (ch == val) {
        count += 1;
      }
    }
    return count;
  }

  private static boolean isValidOperand(BFRegisterSet register, String operand) {
    if (countChar(operand, '/') <= 1) {
      String[] terms = operand.split("/");
      // checks that each string is valid integer
      for (String termAsString : terms) {
        try {
          Integer.parseInt(termAsString);
        } catch (Exception e) {
          return false;
        }
      }
      return true;
      // checks for stored variable
    } else if (operand.length() == 1
        && isLowCaseAlpha(operand.charAt(0))
        && register.get(operand.charAt(0)) != null) {
      return true;
    }
    return false;
  }

  private static boolean isLowCaseAlpha(char ch) {
    return ASCII_A_LOWER <= ch && ch <= ASCII_Z_LOWER;
  }

  private static boolean processExpression(
      BFCalculator calculator, BFRegisterSet register, String str) {

    String[] arguments = str.split(" ");

    if (arguments.length % 2 == 1 && isValidOperand(register, arguments[0])) {

      if (arguments[0].length() == 1) {
        arguments[0] = register.get(arguments[0].charAt(0)).toString();
      }

      calculator.setValue(new BigFraction(arguments[0]));

      for (int i = 1; i < arguments.length; i += 2) {
        char operator = arguments[i].charAt(0);
        String operand = arguments[i + 1];

        if (operators.contains(operator) && isValidOperand(register, operand)) {
          if (operand.length() == 1) {
            operand = register.get(operand.charAt(0)).toString();
          }

          if (operator == '+') {
            calculator.add(new BigFraction(operand));
          } else if (operator == '-') {
            calculator.subtract(new BigFraction(operand));
          } else if (operator == '*') {
            calculator.multiply(new BigFraction(operand));
          } else {
            calculator.divide(new BigFraction(operand));
          }
        } else {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  private static boolean isStore(String str) {
    String[] arguments = str.split(" ");

    if (arguments.length <= 2 && arguments[0].equals("STORE")) {
      return true;
    }
    return false;
  }

  private static boolean processStore(BFCalculator calculator, BFRegisterSet register, String str) {
    String stringVar = str.split(" ")[1];
    char charVar = stringVar.charAt(0);
    if (stringVar.length() == 1 && isLowCaseAlpha(charVar)) {
      register.store(charVar, calculator.get());
      return true;
    }
    return false;
  }

  public static void main(String args[]) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scan = new Scanner(System.in);
    BFRegisterSet register = new BFRegisterSet();
    String inputLine;
    BFCalculator calculator = new BFCalculator();
    while (!(inputLine = scan.nextLine()).equals("QUIT")) {
      if (isStore(inputLine)) {
        if (processStore(calculator, register, inputLine)) {
          pen.println("STORED");
        } else {
          pen.println("*** ERROR [STORE command received invalid register] ***");
        }
      } else if (processExpression(calculator, register, inputLine)) {
        pen.println(calculator.get().toString());
      } else {
        pen.println("*** ERROR [Invalid expression] ***");
      }
    }
    scan.close();
  }
}
