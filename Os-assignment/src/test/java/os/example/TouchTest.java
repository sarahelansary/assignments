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

public class TouchTest {

    private touch touchCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Path tempDir;

    @BeforeEach
    public void setUp() throws Exception {
        touchCommand = new touch();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Create a temporary directory for tests
        tempDir = Files.createTempDirectory("testDir");
    }

    @Test
    public void testCreateFile() {
        String fileName = tempDir.resolve("newFile.txt").toString();

        touchCommand.execute(new String[]{fileName});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertTrue(new File(fileName).exists(), "File should be created");
        assertEquals("File created: " + fileName, actualOutput, "Output mismatch");
    }

    @Test
    public void testUpdateFile() throws IOException {
        String fileName = tempDir.resolve("existingFile.txt").toString();
        new File(fileName).createNewFile();  // Create the file first

        touchCommand.execute(new String[]{fileName});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertTrue(new File(fileName).exists(), "File should still exist");
        assertEquals("File already exists: " + fileName, actualOutput, "Output mismatch");
    }

    @Test
    public void testErrorCreatingFile() {
        String fileName = "/invalid/path/to/file.txt";  // Use an invalid path to trigger an error

        touchCommand.execute(new String[]{fileName});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertTrue(actualOutput.startsWith("touch: error creating file:"), "Should report an error");
    }

    @Test
    public void testMissingArgument() {
        touchCommand.execute(new String[]{});

        String actualOutput = outputStreamCaptor.toString().trim();
        System.out.println("Captured output: " + actualOutput);

        assertEquals("touch: missing argument", actualOutput, "Output mismatch");
    }
}

