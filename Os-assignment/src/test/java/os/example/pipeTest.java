package os.example;

import os.example.pipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class pipeTest {
    private pipe pipe;

    @BeforeEach
    void setUp() {
        pipe= new pipe();
    }

    @Test
    void testPipeCommands() {
        List<String> input = Arrays.asList("file1.txt", "file2.doc", "file3.txt");
        List<String> expectedOutput = Arrays.asList("file1.txt", "file3.txt");
        List<String> actualOutput = pipe.pipeCommands(input, "grep", "txt");
        assertEquals(expectedOutput, actualOutput);
    }

}
