import java.util.Scanner;

/**
 * RegistrationForm class handles the display of the registration form
 * and collection of user input for all required fields.
 * This class implements step 1 from the use case: "View the registration form"
 * and facilitates steps 2-3: "Fill the fields of the form" and "Submit the form"
 */
public class RegistrationForm {
    
    private Scanner scanner;
    
    /**
     * Constructor initializes the RegistrationForm with a Scanner for user input
     * 
     * @param scanner the Scanner object for reading user input
     */
    public RegistrationForm(Scanner scanner) {
        this.scanner = scanner;
    }
    
    /**
     * Displays the registration form and collects user input
     * This method implements the form viewing and field filling steps
     * 
     * @return a User object containing all the collected form data
     */
    public User displayAndCollectForm() {
        System.out.println("\n=== Registration Form ===");
        System.out.println("Please fill in all required fields:");
        
        // Collect all required user information
        String name = collectName();
        String surname = collectSurname();
        String mobilePhone = collectMobilePhone();
        String email = collectEmail();
        String username = collectUsername();
        String password = collectPassword();
        String confirmationPassword = collectConfirmationPassword();
        
        // Create and return a new User object with the collected data
        return new User(name, surname, mobilePhone, email, username, password, confirmationPassword);
    }
    
    /**
     * Collects and validates the user's first name
     * 
     * @return validated first name
     */
    private String collectName() {
        String name;
        while (true) {
            System.out.print("First Name: ");
            name = scanner.nextLine().trim();
            
            // Apply input sanitization
            name = ValidationUtils.sanitizeInput(name);
            
            if (ValidationUtils.isValidName(name)) {
                break;
            } else {
                System.out.println("Invalid name. Please enter a valid first name (2-50 letters and spaces only).");
            }
        }
        return name;
    }
    
    /**
     * Collects and validates the user's surname
     * 
     * @return validated surname
     */
    private String collectSurname() {
        String surname;
        while (true) {
            System.out.print("Surname: ");
            surname = scanner.nextLine().trim();
            
            // Apply input sanitization
            surname = ValidationUtils.sanitizeInput(surname);
            
            if (ValidationUtils.isValidName(surname)) {
                break;
            } else {
                System.out.println("Invalid surname. Please enter a valid surname (2-50 letters and spaces only).");
            }
        }
        return surname;
    }
    
    /**
     * Collects and validates the user's mobile phone number
     * 
     * @return validated mobile phone number
     */
    private String collectMobilePhone() {
        String mobilePhone;
        while (true) {
            System.out.print("Mobile Phone (e.g., +1234567890 or 1234567890): ");
            mobilePhone = scanner.nextLine().trim();
            
            // Apply input sanitization
            mobilePhone = ValidationUtils.sanitizeInput(mobilePhone);
            
            if (ValidationUtils.isValidMobilePhone(mobilePhone)) {
                break;
            } else {
                System.out.println("Invalid phone number. Please enter a valid mobile number (10-15 digits, optional + prefix).");
            }
        }
        return mobilePhone;
    }
    
    /**
     * Collects and validates the user's email address
     * 
     * @return validated email address
     */
    private String collectEmail() {
        String email;
        while (true) {
            System.out.print("Email Address: ");
            email = scanner.nextLine().trim();
            
            // Apply input sanitization and convert to lowercase for consistency
            email = ValidationUtils.sanitizeInput(email).toLowerCase();
            
            if (ValidationUtils.isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Please enter a valid email address (e.g., user@example.com).");
            }
        }
        return email;
    }
    
    /**
     * Collects and validates the user's chosen username
     * 
     * @return validated username
     */
    private String collectUsername() {
        String username;
        while (true) {
            System.out.print("Username (3-20 chars, letters, numbers, underscore only): ");
            username = scanner.nextLine().trim();
            
            // Apply input sanitization
            username = ValidationUtils.sanitizeInput(username);
            
            if (ValidationUtils.isValidUsername(username)) {
                break;
            } else {
                System.out.println("Invalid username. Must be 3-20 characters and contain only letters, numbers, and underscores.");
            }
        }
        return username;
    }
    
