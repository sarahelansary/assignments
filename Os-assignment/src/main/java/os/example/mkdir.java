package os.example;

import java.io.File;

public class mkdir {

    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("mkdir: missing argument");
            return;
        }

        String directoryName = args[0];
        File newDirectory = new File(directoryName);

        if (newDirectory.mkdir()) {
            System.out.println("Directory created: " + directoryName);
        } else {
            System.out.println("mkdir: failed to create directory: " + directoryName);
        }
    }
}

