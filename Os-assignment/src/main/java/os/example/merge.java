package os.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class merge {

    public void merge(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: <file1> <file2> ... <outputFile>");
            return;
        }

        String outputFilePath = args[args.length - 1]; // Last argument is the output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Iterate over all arguments except the last one (output file)
            for (int i = 0; i < args.length - 1; i++) {
                String inputFilePath = args[i];
                if (!Files.exists(Paths.get(inputFilePath))) {
                    System.out.println("Input file not found: " + inputFilePath);
                    continue; // Skip this file if it doesn't exist
                }
                List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine(); // Write each line to the output file
                }
            }
            System.out.println("Files merged into " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error merging files: " + e.getMessage());
        }
    }
}
