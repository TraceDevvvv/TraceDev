package com.example;

import com.example.controller.PasswordChangeController;
import com.example.repository.DatabaseUserRepository;
import com.example.repository.IUserRepository;
import com.example.service.Argon2Hasher;
import com.example.service.IPasswordHasher;
import com.example.service.UserService;
import com.example.util.ConnectionMonitor;
import com.example.view.PasswordChangeForm;
import com.example.view.PasswordChangeView;

import java.util.Scanner;

/**
 * Main class to demonstrate the password change functionality.
 * It sets up the application context, wires dependencies, and simulates user interaction.
 */
public class Main {
    public static void main(String[] args) {
        // --- Application Setup ---
        // Instantiate utility/infrastructure components
        ConnectionMonitor connectionMonitor = new ConnectionMonitor();
        IPasswordHasher passwordHasher = new Argon2Hasher();
        DatabaseUserRepository userRepository = new DatabaseUserRepository(); // Concrete implementation
        PasswordChangeView passwordChangeView = new PasswordChangeView();

        // Instantiate service components, injecting dependencies
        UserService userService = new UserService(userRepository, passwordHasher, connectionMonitor);

        // Instantiate controller, injecting dependencies
        PasswordChangeController passwordChangeController = new PasswordChangeController(userService, passwordChangeView);

        // Simulate a logged-in user
        String currentUserId = "user123";
        Scanner appScanner = new Scanner(System.in);

        System.out.println("--- Password Change Application Demo ---");
        System.out.println("Current User ID: " + currentUserId);
        System.out.println("Initial password for 'testuser' (user123) is 'oldPass123'");


        // --- Scenario 1: Successful Password Change ---
        System.out.println("\n\n=== SCENARIO 1: Successful Password Change ===");
        runPasswordChangeScenario(passwordChangeController, passwordChangeView, currentUserId, appScanner,
                                  "oldPass123", "newPass456", "newPass456", false, false, false);

        // --- Scenario 2: New passwords do not match ---
        System.out.println("\n\n=== SCENARIO 2: New passwords do not match ===");
        runPasswordChangeScenario(passwordChangeController, passwordChangeView, currentUserId, appScanner,
                                  "newPass456", "wrongNewPass", "mismatchedNewPass", false, false, false);

        // --- Scenario 3: Incorrect old password ---
        System.out.println("\n\n=== SCENARIO 3: Incorrect old password ===");
        runPasswordChangeScenario(passwordChangeController, passwordChangeView, currentUserId, appScanner,
                                  "incorrectOld", "correctNew", "correctNew", false, false, false);

        // --- Scenario 4: User cancels operation ---
        System.out.println("\n\n=== SCENARIO 4: User cancels operation ===");
        System.out.println("Simulating user cancelling after initiatePasswordChange...");
        passwordChangeController.initiatePasswordChange(currentUserId);
        passwordChangeView.cancelPasswordChange(); // View indicates cancellation
        passwordChangeController.cancelOperation(currentUserId); // Controller handles the cancellation

        // --- Scenario 5: Connection loss during findById ---
        System.out.println("\n\n=== SCENARIO 5: Connection loss during findById ===");
        userRepository.setSimulateFindByIdConnectionLoss(true); // Simulate connection loss
        runPasswordChangeScenario(passwordChangeController, passwordChangeView, currentUserId, appScanner,
                                  "newPass456", "newerPass789", "newerPass789", true, false, false);
        userRepository.setSimulateFindByIdConnectionLoss(false); // Reset simulation

        // --- Scenario 6: Connection loss during update ---
        System.out.println("\n\n=== SCENARIO 6: Connection loss during update ===");
        // First, change password so oldPass456 is current and connection is fine
        runPasswordChangeScenario(passwordChangeController, passwordChangeView, currentUserId, appScanner,
                                  "newPass456", "tempPass", "tempPass", false, false, false);
        userRepository.setSimulateUpdateConnectionLoss(true); // Simulate connection loss
        connectionMonitor.simulateConnectionRestored(); // Ensure monitor thinks connection is up for new scenario
        runPasswordChangeScenario(passwordChangeController, passwordChangeView, currentUserId, appScanner,
                                  "tempPass", "finalPass", "finalPass", false, true, false);
        userRepository.setSimulateUpdateConnectionLoss(false); // Reset simulation

        // --- Scenario 7: User Not Found (using a non-existent ID) ---
        System.out.println("\n\n=== SCENARIO 7: User Not Found ===");
        runPasswordChangeScenario(passwordChangeController, passwordChangeView, "nonExistentUser", appScanner,
                                  "any", "any", "any", false, false, false);


        System.out.println("\n--- Demo End ---");
        passwordChangeView.closeScanner();
        appScanner.close();
    }

    /**
     * Helper method to run a specific password change scenario without user input.
     * @param controller The PasswordChangeController instance.
     * @param view The PasswordChangeView instance.
     * @param userId The ID of the user.
     * @param appScanner The scanner for general app input (not directly used by view in this helper).
     * @param oldPassword The old password to input.
     * @param newPassword The new password to input.
     * @param confirmNewPassword The confirmed new password to input.
     * @param expectFindByIdError True if ConnectionLostException is expected during findById.
     * @param expectUpdateError True if ConnectionLostException is expected during update.
     * @param simulateCancellation True if the user is simulated to cancel.
     */
    private static void runPasswordChangeScenario(
            PasswordChangeController controller,
            PasswordChangeView view,
            String userId,
            Scanner appScanner,
            String oldPassword,
            String newPassword,
            String confirmNewPassword,
            boolean expectFindByIdError,
            boolean expectUpdateError,
            boolean simulateCancellation) {

        System.out.println("  Simulating: " + (simulateCancellation ? "User cancellation" :
                           (expectFindByIdError ? "Connection loss during user retrieval" :
                            (expectUpdateError ? "Connection loss during user update" : "Normal flow")))
                           + " for user " + userId);
        System.out.println("  Input: Old='" + oldPassword + "', New='" + newPassword + "', Confirm='" + confirmNewPassword + "'");

        controller.initiatePasswordChange(userId);

        if (simulateCancellation) {
            view.cancelPasswordChange();
            controller.cancelOperation(userId);
            return;
        }

        // Create a form with simulated inputs
        PasswordChangeForm form = new PasswordChangeForm(oldPassword, newPassword, confirmNewPassword);
        controller.submitPasswordChange(userId, form);

        // Acknowledge the user prompt to continue (for better readability in console)
        // System.out.print("Press Enter to continue to next scenario...");
        // appScanner.nextLine();
    }
}