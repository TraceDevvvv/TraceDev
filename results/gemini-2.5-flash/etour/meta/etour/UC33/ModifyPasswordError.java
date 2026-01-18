import java.util.Scanner;

/**
 * Represents the system's interaction for handling a password modification error.
 * This class simulates the flow of events described in the "ModifyPasswordError" use case.
 */
public class ModifyPasswordError {

    private Scanner scanner; // Scanner for user input

    /**
     * Constructor for ModifyPasswordError.
     * Initializes the scanner for reading user input.
     */
    public ModifyPasswordError() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the process of a user attempting to change a password,
     * encountering an error, and then returning to the password change screen.
     */
    public void simulatePasswordChangeError() {
        System.out.println("--- Password Change Process ---");
        System.out.println("Entry condition: User has pressed the button to confirm password change.");

        // Simulate an incorrect password confirmation
        boolean passwordConfirmedCorrectly = false; // This would typically come from a validation logic

        if (!passwordConfirmedCorrectly) {
            // Step 4: Notify an error message.
            displayErrorMessage("Error: The new password was not confirmed correctly. Please try again.");

            // Step 5: Confirmation of the reading of the notification.
            waitForUserAcknowledgement();

            // Step 6: Back to change your password.
            returnToPasswordChangeScreen();
        } else {
            // This path would be taken if the password was confirmed correctly
            System.out.println("Password changed successfully!");
        }

        System.out.println("Exit condition: System returns control to the user interaction.");
        System.out.println("--- End of Password Change Process ---");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    private void displayErrorMessage(String message) {
        System.out.println("\n[SYSTEM NOTIFICATION] " + message);
    }

    /**
     * Waits for the user to acknowledge the notification by pressing Enter.
     */
    private void waitForUserAcknowledgement() {
        System.out.println("Press Enter to acknowledge the error message...");
        scanner.nextLine(); // Consume the newline character
        System.out.println("Notification acknowledged.");
    }

    /**
     * Simulates returning the user to the password change screen.
     * In a real application, this would involve navigating to a different UI component or view.
     */
    private void returnToPasswordChangeScreen() {
        System.out.println("\nReturning to the 'Change Your Password' screen...");
        // In a real application, this would involve UI navigation logic.
        // For this simulation, we just print a message.
    }

    /**
     * Closes the scanner resource.
     * It's good practice to close resources when they are no longer needed.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Main method to run the simulation.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        ModifyPasswordError passwordErrorSimulator = new ModifyPasswordError();
        try {
            passwordErrorSimulator.simulatePasswordChangeError();
        } finally {
            // Ensure the scanner is closed even if an exception occurs
            passwordErrorSimulator.closeScanner();
        }
    }
}