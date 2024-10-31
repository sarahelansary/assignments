package os.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TouchTest {
    private File testDirectory;
    private touch touchCommand;

    @Before
    public void setUp() throws Exception {
        testDirectory = Files.createTempDirectory("testDir").toFile();
        touchCommand = new touch();
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
    public void testCreateFile() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        touchCommand.execute(new String[]{"newFile.txt"});
        File newFile = new File(testDirectory, "newFile.txt");

        // Verify the file was created
        assertTrue(newFile.exists());
        String output = outputStream.toString().trim();
        assertEquals("File created: newFile.txt", output);
    }

    @Test
    public void testCreateFileAlreadyExists() throws IOException {
        // Create the file first
        new File(testDirectory, "existingFile.txt").createNewFile();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        touchCommand.execute(new String[]{"existingFile.txt"});

        // Verify the output message
        String output = outputStream.toString().trim();
        assertEquals("File already exists: existingFile.txt", output);
    }

    @Test
    public void testCreateFileNoArgument() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        touchCommand.execute(new String[]{});

        // Verify the output message
        String output = outputStream.toString().trim();
        assertEquals("touch: missing argument", output);
    }
}

