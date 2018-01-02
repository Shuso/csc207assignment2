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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Get class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the users input for "get URL." Specifically, it
 * retrieve the file at a URL address and adds it to the current directory.
 */
public class Get extends Command {

  /**
   * Uses an array list of users input arguments and it retrieves the file at a
   * URL address and adds it to the current directory.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String is empty if file is created correctly.
   */
  @Override
  public String runCommand(String[] argument) throws IOException {
    if (argument.length == 1) {
      Directory tDir = Directory.getCurrDir();
      // parses through the URL string to get the file name
      if ((argument[0].endsWith(".txt")) || (argument[0].endsWith(".html"))) {
        int i = argument[0].lastIndexOf("/");
        String fName = argument[0].substring(i + 1);
        // call Get.createNewFile to create the file in current directory
        this.createNewFile(argument, fName, tDir);
      } else {
        return "Invalid URL address"; // if URL address is not for a file
      }
    } else {
      return "Invalid argument number";
    }
    return "";
  }

  /**
   * Finds the file as the URL address, read it, and appends the content as
   * strings into a list of strings.
   * 
   * @param arg String for URL argument from provided by the user
   * @return List of strings for the content of a file.
   */
  public List<String> getFileContent(String arg) throws IOException {
    //general Reader code from Q&A on Blackboard
    URL oracle = new URL(arg);
    BufferedReader in =
        new BufferedReader(new InputStreamReader(oracle.openStream()));
    String inputLine;
    List<String> str = new ArrayList<String>();
    while ((inputLine = in.readLine()) != null)
      str.add(inputLine);
    in.close();
    return str;
  }

  /**
   * Checks whether the file with fname already exists, if it doesn't it creates
   * a file called fname in the current directory specified by dir.
   * 
   * @param arg String array for arguments from provided by the user
   * @param fname String for the file name
   * @param dir Directory of the current directory
   */
  public void createNewFile(String[] arg, String fname, Directory dir)
      throws IOException {
    File file = File.getFile(fname);
    // validating that it is a URL before getting URL file content
    if (arg[0].startsWith("http://")) {
      List<String> content = this.getFileContent(arg[0]);
      if (file == null) {
        file = File.createFile(fname, dir);
        for (String item : content) {
          file.appendContent(item); // appends every line one by one into new
                                    // file
        }
      } else {
        System.out.println("Error: file already exists");
      }
    } else {
      System.out.println("Invalid argument type"); // if arg not a URL
    }
  }

  /**
   * Creates a specified manual on how to use the [get] command.
   *
   * @return String for the manual documentation of [get].
   */
  @Override
  public String toString() {
    return "get URL" + "\tRetrieves the file at the URL "
        + "and adds it to the current directory.\n";
  }
}
