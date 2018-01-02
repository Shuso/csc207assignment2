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
import driver.Get;
import driver.File;

public class GetTest {
    Get get;
    Directory d;
    String[] test1 = {};
    String[] test2 = {"http://www.cs.cmu.edu/~spok/grimmtmp/073.txt"};
    String[] test3 = {"http://www.cdf.toronto.edu/~dema/csc165/syllabus.html"};
    String[] test4 = {"", ""};
    String[] test5 = {"file.txt"};
    String[] test6 = {"http://google.ca"};

    @Before
    public void setUp() {
      get = new Get();
      d = Directory.rootDir;
      Directory.currDir = d;
    }

    @After
    public void teardown() {
      File.getAllFile().removeAll(File.getAllFile());
    }

    @Test
    public void testNoArg() throws Exception {
      assertEquals("Invalid argument number", get.runCommand((test1)));
    }

    @Test
    public void testAddNewFile() throws Exception {
      get.runCommand(test2);
      //file should already exist in directory
      assertEquals("", get.runCommand(test2));
    //Correctly prints out "Invalid argument type" on console.
    }

    @Test
    public void testAppend() throws Exception {
      get.runCommand(test3);
      //file should already exist in directory
      assertEquals("", get.runCommand(test3));
    //Correctly prints out "Error: file already exists" on console.
    }

    @Test
    public void testExtraArg() throws Exception {
      assertEquals("Invalid argument number", get.runCommand((test4)));
    }
    
    @Test
    public void testWrongArg() throws Exception {
      assertEquals("", get.runCommand((test5)));
      //Correctly prints out "Error: file already exists" on console.
    }
    
    @Test
    public void testWrongArg2() throws Exception {
      assertEquals("Invalid URL address", get.runCommand((test6)));
    }
  }
