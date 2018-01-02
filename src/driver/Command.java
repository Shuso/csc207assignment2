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

package driver;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is an abstract Command class to check user input validity.
 */
public abstract class Command {

  /**
   * Determines whether the users input is valid by checking its letter case
   * valid characters.
   *
   * @param name take a string for the users input
   * @return boolean for the validity of users input, name
   */
  public boolean validate(String name) {
    Pattern p = Pattern.compile("[^a-z0-9./ ]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(name);
    boolean b = m.find();
    return b;
  }

  /**
   * Abstract method that is inherited and overwritten by subclasses.
   * 
   * @param arguments is an array of strings corresponding to the users input
   * @return String specific to the subclass implementation; return in subclass.
   * @throws OutOfBoundsException 
   * @throws NonNumericalInputException 
   */
  public abstract String runCommand(String[] arguments)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, IOException;
}
