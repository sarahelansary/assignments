package os.example;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Cdtest {
    private CD cdCommand;
    private File testDirectory;
    private File subDirectory;

    @Before
    public void setUp() throws Exception {
        // Create a temporary directory structure for testing
        testDirectory = Files.createTempDirectory("testDir").toFile();
        subDirectory = new File(testDirectory, "subDir");
        subDirectory.mkdir(); // Create sub-directory
        cdCommand = new CD(testDirectory); // Set the initial current directory
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
    public void testChangeToValidDirectory() {
        // Capture the output of the cd command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Change to the sub-directory
        cdCommand.cd(new String[]{"subDir"});

        // Verify the current directory changed to sub-directory
        assertEquals(subDirectory.getAbsolutePath(), cdCommand.getCurrentDirectory().getAbsolutePath());

        // Verify the output message
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Changed directory to: " + subDirectory.getAbsolutePath()));
    }

    @Test
    public void testChangeToInvalidDirectory() {
        // Capture the output of the cd command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Try to change to an invalid directory
        cdCommand.cd(new String[]{"invalidDir"});

        // Current directory should remain the same
        assertEquals(testDirectory.getAbsolutePath(), cdCommand.getCurrentDirectory().getAbsolutePath());

        // Verify the output message
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Directory not found: invalidDir"));
    }

    @Test
    public void testChangeToParentDirectory() {
        // Create a subdirectory and navigate to it
        cdCommand.cd(new String[]{"subDir"});
        assertEquals(subDirectory.getAbsolutePath(), cdCommand.getCurrentDirectory().getAbsolutePath());

        // Capture the output of the cd command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Change to the parent directory
        cdCommand.cd(new String[]{".."});

        // Verify current directory is now the test directory
        assertEquals(testDirectory.getAbsolutePath(), cdCommand.getCurrentDirectory().getAbsolutePath());

        // Verify the output message
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Changed directory to: " + testDirectory.getAbsolutePath()));
    }

    @Test
    public void testNoArgumentsProvided() {
        // Capture the output of the cd command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call cd without arguments
        cdCommand.cd(new String[]{});

        // Current directory should remain the same
        assertEquals(testDirectory.getAbsolutePath(), cdCommand.getCurrentDirectory().getAbsolutePath());

        // Verify the output message
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Usage: cd <directory>"));
    }
}
