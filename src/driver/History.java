// **********************************************************
// Assignment2:

// Student1:
// CDF user_name: c4begums
// UT Student #:1000271289
// Author: Saima Begum
//
// Student2:
// CDF user_name: c4iqbalm
// UT Student #: 1001452064
// Author: Monica Iqbal
//
// Student3:
// CDF user_name: c4juntae
// UT Student #: 1001386644
// Author: Tae Hoon Jun
//
// Student4:
// CDF user_name: c4zengzh
// UT Student #: 1001610241
// Author: Zhu Zeng
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************

package driver;

import java.util.ArrayList;
import java.util.List;

/**
 * History class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "history [number]"
 */
public class History extends Command {
  // List of string storing history of command input
  public static List<String> cmdHistory = new ArrayList<String>();

  /**
   * Uses an array list of users input arguments and prints out all inputs the
   * user has made in numbered order from oldest to most recent.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the history of the users input in a numbered list.
   */
  @Override
  public String runCommand(String[] arguments) {
    if (arguments.length < 2) {
      int num = arguments.length == 0 ? Integer.MAX_VALUE
          : Integer.parseInt(arguments[0]);
      int lenHistory = cmdHistory.size();
      int len = Math.min(lenHistory, num);
      for (int i = lenHistory - len; i < lenHistory; i++) {
        System.out.printf("%d.%s\n", i + 1, cmdHistory.get(i));
      }
    }

    else {
      return "Error: Invalid argument number";
    }
    return "";
  }

  /**
   * Creates a specified manual on how to use the [history] command.
   *
   * @return String for the manual documentation for [history].
   */
  @Override
  public String toString() {
    return "history [number]:\n"
        + "\tPrint out recent commands.\n\tNote: If number is "
        + "given, the output will be truncate by this number.";
  }
  
}


