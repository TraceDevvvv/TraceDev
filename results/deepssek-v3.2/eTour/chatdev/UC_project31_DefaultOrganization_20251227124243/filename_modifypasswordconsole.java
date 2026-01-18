/**
 * Main entry point for the ModifyPassword console application.
 * This console-based application simulates a simple password change flow.
 * It reads current user details from a mock database, prompts for current password,
 * new password, and confirmation, validates inputs, and updates the password.
 */
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
public class ModifyPasswordConsole {
    // Mock database of users: username -> [password, agency_name]
    private static Map<String, String[]> users = new HashMap<>();
    static {
        // Initialize with a sample agency operator
        users.put("agency_op", new String[]{"old_pass123", "Global Logistics"});
    }
    /**
     * Validates the current password for the given username.
     * @param username The username of the logged-in agency operator.
     * @param currentPassword The entered current password.
     * @return true if the current password matches the stored one, false otherwise.
     */
    public static boolean validateCurrentPassword(String username, String currentPassword) {
        if (!users.containsKey(username)) {
            return false;
        }
        return users.get(username)[0].equals(currentPassword);
    }
    /**
     * Validates the new password based on typical security rules.
     * Rules: at least 8 characters, contains at least one digit and one letter.
     * @param newPassword The proposed new password.
     * @return true if the new password meets the criteria, false otherwise.
     */
    public static boolean validateNewPassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 8) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasLetter = false;
        for (char c : newPassword.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
            if (hasDigit && hasLetter) {
                break;
            }
        }
        return hasDigit && hasLetter;
    }
    /**
     * Updates the password for the given username in the mock database.
     * @param username The username whose password is to be updated.
     * @param newPassword The new password to set.
     * @return true if the update was successful, false otherwise.
     */
    public static boolean updatePassword(String username, String newPassword) {
        if (!users.containsKey(username)) {
            return false;
        }
        String[] userData = users.get(username);
        userData[0] = newPassword;
        users.put(username, userData);
        return true;
    }
    /**
     * Simulates the password change process.
     * This method handles the console interaction and orchestrates the steps.
     */
    public static void changePasswordProcess() {
        Scanner scanner = new Scanner(System.in);
        // Step 1: Assume the agency operator is logged in. Prompt for username.
        System.out.println("=== Modify Password for Agency Operator ===");
        System.out.print("Enter your username (e.g., 'agency_op'): ");
        String username = scanner.nextLine();
        if (!users.containsKey(username)) {
            System.out.println("Error: User not found. Exiting.");
            scanner.close();
            return;
        }
        // Step 2: Prompt for current password (simulating Step 5 and 6 from use case)
        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();
        if (!validateCurrentPassword(username, currentPassword)) {
            System.out.println("Error: Current password is incorrect. Exiting.");
            scanner.close();
            return;
        }
        // Step 3: Prompt for new password and confirmation (Step 7)
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        // Validate new password
        if (!validateNewPassword(newPassword)) {
            System.out.println("Error: New password must be at least 8 characters and contain at least one letter and one digit. Exiting.");
            scanner.close();
            return;
        }
        // Check that new password matches confirmation
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Error: New password and confirmation do not match. Exiting.");
            scanner.close();
            return;
        }
        // Step 4: Save changes (Step 8)
        boolean success = updatePassword(username, newPassword);
        if (success) {
            System.out.println("Success: Password updated successfully! (Exit condition: operation success)");
        } else {
            System.out.println("Error: Failed to update password. Possibly due to server interruption. (Exit condition: connection interruption)");
        }
        scanner.close();
    }
    public static void main(String[] args) {
        // Entry condition: agency has logged in (simulated by username input).
        changePasswordProcess();
    }
}