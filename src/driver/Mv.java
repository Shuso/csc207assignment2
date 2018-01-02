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

public class Mv extends Command {
  // Helper variable to copy object to the other
  private Cp copy = new Cp();

  /**
   * Uses String array of arguments provided by the user to copy. If user's
   * input is valid, file or directory provided by the user gets moved to the
   * indicated location and returns an empty string. Otherwise, returns an error
   * message.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String The result of move. Empty if successful. Otherwise, error
   *         message gets returned.
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   */
  @Override
  public String runCommand(String[] arguments) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    if (arguments.length == 2) {
      Directory firstArg = Directory.getDir(arguments[0]);
      Directory secondArg = Directory.getDir(arguments[1]);
      if (firstArg == null && secondArg == null) {
        return "Error: File or Folder named " + arguments[0] + " and "
            + arguments[1] + " does not exist";
      } else if (firstArg == null && secondArg != null) {
        return "Error: File or Folder named " + arguments[0]
            + " does not exist";
      } else if ((firstArg.isFile && secondArg == null)
          || (firstArg.isFile && secondArg.isFile)) {
        String inputPath = arguments[1];
        String newName = arguments[1];
        if (arguments[1].lastIndexOf("/") != -1) {
          inputPath = arguments[1].substring(0, arguments[1].lastIndexOf("/"));
          newName = arguments[1].substring(arguments[1].lastIndexOf("/") + 1);
        }
        Directory parentDir = Directory.getDir(inputPath);
        if (parentDir == null && inputPath.contains("/")) {
          return "Error: File or Folder named " + inputPath
              + " does not exist!";
        } else if (parentDir == null) {
          parentDir = Directory.getCurrDir();
        }
        copy.renameFile((File) firstArg, (File) secondArg, newName, parentDir);
        firstArg.parent.delete(firstArg);
      } else if (firstArg.isFile && !secondArg.isFile) {
        copy.copyFile((File) firstArg, secondArg);
        firstArg.parent.delete(firstArg);
      } else if (firstArg != null && secondArg != null && !firstArg.isFile
          && !secondArg.isFile) {
        copy.copyDir(firstArg, secondArg);
        firstArg.parent.delete(firstArg);
      } else {
        return "Error: Wrong arguments input!";
      }
      return "";
    } else {
      return "Error: Wrong arguments number!";
    }
  }

  /**
   * Creates a specified manual on how to use the [mv] command.
   *
   * @return String for the manual documentation for [mv].
   */
  @Override
  public String toString() {
    return "mv OLDPATH NEWPATH:\n"
        + "\tMove item OLDPATH to NEWPATH. Both OLDPATH and NEWPATH may be "
        + "relative to the current directory or may be full paths. If NEWPATH"
        + " is a directory, move the item into the directory.";
  }

}
