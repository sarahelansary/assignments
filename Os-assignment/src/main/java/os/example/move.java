package os.example;

import java.io.File;

public class move {



    public void move(String[] args) {
        // Check if there are enough arguments
        if (args.length < 2) {
            System.out.println("Usage: mv <source> <destination>");
            return;
        }

        String sourcePath = args[0]; // Source file path
        String destinationPath = args[1]; // Destination path
        File sourceFile = new File(sourcePath); // Create a File object for the source
        File destinationFile = new File(destinationPath); // Create a File object for the destination

        // Check if the source file exists
        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist.");
            return;
        }

        // Extract the filename from the source path
        String fileName = cutFileName(sourcePath);

        // Check if the destination is a directory
        if (destinationFile.isDirectory()) {
            // Create a new file object for the new location
            File newFile = new File(destinationFile, fileName);
            // Rename (move) the source file to the new file location
            if (sourceFile.renameTo(newFile)) {
                System.out.println("Moved file to: " + newFile.getPath());
            } else {
                System.out.println("Failed to move the file.");
            }
        } else {
            // Rename the source file to the destination path
            if (sourceFile.renameTo(destinationFile)) {
                System.out.println("Renamed file to: " + destinationFile.getPath());
            } else {
                System.out.println("Failed to rename the file.");
            }
        }
    }

    /**
     * Extracts the filename from a full file path.
     *
     * @param path The full path of the file.
     * @return The name of the file.
     */
    private String cutFileName(String path) {
        // Find the last occurrence of the file separator
        int lastSlashIndex = path.lastIndexOf(File.separator);
        // If no directory separator is found, return the entire path (filename)
        if (lastSlashIndex == -1) {
            return path; // No directory, return the whole path as filename
        }
        // Return the substring after the last file separator
        return path.substring(lastSlashIndex + 1);
    }
}
