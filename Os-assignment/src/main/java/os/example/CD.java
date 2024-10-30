package os.example;
import java.io.File;

public class CD {
    private File currentDirectory;
    public CD(){

    }

    public CD(File currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public void cd(String[] args) {
        // Check if there are enough arguments
        if (args.length < 1) {
            System.out.println("Usage: cd <directory>");
            return;
        }

        String path = args[0]; // Get the path argument
        File newDir;

        // Handle special case for ".."
        if ("..".equals(path)) {
            newDir = currentDirectory.getParentFile();
        } else {
            newDir = new File(currentDirectory, path); // Create new File object based on current directory
        }

        // Check if the new directory is valid
        if (newDir != null && newDir.isDirectory()) {
            currentDirectory = newDir; // Update current directory
            System.out.println("Changed directory to: " + currentDirectory.getAbsolutePath());
        } else {
            System.out.println("Directory not found: " + path);
        }
    }

    public File getCurrentDirectory() {
        return currentDirectory;
    }
}
