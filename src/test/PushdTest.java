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
import driver.Pushd;

public class PushdTest {

  Pushd push;
  Directory root;
  Directory dir1;
  Directory dir2;
  Directory dir3;


  String[] input = {"Cara"};
  String[] badArg1 = {"asdfasdf"}; // when dir name doesn't exist
  String[] badArg2 = {"cara", "cara"}; // extra arguments
  String[] badArg3 = {""}; // no argument

  @Before
  public void setUp() {
    push = new Pushd();
    root = new Directory("/", null);
    // build directory like Cara-> Gigi -> Kendall
    dir1 = new Directory("Cara", root);
    dir2 = new Directory("Gigi", dir1);
    dir3 = new Directory("Kendall", dir2);
    root.addContent(dir1);
    dir1.addContent(dir2);
    dir2.addContent(dir3);
    Directory.setCurrDir(root);

  }

  @After
  public void tearDown() {
    Directory.setCurrDir(root);
    Directory.dirStack = new ArrayList<String>();
  }

  // test weather current directory is changed to DIR

  @Test
  public void testCurrentDirectory() throws Exception {
    String temp;
    push.runCommand(input);
    temp = Directory.getCurrDir().getName();
    assertEquals("Cara", temp);
  }


  // test weather the directory is saved on the top of the stack
  @Test
  public void testPushToStack() throws Exception {
    String temp;
    push.runCommand(input);
    temp = Directory.dirStack.get(Directory.dirStack.size() - 1);
    assertEquals("/", temp);
  }

  @Test
  // when argument is more than 1
  public void testExtraArg() throws Exception {
    assertEquals("Error: Invalid argument number", push.runCommand((badArg2)));
  }

  @Test
  // when argument is empty
  public void testEmptyArg() throws Exception {
    assertEquals("", push.runCommand((badArg3)));
  }

  @Test
  // when directory name doesn't exist
  public void testDirNameNotExist() throws Exception {
    assertEquals("", push.runCommand((badArg1)));
  }
}
