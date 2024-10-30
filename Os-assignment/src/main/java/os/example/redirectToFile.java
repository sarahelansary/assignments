package os.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class redirectToFile {

    public void redirectToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Content written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
