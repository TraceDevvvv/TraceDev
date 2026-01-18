import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This program simulates the password modification flow for an Agency Operator.
 * It follows the use case: ModifyPassword.
 * The program handles password validation, server connection simulation, and edge cases.
 */
public class PasswordModifier {
    
    /**
     * Represents an Agency Operator with login status and password.
     */
    static class AgencyOperator {
        private String username;
        private String password;
        private boolean loggedIn;
        
        public AgencyOperator(String username, String password) {
            this.username = username;
            this.password = password;
            this.loggedIn = false;
        }
        
        public void login(String inputPassword) {
            if (this.password.equals(inputPassword)) {
                loggedIn = true;
                System.out.println("Login successful. Welcome, " + username + "!");
            } else {
                System.out.println("Login failed. Incorrect password.");
            }
        }
        
        public void logout() {
            loggedIn = false;
            System.out.println("Logged out successfully.");
        }
        
        public boolean isLoggedIn() {
            return loggedIn;
        }
        
        public String getUsername() {
            return username;
        }
        
        public boolean changePassword(String oldPassword, String newPassword) {
            if (!loggedIn) {
                System.out.println("Error: Operator must be logged in to change password.");
                return false;
            }
            
            if (!this.password.equals(oldPassword)) {
                System.out.println("Error: Old password is incorrect.");
                return false;
            }
            
            if (oldPassword.equals(newPassword)) {
                System.out.println("Error: New password must be different from old password.");
                return false;
            }
            
            this.password = newPassword;
            System.out.println("Password changed successfully for " + username + ".");
            return true;
        }
    }
    
    /**
     * Validates password strength according to common security requirements.
     * @param password the password to validate
     * @return true if password meets requirements, false otherwise
     */
    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            return false;
        }
        
        // Check for at least one uppercase letter
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }
        
        // Check for at least one lowercase letter
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }
        
        // Check for at least one digit
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            System.out.println("Password must contain at least one digit.");
            return false;
        }
        
        // Check for at least one special character
        if (!Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]").matcher(password).find()) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Simulates server connection. In a real application, this would be a network call.
     * @return true if connection is successful, false if interrupted
     */
    public static boolean connectToServer() {
        // Simulate server connection - 90% success rate
        double random = Math.random();
        if (random < 0.9) {
            System.out.println("Connected to server successfully.");
            return true;
        } else {
            System.out.println("Error: Connection to server interrupted.");
            return false;
        }
    }
    
    /**
     * Simulates saving changes to the server.
     * @param operator the AgencyOperator whose password is being changed
     * @param newPassword the new password to save
     * @return true if save is successful, false otherwise
     */
    public static boolean saveChangesToServer(AgencyOperator operator, String newPassword) {
        System.out.println("Attempting to save changes to server...");
        
        if (!connectToServer()) {
            return false;
        }
        
        // Simulate server processing delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Save operation interrupted.");
            return false;
        }
        
        // In a real application, this would be an API call to update the password in the database
        System.out.println("Changes saved successfully on the server.");
        return true;
    }
    
    /**
     * Main method that runs the password modification flow.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize an Agency Operator with default credentials
        AgencyOperator operator = new AgencyOperator("agency_operator_1", "OldPass123!");
        
        System.out.println("=== Agency Password Modification System ===");
        System.out.println("Step 1: Login");
        System.out.print("Enter current password to login: ");
        String loginPassword = scanner.nextLine();
        operator.login(loginPassword);
        
        // Check if login was successful
        if (!operator.isLoggedIn()) {
            System.out.println("Cannot proceed without successful login.");
            scanner.close();
            return;
        }
        
        System.out.println("\nStep 2: Password Change Form");
        System.out.println("Press 'C' to change password or any other key to exit.");
        String choice = scanner.nextLine();
        
        if (!choice.equalsIgnoreCase("C")) {
            System.out.println("Operation cancelled.");
            operator.logout();
            scanner.close();
            return;
        }
        
        System.out.println("\nStep 3: Enter New Password");
        System.out.print("Enter old password: ");
        String oldPassword = scanner.nextLine();
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        
        // Validate password confirmation
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Error: New password and confirmation do not match.");
            operator.logout();
            scanner.close();
            return;
        }
        
        // Validate password strength
        if (!validatePassword(newPassword)) {
            System.out.println("Password does not meet security requirements.");
            operator.logout();
            scanner.close();
            return;
        }
        
        System.out.println("\nStep 4: Save Changes");
        
        // Change password locally
        boolean localChangeSuccess = operator.changePassword(oldPassword, newPassword);
        if (!localChangeSuccess) {
            System.out.println("Local password change failed.");
            operator.logout();
            scanner.close();
            return;
        }
        
        // Save changes to server
        boolean serverSaveSuccess = saveChangesToServer(operator, newPassword);
        if (!serverSaveSuccess) {
            System.out.println("Error: Failed to save changes to server. Password may not be updated on server.");
            // In a real application, you might want to revert the local change here
            // or implement a retry mechanism
        } else {
            System.out.println("\n=== Operation Successful ===");
            System.out.println("Password has been modified successfully for " + operator.getUsername() + ".");
        }
        
        // Logout
        operator.logout();
        scanner.close();
    }
}