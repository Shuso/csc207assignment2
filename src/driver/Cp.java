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
 * Cp class is a subclass of Command that inherits the runCommand(String[])
 * method in order to run the user's input for "cp DIR DIR" or "cp FILE FILE" or
 * "cp File DIR"
 */
public class Cp extends Command {
  /**
   * Recursively copies dir1's contents to dir2. ("cp dir1 dir2")
   * 
   * @param dir1 Directory gets copied
   * @param dir2 Directory to get dir1's contents
   */
  public void copyDir(Directory dir1, Directory dir2) {
    if (dir1.getSubContent().isEmpty()) {
      if (dir1.isFile) {
        File copiedFile =
            new File(dir1.getName(), dir2, ((File) dir1).getFileContent());
        dir2.addContent(copiedFile);
      } else {
        Directory copiedDir = new Directory(dir1.getName(), dir2);
        dir2.addContent(copiedDir);
      }

    } else {
      Directory copiedDir = new Directory(dir1.getName(), dir2);
      dir2.addContent(copiedDir);
      for (int i = 0; i < dir1.getSubContent().size(); i++) {
        copyDir(dir1.getSubContent().get(i),
            dir2.getSubContent(dir1.getName()));
      }
    }
  }

  /**
   * Overwrite file1's content to file2 if file2 exists. Otherwise, create file2
   * with the file1's content ("cp file1 file2")
   * 
   * @param file1 File to be copied
   * @param file2 If file2 is file, file1's content gets overwritten to file2.
   *        Otherwise, new file named file2 with file1's content gets created
   * @param newName Name of file2
   * @param parent Directory of file2's parent
   */
  public void renameFile(File file1, File file2, String newName,
      Directory parent) {
    if (file2 == null) {
      File.createFile(newName, parent);
      File newFile;
      if (parent.getName().equals("/")) {
        newFile = File.getFileByPath(parent.getAbsolutePath() + newName);
      } else {
        newFile = File.getFileByPath(parent.getAbsolutePath() + "/" + newName);
      }
      newFile.overwriteContent(file1.getFileContent());

    } else {
      file2.overwriteContent(file1.getFileContent());
    }
  }

  /**
   * Copy file to directory dir provided by the user. ("cp file dir")
   * 
   * @param file File to be copied
   * @param dir Directory where file gets copied
   */
  public void copyFile(File file, Directory dir) {
    File newFile = new File(file.getName(), dir, file.getFileContent());
    dir.addContent(newFile);
  }

  /**
   * Uses String array of arguments provided by the user to copy. If user's
   * input is valid, file or directory provided by the user gets copied to the
   * indicated location and returns an empty string. Otherwise, returns an error
   * message.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String The result of copy. Empty if successful. Otherwise, error
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
            + arguments[1] + " does not exist!";
      } else if (firstArg == null && secondArg != null) {
        return "Error: File or Folder named " + arguments[0]
            + " does not exist!";
      } else if ((firstArg.isFile && secondArg == null)
          || (firstArg.isFile && secondArg.isFile)) {
        String inputPath =
            arguments[1].substring(0, arguments[1].lastIndexOf("/"));
        String newName =
            arguments[1].substring(arguments[1].lastIndexOf("/") + 1);
        Directory parentDir = Directory.getDir(inputPath);
        if (parentDir == null && inputPath.contains("/")) {
          return "Error: File or Folder named " + inputPath
              + " does not exist!";
        } else if (parentDir == null) {
          parentDir = Directory.getCurrDir();
        }
        renameFile((File) firstArg, (File) secondArg, newName, parentDir);
      } else if (firstArg.isFile && !secondArg.isFile) {
        copyFile((File) firstArg, secondArg);
      } else if (firstArg != null && secondArg != null && !firstArg.isFile
          && !secondArg.isFile) {
        copyDir(firstArg, secondArg);
      } else {
        return "Error: Wrong arguments input!";
      }
    } else {
      return "Error: Wrong arguments number!";
    }
    return "";
  }

  /**
   * Creates a specified manual on how to use the [cp] command.
   *
   * @return String for the manual documentation for [cp].
   */
  @Override
  public String toString() {
    return "cp OLDPATH NEWPATH:\n"
        + "\tLike mv, but don��t remove OLDPATH. If OLDPATH is a directory, "
        + "recursively copy the contents.";
  }



}

