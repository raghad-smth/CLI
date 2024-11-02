package main;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


import java.io.*;




public class CLI {
    

    // List of valid commands
    private static final List<String> VALID_COMMANDS = Arrays.asList("Is-a", "Is-r", "mkdir","pwd", "Help", "Exit", "List", "Open");

    //function to help in misspelled cases: 
    public static boolean isProbablyMisspelled(String command) {

        // no command is less than one so it is probaly misspelled 
        if (command.length() < 1) {
            return true; 
        }
    
        // Loop through each valid command
        for (String validCommand : VALID_COMMANDS) {
            if (validCommand.equals(command)) {
                return false; // Exact match, not misspelled
            }
         
        }
     
        return true; 
    }
    
    // Is-a: listing all files in the current directory
    public static String Is_a(String command) {
        if (command.equals("Is-a")) {
            String currentDirectory = System.getProperty("user.dir");
            File directory = new File(currentDirectory);
            File[] filesList = directory.listFiles();
            StringBuilder result = new StringBuilder();

            if (filesList != null) {
                result.append("Files and directories in the current directory:").append(System.lineSeparator());
                for (File file : filesList) {
                    result.append(file.getName()).append(System.lineSeparator());
                }
            } else {
                result.append("The directory is empty or an I/O error occurred.").append(System.lineSeparator());
            }
            return result.toString();
        } else 
            return "Did you mean Is-a?";
        
    }

    // Is-r: listing all files in a given directory in descending order according to
    public static String ls_r(String command, String directory) {
        // Check for spelling mistakes
        if (!isProbablyMisspelled(command)) {
           
            if (directory.trim().isEmpty()) {
                directory = System.getProperty("user.dir");
            }

            File dir = new File(directory);
            // Check if the directory exists and is indeed a directory
            if (dir.exists() && dir.isDirectory()) {
                File[] filesList = dir.listFiles();
                StringBuilder result = new StringBuilder();

                // Append the header
                result.append("Files and directories in the specified directory in reverse:\n");

                // Check if the directory is not empty
                if (filesList != null && filesList.length > 0) {
                    // Sorting files in reverse order to print them reversed
                    Arrays.sort(filesList, Comparator.comparing(File::getName).reversed());
                    for (File file : filesList) {
                        // Append the name of each file or directory
                        result.append(file.getName()).append("\n");
                    }
                } else {
                    result.append("The directory is empty.\n");
                }
                return result.toString();
            } else {
                return "Directory doesn't exist.";
            }
        } else {
            return "Did you mean `Is-r`?";
        }
    }

