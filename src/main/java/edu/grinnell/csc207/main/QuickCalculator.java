package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import java.io.PrintWriter;

/** Single line argument calculator program. */
public class QuickCalculator {

  /**
   * Main method to execute QuickCalculator program.
   *
   * @param args string arguments to be computed
   */
  public static void main(String[] args) {

    PrintWriter pen = new PrintWriter(System.out, true);
    BFRegisterSet register = new BFRegisterSet();
    BFCalculator calculator = new BFCalculator();

    for (int i = 0; i < args.length; i++) {
      String fragment = args[i];
      pen.print(fragment);
      if (InteractiveCalculator.isStore(fragment)) {
        if (InteractiveCalculator.processStore(calculator, register, fragment)) {
          pen.println(" -> STORED");
        } else {
          pen.println(" FAILED [Invalid register]");
        } // end if
      } else if (InteractiveCalculator.processExpression(calculator, register, fragment)) {
        pen.println(" -> " + calculator.get().toString());
      } else {
        pen.println(" FAILED [Invalid expression]");
      } // end if
    } // end for
  } // end main()
} // end QuickCalculator
