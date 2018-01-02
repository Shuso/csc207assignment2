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

import driver.Cd;
import driver.Directory;
import driver.File;

/**
 * This tests the functionality of the Cd test.
 *
 */

public class CdTest {

  Cd cd;
  Directory dir1;
  Directory dir2;
  Directory root;
  String[] oneArgument = {"dir2"};
  String[] zeroArguments = {};
  String[] argumentDirNonexistent = {"apple"};
  String[] fileArgument = {"banana.txt"};
  String[] twoArguments = {"dir1", "dir2"};
  File f;

  @Before
  public void setUp() {
    cd = new Cd();
    root = Directory.getRootDir();
    dir1 = new Directory("dir1", root);
    dir2 = new Directory("dir2", root);
    root.addContent(dir1);
    root.addContent(dir2);
    f = new File("banana.txt", root, "I'm a banana");
    
    root.addContent(f);
  }

  @After
  public void tearDown() {
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }

  @Test
  public void testOneArg() {
    cd.runCommand(oneArgument);
    String str = new String(dir1.getName());
    assertEquals("dir1", str);
  }

  @Test
  public void testNoArg() {
    assertEquals("Error: Invalid arguments number", cd.runCommand(zeroArguments));
  }

  @Test
  public void testNonExistentCd() {
    assertEquals("Error: The input path apple doesn't exist!\n",
        cd.runCommand(argumentDirNonexistent));
  }

  @Test
  public void testTwoArg() {
    assertEquals("Error: Invalid arguments number", cd.runCommand(twoArguments));
  }

  @Test
  public void testFileCd() {
    Directory.currDir = root;
    assertEquals("Error: The input path banana.txt is File!"
        + " Please enter folder name.", cd.runCommand(fileArgument));
  }

}
