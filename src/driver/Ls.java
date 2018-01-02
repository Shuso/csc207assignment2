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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ls class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "ls [PATH ...]"
 */
public class Ls extends Command {
  // Helper variables to store recursive listing of sub contents
  private String str = "";
  private String indentation = "\n";

  /**
   * If -R is provided by the user, recursively store all the sub contents of
   * the directory provided by the user to the helper variables.
   * 
   * @param path Path provided by the user
   */
  public void recursiveList(String path) {
    Directory tDir = Directory.getDir(path);
    if (tDir == null) {
      str += "Error: the input path " + path + " does not exist!\n";
    } else if (tDir.getSubContent().isEmpty()) {
      str += tDir.getName() + "\n";
    } else {
      str += tDir.getName() + ":";
      indentation += "\t";
      for (int i = 0; i < tDir.getSubContent().size(); i++) {
        str += indentation;
        recursiveList(tDir.getSubContent().get(i).getAbsolutePath());
      }
      indentation = indentation.substring(0, indentation.length() - 1);
    }
  }

  /**
   * List sub contents of the directory provided by the user if there is no -r
   * provided. If a sub content is a directory with sub contents, the
   * directory's sub contents gets printed also. Final string of the list
   * command gets provided.
   * 
   * @param arg Paths provided by the user
   * @return
   */
  public String listPath(List<String> arg) {
    String output = "";
    for (int path = 0; path < arg.size(); path++) {
      Directory tDir = Directory.getDir(arg.get(path));
      if (tDir == null) {
        output +=
            "Error: The input path " + arg.get(path) + " doesn't exist!\n\n";
      } else if (tDir.isFile) {
        output += tDir.getName();
      } else {
        for (int j = 0; j < tDir.getSubContent().size(); j++) {
          if (!tDir.getSubContent().get(j).isFile) {
            output += tDir.getSubContent().get(j).getName();
            Directory dir = tDir.getSubContent().get(j);
            if (!dir.getSubContent().isEmpty()) {
              output += " : ";
              for (int k = 0; k < tDir.getSubContent().get(j).getSubContent()
                  .size(); k++) {
                if (k != tDir.getSubContent().get(j).getSubContent().size()
                    - 1) {
                  output += tDir.getSubContent().get(j).getSubContent().get(k)
                      .getName() + ", ";
                } else {
                  output += tDir.getSubContent().get(j).getSubContent().get(k)
                      .getName() + "\n";
                }
              }
            } else {
              output += "\n";
            }
          } else {
            output += tDir.getSubContent().get(j).getName() + "\n";
          }
        }
      }
    }
    return output;
  }

  /**
   * Uses an array list of users input arguments and returns the contents of the
   * current directory or a specified directory by its path. If there is an
   * error, error message gets returned.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String for the content of the specified directory.
   */
  @Override
  public String runCommand(String[] arguments) {

    if (arguments.length == 0) {
      List<String> arg = new ArrayList<String>(Arrays.asList());
      arg.add(".");
      return listPath(arg);
    } else {
      boolean checkR = arguments[0].toLowerCase().equals("-r");
      if (checkR) {
        List<String> recursiveArg = new ArrayList<String>(Arrays.asList());
        List<String> args =
            Arrays.asList(Arrays.copyOfRange(arguments, 1, arguments.length));
        recursiveArg.addAll(args);
        if (recursiveArg.isEmpty()) {
          recursiveArg.add(".");
        }
        for (int path = 0; path < recursiveArg.size(); path++) {
          recursiveList(recursiveArg.get(path));
        }
        String output = this.str;
        this.str = "";
        return output;
      } else {
        List<String> multipleArg =
            new ArrayList<String>(Arrays.asList(arguments));
        return listPath(multipleArg);
      }
    }

  }

  /**
   * Creates a specified manual on how to use the [ls] command.
   *
   * @return String for the manual documentation for [ls].
   */
  @Override
  public String toString() {
    return "ls [PATH ...]:\n" + "\tList the contents.\n" + "\tNote:\n"
        + "\t1.If no PATH given, print contents of current directory.\n"
        + "\t2.If PATH specifies a directory, print its contents(file or "
        + "directory).\n" + "\t3.If PATH specifies a file, print its name.";
  }
}
