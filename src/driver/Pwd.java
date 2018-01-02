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
 * Pwd class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "pwd" and display path of currDir
 */
public class Pwd extends Command {

  // no name and no parent folder for root folder

  /**
   * Uses an array list of users input arguments and gets the absolute path of
   * the current directory from Directory class and prints it.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the path of the current directory
   */
  @Override
  public String runCommand(String[] arguments) {
    // Check arguments number
    if (arguments.length == 0) {
      // implement command here
      // sample
      if (Directory.getCurrDir() == null) {
        Directory.setCurrDir(Directory.getRootDir());
      }
      return Directory.getCurrDir().getAbsolutePath();
      // Wrong arguments number
    } else {
      return "Error: Invalid arguments number";
    }
  }

  /**
   * Creates a specified manual on how to use the [pwd] command.
   *
   * @return String for the manual documentation for [pwd].
   */
  @Override
  public String toString() {
    return "pwd:\n" + "\tPrint the current working directory(including the "
        + "whole path).";
  }
}
