// Assignment2:
// Student1:
// CDF user_name: c4iqbalm
// UT Student #: 1001452064
// Author: Monica Iqbal
//
// Student2:
// CDF user_name:c4begums
// UT Student #: 1000271289
// Author: Saima Begum
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
import java.util.Arrays;
import java.util.Scanner;

/**
 * JShell class runs the program.
 */
public class JShell {

  public static void main(String[] args)
      throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, IOException {
    Scanner in = new Scanner(System.in);
    Directory.setRoot();
    while (true) {
      // Display current directory here
      String curr_dir = Directory.getCurrDir().getAbsolutePath();
      System.out.print(curr_dir + " ");
      String input = in.nextLine();
      History.cmdHistory.add(input);

      String[] splitInput = input.trim().split("\\s+");
      String command = splitInput[0];
      String[] arguments = Arrays.copyOfRange(splitInput, 1, splitInput.length);
      if (command.equals("exit")) {
        break;
      } else if (command.equals("echo")) {
        String arg =
            input.substring(input.indexOf(arguments[0]), input.length());
        arguments = arg.split("[ ]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      } else if (input.startsWith("!")) {
        String number = command.substring(1, command.length());
        command = "!";  
        String[] oldArg = arguments;
        arguments = new String[arguments.length + 1];
        arguments[0] = number;
        System.arraycopy(oldArg, 0, arguments, 1, oldArg.length);
      }
      CommandManager a = new CommandManager();
      a.setCommand();
      a.checkCommand(command);
      a.runClass(command, arguments);
    }
    in.close();
  }

}