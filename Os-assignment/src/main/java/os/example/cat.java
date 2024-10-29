package os.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class cat {
    public void handleCatCommand(String[] args) {
        if (args.length == 0) {
            // No file specified: read from standard input until end of input
            System.out.println("Reading from standard input.  Ctrl+Z (Windows) and Enter to end.");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Echo back the input
                }
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        } else {

            String filePath = args[0];
            try {
                Files.lines(Paths.get(filePath)).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }}
}
