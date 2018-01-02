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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.Grep;
import driver.Redirect;

public class GrepTest {
  final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
  Grep grep;
  Directory root;
  Directory dir1;
  Directory dir2;
  File file1;
  File file2;
  File file3;
  File file4;

  String[] regArg = {"e", "/cara/Ivanka/file1.txt"};
  String[] recArg = {"-r", "e",  "/"};
  String[] nomatchArg =  {"e",  "/cara/file3.txt"};
  String[] errorArg1 = {"e", "/"};
  String[] errorArg2 = {"e", "fileDoesNotExist.txt"};
  String[] noArg = {""};

  
  @Before 
  public void setUp() throws Exception {
    System.setOut(new PrintStream(myOut));
    grep = new Grep();
    root = Directory.getRootDir();
    Directory.setRoot();
    file1 = new File("file1.txt", root, "Red");
    file2 = new File("file2.txt", root, "Blue");
    dir1 = new Directory("cara", root);
    dir2 = new Directory("Ivanka", dir1);
    file1 = new File("file1.txt", dir2, "Tae Hoon Jun");
    file2 = new File("file2.txt", dir2, "Saima Begum" );
    file3 = new File("file3.txt", dir1, "Monica Iqbal");
    file4 = new File("file4.txt", dir1, "Zhu Zeng");

    root.addContent(dir1);
    dir1.addContent(dir2);
    dir1.addContent( file3);
    dir1.addContent( file4);
    dir2.addContent( file1);
    dir2.addContent( file2);
  }
  @After
  public void tearDown() throws Exception {
    System.setOut(null);
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }
  @Test
  public void testregArg() throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, IOException {
    String content = "/cara/Ivanka/file1.txt file1.txt: Tae Hoon Jun";
    grep.runCommand(regArg);
    assertEquals( content, grep.getTestString());
}

  @Test
  public void testrecArg() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String content = "/cara/Ivanka/file1.txt file1.txt: Tae Hoon Jun"+
        "/cara/Ivanka/file2.txt file2.txt: Saima Begum" +
        "/cara/file4.txt file4.txt: Zhu Zeng";
    grep.runCommand(recArg);
    assertEquals(content,grep.getTestString());
  }
  @Test
  public void nomatchArg() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String content = "Error: Your search does not exists.";
    assertEquals(content,grep.runCommand(nomatchArg));


  }
  @Test
  public void noArg() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String content = "Error: Invalid argument numbers";
    assertEquals(content,grep.runCommand(noArg));
}
  @Test
  public void errorArg2() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String content = "Error: File doesn't exist";
    assertEquals(content,grep.runCommand(errorArg2));
    
  }  
  @Test
  public void errorArg1() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String content = "Error: File doesn't exist";
    assertEquals(content,grep.runCommand(errorArg1));}}

