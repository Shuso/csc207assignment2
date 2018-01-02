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
 * Cat class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "cat [FILE+]"
 */
public class Cat extends Command {

  /**
   * Uses an array list of users input arguments and prints out the contents of
   * the file(s) specified in the array list. If the user specifies multiple
   * files (i.e. "cat FILE1 FILE2") the method prints out the contents of each
   * file one by one.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the content of the specified files.
   */
  @Override
  public String runCommand(String[] arguments) {
    String str = "";
    if (arguments.length > 0) { // check for appropriate number of arguments
      for (int i = 0; i < arguments.length; i++) {
        // receive each file from the File class storage
        File temp = File.getFileByPath(arguments[i]);
        if (temp == null) { // if this file doesn't exist
          if (i != arguments.length - 1) {
            str += "Error: The target file " + arguments[i] + " do not exist!\n" + "\n\n\n";
          }
          else {
            str += "Error: The target file " + arguments[i] + " do not exist!\n";
          }
        } else { // if the file exists
          // print out file content from File class storage
          if (i != arguments.length - 1) {
            str += temp.fileContent + "\n\n\n";
          } else {
            str += temp.fileContent;
          }
        }
      }
    } else { // if the users input is not valid
      return "Invalid arguments number";
    }
    return str;
  }

  /**
   * Creates a specified manual on how to use the [cat] command.
   *
   * @return String for the manual documentation of [cat].
   */
  @Override
  public String toString() {
    return "cat FILE1 [FILE2 ...]:\n"
        + "\tDisplay the contents of FILE1 and other "
        + "files(i.e. FILE2 ...) one by one.";
  }
}

