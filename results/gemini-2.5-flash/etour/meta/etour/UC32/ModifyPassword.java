package ModifyPassword_1765892656;

import java.util.Scanner;

/**
 * Represents the ModifyPassword use case for an Agency Operator.
 * This class simulates the process of an agency operator changing their password,
 * including input, validation, and saving changes.
 */
public class ModifyPassword {

    // Simulate a database or persistent storage for user credentials
    private static String currentPassword = "initialPassword123"; // Stored password
    private static boolean isLoggedIn = true; // Entry condition: Agency is logged in

    /**
     * Main method to run the ModifyPassword simulation.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entry condition check
        if (!isLoggedIn) {
            System.out.println("Error: Agency Operator is not logged in. Cannot proceed with password modification.");
            scanner.close();
            return;
        }

        System.out.println("--- Modify Password Use Case ---");
        System.out.println("Participating Actor: Agency Operator");
        System.out.println("Entry Condition: Agency is logged in.");

        // Step 5: Choose to change password by pressing the appropriate button (simulated)
        System.out.println("\nStep 5: Agency Operator chooses to change password.");
        System.out.println("Simulating button press for 'Change Password'...");

        // Step 6: Upload the form to change the password associated with that work agency (simulated)
        System.out.println("\nStep 6: Displaying password change form.");
        // In a real application, this would involve rendering a UI form.

        // Step 7: Change your password by entering the new choice and confirming
        String newPassword;
        String confirmPassword;
        boolean passwordChanged = false;

        while (!passwordChanged) {
            System.out.print("Enter new password: ");
            newPassword = scanner.nextLine();

            System.out.print("Confirm new password: ");
            confirmPassword = scanner.nextLine();

            if (validatePassword(newPassword, confirmPassword)) {
                // Step 8: Save your changes
                System.out.println("\nStep 8: Saving changes...");
                if (saveNewPassword(newPassword)) {
                    System.out.println("Exit Condition: System confirms success of operation.");
                    System.out.println("Password changed successfully!");
                    passwordChanged = true;
                } else {
                    // This branch would typically be for database errors or similar
                    System.out.println("Error: Failed to save new password due to a system issue. Please try again.");
                }
            } else {
                System.out.println("Password mismatch or invalid password. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Validates the new password and its confirmation.
     * This method includes basic validation rules.
     *
     * @param newPass The new password entered by the user.
     * @param confirmPass The confirmation of the new password.
     * @return true if passwords match and meet complexity requirements, false otherwise.
     */
    private static boolean validatePassword(String newPass, String confirmPass) {
        // Check if passwords match
        if (!newPass.equals(confirmPass)) {
            System.out.println("Validation Error: New password and confirmation do not match.");
            return false;
        }

        // Basic complexity requirements:
        // - Minimum length of 8 characters
        // - At least one uppercase letter
        // - At least one lowercase letter
        // - At least one digit
        // - At least one special character (e.g., !@#$%^&*)
        if (newPass.length() < 8) {
            System.out.println("Validation Error: Password must be at least 8 characters long.");
            return false;
        }
        if (!newPass.matches(".*[A-Z].*")) {
            System.out.println("Validation Error: Password must contain at least one uppercase letter.");
            return false;
        }
        if (!newPass.matches(".*[a-z].*")) {
            System.out.println("Validation Error: Password must contain at least one lowercase letter.");
            return false;
        }
        if (!newPass.matches(".*\\d.*")) {
            System.out.println("Validation Error: Password must contain at least one digit.");
            return false;
        }
        if (!newPass.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            System.out.println("Validation Error: Password must contain at least one special character.");
            return false;
        }

        // In a real system, you might also check against common passwords or previous passwords.
        return true;
    }

    /**
     * Simulates saving the new password to persistent storage.
     * In a real application, this would involve hashing the password and
     * updating a database record.
     *
     * @param newPass The new password to be saved.
     * @return true if the password was successfully "saved", false if a simulated error occurs.
     */
    private static boolean saveNewPassword(String newPass) {
        // Simulate a server connection interruption or database error
        // For demonstration, let's say there's a 10% chance of failure
        if (Math.random() < 0.1) {
            System.out.println("Simulated Error: Interruption of the connection to the server or database error.");
            return false;
        }

        // In a real scenario, hash the password before storing
        // For this simulation, we just update the static variable
        currentPassword = newPass;
        return true;
    }
}