import org.example.cli;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class pipeTest {
    private cli cliex;

    @BeforeEach
    void setUp() {
        cliex = new cli();
    }

    @Test
    void testPipeCommands() {
        List<String> input = Arrays.asList("file1.txt", "file2.doc", "file3.txt");
        List<String> expectedOutput = Arrays.asList("file1.txt", "file3.txt");
        List<String> actualOutput = cliex.pipeCommands(input, "grep", "txt");
        assertEquals(expectedOutput, actualOutput);
    }

}
