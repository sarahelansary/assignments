package os.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class MkdirTest {

    private mkdir mkdirCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Path tempDir;

    @BeforeEach
    public void setUp() throws Exception {
        mkdirCommand = new mkdir();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create a temporary directory for tests
        tempDir = Files.createTempDirectory("testDir");
    }

    @Test
    public void testCreateDirectory() {
        String dirName = tempDir.resolve("testDir").toString();

        // Clean up before the test
        new File(dirName).delete();

        mkdirCommand.execute(new String[]{dirName});

        // Print captured output for debugging
        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertTrue(new File(dirName).exists(), "Directory should be created");
        assertEquals("Directory created: " + dirName, actualOutput, "Output mismatch");
    }

    @Test
    public void testCreateExistingDirectory() {
        String dirName = tempDir.resolve("existingDir").toString();

        // Create the directory for the test
        new File(dirName).mkdir();

        mkdirCommand.execute(new String[]{dirName});

        // Print captured output for debugging
        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertEquals("mkdir: failed to create directory: " + dirName, actualOutput, "Output mismatch");

        // Clean up after the test
        new File(dirName).delete();
    }

    @Test
    public void testMissingArgument() {
        mkdirCommand.execute(new String[]{});

        // Print captured output for debugging
        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertEquals("mkdir: missing argument", actualOutput, "Output mismatch");
    }
}


