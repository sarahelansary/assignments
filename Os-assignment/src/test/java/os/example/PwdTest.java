package os.example;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class PwdTest {

    @Test
    public void testPwdCommand() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        pwd pwdCommand = new pwd();
        pwdCommand.execute();


        String expected = System.getProperty("user.dir");
        assertEquals(expected, outputStream.toString().trim());
    }
}
