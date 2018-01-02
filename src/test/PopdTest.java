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

import driver.Popd;;

public class PopdTest {

  Popd pop;
  Directory root;
  Directory dir1;
  Directory dir2;
  Directory dir3;


  String[] input = {""};
  String[] badArg1 = {"bbbbb"}; // extra arguments

  @Before
  public void setUp() {
    pop = new Popd();
    root = new Directory("/", null);
    // build directory like Cara-> Gigi -> Kendall
    dir1 = new Directory("Cara", root);
    dir2 = new Directory("Gigi", dir1);
    dir3 = new Directory("Kendall", dir2);
    dir1.addContent(dir2);
    dir2.addContent(dir3);
    // Suppose we Pushed dir1 and then pushed dir2
    Directory.dirStack.add("Cara");
    Directory.setCurrDir(dir1);
    Directory.dirStack.add("Gigi");
    Directory.setCurrDir(dir2);

  }

  @After
  public void tearDown() {
    Directory.setCurrDir(root);
    Directory.dirStack.clear();
  }

  // test weather current directory is changed to DIR
  @Test
  public void testCurrentDirectory() throws Exception {
    String temp;
    pop.runCommand(input);
    temp = Directory.getCurrDir().getName();
    assertEquals("Gigi", temp);
  }


  // test weather the last entry is removed
  @Test
  public void testRemoveLastEntry() throws Exception {
    String temp;

    pop.runCommand(input);
    temp = Directory.dirStack.get(Directory.dirStack.size() - 1);

    assertEquals("Gigi", temp);
  }

  @Test
  // when argument is more than 1
  public void testExtraArg() throws Exception {
    assertEquals("Error: Invalid argument number", pop.runCommand((badArg1)));
  }



}
