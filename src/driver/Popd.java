// **********************************************************
// Assignment2
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
 * Popd class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "popd"
 */
public class Popd extends Command {

  /**
   * Uses an array list of users input arguments and removes the top entry from
   * the directory stack and makes it the current working directory.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the name of latest directory that was removed.
   */
  @Override
  public String runCommand(String[] arguments) {
    if (arguments.length == 0) {
      int stackSize = Directory.dirStack.size();
      if (stackSize <= 0) {
        System.out.println("Error: Directory stack has been empty. "
            + "Please use cmd \"pushd\" firtype filter texttype filter textst.");
      } else {
        Directory.setCurrDir(
            Directory.getDir(Directory.dirStack.get(stackSize - 1)));
        if (stackSize == 1) {
          Directory.dirStack.clear();
        } else {
          Directory.dirStack = Directory.dirStack.subList(0, stackSize - 1);
        }
      }
    } else {
      return "Error: Invalid argument number";
    }
    return "";
  }

  /**
   * Creates a specified manual on how to use the [popd] command.
   *
   * @return String for the manual documentation for [popd].
   */
  @Override
  public String toString() {
    return "popd\n\tremoves the top most directory from "
        + "the directory stack and\n\tmakes it the current working "
        + "directory. ";
  }
}
