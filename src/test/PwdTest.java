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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.Pwd;

public class PwdTest {


  Pwd pwd;
  Directory dir1;
  Directory dir2;
  Directory dir3;
  Directory root = new Directory("", null);
  String[] input = {};
  String[] badArg1 = {"bbbbb"}; // extra arguments


  @Before
  public void setUp() {
    pwd = new Pwd();
    // build directory like Cara-> Gigi
    dir1 = new Directory("Cara", root);
    dir2 = new Directory("Gigi", dir1);
    Directory.currDir = dir2;
  }

  @After
  public void tearDown() {
    Directory.currDir = root;
    Directory.dirStack.clear();
  }

  // test print current directory when current directory is not root directory
  @Test
  public void testCurrentDirectoryPath() throws Exception {
    assertEquals("/Cara/Gigi", pwd.runCommand(input));
  }

  // when argument is more than 1
  @Test
  public void testExtraArguments() throws Exception {
    assertEquals("Error: Invalid arguments number", pwd.runCommand(badArg1));
  }



}
