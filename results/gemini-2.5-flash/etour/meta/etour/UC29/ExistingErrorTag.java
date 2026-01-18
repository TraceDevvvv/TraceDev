import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Represents the system's state, including existing tags and the current user interaction state.
 */
class SystemState {
    private Set<String> existingTags;
    private String previousInput; // Stores the input before an error occurred

    /**
     * Initializes the SystemState with some predefined tags.
     */
    public SystemState() {
        existingTags = new HashSet<>();
        existingTags.add("java");
        existingTags.add("programming");
        existingTags.add("software");
        this.previousInput = ""; // Initialize with an empty string
    }

    /**
     * Checks if a given tag already exists in the system.
     *
     * @param tag The tag to check.
     * @return true if the tag exists, false otherwise.
     */
    public boolean tagExists(String tag) {
        return existingTags.contains(tag.toLowerCase()); // Case-insensitive check
    }

    /**
     * Adds a new tag to the system.
     *
     * @param tag The tag to add.
     */
    public void addTag(String tag) {
        existingTags.add(tag.toLowerCase());
    }

    /**
     * Sets the previous user input. This is used to recover the state.
     *
     * @param input The input string to save.
     */
    public void setPreviousInput(String input) {
        this.previousInput = input;
    }

    /**
     * Retrieves the previous user input.
     *
     * @return The previously saved input string.
     */
    public String getPreviousInput() {
        return previousInput;
    }
}

/**
 * Handles user interaction and system responses for tag management.
 */
class UserInteractionHandler {
    private Scanner scanner;
    private SystemState systemState;

    /**
     * Initializes the UserInteractionHandler with a SystemState.
     *
     * @param systemState The current state of the system.
     */
    public UserInteractionHandler(SystemState systemState) {
        this.scanner = new Scanner(System.in);
        this.systemState = systemState;
    }

    /**
     * Simulates the process of a user entering a tag.
     * This method implements the core flow of events for the ExistingErrorTag use case.
     */
    public void enterTag() {
        System.out.println("Please enter a search tag (type 'exit' to quit):");
        String userInput = scanner.nextLine();

        // Save the current input as previous state before processing
        systemState.setPreviousInput(userInput);

        if (userInput.equalsIgnoreCase("exit")) {
            System.out.println("Exiting tag entry. Goodbye!");
            return;
        }

        // Check if the tag already exists
        if (systemState.tagExists(userInput)) {
            // 1. Notice the error message and asks for confirmation of its reading.
            System.out.println("Error: The tag '" + userInput + "' already exists in the system.");
            System.out.println("Please confirm you have read this message by typing 'ok':");

            String confirmation = scanner.nextLine();

            // 2 Confirmation of the reading of the notification.
            if (confirmation.equalsIgnoreCase("ok")) {
                System.out.println("Confirmation received. Returning to previous state.");
                // 3 Recovers the previous state.
                // In this simple example, recovering the previous state means
                // informing the user about the last valid input or action,
                // and then allowing them to try again.
                System.out.println("Previous input was: '" + systemState.getPreviousInput() + "' (which caused the error).");
                System.out.println("Please try entering a different tag.");
            } else {
                System.out.println("Confirmation not received. Please type 'ok' to acknowledge the error.");
                // If confirmation is not 'ok', we still recover the state by prompting again.
                System.out.println("Previous input was: '" + systemState.getPreviousInput() + "' (which caused the error).");
                System.out.println("Please try entering a different tag.");
            }
        } else {
            // If the tag does not exist, add it to the system
            systemState.addTag(userInput);
            System.out.println("Tag '" + userInput + "' added successfully.");
        }
        // Exit conditions: The system returns control to the user interaction.
        // This is implicitly handled by the loop in the main method.
    }

    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        scanner.close();
    }
}

/**
 * Main class to run the ExistingErrorTag use case simulation.
 */
public class ExistingErrorTag {
    public static void main(String[] args) {
        SystemState systemState = new SystemState();
        UserInteractionHandler uiHandler = new UserInteractionHandler(systemState);

        System.out.println("Welcome to the Tag Management System!");

        // Loop to allow multiple tag entries until the user decides to exit
        while (true) {
            // The enterTag method handles the entire flow for one tag entry,
            // including error handling and state recovery.
            uiHandler.enterTag();

            // Check if the user wants to exit based on the last input
            if (systemState.getPreviousInput().equalsIgnoreCase("exit")) {
                break; // Exit the loop
            }
            System.out.println("\n--- Ready for next action ---");
        }

        uiHandler.closeScanner();
        System.out.println("Application terminated.");
    }
}