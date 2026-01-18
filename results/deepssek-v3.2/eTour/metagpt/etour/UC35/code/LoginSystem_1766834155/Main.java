/**
 * Main class for the Login System application.
 * This class activates the login feature and orchestrates the login flow
 * as described in the use case: LOGIN.
 * 
 * The program simulates:
 * 1. Activating the login feature
 * 2. Displaying the login form
 * 3. Collecting and validating login information
 * 4. Handling errors (LoginError, cancellation, server interruption)
 * 5. Displaying the work area for authenticated users
 * 
 * @Author LoginSystem
 * @Version 1.0
 */
public class Main {

    /**
     * Main method - entry point of the login system.
     * Creates the login service and handles the complete login workflow.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("       WELCOME TO THE LOGIN SYSTEM       ");
        System.out.println("==========================================");
        
        // Create login service that handles all login operations
        LoginService loginService = new LoginService();
        
        // Create scanner for user input (closed in finally block)
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        try {
            // Main program loop - allows multiple login attempts
            boolean continueRunning = true;
            
            while (continueRunning) {
                // Display main menu options
                System.out.println("\nPlease select an option:");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Enter your choice (1 or 2): ");
                
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        // Activate the login feature (as per use case step 1)
                        performLogin(loginService, scanner);
                        break;
                        
                    case "2":
                        System.out.println("\nThank you for using the Login System. Goodbye!");
                        continueRunning = false;
                        break;
                        
                    default:
                        System.out.println("\nInvalid choice. Please enter 1 for Login or 2 for Exit.");
                        break;
                }
            }
            
        } catch (Exception e) {
            // Handle any unexpected exceptions
            System.out.println("\nA system error occurred: " + e.getMessage());
            System.out.println("The program will now exit.");
            e.printStackTrace();
        } finally {
            // Ensure scanner is closed to prevent resource leak
            if (scanner != null) {
                scanner.close();
                System.out.println("\nScanner resource closed.");
            }
        }
    }
    
    /**
     * Performs the complete login process as per the use case flow.
     * This method encapsulates the login workflow:
     * 1. Activate login feature
     * 2. Display form
     * 3. Submit credentials
     * 4. Validate data
     * 5. Handle results
     * 
     * @param loginService the login service that handles business logic
     * @param scanner the scanner for user input
     */
    private static void performLogin(LoginService loginService, java.util.Scanner scanner) {
        System.out.println("\n--- Activating Login Feature ---");
        
        // Allow multiple login attempts within a single login session
        boolean retryLogin = true;
        int attemptCount = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (retryLogin && attemptCount < MAX_ATTEMPTS) {
            attemptCount++;
            
            if (attemptCount > 1) {
                System.out.println("\n--- Login Attempt " + attemptCount + " of " + MAX_ATTEMPTS + " ---");
            }
            
            try {
                // Step 2 & 3: Display form and collect credentials (use case steps 2-3)
                // The LoginService handles form display and credential collection
                loginService.processLogin(scanner);
                
                // If we reach here without exception, login was successful
                // The work area is displayed within the LoginService
                
                // After successful login, ask if user wants to perform another login
                System.out.print("\nWould you like to login with another account? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                
                if (!response.equals("yes")) {
                    retryLogin = false;
                }
                
            } catch (Exception e) {
                // This catch block handles unexpected exceptions (not LoginError)
                // LoginError is handled within LoginService
                System.out.println("\nAn unexpected error occurred during login: " + e.getMessage());
                System.out.println("Please try again or contact system administrator.");
                
                // Check if user wants to retry after unexpected error
                if (attemptCount < MAX_ATTEMPTS) {
                    System.out.print("Would you like to try again? (yes/no): ");
                    String retryResponse = scanner.nextLine().trim().toLowerCase();
                    
                    if (!retryResponse.equals("yes")) {
                        retryLogin = false;
                        System.out.println("Returning to main menu...");
                    }
                } else {
                    System.out.println("\nMaximum login attempts reached. Returning to main menu...");
                    retryLogin = false;
                }
            }
        }
        
        if (attemptCount >= MAX_ATTEMPTS) {
            System.out.println("\nSecurity notice: Maximum login attempts (" + MAX_ATTEMPTS + ") reached.");
            System.out.println("Please wait before trying again or contact administrator.");
        }
    }
    
    /**
     * Helper method to display system information and instructions.
     * This provides additional context to the user about the login system.
     */
    private static void displaySystemInfo() {
        System.out.println("\n=== SYSTEM INFORMATION ===");
        System.out.println("This login system demonstrates:");
        System.out.println("1. User authentication with credential validation");
        System.out.println("2. Privilege-based work area access");
        System.out.println("3. Error handling for:");
        System.out.println("   - Incorrect credentials");
        System.out.println("   - Server connection issues (ETOUR)");
        System.out.println("   - User cancellation");
        System.out.println("\nSample users for testing:");
        System.out.println("Username: admin, Password: admin123 (Administrator privileges)");
        System.out.println("Username: user1, Password: password1 (Standard user privileges)");
        System.out.println("Username: john_doe, Password: securePass (Manager privileges)");
        System.out.println("You can type 'cancel' at any prompt to cancel the operation.");
    }
}