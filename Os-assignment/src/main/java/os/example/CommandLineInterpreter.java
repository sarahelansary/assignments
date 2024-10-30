package os.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Arrays;


public class CommandLineInterpreter {
private File currentDirectory;
cat cat=new cat();
remove remove=new remove();
move move= new move();
Redirection Redirection =new Redirection();
merge merge= new merge();
DisplayHelp help= new DisplayHelp();
redirectToFile  redirectToFile=new redirectToFile();pipe pipe=new pipe();
ls list=new ls();
private CD cd; 
    public void executeCommand(String command, String[] args) {
        switch (command) {
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
            case"|":
                break;

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

