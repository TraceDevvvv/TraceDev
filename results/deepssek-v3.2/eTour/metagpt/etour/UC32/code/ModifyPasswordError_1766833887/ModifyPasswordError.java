import java.util.Scanner;

/**
 * This program simulates the "ModifyPasswordError" use case.
 * It demonstrates a scenario where a user attempts to change their password,
 * but the confirmation fails, leading to error notification and returning
 * to the password change screen.
 * 
 * The program includes:
 * - Password validation and confirmation logic
 * - Error notification mechanism
 * - User interaction for acknowledging error and retrying
 * - Edge case handling (null inputs, mismatched passwords, etc.)
 */
public class ModifyPasswordError {
    
    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);
    
    /**
     * Main entry point of the program.
     * Simulates the password change flow with error handling.
     */
    public static void main(String[] args) {
        System.out.println("=== Password Change System ===");
        
        // Simulate entry condition: button to confirm password change has been pressed
        System.out.println("\nPassword change confirmation initiated...");
        
        // Start the password change flow
        boolean passwordChangedSuccessfully = false;
        
        // Loop until password is changed successfully or user exits
        while (!passwordChangedSuccessfully) {
            try {
                passwordChangedSuccessfully = attemptPasswordChange();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Returning to password change screen...\n");
            }
        }
        
        System.out.println("\n=== Password Change Completed ===");
        scanner.close();
    }
    
    /**
     * Attempts to change the password by following the use case flow.
     * 
     * @return true if password was changed successfully, false if error occurred
     */
    private static boolean attemptPasswordChange() {
        // Step 1: Get new password from user
        String newPassword = getNewPassword();
        
        // Step 2: Get confirmation password from user
        String confirmPassword = getConfirmationPassword();
        
        // Step 3: Validate passwords
        ValidationResult validationResult = validatePasswords(newPassword, confirmPassword);
        
        if (validationResult.isValid()) {
            // Success case - passwords match and are valid
            System.out.println("\n✓ Password changed successfully!");
            return true;
        } else {
            // Error case - passwords don't match or validation failed
            // Step 4: Notify an error message (as per flow step 4)
            notifyErrorMessage(validationResult.getErrorMessage());
            
            // Step 5: Confirmation of the reading of the notification (flow step 5)
            boolean notificationRead = confirmNotificationRead();
            
            if (!notificationRead) {
                // User didn't confirm reading - treat as they want to exit
                System.out.println("Returning to main menu...");
                return false; // Could also exit or handle differently based on requirements
            }
            
            // Step 6: Back to change your password (flow step 6)
            System.out.println("\nReturning to password change screen...\n");
            return false; // Indicates password change not successful, retry
        }
    }
    
    /**
     * Prompts user to enter a new password.
     * 
     * @return the entered new password
     */
    private static String getNewPassword() {
        System.out.print("\nEnter new password: ");
        String password = scanner.nextLine();
        
        // Handle empty input
        while (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty. Please try again.");
            System.out.print("Enter new password: ");
            password = scanner.nextLine();
        }
        
        return password;
    }
    
    /**
     * Prompts user to confirm the new password.
     * 
     * @return the entered confirmation password
     */
    private static String getConfirmationPassword() {
        System.out.print("Confirm new password: ");
        String password = scanner.nextLine();
        
        // Handle empty input
        while (password == null || password.trim().isEmpty()) {
            System.out.println("Confirmation password cannot be empty. Please try again.");
            System.out.print("Confirm new password: ");
            password = scanner.nextLine();
        }
        
        return password;
    }
    
    /**
     * Validates the new password and confirmation password.
     * 
     * @param newPassword the new password entered
     * @param confirmPassword the confirmation password entered
     * @return ValidationResult containing validation status and error message if any
     */
    private static ValidationResult validatePasswords(String newPassword, String confirmPassword) {
        // Check for null values (defensive programming)
        if (newPassword == null || confirmPassword == null) {
            return new ValidationResult(false, "Passwords cannot be null");
        }
        
        // Trim passwords for validation
        String trimmedNew = newPassword.trim();
        String trimmedConfirm = confirmPassword.trim();
        
        // Check if passwords match
        if (!trimmedNew.equals(trimmedConfirm)) {
            return new ValidationResult(false, "Passwords do not match. Please try again.");
        }
        
        // Additional password strength validation (optional)
        if (!isPasswordStrong(trimmedNew)) {
            return new ValidationResult(false, 
                "Password is too weak. It must be at least 8 characters long and contain both letters and numbers.");
        }
        
        // All validations passed
        return new ValidationResult(true, null);
    }
    
    /**
     * Checks if a password meets strength requirements.
     * 
     * @param password the password to check
     * @return true if password is strong enough, false otherwise
     */
    private static boolean isPasswordStrong(String password) {
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasLetter = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            
            if (hasLetter && hasDigit) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Displays an error message to the user (flow step 4).
     * 
     * @param errorMessage the error message to display
     */
    private static void notifyErrorMessage(String errorMessage) {
        System.out.println("\n✗ ERROR: " + errorMessage);
        System.out.println("═══════════════════════════════════════");
    }
    
    /**
     * Confirms that the user has read the error notification (flow step 5).
     * 
     * @return true if user confirms reading, false if user wants to cancel
     */
    private static boolean confirmNotificationRead() {
        System.out.println("\nPlease acknowledge that you have read the error message.");
        System.out.print("Enter 'Y' to continue or 'N' to cancel: ");
        
        String response;
        while (true) {
            response = scanner.nextLine().trim().toUpperCase();
            
            if (response.equals("Y") || response.equals("YES")) {
                System.out.println("Acknowledgment received. Continuing...");
                return true;
            } else if (response.equals("N") || response.equals("NO")) {
                System.out.println("Acknowledgment cancelled.");
                return false;
            } else {
                System.out.print("Invalid input. Please enter 'Y' to continue or 'N' to cancel: ");
            }
        }
    }
    
    /**
     * Helper class to encapsulate password validation results.
     */
    private static class ValidationResult {
        private final boolean valid;
        private final String errorMessage;
        
        public ValidationResult(boolean valid, String errorMessage) {
            this.valid = valid;
            this.errorMessage = errorMessage;
        }
        
        public boolean isValid() {
            return valid;
        }
        
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}