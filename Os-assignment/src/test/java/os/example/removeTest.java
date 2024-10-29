package os.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import os.example.remove;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class removeTest {
    private remove rm; // Instance of the Rm class to be tested
    private Path tempFile; // Path for the temporary test file

    @BeforeEach
    public void setUp() throws IOException {
        rm = new remove();
        tempFile = Files.createTempFile("testFile", ".txt"); // Create a temporary file for testing
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Attempt to delete the temporary file if it still exists
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    public void testRemoveFileSuccessfully() {
        String[] args = {tempFile.toString()}; // Argument to remove the temporary file

        // Call the remove method
        rm.remove(args);

        // Verify that the file has been deleted
        assertFalse(Files.exists(tempFile), "File should have been deleted.");
    }

    @Test
    public void testRemoveNonExistentFile() {
        String[] args = {"nonExistentFile.txt"}; // Non-existent file path

        // Capture output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the remove method
        rm.remove(args);

        // Check the output message
        assertTrue(outContent.toString().contains("Error deleting nonExistentFile.txt:"));
    }

    @Test
    public void testRemoveWithNoArguments() {
        String[] args = {}; // No arguments provided

        // Capture output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the remove method
        rm.remove(args);

        // Check the output message
        assertTrue(outContent.toString().contains("Usage: rm <file1> <file2> ..."));
    }
}
