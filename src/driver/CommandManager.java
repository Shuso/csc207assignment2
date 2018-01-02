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
import java.util.HashMap;

/**
 * CommandManager class collaborates with Command class to call the appropriate
 * subclasses of Command class.
 */
public class CommandManager {

  private HashMap<String, String> commands;
  private boolean validator = false;

  /**
   * Keeps track of all the commands that the user can use in a HashMap; where
   * the key is the command and the value is the location of the command in the
   * src folder.
   */
  public void setCommand() {
    this.commands = new HashMap<String, String>();
    this.commands.put("mkdir", "driver.Mkdir");
    this.commands.put("cd", "driver.Cd");
    this.commands.put("ls", "driver.Ls");
    this.commands.put("pwd", "driver.Pwd");
    this.commands.put("pushd", "driver.Pushd");
    this.commands.put("popd", "driver.Popd");
    this.commands.put("history", "driver.History");
    this.commands.put("cat", "driver.Cat");
    this.commands.put("echo", "driver.Echo");
    this.commands.put("man", "driver.Man");
    this.commands.put("cp", "driver.Cp");
    this.commands.put("mv", "driver.Mv");
    this.commands.put("get", "driver.Get");
    this.commands.put("grep", "driver.Grep");
    this.commands.put("!", "driver.NumberCommand");
    this.commands.put("Redirect", "driver.Redirect");
    this.commands.put("exit", "exit:\nQuit the program.");
  }

  /**
   * Checks if the key is in the HashMap commands.
   * 
   * @param input takes a string that represents the command from users input
   */
  public void checkCommand(String input) {
    this.validator = this.commands.containsKey(input);
  }

  /**
   * Creates an object for the appropriate command class in the driver package
   * based on the users input.
   * 
   * @param cmdname takes a string that represents the command from users input
   * @return Object for calling and using the appropriate command class.
   */
  public Command getClass(String cmdname) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    return (Command) Class.forName(this.commands.get(cmdname)).newInstance();
  }

  /**
   * Gets the manual documentation from each Command subclass.
   * 
   * @param input takes a string that represents the users input
   * @return String for the manual documentation in the command classes.
   */
  public String getMan(String input) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException {
    String manual = "";
    if (this.validator) {
      if (input.equals("exit")) {
        manual = this.commands.get("exit");
      } else {
        Command cmd = getClass(input);
        manual = cmd.toString();
      }
      this.validator = false;
    } else {
      manual = "Error: There is no manual for the command " + input;
    }
    return manual;
  }

  /**
   * Uses an array list of users input arguments and prints out the contents of
   * the file(s) specified in the array list. If the user specifies multiple
   * files (i.e. "cat FILE1 FILE2") the method prints out the contents of each
   * file one by one.
   * 
   * @param argument is an array of strings corresponding to the users input
   * @param input takes a string that represents the users command name input
   * @throws IOException
   */
  public void runClass(String input, String[] argument)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, IOException {
    String output = "";
    if (this.validator) {
      if (!redirectCheck(argument)) {
        if (input.startsWith("!") && argument.length == 0) {
          Command cmd = getClass("!");          
          String[] numClass = {input.substring(1, input.length())};
          output = cmd.runCommand(numClass);
        }
        else {
          Command cmd = getClass(input);
          output = cmd.runCommand(argument);
        }
        this.validator = false;
      } else if (redirectCheck(argument)
          && !argument[argument.length - 1].equals(">")
          && !argument[argument.length - 1].equals(">>")) {
        Command cmd = getClass("Redirect");
        String[] args = new String[argument.length + 1];
        args[0] = input;
        System.arraycopy(argument, 0, args, 1, argument.length);
        output = cmd.runCommand(args);
        this.validator = false;
      } else {
        output = "Error: wrong argument";
      }
      if (!output.equals("") && output != null) {
        System.out.println(output);
      }
    }
  }

  /**
   * Returns true if arguments contains only one > or >> symbol.
   * 
   * @param arguments Arguments provided by the user
   * @return True if there is only one > or >> symbol in the arguments. False
   *         otherwise.
   */
  public boolean redirectCheck(String[] arguments) {
    boolean one = false;
    boolean more = false;
    for (int i = 0; i < arguments.length; i++) {
      if (arguments[i].equals(">") || arguments[i].equals(">>")) {
        if (!more) {
          if (!one) {
            one = true;
          } else {
            more = true;
          }
        }
      }
    }
    return more == false && one == true;
  }

  /**
   * Returns an output of user's command.
   * 
   * @param input Command name provided by the User
   * @param argument Arguments of the command provided by the User
   * @return Returns String of the output of user's command
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public String getOutput(String input, String[] argument)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, IOException {
    String output = "";
    if (this.validator) {
      if (input.startsWith("!") && argument.length == 0) {
        Command cmd = getClass("!");
        String[] numClass = {input.substring(1, input.length())};
        output = cmd.runCommand(numClass);
      }
      else {
        Command cmd = getClass(input);
        output = cmd.runCommand(argument);
      }
      this.validator = false;
    }
    return output;
  }
}
