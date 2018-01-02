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
import java.util.List;

/**
 * Directory class creates a directory system that keeps track of every current
 * directory, parent directory, and each child directory. Also keeps track of
 * the absolute and relative path of each directory.
 */
public class Directory {
  // Directory Stack used for pushd and popd
  public static List<String> dirStack = new ArrayList<String>();
  // Root directory
  public static Directory rootDir = new Directory("", null);
  // Current directory
  public static Directory currDir;
  // ArrayList storing every directory created
  private static ArrayList<Directory> allDirectory = new ArrayList<Directory>();

  public static ArrayList<Directory> getAllDirectory() {
    return allDirectory;
  }

  // Name of the directory
  private String name;
  // Parent of the directory
  protected Directory parent;
  // Children of the directory
  private ArrayList<Directory> subContent;

  // Gets root directory
  public static Directory getRootDir() {
    return rootDir;
  }

  // Gets current directory
  public static Directory getCurrDir() {
    return currDir;
  }

  /**
   * Sets current directory to the directory provided by the user
   * 
   * @param currDir directory provided by the user
   */
  public static void setCurrDir(Directory currDir) {
    Directory.currDir = currDir;
  }

  // Absolute path of the location of the directory
  protected String AbsolutePath;
  // False if directory is created, true is file is created
  protected boolean isFile;

  /**
   * Constructor that instantiates the variables
   * 
   * @param name Name of the directory
   * @param parent Parent directory for the directory created
   */
  public Directory(String name, Directory parent) {
    this.setName(name);
    this.parent = parent;
    this.AbsolutePath = this.getAbsolutePath();
    this.setSubContent(new ArrayList<Directory>());
    this.isFile = false;
  }

  /**
   * Creates the root directory for all directories and files. Does not have a
   * name or a parent directory.
   */
  public static void setRoot() {
    currDir = rootDir;
  }

  // Gets name of the directory
  public String getName() {
    return name;
  }

  /**
   * Sets name of the directory with name provided by the user
   * 
   * @param name Directory name provided by the user
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Creates a string that shows the absolute path of the current directory from
   * the root to the current directory. Each directory is separated by a "/"
   * 
   * @return String for the absolute path.
   */
  public String getAbsolutePath() {
    if (this.parent == null) // the this directory is root directory
      return "/";
    else {
      String path = getName();
      Directory temp = this;
      while (temp.parent != null) { // traversal through the tree until reach
                                    // the root
        temp = temp.parent;
        path = temp.getName() + "/" + path;
      }
      return path;
    }
  }

  /**
   * Creates an array of strings that represent the names of the directories in
   * the absolute path.
   * 
   * @param path takes a string that represents the absolute path of currDir
   * @return String[] for the names of each directory in directory path
   */
  public static String[] getDirNamesFromPath(String path) {
    path = path.replace("\\", "/").replace("\\\\", "/"); // compatible for win
    return path.replace("/", " ").trim().split(" "); // remove "/" in both ends
  }

  /**
   * Gets a directory of the specified path.
   * 
   * @param path String type path provided by the user
   * @return Directory of the path
   */
  public static Directory getDir(String path) {
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
        continue;
      } else if (tDir == null) {
        return null;
      } else {
        tDir = tDir.getSubContent(args[i]);
      }
    }
    return tDir;
  }

  /**
   * Add directory provided by the user as a children
   * 
   * @param dir Directory provided by the user
   */
  public void addContent(Directory dir) {
    this.getSubContent().add(dir);
  }

  /**
   * Gets sub directory if there is same named directory as the name provided by
   * the user.
   * 
   * @param name Name of the directory provided by the user
   * @return Returns directory if there is same named directory in the children.
   *         Else return null.
   */
  public Directory getSubContent(String name) {
    for (Directory tDir : subContent) {
      if (tDir.getName().equals(name)) {
        return tDir;
      }
    }
    return null;
  }

  /** Gets list of sub content of current directory
   * 
   * @return Return an ArrayList of sub contents of current directory.
   */
  public ArrayList<Directory> getSubContent() {
    return subContent;
  }
  
  /**
   * Sets list of sub content of current directory
   * @param subContent List of sub content of current directory provided by
   *                   the user.
   */
  public void setSubContent(ArrayList<Directory> subContent) {
    this.subContent = subContent;
  }
  
  /**
   * Deletes directory or file provided by the user in the current directory.
   * @param dir Directory or file provided by the user
   */
  public void delete(Directory dir) {
    for (int i = 0; i < this.getSubContent().size(); i ++) {
      if (dir.getName().equals(this.getSubContent().get(i).getName())) {
        this.getSubContent().remove(i);
      }
    }
  }

}


