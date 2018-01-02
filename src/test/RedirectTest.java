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
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.Redirect;

public class RedirectTest {
  Redirect redirect;
  Directory root;
  File file1;
  File file2;
  Directory dir1;
  Directory dir2;
  Directory d11;
  File dir1File;
  File d11File;
  String[] noArg = {"mkdir", "/dir3", ">", "file5.txt"};
  String[] oneArg = {"ls", "-r", "/dir1", ">", "/dir2/file5.txt"};
  String[] errorArg = {"echo", "hello", "hi", ">", "/dir2/file5.txt"};
  String[] errorArg2 = {"some", "command", ">"};
  String[] echoNew =
      {"echo", "\"Harry Kane is top scorer\"", ">", "/dir2/newFile.txt"};
  String[] append =
      {"echo", "\"Jamie Vardy is second scorer\"", ">>", "/dir2/newFile.txt"};
  String[] overwrite =
      {"echo", "\"Mahrez is good midfielder\"", ">", "/dir2/newFile.txt"};

  @Before
  public void setUp() throws Exception {
    redirect = new Redirect();
    root = Directory.getRootDir();
    Directory.setRoot();
    file1 = new File("file1.txt", root, "Red");
    file2 = new File("file2.txt", root, "Blue");
    dir1 = new Directory("dir1", root);
    dir2 = new Directory("dir2", root);
    d11 = new Directory("d11", dir1);
    dir1File = new File("file3.txt", dir1, "Yellow");
    d11File = new File("file4.txt", d11, "Green");
    root.addContent(file1);
    root.addContent(file2);
    root.addContent(dir1);
    dir1.addContent(d11);
    d11.addContent(d11File);
    dir1.addContent(dir1File);
    root.addContent(dir2);
  }

  @After
  public void tearDown() throws Exception {
    ArrayList<Directory> empty = new ArrayList<Directory>();
    root.setSubContent(empty);
  }

  @Test
  public void testNoArg() throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, IOException {
    assertEquals(
        "Command does not give any output so file file5.txt have not been created",
        redirect.runCommand(noArg));
  }

  @Test
  public void testOneArg() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    String content = "dir1:\n\td11:\n\t\tfile4.txt\n\n\tfile3.txt\n";
    assertEquals("", redirect.runCommand(oneArg));
    File created = File.getFileByPath("/dir2/file5.txt");
    assertEquals(content, created.getFileContent());
  }

  @Test
  public void testErrorArg() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    assertEquals("Error: Invalid arguments number!",
        redirect.runCommand(errorArg));
    File checkFile = File.getFileByPath("/dir2/file5.txt");
    assertTrue(checkFile == null);
    assertEquals("Error: Wrong arguments input",
        redirect.runCommand(errorArg2));
  }

  @Test
  public void testAppendOverwrite() throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    assertEquals("", redirect.runCommand(echoNew));
    File newFile = File.getFileByPath("/dir2/newFile.txt");
    assertEquals("Harry Kane is top scorer", newFile.getFileContent());
    assertEquals("", redirect.runCommand(append));
    assertEquals(
        "Harry Kane is top scorer\n" + "Jamie Vardy is second scorer",
        newFile.getFileContent());
    assertEquals("", redirect.runCommand(overwrite));
    assertEquals("Mahrez is good midfielder", newFile.getFileContent());
  }


}
