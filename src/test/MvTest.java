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

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import driver.Directory;
import driver.File;
import driver.Mv;

public class MvTest {
  Mv move;
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
    move = new Mv();
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
  public void testMvFileFile() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    // OCreates new file
    String[] createFile = {"file1.txt", "newFile.txt"};
    move.runCommand(createFile);
    assertEquals(root.getSubContent("file1.txt"), null);
    assertEquals(((File) root.getSubContent("newFile.txt")).getFileContent(),
        file1.getFileContent());

    // Overwrites file
    String[] overwriteFile = {"newFile.txt", "file2.txt"};
    move.runCommand(overwriteFile);
    assertEquals(root.getSubContent("newFile.txt"), null);
    assertEquals(file2.getFileContent(), file1.getFileContent());
  }

  @Test
  public void testMvFileDir() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    String[] filetoDir = {"file1.txt", dir2.getName()};
    move.runCommand(filetoDir);
    assertEquals(root.getSubContent("file1.txt"), null);
    assertEquals(((File) dir2.getSubContent().get(0)).getFileContent(),
        file1.getFileContent());


  }

  @Test
  public void testMvDirDir() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    String[] DirtoDir = {"dir1", "dir2"};
    move.runCommand(DirtoDir);
    assertEquals(root.getSubContent("dir1"), null);
    assertEquals(dir2.getSubContent().size(), 1);
    Directory dir1dir2 = dir2.getSubContent("dir1");
    assertEquals(
        ((File) dir1dir2.getSubContent(dir1File.getName())).getFileContent(),
        dir1File.getFileContent());
    Directory d11dir2 = dir1dir2.getSubContent("d11");
    File file4 = (File) d11dir2.getSubContent(d11File.getName());
    
    assertEquals(file4.getFileContent(), d11File.getFileContent());
  }
  
  @Test
  public void testMvDirFile() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    String[] dirtoFile = {"dir1", "file1.txt"};
    String[] dirtoNewfile = {"dir1", "newFile.txt"};
    assertEquals("Error: Wrong arguments input!", move.runCommand(dirtoFile));
    assertEquals("Error: Wrong arguments input!",
        move.runCommand(dirtoNewfile));
  }
  
  @Test
  public void testErrors() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    String[] newnew = {"Ronaldo", "Messi"};
    String[] wronginput = {"Bale", "Neymar", "Martial"};
    String[] onecorrect = {"dir1", "Pogba"};
    String[] othercorrect = {"Dybala", "dir1"};

    assertEquals("Error: File or Folder named Ronaldo and Messi does not exist",
        move.runCommand(newnew));
    assertEquals("Error: Wrong arguments number!", move.runCommand(wronginput));
    assertEquals("Error: Wrong arguments input!",
        move.runCommand(onecorrect));
    assertEquals("Error: File or Folder named Dybala does not exist",
        move.runCommand(othercorrect));
  }


}
