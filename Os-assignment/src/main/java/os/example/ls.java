package org.example;

import java.io.File;
import java.util.Arrays;

public class ls {
    public void ls(String[] args, File currentDirectory) {
        // Set default options for `ls` command
        boolean all = false;
        boolean reverse = false;

        // Check for arguments like "-a" (show all files) and "-r" (reverse order)
        for (String arg : args) {
            if ("-a".equals(arg)) {
                all = true;
            }
            if ("-r".equals(arg)) {
                reverse = true;
            }
        }

        // Get the files in the current directory
        File[] files = currentDirectory.listFiles();

        if (files == null) {
            System.out.println("Cannot access directory.");
            return;
        }

        // Sort the files array
        Arrays.sort(files);
        if (reverse) {
            for (int i = files.length - 1; i >= 0; i--) {
                if (all || !files[i].isHidden()) {
                    System.out.println(files[i].getName());
                }
            }
        } else {
            for (File file : files) {
                if (all || !file.isHidden()) {
                    System.out.println(file.getName());
                }
            }
        }
    }
}