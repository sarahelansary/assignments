package os.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import os.example.Redirection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class redirectionTest {
    private Redirection redirection;
    private File tempFile;

    @BeforeEach
    public void setUp() {
        try {
            redirection = new Redirection();
            tempFile = File.createTempFile("testFile", ".txt");
            tempFile.deleteOnExit(); // Ensure the file is deleted after the test
        } catch (IOException e) {
            fail("Failed to create temporary file: " + e.getMessage());
        }
    }


    @Test
    public void testHandleRedirection() {
        String content = "Hello, World!" + System.lineSeparator() + "This is a test.";
        String[] args = {tempFile.getAbsolutePath()};

        // Simulate user input: add an extra newline to indicate end of input
        System.setIn(new java.io.ByteArrayInputStream((content + System.lineSeparator() + System.lineSeparator()).getBytes()));

        redirection.handleRedirection(args);

        // Verify that the content was written to the file
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(tempFile.getAbsolutePath())));
            // Use assertEquals to compare the actual content with expected
            assertEquals(content + System.lineSeparator(), fileContent); // Ensure there's a newline after the last line
        } catch (IOException e) {
            fail("IOException while reading the temporary file: " + e.getMessage());
        }
    }


    @Test
    public void testHandleRedirectionEmptyFilePath() {
        String[] args = {};

        // Capture output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        redirection.handleRedirection(args);

        assertTrue(outContent.toString().contains("Error: No file specified for redirection."));
    }
}
