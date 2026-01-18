import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * LoginErrorHandler - Java program implementing the LoginError use case
 * 
 * This program simulates a login error scenario where invalid login data is entered.
 * It follows the specified flow:
 * 1. Notice invalid login data and ask for confirmation of reading the error
 * 2. Get confirmation from user
 * 3. Recover to previous state
 * 4. Return control to user interaction
 */
public class LoginErrorHandler {
    
    /**
     * Simulated user data class to represent login credentials
     */
    private static class UserCredentials {
        private String username;
        private String password;
        private int loginAttempts;
        
        public UserCredentials(String username, String password) {
            this.username = username;
            this.password = password;
            this.loginAttempts = 0;
        }
        
        public boolean validate(String inputUsername, String inputPassword) {
            return this.username.equals(inputUsername) && this.password.equals(inputPassword);
        }
        
        public void incrementLoginAttempts() {
            this.loginAttempts++;
        }
        
        public int getLoginAttempts() {
            return this.loginAttempts;
        }
        
        public void resetLoginAttempts() {
            this.loginAttempts = 0;
        }
    }
    
    /**
     * Application state class to manage the current state of the application
     */
    private static class ApplicationState {
        private boolean isLoggedIn;
        private UserCredentials currentUser;
        private String lastUsernameAttempted;
        
        public ApplicationState() {
            this.isLoggedIn = false;
            this.currentUser = null;
            this.lastUsernameAttempted = "";
        }
        
        public void setLoggedIn(boolean loggedIn) {
            this.isLoggedIn = loggedIn;
        }
        
        public boolean isLoggedIn() {
            return this.isLoggedIn;
        }
        
        public void setCurrentUser(UserCredentials user) {
            this.currentUser = user;
        }
        
        public UserCredentials getCurrentUser() {
            return this.currentUser;
        }
        
        public void setLastUsernameAttempted(String username) {
            this.lastUsernameAttempted = username;
        }
        
        public String getLastUsernameAttempted() {
            return this.lastUsernameAttempted;
        }
        
        /**
         * Recover to previous state - reset to initial state before login attempt
         */
        public void recoverToPreviousState() {
            this.isLoggedIn = false;
            this.lastUsernameAttempted = "";
            System.out.println("✓ Application state recovered to pre-login state.");
        }
    }
    
    /**
     * Main method to run the login error scenario
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationState appState = new ApplicationState();
        
        // Pre-configured valid user for simulation
        UserCredentials validUser = new UserCredentials("admin", "secure123");
        
        System.out.println("=== Login System Simulation ===\n");
        
        // Step 1: Simulate login attempt with invalid data
        System.out.println("Please enter your login credentials:");
        
        String inputUsername = "";
        String inputPassword = "";
        
        try {
            System.out.print("Username: ");
            inputUsername = scanner.nextLine().trim();
            
            System.out.print("Password: ");
            inputPassword = scanner.nextLine().trim();
            
            appState.setLastUsernameAttempted(inputUsername);
            
            // Step 1 (from use case): Notice that data is not valid
            if (!validUser.validate(inputUsername, inputPassword)) {
                System.out.println("\n✗ ERROR: Invalid login credentials provided.");
                validUser.incrementLoginAttempts();
                
                // Ask for confirmation of reading the error notification
                boolean confirmationReceived = askForConfirmation(scanner);
                
                if (confirmationReceived) {
                    System.out.println("\n✓ User confirmed reading of error notification.");
                    
                    // Step 3: Recover previous state
                    appState.recoverToPreviousState();
                    validUser.resetLoginAttempts();
                    
                    // Step 4: Exit conditions - return control to user interaction
                    System.out.println("\n✓ Control returned to user interaction.");
                    System.out.println("You can now try logging in again or perform other actions.");
                } else {
                    System.out.println("\n✗ No confirmation received. Cannot proceed with state recovery.");
                }
            } else {
                System.out.println("\n✓ Login successful!");
                appState.setLoggedIn(true);
                appState.setCurrentUser(validUser);
                System.out.println("Welcome, " + inputUsername + "!");
            }
            
        } catch (InputMismatchException e) {
            System.out.println("\n✗ ERROR: Invalid input format. Please enter text values.");
            appState.recoverToPreviousState();
        } catch (Exception e) {
            System.out.println("\n✗ ERROR: An unexpected error occurred: " + e.getMessage());
            appState.recoverToPreviousState();
        } finally {
            displaySystemStatus(validUser, appState);
            scanner.close();
        }
    }
    
    /**
     * Helper method to ask for confirmation of reading the error notification
     * 
     * @param scanner Scanner object for user input
     * @return true if user confirms, false otherwise
     */
    private static boolean askForConfirmation(Scanner scanner) {
        System.out.print("\n⚠️  Please confirm you have read this error notification (yes/no): ");
        
        try {
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            // Handle various confirmation responses
            if (confirmation.equals("yes") || confirmation.equals("y") || 
                confirmation.equals("confirm") || confirmation.equals("ok")) {
                return true;
            } else if (confirmation.equals("no") || confirmation.equals("n") || 
                       confirmation.equals("cancel")) {
                return false;
            } else {
                // Handle invalid confirmation response
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                System.out.print("Do you confirm reading the error? (yes/no): ");
                String retryConfirmation = scanner.nextLine().trim().toLowerCase();
                
                if (retryConfirmation.equals("yes") || retryConfirmation.equals("y")) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading confirmation: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Helper method to display the current system status
     * 
     * @param user UserCredentials object
     * @param state ApplicationState object
     */
    private static void displaySystemStatus(UserCredentials user, ApplicationState state) {
        System.out.println("\n=== System Status ===");
        System.out.println("Login Attempts: " + user.getLoginAttempts());
        System.out.println("Logged In Status: " + (state.isLoggedIn() ? "Yes" : "No"));
        System.out.println("Last Username Attempted: " + 
                          (state.getLastUsernameAttempted().isEmpty() ? "None" : state.getLastUsernameAttempted()));
        System.out.println("========================\n");
    }
    
    /**
     * Alternative entry point for testing the login error scenario programmatically
     * This can be used for unit testing or integration testing
     * 
     * @param testUsername username to test
     * @param testPassword password to test
     * @return true if error was handled successfully, false otherwise
     */
    public static boolean simulateLoginError(String testUsername, String testPassword) {
        ApplicationState testState = new ApplicationState();
        UserCredentials validUser = new UserCredentials("admin", "secure123");
        
        try {
            testState.setLastUsernameAttempted(testUsername);
            
            if (!validUser.validate(testUsername, testPassword)) {
                System.out.println("Test: Invalid credentials detected.");
                validUser.incrementLoginAttempts();
                
                // In automated test mode, we assume confirmation is always received
                System.out.println("Test: Confirmation assumed received.");
                
                testState.recoverToPreviousState();
                validUser.resetLoginAttempts();
                
                System.out.println("Test: State recovered successfully.");
                return true;
            } else {
                System.out.println("Test: Valid credentials - no error to handle.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Test: Error during simulation: " + e.getMessage());
            return false;
        }
    }
}