    // mkdir: Creates a new directory
    public static String mkdir(String command, String directoryName) {
        // Create a File object with the specified directory name
        File directory = new File(directoryName);
        StringBuilder result = new StringBuilder();

        // Create the directory
        if (!isProbablyMisspelled(command)) {
            //user entered correct command but didn't provid any name for the directory
            if(directoryName.isEmpty()){
                result.append("Directory name must be provided");
            }
            else if (directory.exists()) {
                result.append("Directory already exists at: ").append(directory.getAbsolutePath()).append("\n");
            } else if (directory.mkdir()) {
                result.append("Directory successfully created: ").append(directory.getName()).append("\n");
                result.append("Absolute path: ").append(directory.getAbsolutePath()).append("\n");
            } 
        }
            
        else if((isProbablyMisspelled(command))){
                result.append("Did you mean mkdir?\n");
            }
            
        else{
                result.append("Failed to create the directory.\n");
            }
         
        
    
        return result.toString();
    }
    // help : Displays all the commands that the user can use with a summary about
    // each command
    public static String help(String h, String command) {

        if (!isProbablyMisspelled("help") || command == "" ) {
            String helpInfo = "GNU bash, version 5.0.17(1)-release (x86_64-pc-linux-gnu)\n" + //
                    "These shell commands are defined internally. Type help' to see this list.\n" + //
                    "Type help name' to find out more about the function name'.\n" + //
                    "\n" + //
                    "  alias    bg       bind      break   builtin   caller  cd        command  compgen\n" + //
                    "  complete compopt  continue  declare dirs      disown  echo      enable   eval\n" + //
                    "  exec     exit     export    false   fc        fg      getopts   hash     help\n" + //
                    "  history  jobs     kill      let     local     logout  mapfile  popd     printf\n" + //
                    "  pushd    pwd      read      readarray readonly return  set      shift    shopt\n" + //
                    "  source   suspend  test      times   trap      true    type     typeset  ulimit\n" + //
                    "  umask    unalias  unset     wait    while\n" + //
                    "\n" + //
                    "Type help followed by a built-in command name to display information about that command.";
            return helpInfo;
        }
        
        // Infromations for each command
        if(!command.isEmpty()){
          if (command.equals("cd")) {
            return "cd: cd [dir] Change the shell working directory.";
        } else if (command.equals("alias")) {
            return "alias: alias [name[=value] ... ]Define or display aliases.";
        } else if (command.equals("bg")) {
            return "bg: bg [job_spec ... ] Resume suspended jobs in the background.";
        } else if (command.equals("bind")) {
            return "bind: bind [-lpvsPVS] [-m keymap] [-f file] [-q name] [-u function] [-r keyseq] Display or modify key bindings.";
        } else if (command.equals("break")) {
            return "break: break [n] Exit from within a for, while, or until loop.";
        } else if (command.equals("builtin")) {
            return "builtin: builtin [shell-builtin [arg ...]]  Run a shell builtin, passing it arguments.";
        } else if (command.equals("caller")) {
            return "caller: caller [expr] Return the context of the current subroutine call.";
        } else if (command.equals("command")) {
            return "command: command [-pVv] command [arg ...]  Run a command without applying aliases.";
        } else if (command.equals("compgen")) {
            return "compgen: compgen [option] [word] Generate possible completion matches.";
        } else if (command.equals("complete")) {
            return "complete: complete [-abcdefgjksuv] [-pr] [-DE] [-o option] [-A action] [-G glob] [-W wordlist]  Specify how arguments are to be completed by the readline library.";
        } else if (command.equals("continue")) {
            return "continue: continue [n] Resume the next iteration of a for, while, or until loop.";
        } else if (command.equals("declare")) {
            return "declare: declare [-aAfFgilnrtux] [-p] [name[=value] ...] Declare variables and give them attributes.";
        } else if (command.equals("dirs")) {
            return "dirs: dirs [-clpv] [+N] [-N] Display directory stack.";
        } else if (command.equals("disown")) {
            return "disown: disown [-h] [-ar] [jobspec ...] Remove jobs from the job table.";
        } else if (command.equals("echo")) {
            return "echo: echo [arg ...] Write arguments to standard output.";
        } else if (command.equals("enable")) {
            return "enable: enable [-a] [-dnps] [-f filename] [name ...]Enable and disable shell builtins.";
        } else if (command.equals("exit")) {
            return "exit: exit [n]Exit the shell.";
        } else if (command.equals("export")) {
            return "export: export [-fn] [name[=value] ...] or export -p Set export attribute for variables and functions.";
        } else if (command.equals("false")) {
            return "false: false Do nothing, unsuccessfully.";
        } else if (command.equals("fg")) {
            return "fg: fg [job_spec] Place a job in the foreground.";
        } else if (command.equals("getopts")) {
            return "getopts: getopts optstring name [args] Parse positional parameters.";
        } else if (command.equals("hash")) {
            return "hash: hash [-lr] [-p pathname] [-dt] [name ...] Remember or display program locations.";
        } else if (command.equals("help")) {
            return "help: help [-dms] [pattern ...] Display information about built-in commands.";
        } else if (command.equals("history")) {
            return "history: history [-c] [-d offset] [n] or history -anrw [filename] or history -ps arg [arg ...] Display or manipulate the history list.";
        } else if (command.equals("jobs")) {
            return "jobs: jobs [-lnprs] [jobspec ...] or jobs -x command [args] Display status of jobs.";
        } else if (command.equals("kill")) {
            return "kill: kill [-s sigspec | -n signum | -sigspec] [pid | jobspec ...] or kill -l [sigspec] Send a signal to a job.";
        } else if (command.equals("let")) {
            return "let: let arg [arg ...]  Evaluate arithmetic expressions.";
        } 
    }
            return "No help available for: " + command;
        

    }

    public  String moveFile(String sourcePath, String destinationPath) {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        // Check if the destination path is a directory
        if (Files.isDirectory(destination)) {
            // Append the original filename to the destination directory
            destination = destination.resolve(source.getFileName());
        }

        try {
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return "File moved successfully to " + destination.toString();
        } catch (NoSuchFileException e) {
            return "Error: Source file not found.";
        } catch (FileAlreadyExistsException e) {
            return "Error: Destination file already exists.";
        } catch (IOException e) {
            return "Error: Unable to move the file due to an I/O error.";
        }
    }
 
