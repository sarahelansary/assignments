package os.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import os.example.CommandLineInterpreter;

public class CommandLineInterpreterTest {

    @Test
    void testCatFile() {
        CommandLineInterpreter cli = new CommandLineInterpreter();

        // Assume file1.txt exists with some content for the test
        String result = cli.cat("file1.txt");

        assertNotNull(result, "cat command should return content when file exists");
        assertTrue(result.contains("welcome to File1"), "File content should contain welcome to File1");
    }

    @Test
    void testCatFileNotFound() {
        CommandLineInterpreter cli = new CommandLineInterpreter();

        // Assuming cat should return a specific error message if the file doesn't exist
        String result = cli.cat("nonexistent.txt");

        assertEquals("Error reading file: nonexistent.txt", result, "cat command should handle file not found");
    }
}
