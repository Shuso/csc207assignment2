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
 * Echo class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "echo "String" > file.txt" or
 * "echo "String" >> file.txt."
 *
 */
public class Echo extends Command {
  /**
   * Uses an array list of users input arguments and if there is only string
   * provided by the user, the method prints out the string. Otherwise, the
   * method checks name for the file and edit contents of the file indicated by
   * the user.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the result of editing content gets printed. Empty if
   *         successful.
   */
  @Override
  public String runCommand(String[] arguments) {
    if (arguments.length == 1) {
      if (arguments[0].startsWith("\"")) {
        return arguments[0].substring(1, arguments[0].length() - 1);
      } else {
        return arguments[0];
      }
    } else {
      return "Error: Invalid arguments number!";
    }
  }

  /**
   * Creates a specified manual on how to use the [echo] command.
   *
   * @return String for the manual documentation for [echo].
   */
  @Override
  public String toString() {
    return "echo STRING [> OUTFILE] or [>> OUTFILE]:\n"
        + "\tWrite STRING into OUTFILE.\n" + "\tNote:\n"
        + "\t1.If OUTFILE is not provided, just print STRING on the shell"
        + ".\n\t2.If OUTFILE do not exist, this command will create it.\n"
        + "\t3.\">\" means overwrites while \">>\" means appends.";
  }

}