    public String touchFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            File file = path.toFile();
            if (file.exists()) {
                // Update the last modified time
                file.setLastModified(System.currentTimeMillis());
                return "File timestamp updated successfully.";
            } else {
                // Create a new file
                Files.createFile(path);
                return "File created successfully.";
            }
        } catch (IOException e) {
            return "Error: Unable to create or update the file due to an I/O error.";
        } catch (SecurityException e) {
            return "Error: Permission denied.";
        }
    }
    
    public static String removeDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return "Error: Directory does not exist.";
        }
        if (!dir.isDirectory()) {
            return "Error: Specified path is not a directory.";
        }
        if (dir.list().length > 0) {
            return "Error: Directory is not empty.";
        }
        if (dir.delete()) {
            return "Directory removed successfully.";
        } else {
            return "Error: Failed to remove directory.";
        }
    }
   
    public String pwd() {

        File currentDirectory =  new File(System.getProperty("user.dir")); 
        return currentDirectory.getAbsolutePath(); // Start in the user's current directory
    }
        
    public String cd(String path) {
        File currentDirectory =  new File(System.getProperty("user.dir")); 
    
        File newDirectory = new File(currentDirectory, path);
        if (newDirectory.isDirectory()) {
            currentDirectory = newDirectory;
            return "Changed directory to: " + currentDirectory.getAbsolutePath();
        } else {
            return "Error: Directory does not exist.";
        }
    }

    public String ls(String... options) {
            File currentDirectory =  new File(System.getProperty("user.dir")); 
        String[] items = currentDirectory.list();
        
        if (items == null) {
            return "Error: Unable to list directory contents.";
        }

        boolean showHidden = Arrays.asList(options).contains("-a");
        boolean reverseOrder = Arrays.asList(options).contains("-r");

        if (!showHidden) {
            items = Arrays.stream(items)
                          .filter(item -> !item.startsWith("."))
                          .toArray(String[]::new);
        }

        if (reverseOrder) {
            Arrays.sort(items, Comparator.reverseOrder());
        } else {
            Arrays.sort(items);
        }

        return String.join("\n", items);
    }
    // rm method
    public String rm(String[] args) {
        if (args.length == 0) {
            return "Usage: rm [-r] <file/directory>";
        }

        boolean recursive = false;
        int startIndex = 0;

        // Check for -r flag
        if (args[0].equals("-r")) {
            recursive = true;
            startIndex = 1;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = startIndex; i < args.length; i++) {
                File file = new File(args[i]);

                if (!file.exists()) {
                    return "Error: " + args[i] + " does not exist.";
                }

                // Check if file is a directory and if -r flag is missing
                if (file.isDirectory() && !recursive) {
                    return "Error: " + args[i] + " is a directory. Use -r to remove directories.";
                }

                if (!file.canWrite()) {
                    System.out.println("File " + args[i] + " is unwritable. Confirm deletion (y/n): ");
                    String response = scanner.nextLine();
                    if (!response.equalsIgnoreCase("y")) {
                        continue;
                    }
                }

                if (!deleteFile(file, recursive)) {
                    return "Error: Failed to delete " + args[i];
                }
            }
        }
        return "Success: Files and/or directories deleted.";
    }  
    // Helper method to delete file or directory recursively
    private boolean deleteFile(File file, boolean recursive) {
            if (file.isDirectory() && recursive) {
                File[] contents = file.listFiles();
                if (contents != null) {
                    for (File f : contents) {
                        deleteFile(f, true);
                    }
                }
            }
            return file.delete();
        }
    // ">" method
    public String redirectTo(String command, String fileName) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(command);
            return "Success: Output redirected to " + fileName;
        } catch (IOException e) {
            return "Error: Unable to write to file " + fileName + ": " + e.getMessage();
        }
    }
    // "cat > file" method
    public String catToFile(String fileName) {
            File file = new File(fileName);
            try (FileWriter writer = new FileWriter(file);
                 Scanner scanner = new Scanner(System.in)) {
                System.out.println("Enter text (Press Ctrl+D or type 'EOF' to finish):");
    
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.equals("EOF")) {
                        break;
                    }
                    writer.write(line + System.lineSeparator());
                }
                return "Success: Input saved to " + fileName;
            } catch (IOException e) {
                return "Error: Unable to write to file " + fileName + ": " + e.getMessage();
            }
        }
    //">>" method
    public String appendToFile(String command, String fileName) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file, true)) {  // 'true' enables appending mode
            writer.write(command + System.lineSeparator());
            return "Success!" + fileName;
        } catch (IOException e) {
            return "Error! Unable to write to file " + fileName + ": " + e.getMessage();
        }
    }
    //"pip" method
    public static void executePipedCommands(String commandLine) {
        String[] commands = commandLine.split("\\|"); // Split on pipe

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands[0].trim().split(" "));
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void existCommand (String command){
        while (true) {
        if (command.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the shell . . Goodbye!");
            break;
        }
    }
    }
}
