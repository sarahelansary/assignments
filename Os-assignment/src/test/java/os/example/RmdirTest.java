package os.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class RmdirTest {

    private rmdir rmdirCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Path tempDir;

    @BeforeEach
    public void setUp() throws Exception {
        rmdirCommand = new rmdir();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create a temporary directory for tests
        tempDir = Files.createTempDirectory("testDir");
    }

    @Test
    public void testRemoveDirectory() {
        String dirName = tempDir.resolve("removableDir").toString();
        new File(dirName).mkdir();  // Create the directory to be removed

        rmdirCommand.execute(new String[]{dirName});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertFalse(new File(dirName).exists(), "Directory should be removed");
        assertEquals("Directory removed: " + dirName, actualOutput, "Output mismatch");
    }

    @Test
    public void testRemoveNonExistentDirectory() {
        String dirName = tempDir.resolve("nonExistentDir").toString();

        rmdirCommand.execute(new String[]{dirName});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertEquals("rmdir: no such directory: " + dirName, actualOutput, "Output mismatch");
    }

    @Test
    public void testRemoveNonEmptyDirectory() throws IOException {
        String dirName = tempDir.resolve("nonEmptyDir").toString();
        new File(dirName).mkdir();  // Create the directory to be removed
        new File(dirName, "file.txt").createNewFile();  // Create a file inside the directory

        rmdirCommand.execute(new String[]{dirName});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertTrue(new File(dirName).exists(), "Directory should not be removed");
        assertEquals("rmdir: directory not empty or cannot be removed: " + dirName, actualOutput, "Output mismatch");
    }

    @Test
    public void testMissingArgument() {
        rmdirCommand.execute(new String[]{});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertEquals("rmdir: missing argument", actualOutput, "Output mismatch");
    }
}
