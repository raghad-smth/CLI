import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CLI {
    // Method to execute the rm command
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

        try (Scanner scanner = new Scanner(System.in)) { // Close scanner after use
            for (int i = startIndex; i < args.length; i++) {
                File file = new File(args[i]);

                if (!file.exists()) {
                    return "Error: " + args[i] + " does not exist.";
                }

                // Check if file is a directory and if -r flag is missing
                if (file.isDirectory() && !recursive) {
                    return "Error: " + args[i] + " is a directory. Use -r to remove directories.";
                }

                // Check if file is writable
                if (!file.canWrite()) {
                    System.out.println("File " + args[i] + " is unwritable. Confirm deletion (y/n): ");
                    String response = scanner.nextLine();
                    if (!response.equalsIgnoreCase("y")) {
                        continue;
                    }
                }

                // Remove file or directory
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

    // Method to execute the redirection command ">"
    public String redirectTo(String command, String fileName) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(command);
            return "Success: Output redirected to " + fileName;
        } catch (IOException e) {
            return "Error: Unable to write to file " + fileName + ": " + e.getMessage();
        }
    }

    // Method to execute "cat > file" for user input
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

    // Method to execute the append command ">>"
    public String appendToFile(String command, String fileName) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file, true)) {  // 'true' enables appending mode
            writer.write(command + System.lineSeparator());
            return "Success: Output appended to " + fileName;
        } catch (IOException e) {
            return "Error: Unable to write to file " + fileName + ": " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        // Example command; replace with dynamic input as needed
        String commandLine = "echo Hello"; // Example command for Windows
        executePipedCommands(commandLine);
    }

    public static void executePipedCommands(String commandLine) {
        String[] commands = commandLine.split("\\|"); // Split on pipe

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands[0].trim().split(" "));
            Process process = processBuilder.start();

            // Print output from the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
