package os.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class remove {

    public void remove(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: rm <file1> <file2> ...");
            return;
        }

        for (String filePath : args) {
            Path path = Paths.get(filePath);
            try {
                // Attempt to delete the file or directory
                Files.delete(path);
                System.out.println("Deleted: " + filePath);
            } catch (IOException e) {
                System.out.println("Error deleting " + filePath + ": " + e.getMessage());
            }
        }
    }
}
