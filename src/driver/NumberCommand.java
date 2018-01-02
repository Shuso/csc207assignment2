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

import java.io.IOException;
import java.util.Arrays;

/**
 * This class is a subclass of the Command class that inherits the class's
 * runCommand class in order to run this command.
 *
 */
public class NumberCommand extends Command {
  /**
   * Takes in ! as the command and a number as the input to execute a command at
   * that number in the command history.
   * 
   * @param arguments String array containing the number provided by the user
   * @return The output of the command  at the given index of command history
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  @Override
  public String runCommand(String[] arguments) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    try {
      int index = Integer.parseInt(arguments[0]) - 1;
      if (index < History.cmdHistory.size() - 1) {
        String history = History.cmdHistory.get(index);
        String[] splitInput = history.trim().split("\\s+");
        String command = splitInput[0];
        String[] argument = Arrays.copyOfRange(splitInput, 1, splitInput.length);
        if (command.equals("echo")) {
          String arg =
              history.substring(history.indexOf(argument[0]), history.length());
          argument = arg.split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        }
        CommandManager runcmd = new CommandManager();
        runcmd.setCommand();
        runcmd.checkCommand(command);
        return runcmd.getOutput(command, argument);
      }
      else {
        return "Error: number provided exceeds number of command history!";
      }
    } catch (NumberFormatException e) {
      return ("Error: Integer not provided!");
    }
  }

  /**
   * Creates a specified manual on how to use the [!Number] command.
   * 
   * @return the string containing the documentation for [!Number]
   */
  @Override
  public String toString() {
    return "!number: \n"
        + "Executes a command associated with a number in the user's history,\n"
        + "if that number exists. If the number is not present in the history"
        + "or if the user inputs something other than a number, return an"
        + "error message.";
  }
}
