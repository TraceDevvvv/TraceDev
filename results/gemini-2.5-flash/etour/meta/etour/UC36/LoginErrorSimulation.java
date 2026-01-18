package LoginError_1765950515;

import java.util.Scanner;

/**
 * This class simulates a login error scenario as described in the use case.
 * It demonstrates how a system might handle incorrect login data,
 * notify the user, confirm the notification reading, and recover to a previous state.
 */
public class LoginErrorSimulation {

    // Represents the previous state before the login attempt.
    // In a real application, this could be a complex object representing
    // the application's UI state, data, etc. For this simulation, a simple string suff.
    private static String previousState = "Application_Initial_State";

    /**
     * Main method to run the login error simulation.
     *
     * @param args Command line arguments (not used in this simulation).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Login Error Simulation Started ---");
        System.out.println("Current State: " + previousState);

        // Simulate an incorrect login attempt
        simulateLoginAttempt(scanner);

        System.out.println("--- Login Error Simulation Finished ---");
        scanner.close();
    }

    /**
     * Simulates a login attempt. If the login data is incorrect (which it always is
     * in this simulation to trigger the error), it proceeds with the error handling flow.
     *
     * @param scanner The Scanner object to read user input.
     */
    private static void simulateLoginAttempt(Scanner scanner) {
        System.out.println("\nAttempting to log in with incorrect data...");

        // Step 1: Notice that the data entered for the login is not valid and asks for confirmation of the reading.
        displayLoginErrorNotification();

        // Step 2: Confirmation of the reading of the notification.
        confirmNotificationReading(scanner);

        // Step 3: Recovers the previous state.
        recoverPreviousState();
    }

    /**
     * Displays a notification to the user indicating that the login data is invalid.
     */
    private static void displayLoginErrorNotification() {
        System.out.println("\n--- SYSTEM NOTIFICATION ---");
        System.out.println("Error: The username or password you entered is incorrect.");
        System.out.println("Please check your credentials and try again.");
        System.out.println("---------------------------");
    }

    /**
     * Prompts the user to confirm that they have read the notification.
     * It waits for the user to type 'ok' (case-insensitive) to proceed.
     *
     * @param scanner The Scanner object to read user input.
     */
    private static void confirmNotificationReading(Scanner scanner) {
        String confirmation;
        do {
            System.out.print("Please type 'ok' to confirm you have read this notification: ");
            confirmation = scanner.nextLine();
            if (!confirmation.equalsIgnoreCase("ok")) {
                System.out.println("Invalid input. Please type 'ok' to confirm.");
            }
        } while (!confirmation.equalsIgnoreCase("ok"));
        System.out.println("Notification confirmed. Proceeding...");
    }

    /**
     * Recovers the system to its previous state.
     * In this simulation, it simply prints a message indicating the recovery
     * and shows the restored state.
     */
    private static void recoverPreviousState() {
        System.out.println("\n--- SYSTEM RECOVERY ---");
        System.out.println("Recovering to previous state...");
        // In a real application, this would involve restoring UI elements,
        // clearing input fields, resetting flags, etc.
        System.out.println("Previous state (" + previousState + ") has been restored.");
        System.out.println("You can now attempt to log in again or navigate elsewhere.");
        System.out.println("-----------------------");
    }
}