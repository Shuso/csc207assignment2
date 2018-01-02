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

/**
 * Pushd class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "pushd DIR"
 */
public class Pushd extends Command {

  /**
   * Uses an array list of users input arguments and push a directory onto a
   * stack and change the currDir to that directory,
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the result of pushing a directory onto a stack.
   */
  @Override
  public String runCommand(String[] arguments) {
    if (arguments.length == 1) {
      Directory tDir = Directory.getDir(arguments[0]);
      if (tDir == null) {
        System.out.printf("Error: Fail to push current path. Input Directory"
            + " \"%s\" doesn't exist!", arguments[0]);
      } else if (tDir.isFile) {
        System.out.printf("Error: Fail to push current path. Input Directory"
            + " \"%s\" is File!", arguments[0]);
      } else {
        Directory.dirStack.add(Directory.currDir.getAbsolutePath());
        Directory.setCurrDir(tDir);
      }
    } else {
      return "Error: Invalid argument number";
    }
    return "";
  }

  /**
   * Creates a specified manual on how to use the [pushd] command.
   *
   * @return String for the manual documentation for [pushd].
   */
  @Override
  public String toString() {
    return "pushd DIR:\n\tSaves the current working directory"
        + " by pushing onto directory stack\n\tand then changes the new "
        + "current working directory to DIR.";
  }

}


