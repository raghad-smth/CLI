import java.util.Scanner;

public class Shell {

    private CLI cli;

    public Shell() {
        cli = new CLI(); // Initialize your CLI class
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Simple Shell! Type 'exit' to quit.");

        while (true) {
            System.out.print("shell> "); // Prompt for input
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the shell.");
                break; // Exit the loop if user types 'exit'
            }

            // Process the command
            String[] commands = input.split("\\|"); // Handle pipes
            for (String command : commands) {
                command = command.trim(); // Trim whitespace

                // Here you can add conditions to handle specific commands
                if (command.startsWith("rm ")) {
                    // Extract arguments and call rm method
                    String[] args = command.split(" ");
                    String result = cli.rm(args);
                    System.out.println(result);
                } else if (command.startsWith("cat ")) {
                    // Handle the cat command
                    String[] args = command.split(" ");
                    if (args.length < 2) {
                        System.out.println("Usage: cat <file>");
                        continue;
                    }
                    String fileName = args[1];
                    String result = cli.catToFile(fileName);
                    System.out.println(result);
                } else if (command.startsWith("redirect ")) {
                    // Handle redirect command
                    String[] parts = command.split(" ", 3);
                    if (parts.length < 3) {
                        System.out.println("Usage: redirect <command> <file>");
                        continue;
                    }
                    String result = cli.redirectTo(parts[1], parts[2]);
                    System.out.println(result);
                } else if (command.startsWith("append ")) {
                    // Handle append command
                    String[] parts = command.split(" ", 3);
                    if (parts.length < 3) {
                        System.out.println("Usage: append <command> <file>");
                        continue;
                    }
                    String result = cli.appendToFile(parts[1], parts[2]);
                    System.out.println(result);
                } else {
                    // If the command is not recognized
                    System.out.println("Command not recognized: " + command);
                }
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.start(); // Start the shell
    }
}
