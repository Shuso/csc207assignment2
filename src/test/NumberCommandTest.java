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

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import driver.History;
import driver.NumberCommand;

public class NumberCommandTest {

  NumberCommand num;
  History h;
  String[] numberArg = {"2"};
  String[] numberOutOfBoundsArg = {"8"};
  String[] nonNumericalArg = {"f"};
  String[] invalidArgs = {};

  @Before
  public void setUp() {
    num = new NumberCommand();
    h = new History();
  }

  @SuppressWarnings("static-access")
  @Test
  public void testNumberInHistory() throws InstantiationException,
  IllegalAccessException, ClassNotFoundException, IOException {
    h.cmdHistory.add("mkdir dir3");
    h.cmdHistory.add("echo DEBUGGED");
    h.cmdHistory.add("ls");
    h.cmdHistory.add("pwd");
    assertEquals("DEBUGGED", num.runCommand(numberArg));
    //console successfully executes "echo DEBUGGED" and prints DEBUGGED.
  }

  @SuppressWarnings("static-access")
  public void testNumberOutOfBounds() throws InstantiationException,
  IllegalAccessException, ClassNotFoundException, IOException{
    h.cmdHistory.add("pwd");
    h.cmdHistory.add("mkdir movies");
    h.cmdHistory.add("mkdir music");
    h.cmdHistory.add("mkdir assignments");
    h.cmdHistory.add("cd music");
    assertEquals("Number is out of bounds",
        num.runCommand(numberOutOfBoundsArg));
  }

  @SuppressWarnings("static-access")
  public void testInputNonNumerical() throws InstantiationException,
  IllegalAccessException, ClassNotFoundException, IOException {
    h.cmdHistory.add("ls");
    h.cmdHistory.add("pwd");
    h.cmdHistory.add("mkdir bookmarks");
    assertEquals("Input is not a number", num.runCommand(nonNumericalArg));
  }

  @SuppressWarnings("static-access")
  public void testInputInvalidArgs() throws InstantiationException,
  IllegalAccessException, ClassNotFoundException, IOException {
    h.cmdHistory.add("man cd");
    h.cmdHistory.add("history");
    assertEquals("Error: Invalid argument number", num.runCommand(invalidArgs));
  }

}
