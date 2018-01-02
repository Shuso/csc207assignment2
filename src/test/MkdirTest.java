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
import driver.Mkdir;

public class MkdirTest {
  Mkdir m;
  String[] oneArg = {"cake"};
  String[] twoArgs = {"/pie", "icecream"};
  String[] existing = {"/cake"};
  Directory root;
  Directory a;

  @Before
  public void setUp() {
    m = new Mkdir();
    root = Directory.getRootDir();
    Directory.setRoot();
    a = new Directory("dir1", root);
    root.addContent(a);
  }
  
  @After
  public void tearDown() {
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }


  @Test
  public void testCreateNewDirectory() {
    assertEquals("", m.runCommand(oneArg));
  }

  @Test
  public void testMultipleArgs() {
    assertEquals("", m.runCommand(twoArgs));
    assertTrue(root.getSubContent("pie") != null);
    assertTrue(root.getSubContent("icecream") != null);
  }

  @Test
  public void testExistingFolderFile() {
    assertEquals(m.runCommand(oneArg), "");
    assertEquals(
        "Error in argument 1: Folder or File named /cake already exists\n",
        m.runCommand(existing));
  }


}
