package os.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class lsTest{
    private ls lsCommand;
    private File testDirectory;

    @Before
    public void setUp() throws Exception {
        // Create a temporary directory for testing
        testDirectory = Files.createTempDirectory("testDir").toFile();

        // Create test files
        new File(testDirectory, "file1.txt").createNewFile();
        new File(testDirectory, "file2.txt").createNewFile();
        new File(testDirectory, ".hiddenfile").createNewFile(); // Hidden file
        lsCommand = new ls(); // Initialize the ls command
    }

    @After
    public void tearDown() throws Exception {
        // Clean up after tests
        Files.walk(testDirectory.toPath())
                .sorted((a, b) -> b.compareTo(a)) // Delete files/directories first
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    public void testLSWithAllOption() {
        // Capture the output of the ls command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Execute the ls command with the -a option
        lsCommand.ls(new String[]{"-a"}, testDirectory);

        // Get the output
        String output = outputStream.toString().trim();

        // Verify the output contains all files including the hidden one
        assertTrue(output.contains("file1.txt"));
        assertTrue(output.contains("file2.txt"));
        assertTrue(output.contains(".hiddenfile")); // Should include hidden files
    }



    @Test
    public void testLSWithAllAndReverseOptions() {
        // Capture the output of the ls command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Execute the ls command with both the -a and -r options
        lsCommand.ls(new String[]{"-a", "-r"}, testDirectory);

        // Get the output
        String output = outputStream.toString().trim();

        // Verify the output contains all files in reverse order
        String[] files = output.split("\n");
        assertEquals(3, files.length); // file1.txt, file2.txt, and .hiddenfile
        assertTrue(output.contains("file1.txt"));
        assertTrue(output.contains("file2.txt"));
        assertTrue(output.contains(".hiddenfile")); // Should include hidden files
    }

    @Test
    public void testLSWithInvalidDirectory() {
        // Create an invalid directory to test the ls command
        File invalidDirectory = new File(testDirectory, "invalidDir");

        // Capture the output of the ls command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Execute the ls command with an invalid directory
        lsCommand.ls(new String[]{}, invalidDirectory);

        // Get the output
        String output = outputStream.toString().trim();

        // Verify output when accessing an invalid directory
        assertTrue(output.contains("Cannot access directory."));
    }
}
