import java.util.Scanner;

/**
 * Main application class for demonstrating the LoginError use case.
 * This program simulates a login system where users can attempt to log in
 * with credentials. If incorrect authentication data is provided,
 * a LoginErrorException is thrown and the user can try again.
 * 
 * The LoginError use case occurs when:
 * - Actors: registered user
 * - Description: user has entered incorrect authentication data
 * - Preconditions: data provided for login are incorrect
 * - Events: System displays login form
 * - Postconditions: user can try to log in
 */
public class Main {
    /**
     * Main entry point of the application.
     * 
     * @param args command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Initialize components
        Scanner scanner = new Scanner(System.in);
        AuthenticationService authService = new AuthenticationService();
        LoginForm loginForm = new LoginForm(authService, scanner);
        
        System.out.println("=== Welcome to the Login System ===");
        System.out.println("This application demonstrates the LoginError use case.");
        System.out.println("You will be prompted to enter login credentials.");
        System.out.println("Try incorrect credentials to see the error handling.\n");
        
        boolean continueLogin = true;
        int attemptCount = 0;
        final int MAX_ATTEMPTS = 3;
        
        // Main login loop - allows user to try multiple times
        while (continueLogin && attemptCount < MAX_ATTEMPTS) {
            attemptCount++;
            System.out.println("=== Login Attempt " + attemptCount + " of " + MAX_ATTEMPTS + " ===");
            
            try {
                // Display login form and attempt authentication
                boolean loginSuccessful = loginForm.displayAndAuthenticate();
                
                if (loginSuccessful) {
                    System.out.println("Login successful! Welcome to the system.");
                    continueLogin = false; // Exit loop on successful login
                } 
                // Note: If authentication fails, LoginErrorException is thrown
                // and caught below, so we won't reach this point for failed attempts
                
            } catch (LoginErrorException e) {
                // Handle the LoginError use case - incorrect authentication data
                System.out.println("\n" + e.getMessage());
                System.out.println("Login failed. Please try again with correct credentials.\n");
                
                // Check if we should allow another attempt
                if (attemptCount < MAX_ATTEMPTS) {
                    System.out.println("You have " + (MAX_ATTEMPTS - attemptCount) + " attempt(s) remaining.");
                    
                    // Ask user if they want to continue (only for demonstration)
                    if (attemptCount < MAX_ATTEMPTS) {
                        System.out.print("Do you want to try again? (yes/no): ");
                        String response = scanner.nextLine().trim().toLowerCase();
                        
                        if (!response.equals("yes") && !response.equals("y")) {
                            continueLogin = false;
                            System.out.println("Login process terminated by user.");
                        }
                    }
                } else {
                    System.out.println("Maximum login attempts (" + MAX_ATTEMPTS + ") reached.");
                    System.out.println("Please contact system administrator for assistance.");
                }
                
            } catch (Exception e) {
                // Handle any unexpected exceptions
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again later.");
                continueLogin = false;
            }
        }
        
        if (attemptCount >= MAX_ATTEMPTS) {
            System.out.println("\n=== Security Notice ===");
            System.out.println("Too many failed login attempts. Account temporarily locked.");
            System.out.println("For security reasons, please wait 15 minutes or contact support.");
        }
        
        // Close resources
        scanner.close();
        System.out.println("\nThank you for using the Login System. Goodbye!");
    }
    
    /**
     * Helper method to demonstrate successful login scenario.
     * This is not called in the main flow but shows how successful login would work.
     */
    private static void demonstrateSuccessfulLogin() {
        System.out.println("\n=== Successful Login Demonstration ===");
        System.out.println("When correct credentials are entered:");
        System.out.println("1. Username: 'admin'");
        System.out.println("2. Password: 'password123'");
        System.out.println("3. System validates credentials");
        System.out.println("4. Login successful message displayed");
        System.out.println("5. User gains access to the system");
    }
}