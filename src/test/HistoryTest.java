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

import driver.History;

public class HistoryTest {
  History his;
  String[] historyNoArgs = {};
  String[] historyArg = {"2"};

  @Before
  public void setUp() {
    his = new History();
  }

  @After
  public void tearDown() {
    History.cmdHistory.removeAll(History.cmdHistory);
  }

  @Test
  public void testNoHistoryNoArguments() {
    assertEquals("", his.runCommand(historyNoArgs));
  }

  @SuppressWarnings("static-access")
  @Test
  public void testHistoryNoArguments() {
    his.cmdHistory.add("mkdir lol");
    his.cmdHistory.add("ls");
    his.cmdHistory.add("cd");
    his.cmdHistory.add("history");
    assertEquals("", his.runCommand(historyNoArgs));
    // prints appropriate output on the console and returns empty string
  }

  @SuppressWarnings("static-access")
  @Test
  public void testHistoryWithArguments() {
    his.cmdHistory.add("mkdir food");
    his.cmdHistory.add("pwd");
    his.cmdHistory.add("history 2");
    assertEquals("", his.runCommand(historyArg));
  }

}
