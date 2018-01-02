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

/**
 * File class creates a file system that keeps track of every file that has been
 * created in an array list by name. Also keeps track of the absolute and
 * relative path of each file.
 */
public class File extends Directory {
  // ArrayList storing every files
  public static ArrayList<File> allFile = new ArrayList<File>();
  // Content of the file
  protected String fileContent = "";

  /**
   * Constructor that instantiates the variables
   * 
   * @param name File name
   * @param parent Parent directory for the file created
   * @param fileContent Contents of the file created
   */
  public File(String name, Directory parent, String fileContent) {
    super(name, parent);
    this.fileContent = fileContent;
    this.AbsolutePath = this.getAbsolutePath();
    this.isFile = true;
  }

  /**
   * Gets file by name provided by the user
   * 
   * @param fileName File name provided by the user
   * @return Returns file if there is same named file. Otherwise, return null.
   */
  public static File getFile(String fileName) {
    for (File item : getAllFile()) {
      if (item.getName().equals(fileName)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Overwrites file content with the string provided by the user.
   * 
   * @param str String provided by the user
   */
  public void overwriteContent(String str) {
    this.fileContent = str;
  }

  /**
   * Appends file content with the string provide by the user
   * 
   * @param str String provided by the user
   */
  public void appendContent(String str) {
    if (this.fileContent.isEmpty()) {
      this.fileContent += str;
    } else {
      this.fileContent += "\n" + str;
    }

  }

  /**
   * Gets file content
   * 
   * @return Returns the file content
   */
  public String getFileContent() {
    return this.fileContent;
  }

  /**
   * Gets file by the path provided by the user
   * 
   * @param path Path containing file name provided by the user
   * @return Returns File if there is file in the path. Otherwise, return null.
   */
  public static File getFileByPath(String path) {
    Directory tDir = currDir;
    if (path.startsWith("/")) {
      tDir = rootDir;
    }
    String args[] = getDirNamesFromPath(path);
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("..") && tDir.parent != null) {
        tDir = tDir.parent;
      } else
        if (args[i].equals(".") || (args[i].equals("..") && tDir.parent == null)
            || args[i].equals("")) {

        if (i == args.length - 1) {
          return null;
        } else {
          continue;
        }
      } else if (tDir == null) {
        return null;
      } else {
        tDir = tDir.getSubContent(args[i]);
        if (i == args.length - 1) {
          if (tDir != null) {
            if (tDir.isFile) {
              tDir = (File) tDir;
            } else {
              return null;
            }
          }
        }
      }
    }
    return (File) tDir;
  }

  /**
   * Create a new file with name under directory provided.
   * 
   * @param fileName Name of the file to be created
   * @param dir Directory where new file created will be stored
   * @return Returns new file created.
   */
  public static File createFile(String fileName, Directory dir) {
    File newFile = new File(fileName, dir, "");
    dir.addContent(newFile);
    File.getAllFile().add(newFile);
    return newFile;
  }

  public static ArrayList<File> getAllFile() {
    return allFile;
  }

  public static void setAllFile(ArrayList<File> allFile) {
    File.allFile = allFile;
  }
}
