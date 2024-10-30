package os.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        os.example.CommandLineInterpreter cli = new os.example.CommandLineInterpreter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String currnentDir=System.getProperty("user.dir");
            System.out.print("cli>"+currentDir+">");

            // Check if there's a next line to read
            if (!scanner.hasNextLine()) {
                System.out.println("No input available. Exiting.");
                break; // Exit the loop if no input is available
            }

            String input = scanner.nextLine().trim(); // Trim any leading/trailing whitespace

            // Check for termination command
            if (input.equalsIgnoreCase("exit") ) {
                System.out.println("Exiting the command line interpreter.");
                break; // Exit the loop
            }

            // Skip empty input
            if (input.isEmpty()) {
                continue; // Skip the rest of the loop if no command was entered
            }

            // Split input into command and arguments
            String[] parts = input.split(" ");
            String command = parts[0];
            String[] commandArgs = new String[parts.length - 1];
            System.arraycopy(parts, 1, commandArgs, 0, commandArgs.length);

            // Execute the command
            cli.executeCommand(command, commandArgs);
        }

        scanner.close(); // Close the scanner resource
    }
}
