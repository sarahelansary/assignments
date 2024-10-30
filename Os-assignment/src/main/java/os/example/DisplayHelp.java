package os.example;

public class DisplayHelp {
    public void displayHelp() {
        System.out.println("Available commands:");
        System.out.println(" pwd          - Print working directory");
        System.out.println(" cd <dir>     - Change the current directory");
        System.out.println(" ls           - List directory contents");
        System.out.println(" ls -a        - List directory contents including hidden ones");
        System.out.println(" ls -r        - List directory contents in reverse order");
        System.out.println(" mkdir <dir>  - Create a directory");
        System.out.println(" rmdir <dir>  - Remove a directory");
        System.out.println(" touch <file> - Create an empty file");
        System.out.println(" mv           - Moves or renames files and directories");
        System.out.println(" rm <file>    - Remove a file");
        System.out.println(" cat <file>   - Display file contents");
        System.out.println(" >            - Redirects output to a file,overwriting existing content");
        System.out.println(" >>           - Appends output to the end of a file without overwriting");
        System.out.println(" |            - Sends the output of one command as input to another command");
        System.out.println(" exit         - Exit the CLI");
        System.out.println(" help         - Display this help message");
    }

}
