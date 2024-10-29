package os.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Redirection {
   public void handleRedirection( String[] args) {
       if (args.length == 0 || args[0].isEmpty()) {
            System.out.println("Error: No file specified for redirection.");
            return;
        }

        String filePath = args[0];
        System.out.println("Enter content for " + filePath + " (Press Enter on an empty line to finish):");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Scanner scanner = new Scanner(System.in);
            String line;
            while (!(line = scanner.nextLine()).isEmpty()) { // Keep reading until an empty line is entered
                writer.write(line);
                writer.newLine(); // Write a new line to the file
            }
            System.out.println("Content written to " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

