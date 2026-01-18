import java.util.Scanner;

/**
 * LoginForm class that handles the display of the login form and user authentication.
 * This class implements the "Displays the login form" event from the LoginError use case.
 * It collects user credentials, validates input, and attempts authentication through
 * the AuthenticationService. If authentication fails, it throws a LoginErrorException.
 * 
 * Key responsibilities:
 * 1. Display login form to user
 * 2. Collect username and password input
 * 3. Validate input format
 * 4. Attempt authentication via AuthenticationService
 * 5. Handle the LoginError use case by throwing appropriate exception
 */
public class LoginForm {
    
    private final AuthenticationService authService;
    private final Scanner scanner;
    
    /**
     * Constructor for LoginForm.
     * 
     * @param authService the authentication service used to validate credentials
     * @param scanner the Scanner object for reading user input
     * @throws IllegalArgumentException if authService or scanner is null
     */
    public LoginForm(AuthenticationService authService, Scanner scanner) {
        if (authService == null) {
            throw new IllegalArgumentException("AuthenticationService cannot be null");
        }
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }
        
        this.authService = authService;
        this.scanner = scanner;
    }
    
    /**
     * Displays the login form and attempts authentication.
     * This method implements the core flow of the LoginError use case:
     * 1. Displays the login form (UI)
     * 2. Collects user credentials
     * 3. Validates input format
     * 4. Attempts authentication
     * 5. Returns authentication result or throws LoginErrorException
     * 
     * @return true if authentication is successful, false is never returned (exception thrown instead)
     * @throws LoginErrorException when authentication fails (incorrect credentials)
     * @throws IllegalArgumentException when input validation fails
     */
    public boolean displayAndAuthenticate() throws LoginErrorException {
        System.out.println("\n=== Login Form ===");
        System.out.println("Please enter your credentials:");
        
        // Get username with validation
        String username = promptForUsername();
        
        // Get password with validation
        String password = promptForPassword();
        
        System.out.println("Authenticating...");
        
        // Attempt authentication through the service
        boolean isAuthenticated = authService.authenticate(username, password);
        
        if (isAuthenticated) {
            return true; // Authentication successful
        } else {
            // Throw LoginErrorException when authentication fails
            // This represents the LoginError use case: incorrect authentication data
            throw new LoginErrorException("Authentication failed: Incorrect username or password");
        }
    }
    
    /**
     * Prompts user for username and validates input.
     * 
     * @return validated username string
     * @throws IllegalArgumentException if username is empty or contains invalid characters
     */
    private String promptForUsername() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        // Validate username
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        
        if (username.length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long");
        }
        
        if (username.length() > 50) {
            throw new IllegalArgumentException("Username cannot exceed 50 characters");
        }
        
        // Check for invalid characters (basic validation)
        if (!username.matches("^[a-zA-Z0-9._-]+$")) {
            throw new IllegalArgumentException("Username can only contain letters, numbers, dots, underscores, and hyphens");
        }
        
        return username;
    }
    
    /**
     * Prompts user for password and validates input.
     * 
     * @return validated password string
     * @throws IllegalArgumentException if password is empty or too short
     */
    private String promptForPassword() {
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        // Validate password
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        
        if (password.length() > 100) {
            throw new IllegalArgumentException("Password cannot exceed 100 characters");
        }
        
        return password;
    }
    
    /**
     * Helper method to display login form instructions.
     * This can be called before the actual login attempt to provide user guidance.
     */
    public void displayInstructions() {
        System.out.println("\n=== Login Instructions ===");
        System.out.println("1. Enter your username (3-50 characters, letters/numbers only)");
        System.out.println("2. Enter your password (6-100 characters)");
        System.out.println("3. Press Enter to submit");
        System.out.println("4. If credentials are incorrect, you'll see an error and can try again");
        System.out.println("5. You have 3 attempts maximum for security reasons");
    }
    
    /**
     * Demonstrates the LoginError use case flow.
     * This method shows what happens when incorrect credentials are entered.
     */
    public void demonstrateLoginErrorScenario() {
        System.out.println("\n=== LoginError Use Case Demonstration ===");
        System.out.println("Scenario: User enters incorrect authentication data");
        System.out.println("1. System displays login form");
        System.out.println("2. User enters incorrect username/password");
        System.out.println("3. System validates credentials and finds they are incorrect");
        System.out.println("4. LoginErrorException is thrown");
        System.out.println("5. User sees error message");
        System.out.println("6. User can try to log in again (postcondition)");
    }
}