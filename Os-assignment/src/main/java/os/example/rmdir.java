package os.example;

import java.io.File;

public class rmdir {

    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("rmdir: missing argument");
            return;
        }

        String directoryName = args[0];
        File directory = new File(directoryName);

        if (directory.exists() && directory.isDirectory()) {
            if (directory.delete()) {
                System.out.println("Directory removed: " + directoryName);
            } else {
                System.out.println("rmdir: directory not empty or cannot be removed: " + directoryName);
            }
        } else {
            System.out.println("rmdir: no such directory: " + directoryName);
        }
    }
}


