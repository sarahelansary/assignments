package os.example;
import java.io.*;
import java.nio.file.*;
import java.util.*;


public class pipe {

    public List<String> pipeCommands(List<String> input, String command,String text) {
        List<String> output = new ArrayList<>();
        if (command.equals("grep")) {
            for (String line : input) {
                if (line.contains("txt")) {
                    output.add(line);
                }
            }
        }
        return output;
    }

}