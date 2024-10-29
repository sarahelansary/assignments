package os.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class moveTest {

    private move move;
    private final String testDir = "testDir";
    private final String sourceFile = "testDir/source.txt";
    private final String destFile = "testDir/destination.txt";
    private final String destDir = "src/main";

    @BeforeEach
    public void setUp() throws IOException {
        move = new move();

        // Create a test directory and a source file
        Files.createDirectories(Paths.get(testDir));
        Files.createFile(Paths.get(sourceFile));
    }

    @Test
    public void testMoveFileToDirectory() {
        String[] args = {sourceFile, destDir};
        new File(destDir).mkdir(); // Create the destination directory

        move.move(args);

        assertTrue(new File(destDir, "source.txt").exists(), "File should be moved to the destination directory");
        assertFalse(new File(sourceFile).exists(), "Source file should no longer exist");
    }

    @Test
    public void testRenameFile() {
        String[] args = {sourceFile, destFile};

        move.move(args);

        assertTrue(new File(destFile).exists(), "File should be renamed to the destination file");
        assertFalse(new File(sourceFile).exists(), "Source file should no longer exist");
    }

    @Test
    public void testSourceFileDoesNotExist() {
        String[] args = {"nonexistent.txt", destFile};
        move.move(args);

        // Expect a message printed to the console
        assertFalse(new File(destFile).exists(), "Destination file should not exist since source doesn't exist");
    }

    @Test
    public void testMoveFileWithInsufficientArgs() {
        String[] args = {sourceFile}; // Only source provided
        move.move(args);

        // Expect a message printed to the console for insufficient arguments
    }


}
