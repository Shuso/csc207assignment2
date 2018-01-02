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

package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import driver.File;
import org.junit.Test;

import driver.Cp;
import driver.Directory;

public class CpTest {
  Cp copy;
  Directory root;
  File file1;
  File file2;
  Directory dir1;
  Directory dir2;
  Directory d11;
  File dir1File;
  File d11File;

  @Before
  public void setUp() {
    copy = new Cp();
    root = Directory.getRootDir();
    Directory.setRoot();
    file1 = new File("file1.txt", root, "Red");
    file2 = new File("file2.txt", root, "Blue");
    dir1 = new Directory("dir1", root);
    dir2 = new Directory("dir2", root);
    d11 = new Directory("d11", dir1);
    dir1File = new File("file3.txt", dir1, "Yellow");
    d11File = new File("file4.txt", d11, "Green");
    root.addContent(file1);
    root.addContent(file2);
    root.addContent(dir1);
    dir1.addContent(d11);
    d11.addContent(d11File);
    dir1.addContent(dir1File);
    root.addContent(dir2);

  }

  @After
  public void tearDown() {
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }

  @Test
  public void testCpFileFile() {
    // Overwrites file1 content
    copy.renameFile(file1, file2, file2.getName(), Directory.getRootDir());
    // Creates new File named file1
    copy.renameFile(file1, null, "newFile", Directory.getCurrDir());
    File newFile = (File) root.getSubContent("newFile");

    assertEquals(file1.getFileContent(), file2.getFileContent());
    assertEquals(newFile.getName(), "newFile");
    assertEquals(newFile.getFileContent(), file1.getFileContent());
  }

  @Test
  public void testCpFileDir() {
    copy.copyFile(file1, dir2);
    copy.copyFile(file2, dir2);

    assertFalse(dir2.getSubContent().isEmpty());
    // files from dir2
    File dir2file1 = (File) dir2.getSubContent(file1.getName());
    File dir2file2 = (File) dir2.getSubContent(file2.getName());
    assertEquals("Red", dir2file1.getFileContent());
    assertEquals("Blue", dir2file2.getFileContent());
  }

  @Test
  public void testCpDirDir() {
    copy.copyDir(dir1, dir2);
    // dir1 in dir2
    Directory dir1dir2 = dir2.getSubContent().get(0);

    assertEquals(dir1dir2.getSubContent().size(), 2);
    assertEquals(dir1dir2.getName(), dir1.getName());
    // file3.txt in dir2
    File file3 = (File) dir1dir2.getSubContent(dir1File.getName());
    assertEquals(file3.getFileContent(), dir1File.getFileContent());
    assertEquals(file3.getName(), "file3.txt");
    // d11 in dir1 in dir2
    Directory newd11 = dir1dir2.getSubContent(d11.getName());

    // file4.txt in d11
    File file4 = (File) newd11.getSubContent(d11File.getName());

    assertEquals(file4.getFileContent(), d11File.getFileContent());
  }


  @Test
  public void testCpDirFile() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    String[] dirtoFile = {"dir1", "file1.txt"};
    String[] dirtoNewfile = {"dir1", "newFile.txt"};

    assertEquals("Error: Wrong arguments input!", copy.runCommand(dirtoFile));
    assertEquals("Error: Wrong arguments input!",
        copy.runCommand(dirtoNewfile));
  }

  @Test
  public void testErrors() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    String[] newnew = {"Ronaldo", "Messi"};
    String[] wronginput = {"Bale", "Neymar", "Martial"};
    String[] onecorrect = {"dir1", "Pogba"};

    assertEquals(
        "Error: File or Folder named Ronaldo and Messi does not exist!",
        copy.runCommand(newnew));
    assertEquals("Error: Wrong arguments number!", copy.runCommand(wronginput));
    assertEquals("Error: Wrong arguments input!", copy.runCommand(onecorrect));
  }

}
