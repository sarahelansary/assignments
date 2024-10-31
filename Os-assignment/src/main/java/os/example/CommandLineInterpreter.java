package os.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CommandLineInterpreter {
private File currentDirectory;
cat cat=new cat();
remove remove=new remove();
move move= new move();
Redirection Redirection =new Redirection();
merge merge= new merge();
DisplayHelp help= new DisplayHelp();
redirectToFile  redirectToFile=new redirectToFile();
pipe pipe=new pipe();
ls list=new ls();
    int i=0;
private CD cd;

    public void executeCommand(String command, String[] args) {
        switch (command) {
            case "pwd":
                new pwd().execute();
                break;
            case "mkdir":
                new mkdir().execute(args);
                break;
            case "rmdir":
                new rmdir().execute(args);
                break;
            case "touch":
                new touch().execute(args);
                break;

            case "cat":
                cat.handleCatCommand(args);
                break;
            case "cat>":
                Redirection.handleRedirection(args);
                break;
            case">":
                merge.merge(args);
                break;
            case"rm":
                remove.remove(args);
                break;
            case"mv" :
                move.move(args);
                break;
            case "ls":
                list.ls(args,currentDirectory);
                break;
            case "cd":
                cd.cd(args);
                currentDirectory = cd.getCurrentDirectory();
                break;
            case "help":
                help.displayHelp();
            case ">>":
                // Ensure correct usage: ">> filename content[]"
                if (args.length >= 2) {
                    String filename = args[0];
                    String[] contentArray = Arrays.copyOfRange(args, 1, args.length);
                    redirectToFile.redirectToFile(filename, contentArray);
                } else {
                    System.out.println("Error: Missing filename or content for redirection.");
                }
                break;

            case "|": {
                // Collect user input for the first command in the pipe
                List<String> input = new ArrayList<>();
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter lines of text (type 'end' to finish):");
                String line;
                while (!(line = scanner.nextLine()).equals("end")) {
                    input.add(line);
                }

                // Check if the next command in the pipe is 'grep' with a search term
                if (args.length >= 2 && args[0].equals("grep")) {
                    String searchText = args[1]; // Get the search text for 'grep'
                    List<String> output = pipe.pipeCommands(input, "grep", searchText);

                    // Print the output of the pipe command
                    System.out.println("Filtered output:");
                    for (String result : output) {
                        System.out.println(result);
                    }
                } else {
                    System.out.println("Invalid command or arguments for pipe.");
                }
                break;
            }
            default:
                System.out.println("Unknown command: " + command);
        }
    }


    public String cat(String filePath) {
        try {
            return String.join("\n", java.nio.file.Files.readAllLines(java.nio.file.Paths.get(filePath)));
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }
    }


}

