import java.util.Scanner;

/**
 * Main class for the Registration System.
 * This class implements the console interface for user registration.
 * It follows the RegistrationAtSite use case with all required functionality.
 */
public class RegistrationSystem {
    
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    
    // Database simulator for storing user registrations
    private static DatabaseSimulator database = new DatabaseSimulator();
    
    // Registration form handler
    private static RegistrationForm registrationForm = new RegistrationForm(scanner);
    
    /**
     * Main method - Entry point of the application
     * Displays the registration form and processes user registration
     */
    public static void main(String[] args) {
        System.out.println("=== Student Registration System ===");
        System.out.println("Welcome to the registration portal!");
        
        // Loop to allow multiple registrations
        boolean continueRegistration = true;
        
        while (continueRegistration) {
            System.out.println("\n--- Registration Form ---");
            
            try {
                // Display registration form and collect user input
                User newUser = registrationForm.displayAndCollectForm();
                
                // Validate user input
                if (ValidationUtils.validateUser(newUser)) {
                    
                    // Check if username already exists
                    if (database.isUsernameExists(newUser.getUsername())) {
                        System.out.println("Error: Username '" + newUser.getUsername() + "' is already taken. Please choose a different username.");
                        continue;
                    }
                    
                    // Check if email already exists
                    if (database.isEmailExists(newUser.getEmail())) {
                        System.out.println("Error: Email '" + newUser.getEmail() + "' is already registered. Please use a different email.");
                        continue;
                    }
                    
                    // Insert registration request into the system (simulated database)
                    boolean registrationSuccess = database.insertUser(newUser);
                    
                    if (registrationSuccess) {
                        System.out.println("\n✅ Registration successful!");
                        System.out.println("Welcome, " + newUser.getName() + " " + newUser.getSurname() + "!");
                        System.out.println("Your username: " + newUser.getUsername());
                        System.out.println("A confirmation email has been sent to: " + newUser.getEmail());
                    } else {
                        System.out.println("❌ Registration failed due to a system error. Please try again.");
                    }
                } else {
                    System.out.println("❌ Registration failed: Invalid input data. Please check your information and try again.");
                }
                
            } catch (Exception e) {
                System.out.println("❌ An error occurred during registration: " + e.getMessage());
                System.out.println("Registration process interrupted.");
                
                // Simulate connection interruption as per postcondition
                System.out.println("⚠️  Connection to SMOS server interrupted.");
            }
            
            // Ask user if they want to register another user
            System.out.print("\nDo you want to register another user? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (!choice.equals("yes")) {
                continueRegistration = false;
                System.out.println("\nThank you for using the Registration System!");
            }
        }
        
        // Display all registered users (for demonstration purposes)
        System.out.println("\n=== Registered Users ===");
        database.displayAllUsers();
        
        scanner.close();
        System.out.println("\nSystem shutdown completed.");
    }
    
    /**
     * Simulates the "Register" button click from homepage
     * This method represents the pre-condition trigger
     */
    public static void simulateRegisterButtonClick() {
        System.out.println("Register button clicked - Redirecting to registration form...");
    }
}