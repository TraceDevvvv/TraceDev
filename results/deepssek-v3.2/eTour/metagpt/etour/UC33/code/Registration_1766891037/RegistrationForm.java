import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * RegistrationForm class handles the registration form display, data collection,
 * validation, and submission process according to the use case requirements.
 * This class simulates a registration form that interacts with the Guest User.
 */
public class RegistrationForm {
    private Scanner scanner;
    private boolean loggingEnabled;
    
    // Form data fields
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    
    // Validation patterns
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    
    /**
     * Constructor for RegistrationForm.
     * Initializes the scanner for user input.
     */
    public RegistrationForm() {
        this.scanner = new Scanner(System.in);
        this.loggingEnabled = false;
        resetFormData();
    }
    
    /**
     * Reset all form data fields to empty strings.
     */
    private void resetFormData() {
        this.username = "";
        this.email = "";
        this.password = "";
        this.confirmPassword = "";
        this.fullName = "";
    }
    
    /**
     * Enable or disable logging feature.
     * According to use case step 1: "Enable the logging feature."
     * 
     * @param enabled true to enable logging, false to disable
     */
    public void setLoggingEnabled(boolean enabled) {
        this.loggingEnabled = enabled;
        log("Logging feature has been " + (enabled ? "enabled" : "disabled"));
    }
    
    /**
     * Log a message if logging is enabled.
     * 
     * @param message the message to log
     */
    private void log(String message) {
        if (loggingEnabled) {
            System.out.println("[LOG] " + message);
        }
    }
    
    /**
     * Display the registration form to the user.
     * According to use case step 2: "View the registration form."
     */
    public void displayForm() {
        log("Displaying registration form");
        System.out.println("\n=== REGISTRATION FORM ===");
        System.out.println("Please fill in the following information:");
        System.out.println("=========================================");
    }
    
    /**
     * Collect user input for all form fields.
     * According to use case step 3: "Fill out the form and submit."
     * This method guides the user through filling each field.
     */
    public void collectFormData() {
        log("Starting form data collection");
        
        System.out.print("Enter username (3-20 chars, letters, numbers, underscore): ");
        this.username = scanner.nextLine().trim();
        
        System.out.print("Enter email address: ");
        this.email = scanner.nextLine().trim();
        
        System.out.print("Enter password (min 8 chars, with upper, lower, number, special): ");
        this.password = scanner.nextLine();
        
        System.out.print("Confirm password: ");
        this.confirmPassword = scanner.nextLine();
        
        System.out.print("Enter full name: ");
        this.fullName = scanner.nextLine().trim();
        
        log("Form data collection completed");
    }
    
    /**
     * Validate the collected form data.
     * According to use case step 4: "Verify the data entered..."
     * Performs comprehensive validation on all form fields.
     * 
     * @return ValidationResult containing validation status and error messages
     */
    public ValidationResult validateFormData() {
        log("Validating form data");
        ValidationResult result = new ValidationResult();
        
        // Validate username
        if (username.isEmpty()) {
            result.addError("Username is required");
        } else if (!USERNAME_PATTERN.matcher(username).matches()) {
            result.addError("Username must be 3-20 characters long and contain only letters, numbers, and underscore");
        }
        
        // Validate email
        if (email.isEmpty()) {
            result.addError("Email is required");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            result.addError("Email address is invalid");
        }
        
        // Validate password
        if (password.isEmpty()) {
            result.addError("Password is required");
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            result.addError("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character");
        }
        
        // Validate password confirmation
        if (confirmPassword.isEmpty()) {
            result.addError("Password confirmation is required");
        } else if (!password.equals(confirmPassword)) {
            result.addError("Passwords do not match");
        }
        
        // Validate full name
        if (fullName.isEmpty()) {
            result.addError("Full name is required");
        } else if (fullName.length() < 2) {
            result.addError("Full name must be at least 2 characters long");
        }
        
        log("Validation completed: " + (result.isValid() ? "Valid" : "Invalid - " + result.getErrorCount() + " errors"));
        return result;
    }
    
    /**
     * Ask for confirmation from the user before proceeding.
     * According to use case step 5: "Confirm the operation."
     * 
     * @return true if user confirms, false if user cancels
     */
    public boolean askForConfirmation() {
        log("Asking for user confirmation");
        System.out.println("\n=== CONFIRM REGISTRATION ===");
        System.out.println("Please review your information:");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Full Name: " + fullName);
        System.out.println("=========================================");
        System.out.print("Do you want to proceed with registration? (yes/no): ");
        
        String response = scanner.nextLine().trim().toLowerCase();
        boolean confirmed = response.equals("yes") || response.equals("y");
        
        log("User confirmation: " + (confirmed ? "Confirmed" : "Cancelled"));
        return confirmed;
    }
    
    /**
     * Create a User object from the collected form data.
     * According to use case step 6: "Create a new account with the data entered."
     * 
     * @return a new User object populated with form data
     */
    public User createUserFromFormData() {
        log("Creating User object from form data");
        return new User(username, email, password, fullName);
    }
    
    /**
     * Getter for username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Getter for email.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Getter for full name.
     * 
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Close the form resources.
     * Should be called when the form is no longer needed.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
        log("Registration form closed");
    }
    
    /**
     * Inner class to represent validation results.
     * Contains validation status, error messages, and error count.
     */
    public static class ValidationResult {
        private boolean valid;
        private StringBuilder errorMessages;
        private int errorCount;
        
        /**
         * Constructor for ValidationResult.
         * Initializes as valid with no errors.
         */
        public ValidationResult() {
            this.valid = true;
            this.errorMessages = new StringBuilder();
            this.errorCount = 0;
        }
        
        /**
         * Add an error message to the validation result.
         * Automatically sets valid to false and increments error count.
         * 
         * @param errorMessage the error message to add
         */
        public void addError(String errorMessage) {
            if (valid) {
                valid = false;
            }
            errorMessages.append("- ").append(errorMessage).append("\n");
            errorCount++;
        }
        
        /**
         * Check if the validation passed.
         * 
         * @return true if valid, false otherwise
         */
        public boolean isValid() {
            return valid;
        }
        
        /**
         * Get all error messages as a single string.
         * 
         * @return formatted error messages, or empty string if no errors
         */
        public String getErrorMessages() {
            return errorMessages.toString();
        }
        
        /**
         * Get the number of validation errors.
         * 
         * @return the error count
         */
        public int getErrorCount() {
            return errorCount;
        }
        
        /**
         * Display validation errors to the user.
         */
        public void displayErrors() {
            if (!valid) {
                System.out.println("\n=== VALIDATION ERRORS ===");
                System.out.println("Please correct the following errors:");
                System.out.println(errorMessages.toString());
                System.out.println("Please try again.");
            }
        }
    }
}