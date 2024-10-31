package os.example;

import java.io.File;
import java.io.IOException;

public class touch {

    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("touch: missing argument");
            return;
        }

        String fileName = args[0];
        File file = new File(fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("touch: error creating file: " + e.getMessage());
        }
    }
}
