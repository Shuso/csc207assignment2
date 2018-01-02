// **********************************************************
// Assignment2:

// Student1:
// CDF user_name: c4begums
// UT Student #: 1000271289
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

/**
 * Man class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "man CMD"
 */
public class Man extends Command {

  /**
   * Uses an array list of users input arguments and gets the manual
   * documentation for the specified command and prints it out.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the manual documentation of the command
   */
  @Override
  public String runCommand(String[] arguments) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    if (arguments.length == 1) {
      String cmdName = arguments[0];
      CommandManager cmd = new CommandManager();
      cmd.setCommand();
      cmd.checkCommand(cmdName);
      return cmd.getMan(cmdName);

    } else {
      return "Error: Invalid argument number";
    }
  }

  /**
   * Creates a specified manual on how to use the [man] command.
   *
   * @return String for the manual documentation for [man].
   */
  @Override
  public String toString() {
    return "man CMD:\n\tPrint documentation for CMD.";
  }

}
