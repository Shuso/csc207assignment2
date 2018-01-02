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
import driver.Echo;
import driver.File;

public class EchoTest {

  File f;
  File f2;
  File f3;
  Echo echo;
  Directory root;
  String[] test1 = {"hello"};
  String[] test2 = {"!yello"};
  String[] test3 = {"hello", " ey"};
  String[] test4 = {"  hi"};
  String[] test5 = {"\" hello hi!\""};
  String[] test6 = {};

  @Before
  public void setUp() {
    echo = new Echo();
    root = Directory.getRootDir();
    Directory.setRoot();
  }

  @After
  public void teardown() {
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }

  @Test
  public void testArgs() throws Exception {
    assertEquals("hello", echo.runCommand((test1)));
    assertEquals("!yello", echo.runCommand((test2)));
    assertEquals("  hi", echo.runCommand((test4)));
    assertEquals(" hello hi!", echo.runCommand((test5)));
  }
  
  @Test
  public void testNoArg() {
    assertEquals("Error: Invalid arguments number!", echo.runCommand((test6)));
  }

  @Test
  public void testExtraArg() throws Exception {
    assertEquals("Error: Invalid arguments number!", echo.runCommand((test3)));
  }
}
