import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CLI {
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
                    return "Error! Failed to delete " + args[i];
                }
            }
        }
        return "Success! Files and/or directories deleted.";
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
            return "Success!" + fileName;
        } catch (IOException e) {
            return "Error! unable to write to file " + fileName + ": " + e.getMessage();
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
            return "Error: unable to write to file " + fileName + ": " + e.getMessage();
        }
    }

    //">>" method
    public String appendToFile(String command, String fileName) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file, true)) {  // 'true' enables appending mode
            writer.write(command + System.lineSeparator());
            return "Success!" + fileName;
        } catch (IOException e) {
            return "Error! unable to write to file " + fileName + ": " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        String commandLine = "Hello world"; // Example command for Windows
        executePipedCommands(commandLine);
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

}
