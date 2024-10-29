import java.util.Scanner;

public class Shell {

    private CLI cli;

    public Shell() {
        cli = new CLI(); 
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Simple Shell! type 'exit' to quit.");

        while (true) {
            System.out.print("shell> "); 
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the shell . . Goodbye!");
                break; 
            }

            // Process the command
            String[] commands = input.split("\\|"); 
            for (String command : commands) {
                command = command.trim(); // Trim whitespace
                
                if (command.startsWith("rm ")) {
                    // Extract arguments and call rm method
                    String[] args = command.split(" ");
                    String result = cli.rm(args);
                    System.out.println(result);
                } else if (command.startsWith("cat ")) {
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
        shell.start(); 
    }
}
