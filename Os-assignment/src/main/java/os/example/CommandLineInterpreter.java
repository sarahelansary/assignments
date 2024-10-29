package os.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CommandLineInterpreter {
cat cat=new cat();
remove remove=new remove();
move move= new move();
Redirection Redirection =new Redirection();
merge merge= new merge();
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

