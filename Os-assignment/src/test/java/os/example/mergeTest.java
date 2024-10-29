package os.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import os.example.merge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class mergeTest {
    private os.example.merge merge; // Instance of the Merge class to be tested
    private Path tempFile1; // Path for the first temporary test file
    private Path tempFile2; // Path for the second temporary test file
    private Path outputFile; // Path for the output test file

    @BeforeEach
    public void setUp() throws IOException {
        // Initialize the Merge object
        merge = new merge();

        // Create temporary files for testing
        tempFile1 = Files.createTempFile("testFile1", ".txt");
        tempFile2 = Files.createTempFile("testFile2", ".txt");
        outputFile = Files.createTempFile("outputFile", ".txt");

        // Write sample content to the temporary files
        Files.writeString(tempFile1, "Hello, World!" + System.lineSeparator() + "This is file 1.");
        Files.writeString(tempFile2, "This is file 2." + System.lineSeparator() + "Goodbye, World!");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary files after each test to clean up
        Files.deleteIfExists(tempFile1);
        Files.deleteIfExists(tempFile2);
        Files.deleteIfExists(outputFile);
    }

    @Test
    public void testMergeFilesSuccessfully() {
        // Prepare arguments for merging files
        String[] args = {tempFile1.toString(), tempFile2.toString(), outputFile.toString()};

        // Call the merge method
        merge.merge(args);

        // Verify that the output file contains the expected content
        try {
            String expectedContent = "Hello, World!" + System.lineSeparator() +
                    "This is file 1." + System.lineSeparator() +
                    "This is file 2." + System.lineSeparator() +
                    "Goodbye, World!" + System.lineSeparator();
            String actualContent = Files.readString(outputFile);
            assertEquals(expectedContent, actualContent); // Check if content matches
        } catch (IOException e) {
            fail("IOException while reading the output file: " + e.getMessage());
        }
    }

    @Test
    public void testMergeWithNonExistentFile() {
        // Prepare arguments with one valid file and one non-existent file
        String[] args = {tempFile1.toString(), "nonExistentFile.txt", outputFile.toString()};

        // Call the merge method
        merge.merge(args);

        // Verify that the output file is created and contains only the first file's content
        try {
            String expectedContent = "Hello, World!" + System.lineSeparator() +
                    "This is file 1." + System.lineSeparator();
            String actualContent = Files.readString(outputFile);
            assertEquals(expectedContent, actualContent); // Check if content matches
        } catch (IOException e) {
            fail("IOException while reading the output file: " + e.getMessage());
        }
    }

    @Test
    public void testMergeWithNoInputFiles() {
        // Prepare arguments with no input files
        String[] args = {outputFile.toString()}; // Only the output file is specified

        // Capture output to verify the usage message
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the merge method
        merge.merge(args);

        // Check if the correct usage message is displayed
        assertTrue(outContent.toString().contains("Usage: <file1> <file2> ... <outputFile>"));
    }
}
