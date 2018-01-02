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
 * Mkdir class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "mkdir DIR ..."
 */
public class Mkdir extends Command {
  //Helper variable to know current argument number
  private int currArg;
  /**
   * Helper method for the mkdir. The method checks directory provided by the
   * user and if there is no error, creates new directory with specified name
   * provided by the user.
   * 
   * @param tDir Directory provided by the user
   * @param args String array containing separated directory
   */
  private String makeDir(Directory tDir, String[] args) {
    for (int j = 0; j < args.length; j++) {
      if (args[j].equals("..") & tDir.parent != null) {
        tDir = tDir.parent;
      } else
        if (args[j].equals(".") || (args[j].equals("..") & tDir.parent == null)
            || args[j].equals("")) {
        continue;
      } else {
        Directory tmp = tDir.getSubContent(args[j]);
        if (tmp == null) {
          if (j == (args.length - 1)) {
            if (args[j].equals(".") || args[j].equals("..")
                || validate(args[j])) {
              return ("Error in argument " + (currArg + 1)
                  + ": Special characters should not be used for "
                  + "creating a folder.\n");
            }
              Directory newDir = new Directory(args[j], tDir);
              tDir.addContent(newDir);
              Directory.getAllDirectory().add(newDir);
            
          } else {
            return ("Error! no directory named " + args[j] + " exist!\n");
          }
        } else {
          if (tmp.isFile) {
            return ("Error! path " + tmp.getName() + " is File!\n");
          } else {
            tDir = tmp;
          }
        }
      }
    }
    return "";

  }
  
  /**
   * Uses an array list of users input arguments and creates the new directory
   * by name in the current directory (i.e. "mkdir DIR1" creates a directory
   * named DIR1 in the current directory).
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the result of creating a directory. Empty if successful.
   */
  @Override
  public String runCommand(String[] arguments) {
    // Check arguments number
    if (arguments.length > 0) {
      String output = "";
      for (int i = 0; i < arguments.length; i++) {
        currArg = i;
        if (Directory.getDir(arguments[i]) != null) {
          output += "Error in argument " + (i + 1) + ": Folder or File named "
              + arguments[i] + " already exists\n";
        } else {
          Directory tDir = Directory.getCurrDir();
          if (arguments[i].startsWith("/")) {
            tDir = Directory.rootDir;
          }
          String args[] = Directory.getDirNamesFromPath(arguments[i]);
          output += makeDir(tDir, args);
        }


      }
      return output;
    }

    else {
      return "Error: Invalid argument number";
    }
  }

  /**
   * Creates a specified manual on how to use the [mkdir] command.
   *
   * @return String for the manual documentation for [mkdir].
   */
  @Override
  public String toString() {
    return "mkdir DIR ...:\n" + "\tCreate directories.\n"
        + "\tNote: Each of DIR may be relative to the current directory "
        + "or may be a full path.";
  }
}

