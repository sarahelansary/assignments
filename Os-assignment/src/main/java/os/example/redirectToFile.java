package os.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class redirectToFile {


        public void redirectToFile( String filename,String[] contentArray) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                for (String content : contentArray) {
                    writer.write(content);
                    writer.newLine();  // Add a new line after each string
                }
                System.out.println("Content written to " + filename);
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }
