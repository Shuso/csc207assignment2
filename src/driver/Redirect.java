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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Redirect class is a subclass of Command that inherits the
 * runCommand(String[]) method in order to run the users input when > OUTFILE or
 * >> OUTFILE provided at the end of users input. Redirection can be applied to
 * every command except for exit. This captures the output of the command if it
 * is not an error and redirects it to OUTFILE and no output is shown to the
 * user.
 */
public class Redirect extends Command {
  /**
   * Edit the specified file content with a string provided by a user. If the
   * specified file is empty, the method creates the file with the name
   * specified and sets content as the string. When a symbol for the echo
   * command is >, the file's content gets overwritten by the string. Otherwise,
   * when the symbol is >>, the file's content gets appended by the string.
   * 
   * @param fname is name of the file user wants to edit
   * @param symbol Symbol for choosing whether append or overwrite the file
   *        content
   * @param str String provided by the user used for overwriting or appending
   * @param dir Directory specified by the user to find the file
   * @param path Path of the file provided by the user
   */
  private void editContent(String fname, String symbol, String str,
      Directory dir, String path) {
    File file = File.getFileByPath(path);
    if (symbol.equals(">")) {
      if (file == null) {
        file = File.createFile(fname, dir);
        file.appendContent(str);
      } else {
        file.overwriteContent(str);
      }
    } else if (symbol.equals(">>")) {
      if (file == null) {
        file = File.createFile(fname, dir);
        file.appendContent(str);
      } else {
        file.appendContent(str);
      }
    }
  }

  /**
   * Checks directory name provided by the user then if there is no error until
   * the end, editContent gets called. Otherwise, error message gets returned.
   * 
   * @param tDir Directory provided by the user
   * @param args String array containing separated directory
   * @param symbol Symbol for choosing whether append or overwrite the file
   *        content
   * @param str String provided by the user used for overwriting or appending
   * @param path Full path of the file provided by the user
   * @return Returns empty string if there is no error. Otherwise, return an
   *         error message.
   */
  private String echoFile(Directory tDir, String[] args, String symbol,
      String str, String path) {
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

            editContent(args[j], symbol, str, tDir, path);
          } else {
            return ("Error! no directory named " + args[j] + " exist!");
          }
        } else {
          if (tmp.isFile) {
            if (j == (args.length - 1)) {
              editContent(args[j], symbol, str, tDir, path);
            } else {
              return ("Error! the path " + tmp.getName() + " is File!");
            }
          } else {
            tDir = tmp;
          }
        }
      }
    }
    return "";
  }

  /**
   * Get an output of the command provided by the user to use as content of the
   * OUTFILE.
   * 
   * @param arguments Command name and arguments provided by the user
   * @return Returns output of the command
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private String getContent(String[] arguments) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String input = arguments[0];
    String[] argument = Arrays.copyOfRange(arguments, 1, arguments.length);
    CommandManager a = new CommandManager();
    a.setCommand();
    a.checkCommand(input);
    return a.getOutput(input, argument);
  }

  /**
   * Uses String array of arguments provided by the user to redirect. If
   * arguments are valid, user gets output from the user's command then edit or
   * create file with the output and returns with an empty string. If there is
   * an error, Error gets returned and no file gets created.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String The result of redirection. Empty if successful. Otherwise,
   *         error message gets returned.
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   * @throws IOException
   */
  @Override
  public String runCommand(String[] arguments) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String output = "";
    if (arguments.length > 2) {
      String[] command = Arrays.copyOfRange(arguments, 0, arguments.length - 2);
      String content = getContent(command);
      if (content.length() > 0 && !content.contains("Error")) {
        Directory tDir = Directory.getCurrDir();
        String path = arguments[arguments.length - 1];
        if (arguments[arguments.length - 1].startsWith("/")) {
          tDir = Directory.getRootDir();
        }
        String args[] =
            Directory.getDirNamesFromPath(arguments[arguments.length - 1]);
        output += echoFile(tDir, args, arguments[arguments.length - 2],
            getContent(command), path);
      } else if (content.contains("Error")) {
        output += (content);
      } else {
        if (arguments[arguments.length - 1].equals(">")
            || arguments[arguments.length - 1].equals(">>")) {
          return "Error: Wrong arguments input";
        }
        output += ("Command does not give any output so file "
            + arguments[arguments.length - 1] + " have not been created");
      }
    } else {
      return "Error: Wrong arguments input";
    }
    return output;
  }
}
