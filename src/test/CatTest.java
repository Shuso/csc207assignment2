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

import driver.Cat;
import driver.Directory;
import driver.File;

public class CatTest {
  Directory root;
  File f;
  File f2;
  File f3;
  Cat cat;
  String[] noArg = {};
  String[] oneArg = {"file"};
  String[] twoArg = {"file", "file2"};
  String[] threeArg = {"file", "file2", "file3"};
  String[] badArg = {"dirName"};
  String[] badArg2 = {"noFile"};

  @Before
  public void setUp() {
    cat = new Cat();
    root = Directory.getRootDir();
    Directory.setRoot();
    f = new File("file", Directory.getCurrDir(), "hello");
    f2 = new File("file2", Directory.getCurrDir(), "bye");
    f3 = new File("file3", Directory.getCurrDir(), "hi");
    root.addContent(f);
    root.addContent(f2);
    root.addContent(f3);
  }

  @After
  public void teardown() {
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }

  @Test
  public void testNoArg() throws Exception {
    assertEquals("Invalid arguments number", cat.runCommand((noArg)));
  }

  @Test
  public void testOneArg() throws Exception {
    assertEquals("hello", cat.runCommand((oneArg)));
  }

  @Test
  public void testTwoArg() throws Exception {
    assertEquals("hello\n\n\nbye", cat.runCommand((twoArg)));
  }

  @Test


  public void testThreeArg() throws Exception {
    assertEquals("hello\n\n\nbye\n\n\nhi", cat.runCommand((threeArg)));
  }

  @Test
  public void testBadArg1() throws Exception {
    assertEquals("Error: The target file dirName do not exist!\n",
        cat.runCommand((badArg)));
  }

  @Test
  public void testBadArg2() throws Exception {
    assertEquals("Error: The target file noFile do not exist!\n",
        cat.runCommand((badArg2)));
  }

}
