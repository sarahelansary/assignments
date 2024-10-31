package os.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MkdirTest {
    private File testDirectory;
    private mkdir mkdirCommand;

    @Before
    public void setUp() throws Exception {
        testDirectory = Files.createTempDirectory("testDir").toFile();
        mkdirCommand = new mkdir();
    }

    @After
    public void tearDown() throws Exception {
        Files.walk(testDirectory.toPath())
                .sorted((a, b) -> b.compareTo(a))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    public void testCreateDirectory() {
        // Capture the output of the mkdir command
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        mkdirCommand.execute(new String[]{"newDir"});
        File newDir = new File(testDirectory, "newDir");

        // Verify the directory was created
        assertTrue(newDir.exists());
        String output = outputStream.toString().trim();
        assertEquals("Directory created: newDir", output);
    }

    @Test
    public void testCreateDirectoryAlreadyExists() {
        // Create the directory first
        new File(testDirectory, "existingDir").mkdir();

        // Capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        mkdirCommand.execute(new String[]{"existingDir"});

        // Verify the output message
        String output = outputStream.toString().trim();
        assertEquals("File already exists: existingDir", output);
    }

    @Test
    public void testCreateDirectoryNoArgument() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        mkdirCommand.execute(new String[]{});

        // Verify the output message
        String output = outputStream.toString().trim();
        assertEquals("mkdir: missing argument", output);
    }
}

