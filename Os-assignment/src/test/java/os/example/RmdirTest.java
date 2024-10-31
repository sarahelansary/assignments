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

public class RmdirTest {
    private File testDirectory;
    private rmdir rmdirCommand;

    @Before
    public void setUp() throws Exception {
        testDirectory = Files.createTempDirectory("testDir").toFile();
        rmdirCommand = new rmdir();
        new File(testDirectory, "testDir").mkdir(); // Create a directory for testing
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
    public void testRemoveDirectory() {
        // Capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        rmdirCommand.execute(new String[]{"testDir"});

        // Verify the directory was removed
        File removedDir = new File(testDirectory, "testDir");
        assertTrue(!removedDir.exists());
        String output = outputStream.toString().trim();
        assertEquals("Directory removed: testDir", output);
    }

    @Test
    public void testRemoveNonExistentDirectory() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        rmdirCommand.execute(new String[]{"nonExistentDir"});

        // Verify the output message
        String output = outputStream.toString().trim();
        assertEquals("rmdir: no such directory: nonExistentDir", output);
    }

    @Test
    public void testRemoveDirectoryNotEmpty() throws IOException {
        // Create a directory and file inside it
        File dir = new File(testDirectory, "notEmptyDir");
        dir.mkdir();
        new File(dir, "file.txt").createNewFile();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        rmdirCommand.execute(new String[]{"notEmptyDir"});

        // Verify the output message
        String output = outputStream.toString().trim();
        assertEquals("rmdir: directory not empty or cannot be removed: notEmptyDir", output);
    }
}