    /**
     * Collects and validates the user's password with strength requirements
     * 
     * @return validated password
     */
    private String collectPassword() {
        String password;
        while (true) {
            System.out.print("Password (8-30 chars, must include uppercase, lowercase, digit, special char): ");
            password = scanner.nextLine();
            
            // Note: We don't sanitize passwords to allow special characters
            // but we do trim leading/trailing whitespace
            password = password.trim();
            
            if (ValidationUtils.isValidPassword(password)) {
                break;
            } else {
                System.out.println("Password does not meet security requirements:");
                System.out.println("- Must be 8-30 characters long");
                System.out.println("- Must contain at least one uppercase letter");
                System.out.println("- Must contain at least one lowercase letter");
                System.out.println("- Must contain at least one digit");
                System.out.println("- Must contain at least one special character (!@#$%^&*()_+-=[]{};':\"|,.<>/?)" );
                System.out.println("- Cannot contain whitespace");
                System.out.println("- Avoid common weak passwords (e.g., 'password', '123456')");
            }
        }
        return password;
    }
    
    /**
     * Collects and validates the password confirmation
     * Ensures it matches the original password
     * 
     * @return validated confirmation password
     */
    private String collectConfirmationPassword() {
        String confirmationPassword;
        while (true) {
            System.out.print("Confirm Password: ");
            confirmationPassword = scanner.nextLine().trim();
            
            if (!confirmationPassword.isEmpty()) {
                break;
            } else {
                System.out.println("Please confirm your password by entering it again.");
            }
        }
        return confirmationPassword;
    }
    
    /**
     * Provides a summary of the entered information before final submission
     * Allows user to confirm or edit their information
     * 
     * @param user the User object containing the form data
     * @return true if user confirms the information, false if they want to edit
     */
    public boolean confirmInformation(User user) {
        System.out.println("\n=== Please Review Your Information ===");
        System.out.println("First Name: " + user.getName());
        System.out.println("Surname: " + user.getSurname());
        System.out.println("Mobile Phone: " + user.getMobilePhone());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + "*".repeat(Math.min(user.getPassword().length(), 10)) + "...");
        
        System.out.print("\nIs this information correct? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        return confirmation.equals("yes") || confirmation.equals("y");
    }
    
    /**
     * Provides a quick registration form for testing/demo purposes
     * Collects minimal information with fewer validations
     * 
     * @return a User object with basic information
     */
    public User quickRegistration() {
        System.out.println("\n=== Quick Registration (Demo) ===");
        
        System.out.print("First Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Surname: ");
        String surname = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim().toLowerCase();
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine().trim();
        
        System.out.print("Mobile Phone: ");
        String mobile = scanner.nextLine().trim();
        
        return new User(name, surname, mobile, email, username, password, confirmPassword);
    }
    
    /**
     * Displays the registration form header and instructions
     * This simulates the initial form view from the use case
     */
    public void displayFormHeader() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("          REGISTRATION FORM");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Please complete all fields below:");
        System.out.println("• All fields are required");
        System.out.println("• Ensure information is accurate");
        System.out.println("• Passwords are case-sensitive");
        System.out.println("═══════════════════════════════════════════");
    }
    
    /**
     * Displays registration success message
     */
    public void displaySuccessMessage() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        REGISTRATION SUCCESSFUL!");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Thank you for registering with our system.");
        System.out.println("You will receive a confirmation email shortly.");
    }
    
    /**
     * Displays registration failure message
     * 
     * @param errorMessage the error message to display
     */
    public void displayErrorMessage(String errorMessage) {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("        REGISTRATION FAILED");
        System.out.println("═══════════════════════════════════════════");
        System.out.println("Error: " + errorMessage);
        System.out.println("Please try again or contact support.");
    }
    
    /**
     * Simulates the form submission process
     * This method represents step 3 from the use case: "Submit the form"
     * 
     * @param user the User object containing form data
     * @return true if form submission is successful
     */
    public boolean submitForm(User user) {
        System.out.println("\nSubmitting registration form...");
        
        // Simulate form submission processing
        try {
            Thread.sleep(300); // Simulate processing time
            System.out.println("Form submitted successfully!");
            return true;
        } catch (InterruptedException e) {
            System.out.println("Form submission interrupted.");
            Thread.currentThread().interrupt();
            return false;
        }
    }
}