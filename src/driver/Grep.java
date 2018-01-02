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
import java.util.regex.Pattern;

/**
 * Grep class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "grep [-R] REGEX PATH "
 */
public class Grep extends Command {
  public String testString;

  /**
   * search and print the lines that matches with given regular expression
   * within a given file.
   * 
   * @param regex a regular expression
   * @param dir a given directory or file
   * @return String error messages or the content that were printed
   */
  public static String searchRegexInFile(Directory f, String regex) {
    ArrayList<String> lstLines = new ArrayList<String>();
    String printResult = "";
    String print = f.getAbsolutePath() + " " + ": ";
    String[] lines = ((File) f).getFileContent().split("\n");
    String newRegex;
    if (regex.startsWith("\"")) {
      newRegex = ".*";
      newRegex += regex.substring(1, regex.length() - 1);
      newRegex += "*.";
    } else {
      newRegex = ".*";
      newRegex += regex;
      newRegex += "*.";
    }
    for (String l : lines) {
      // check if regex in those lines.
      if (Pattern.matches(newRegex, l)) {
        lstLines.add(l);
      }

    }
    if (!lstLines.isEmpty()) {
      for (String line : lstLines) {
        printResult += print + line + "\n";
      }
    }
    return printResult.trim();
  }


  /**
   * Recursively search lines in files that matching regular expression within a
   * given directory and print them one per line.
   * 
   * 
   * @param regex a regular expression
   * @param dir a given directory or file
   * @return String error messages or the content that were printed
   */
  public static String recSearchDirectory(Directory dir, String regex) {
    ArrayList<Directory> children; // maybe file or dir
    String result = "";
    children = dir.getSubContent();
    if (children.isEmpty()) { // base case
      if (dir.isFile) {
        result += result += Grep.searchRegexInFile(dir, regex);
      }
    } else {
      for (Directory child : children) {
        if (child.isFile) {
          result += Grep.searchRegexInFile(child, regex);
        } else {
          result += recSearchDirectory(child, regex);
        }
      }
    }
    return result;
  }


  /**
   * Run the Grep command search the path and names of all files containing the
   * regular expression input by the user and print them one per line.
   * 
   * @param arguments an array of strings corresponding to the users input
   * @return String error messages and content printed
   */
  @Override
  public String runCommand(String[] arguments) {
    File file;
    String regex;
    Directory dir;
    String result = "";
    boolean checkR;
    if (arguments.length > 1) {
      checkR = arguments[0].toLowerCase().equals("-r");
      // case 1
      if (!checkR) {
        regex = arguments[0];
        for (int i = 1; i < arguments.length; i++) {
          file = File.getFileByPath(arguments[i]);
          if (file != null) {
            result = Grep.searchRegexInFile(file, regex);
            if (result.equals(""))
              result += "Error: Your search does not exist.\n";
          } else {
            result += "Error: File doesn't exist\n";
          }
        }
      } else {
        regex = arguments[1];
        for (int j = 2; j < arguments.length; j++) {
          dir = Directory.getDir(arguments[j]);
          // when path is pointing to a directory
          if (dir != null && !dir.isFile) {
            result = Grep.recSearchDirectory(dir, regex);
          }
          // when path is pointing to a file

          else if (dir.isFile) {
            result = Grep.searchRegexInFile(dir, regex);
          } else {
            result += "Error: Directory or file doesn't exist\n";
          }

          if (result.equals(""))
            result += "Error: Your search does not exist.";
        }
      }
    } else {
      result += "Error: Wrong arguments number!\n";
    }
    testString = result;
    return result;
  }

  public String getTestString() {
    return testString;

  }


  /**
   * Creates a specified manual on how to use the [grep] command.
   *
   * @return String for the manual documentation for [grep].
   */
  @Override
  public String toString() {
    return "grep [-R] REGEX PATH ...\n" + "\tfind and print the path to "
        + "all files that contain REGEX";
  }
}
