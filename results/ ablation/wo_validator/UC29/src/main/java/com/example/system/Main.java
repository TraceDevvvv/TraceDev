package com.example.system;

/**
 * Main class to demonstrate the duplicate search tag handling system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Duplicate Search Tag Handling System Demo ===");
        
        // Create user interface
        UserInterface ui = new UserInterface();
        
        // Simulate user entering a duplicate tag
        System.out.println("\n--- Scenario 1: Duplicate Tag Entry ---");
        ui.enterSearchTag("existing_tag");
        
        // Simulate user entering a valid tag
        System.out.println("\n--- Scenario 2: Valid Tag Entry ---");
        ui.enterSearchTag("new_unique_tag");
        
        // Demonstrate command history
        System.out.println("\n--- Command History Demo ---");
        UseCaseController controller = UseCaseController.getInstance();
        CommandHistory history = new CommandHistory();
        SystemStateDAO dao = new SystemStateDAO();
        Command cmd = new RecoverPreviousStateCommand(dao);
        history.push(cmd);
        System.out.println("Command history empty? " + history.isEmpty());
        
        System.out.println("\n=== Demo Complete ===");
    }
}