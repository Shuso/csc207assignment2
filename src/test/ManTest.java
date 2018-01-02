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

/**
 * This class tests the functionality of the Man class.
 */


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.Man;

public class ManTest {

  Man m;
  String[] cat = {"cat"};
  String[] mkdir = {"mkdir"};
  String[] history = {"history"};

  @Before
  public void setUp() {
    m = new Man();
  }

  @Test
  public void testCatMan() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    assertEquals("cat FILE1 [FILE2 ...]:\n"
        + "\tDisplay the contents of FILE1 and other "
        + "files(i.e. FILE2 ...) one by one.", m.runCommand(cat));
  }

  @Test
  public void testMkdirMan() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    assertEquals("mkdir DIR ...:\n" + "\tCreate directories.\n"
        + "\tNote: Each of DIR may be relative to the current directory "
        + "or may be a full path.", m.runCommand(mkdir));

  }

  @Test
  public void testHistoryMan() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    assertEquals(
        "history [number]:\n"
            + "\tPrint out recent commands.\n\tNote: If number is "
            + "given, the output will be truncate by this number.",
        m.runCommand(history));

  }

}
