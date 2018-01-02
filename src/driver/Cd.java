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
 * Changes the current directory to the specified location from users input.
 */
public class Cd extends Command {

  /**
   * Uses an array list of users input arguments and changes the goes to the
   * specified directory (i.e. cd DIR1) or the the specified path (i.e. cd
   * DIR1/DIR2). If the user specifies a directory or directory path that does
   * not exist the current directory does not change. The root directory has no
   * name and no parent directory.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the result of the directory change.
   */
  @Override
  public String runCommand(String[] arguments) {
    // Check arguments number
    if (arguments.length == 1) {
      Directory tDir = Directory.getDir(arguments[0]);
      if (tDir == null) {
        return ("Error: The input path " + arguments[0] + " doesn't exist!\n");
      } else if (tDir.isFile) {
        return ("Error: The input path " + arguments[0] + " is File! "
            + "Please enter folder name.");
      } else { // if the arguments are valid
        Directory.setCurrDir(tDir);
        return "";
      }
    } else { // Wrong arguments number
      return "Error: Invalid arguments number";
    }
  }

  /**
   * Creates a specified manual on how to use the [cd] command.
   *
   * @return String for the manual documentation of [cd].
   */
  @Override
  public String toString() {
    return "cd DIR:\n" + "\tChange directory to DIR.\n"
        + "\tNote: DIR may be relative to the current directory or may "
        + "be a full path.";
  }
}
