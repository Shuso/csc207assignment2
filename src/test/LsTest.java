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
import org.junit.Test;
import driver.Directory;
import driver.File;
import driver.Ls;

public class LsTest {

  Ls ls;
  Directory root;
  File file1;
  File file2;
  Directory dir1;
  Directory dir2;
  Directory d11;
  File dir1File;
  File d11File;
  String[] oneArg = {"/"};
  String[] zeroArg = {};
  String[] noDir = {"dir5"};
  String[] noPath = {"/dir1/dir2"};

  @Before
  public void setUp() {
    ls = new Ls();
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
  public void teardown() {
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }
  @Test
  public void testZeroArg() { // prints content of the current directory, root
    String rootContent = "file1.txt\nfile2.txt\ndir1 : d11, file3.txt\ndir2\n";
    assertEquals(rootContent, ls.runCommand((zeroArg)));
    Directory.setCurrDir(dir1);
    String dir1Content = "d11 : file4.txt\nfile3.txt\n";
    assertEquals(dir1Content, ls.runCommand(zeroArg));
  }

  @Test
  public void testOneArg() {
    String rootContent = "file1.txt\nfile2.txt\ndir1 : d11, file3.txt\ndir2\n";
    assertEquals(rootContent, ls.runCommand((oneArg)));
    String[] dir1oneArg = {"/dir1"};
    String dir1Content = "d11 : file4.txt\nfile3.txt\n";
    assertEquals(dir1Content, ls.runCommand(dir1oneArg));
  }

  @Test
  public void testMultipleArg() {
    String[] multi = {"/dir1/d11", "/dir1"};
    String multiContent = "file4.txt\nd11 : file4.txt\nfile3.txt\n";
    assertEquals(multiContent, ls.runCommand(multi));
    String[] oneWrong = {"/dir1/d11", "/dir3", "/dir1"};
    String oneWrongContent =
        "file4.txt\nError: The input path /dir3 doesn't exist!\n\nd11 : file4.txt\nfile3.txt\n";
    assertEquals(oneWrongContent, ls.runCommand(oneWrong));
  }

  @Test
  public void testRecursiveLs() {
    String[] recursive = {"-r", "/dir1"};
    String recursedir1 = "dir1:\n\td11:\n\t\tfile4.txt\n\n\tfile3.txt\n";
    assertEquals(recursedir1, ls.runCommand(recursive));
    String[] recursiveMulti = {"-r", "/dir1", "/"};
    recursedir1 +=
        ":\n\tfile1.txt\n\n\tfile2.txt\n\n\tdir1:\n\t\td11"
        + ":\n\t\t\tfile4.txt\n\n\t\tfile3.txt\n\n\tdir2\n";
    assertEquals(recursedir1, ls.runCommand(recursiveMulti));
    String[] recursiveOneWrong = {"-r", "/dir1", "/", "/dir3"};
    recursedir1 += "Error: the input path /dir3 does not exist!\n";
    assertEquals(recursedir1, ls.runCommand(recursiveOneWrong));
  }
  
  @Test
   public void testNoDirectory() { 
    assertEquals(
   "Error: The input path dir5 doesn't exist!\n\n", ls.runCommand((noDir))); }
   
   @Test public void testInvalidPath() { 
     assertEquals(
   "Error: The input path /dir1/dir2 doesn't exist!\n\n",
   ls.runCommand((noPath))); 
   }
}
