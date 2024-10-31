package os.example;
import java.io.File;
public class pwd {

    public void execute() {
        // Print the current working directory
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
    }
}
