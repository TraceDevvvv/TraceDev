import java.util.Scanner;

/**
 * UserRegistration class representing the console interface for user registration.
 * This class handles the form input for all required fields and manages the registration flow.
 */
public class UserRegistration {
    private final Scanner scanner;
    private final UserService userService;
    
    /**
     * Constructor initializes the registration system with UserService
     */
    public UserRegistration(UserService userService) {
        this.scanner = new Scanner(System.in);
        this.userService = userService;
    }
    
    /**
     * Displays the user registration form and collects input
     * This corresponds to the system displaying the form with all required fields
     * @return a User object populated with form data, or null if registration was cancelled
     */
    public User displayRegistrationForm() {
        System.out.println("\n======= NEW USER REGISTRATION FORM =======");
        System.out.println("Please fill in all fields. Enter 'cancel' at any time to abort.\n");
        
        // Collect all required fields
        String name = getInput("Name: ", true);
        if ("cancel".equalsIgnoreCase(name)) return null;
        
        String surname = getInput("Surname: ", true);
        if ("cancel".equalsIgnoreCase(surname)) return null;
        
        String email = getInput("Email: ", true);
        if ("cancel".equalsIgnoreCase(email)) return null;
        
        String cell = getInput("Cell phone (10-15 digits, optional +): ", true);
        if ("cancel".equalsIgnoreCase(cell)) return null;
        
        String login = getInput("Login (4-20 alphanumeric characters with underscores): ", true);
        if ("cancel".equalsIgnoreCase(login)) return null;
        
        String password = getInput("Password (min 8 characters): ", false);
        if ("cancel".equalsIgnoreCase(password)) return null;
        
        String confirmPassword = getInput("Confirm Password: ", false);
        if ("cancel".equalsIgnoreCase(confirmPassword)) return null;
        
        // Create user object with collected data
        User user = new User(name, surname, email, cell, login, password);
        
        return user;
    }
    
    /**
     * Helper method to get user input with validation prompt
     * @param prompt the prompt to display to user
     * @param showCurrentValue whether to show current input value (for password fields we hide)
     * @return user input as string
     */
    private String getInput(String prompt, boolean showCurrentValue) {
        String input;
        while (true) {
            System.out.print(prompt);
            if (showCurrentValue) {
                input = scanner.nextLine().trim();
            } else {
                // For password fields, don't echo input
                input = new String(System.console().readPassword());
            }
            
            if ("cancel".equalsIgnoreCase(input)) {
                return input;
            }
            
            // Basic validation - field cannot be empty
            if (input.isEmpty()) {
                System.out.println("Error: This field cannot be empty. Please enter a value.");
                continue;
            }
            
            return input;
        }
    }
    
    /**
     * Processes the user registration attempt
     * This handles the "Save" button click scenario
     * @param user the user object to register
     * @param confirmPassword the confirmed password for validation
     * @return true if registration was successful, false otherwise
     */
    public boolean processRegistration(User user, String confirmPassword) {
        if (user == null) {
            System.out.println("Registration cancelled by user.");
            return false;
        }
        
        try {
            // Attempt to create the user (includes validation)
            boolean success = userService.createUser(user, confirmPassword);
            
            if (success) {
                System.out.println("\n✓ SUCCESS: New user has been created successfully!");
                System.out.println("User details: " + user.toString());
                return true;
            } else {
                System.out.println("\n✗ ERROR: User registration failed.");
                return false;
            }
            
        } catch (IllegalArgumentException e) {
            // Handle validation errors - corresponds to "Error data" use case
            System.out.println("\n✗ VALIDATION ERRORS:");
            System.out.println(e.getMessage());
            System.out.println("\nPlease correct the errors and try again.");
            return false;
        } catch (Exception e) {
            // Handle any other unexpected errors
            System.out.println("\n✗ SYSTEM ERROR: " + e.getMessage());
            System.out.println("Please contact system administrator.");
            return false;
        }
    }
    
    /**
     * Simulates the "viewing the list of users" precondition
     * The administrator must view the list before creating a new user
     */
    public void displayUserList() {
        System.out.println("\n======= VIEWING USER LIST (Precondition) =======");
        
        if (userService.hasUsers()) {
            System.out.println(userService.viewAllUsersFormatted());
        } else {
            System.out.println("No users in the system. You will be the first administrator.");
        }
        
        System.out.println("\nPress Enter to continue to new user registration...");
        scanner.nextLine();
    }
    
    /**
     * Displays detailed help information about field requirements
     */
    public void displayFieldRequirements() {
        System.out.println("\n======= FIELD REQUIREMENTS =======");
        System.out.println("1. Name: Only letters and spaces");
        System.out.println("2. Surname: Only letters and spaces");
        System.out.println("3. Email: Must be in format user@domain.com");
        System.out.println("4. Cell: 10-15 digits, optionally starting with +");
        System.out.println("5. Login: 4-20 characters, alphanumeric with underscores only");
        System.out.println("6. Password: Minimum 8 characters");
        System.out.println("===================================\n");
    }
    
    /**
     * Main registration flow that ties everything together
     * @return true if a new user was successfully registered, false otherwise
     */
    public boolean runRegistrationFlow() {
        // First, display the user list (precondition)
        displayUserList();
        
        // Display field requirements for user reference
        displayFieldRequirements();
        
        // Display form and collect data
        User user = displayRegistrationForm();
        
        if (user == null) {
            System.out.println("Registration process cancelled.");
            return false;
        }
        
        // Get confirm password (we need to ask again since password input is hidden)
        System.out.print("Confirm Password: ");
        String confirmPassword = new String(System.console().readPassword());
        
        // Process the registration
        return processRegistration(user, confirmPassword);
    }
    
    /**
     * Provides an alternative simplified registration flow for testing
     * when System.console() might not be available
     * @return true if registration was successful
     */
    public boolean runRegistrationFlowSimple() {
        System.out.println("\n======= NEW USER REGISTRATION =======");
        System.out.println("Enter user details (type 'cancel' at any prompt to abort):\n");
        
        // Collect all required fields with standard input (visible)
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if ("cancel".equalsIgnoreCase(name)) {
            System.out.println("Registration cancelled.");
            return false;
        }
        
        System.out.print("Surname: ");
        String surname = scanner.nextLine().trim();
        if ("cancel".equalsIgnoreCase(surname)) {
            System.out.println("Registration cancelled.");
            return false;
        }
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        if ("cancel".equalsIgnoreCase(email)) {
            System.out.println("Registration cancelled.");
            return false;
        }
        
        System.out.print("Cell phone: ");
        String cell = scanner.nextLine().trim();
        if ("cancel".equalsIgnoreCase(cell)) {
            System.out.println("Registration cancelled.");
            return false;
        }
        
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();
        if ("cancel".equalsIgnoreCase(login)) {
            System.out.println("Registration cancelled.");
            return false;
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        if ("cancel".equalsIgnoreCase(password)) {
            System.out.println("Registration cancelled.");
            return false;
        }
        
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine().trim();
        if ("cancel".equalsIgnoreCase(confirmPassword)) {
            System.out.println("Registration cancelled.");
            return false;
        }
        
        // Create and process user
        User user = new User(name, surname, email, cell, login, password);
        return processRegistration(user, confirmPassword);
    }
    
    /**
     * Closes the scanner and releases resources
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}