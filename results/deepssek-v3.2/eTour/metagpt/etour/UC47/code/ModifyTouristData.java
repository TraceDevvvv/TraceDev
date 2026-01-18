import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Main class for the ModifyTouristData use case implementation.
 * This program allows authenticated tourists to modify their account data.
 */
public class ModifyTouristData {
    
    /**
     * Tourist class representing a tourist/user with personal data.
     */
    static class Tourist {
        private String username;
        private String password;
        private String fullName;
        private String email;
        private String phoneNumber;
        private String nationality;
        
        public Tourist(String username, String password, String fullName, 
                      String email, String phoneNumber, String nationality) {
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.nationality = nationality;
        }
        
        // Getters
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getFullName() { return fullName; }
        public String getEmail() { return email; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getNationality() { return nationality; }
        
        // Setters for modification
        public void setFullName(String fullName) { this.fullName = fullName; }
        public void setEmail(String email) { this.email = email; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
        public void setNationality(String nationality) { this.nationality = nationality; }
        
        /**
         * Display tourist data in a readable format
         */
        public void displayData() {
            System.out.println("Current Tourist Data:");
            System.out.println("Username: " + username);
            System.out.println("Full Name: " + fullName);
            System.out.println("Email: " + email);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Nationality: " + nationality);
            System.out.println("-----------------------------------");
        }
    }
    
    /**
     * Validator class for validating tourist data
     */
    static class DataValidator {
        // Email validation pattern
        private static final String EMAIL_PATTERN = 
            "^[A-Za-z0-9+_.-]+@(.+)$";
        
        // Phone validation pattern (basic international format)
        private static final String PHONE_PATTERN = 
            "^\\+?[0-9]{10,15}$";
        
        private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);
        
        /**
         * Validate email format
         * @param email email to validate
         * @return true if valid, false otherwise
         */
        public static boolean isValidEmail(String email) {
            if (email == null || email.trim().isEmpty()) {
                return false;
            }
            Matcher matcher = emailPattern.matcher(email.trim());
            return matcher.matches();
        }
        
        /**
         * Validate phone number format
         * @param phone phone number to validate
         * @return true if valid, false otherwise
         */
        public static boolean isValidPhone(String phone) {
            if (phone == null || phone.trim().isEmpty()) {
                return false;
            }
            // Remove spaces and dashes
            String cleanedPhone = phone.replaceAll("[\\s-]", "");
            Matcher matcher = phonePattern.matcher(cleanedPhone);
            return matcher.matches();
        }
        
        /**
         * Validate name (non-empty)
         * @param name name to validate
         * @return true if valid, false otherwise
         */
        public static boolean isValidName(String name) {
            return name != null && !name.trim().isEmpty() && name.trim().length() >= 2;
        }
        
        /**
         * Validate nationality (non-empty)
         * @param nationality nationality to validate
         * @return true if valid, false otherwise
         */
        public static boolean isValidNationality(String nationality) {
            return nationality != null && !nationality.trim().isEmpty();
        }
        
        /**
         * Validate all tourist data
         * @param fullName full name to validate
         * @param email email to validate
         * @param phone phone to validate
         * @param nationality nationality to validate
         * @return Map of validation errors, empty if all valid
         */
        public static Map<String, String> validateAllData(String fullName, String email, 
                                                         String phone, String nationality) {
            Map<String, String> errors = new HashMap<>();
            
            if (!isValidName(fullName)) {
                errors.put("fullName", "Full name must be at least 2 characters long");
            }
            
            if (!isValidEmail(email)) {
                errors.put("email", "Invalid email format");
            }
            
            if (!isValidPhone(phone)) {
                errors.put("phone", "Phone number must be 10-15 digits (optional + prefix)");
            }
            
            if (!isValidNationality(nationality)) {
                errors.put("nationality", "Nationality cannot be empty");
            }
            
            return errors;
        }
    }
    
    /**
     * Database simulator class (simulates connection to server ETOUR)
     */
    static class TouristDatabase {
        // Simulated database with some initial tourists
        private static Map<String, Tourist> tourists = new HashMap<>();
        
        static {
            // Initialize with sample data
            tourists.put("john_doe", new Tourist("john_doe", "password123", 
                "John Doe", "john@example.com", "+1234567890", "USA"));
            tourists.put("jane_smith", new Tourist("jane_smith", "securepass", 
                "Jane Smith", "jane@example.com", "+441234567890", "UK"));
        }
        
        /**
         * Authenticate user
         * @param username username
         * @param password password
         * @return Tourist object if authenticated, null otherwise
         */
        public static Tourist authenticate(String username, String password) {
            Tourist tourist = tourists.get(username);
            if (tourist != null && tourist.getPassword().equals(password)) {
                return tourist;
            }
            return null;
        }
        
        /**
         * Update tourist data in database
         * @param tourist updated tourist object
         * @return true if update successful, false otherwise
         * @throws DatabaseConnectionException if connection fails
         */
        public static boolean updateTourist(Tourist tourist) throws DatabaseConnectionException {
            // Simulate connection interruption (10% chance for demonstration)
            if (Math.random() < 0.1) {
                throw new DatabaseConnectionException("Connection to server ETOUR interrupted");
            }
            
            if (tourists.containsKey(tourist.getUsername())) {
                tourists.put(tourist.getUsername(), tourist);
                return true;
            }
            return false;
        }
    }
    
    /**
     * Custom exception for database connection errors
     */
    static class DatabaseConnectionException extends Exception {
        public DatabaseConnectionException(String message) {
            super(message);
        }
    }
    
    /**
     * Custom exception for data validation errors
     */
    static class ValidationException extends Exception {
        private Map<String, String> errors;
        
        public ValidationException(String message, Map<String, String> errors) {
            super(message);
            this.errors = errors;
        }
        
        public Map<String, String> getErrors() {
            return errors;
        }
    }
    
    /**
     * Main application class managing the modify tourist data flow
     */
    static class ModifyTouristDataApp {
        private Scanner scanner;
        private Tourist currentTourist;
        
        public ModifyTouristDataApp() {
            scanner = new Scanner(System.in);
        }
        
        /**
         * Main application flow
         */
        public void run() {
            System.out.println("=== Modify Tourist Data System ===");
            
            try {
                // Entry Condition: Authentication
                if (!authenticateUser()) {
                    System.out.println("Authentication failed. Exiting...");
                    return;
                }
                
                // Main modification loop
                boolean continueModification = true;
                while (continueModification) {
                    System.out.println("\n=== Modify Tourist Data Menu ===");
                    System.out.println("1. View current data");
                    System.out.println("2. Modify data");
                    System.out.println("3. Cancel operation and exit");
                    System.out.print("Enter your choice: ");
                    
                    String choice = scanner.nextLine().trim();
                    
                    switch (choice) {
                        case "1":
                            // Flow step 2: Load and display data
                            displayCurrentData();
                            break;
                        case "2":
                            // Start modification process
                            modifyDataProcess();
                            break;
                        case "3":
                            // Exit condition: Tourist cancels the operation
                            System.out.println("Operation cancelled by user.");
                            continueModification = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
                
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            } finally {
                scanner.close();
                System.out.println("\nThank you for using Modify Tourist Data System.");
            }
        }
        
        /**
         * Authenticate user (Entry Condition)
         * @return true if authentication successful
         */
        private boolean authenticateUser() {
            System.out.println("\n=== Authentication Required ===");
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            
            currentTourist = TouristDatabase.authenticate(username, password);
            
            if (currentTourist != null) {
                System.out.println("Authentication successful! Welcome, " + currentTourist.getFullName());
                return true;
            } else {
                System.out.println("Authentication failed. Invalid credentials.");
                return false;
            }
        }
        
        /**
         * Display current tourist data (Flow step 2)
         */
        private void displayCurrentData() {
            System.out.println("\n=== Current Tourist Data ===");
            currentTourist.displayData();
        }
        
        /**
         * Complete data modification process
         */
        private void modifyDataProcess() {
            System.out.println("\n=== Data Modification ===");
            
            try {
                // Step 1 & 2: Load and display current data
                displayCurrentData();
                
                // Step 3: Edit fields in form
                Map<String, String> modifiedData = collectModifiedData();
                
                // Step 4: Check modified information
                validateAndConfirm(modifiedData);
                
                // Step 5: Confirmation of transaction change
                if (confirmChanges()) {
                    // Step 6: Store modified data
                    saveModifiedData(modifiedData);
                    
                    // Exit condition: Successful modification
                    System.out.println("Data modification successful!");
                } else {
                    System.out.println("Changes not confirmed. Returning to menu.");
                }
                
            } catch (ValidationException e) {
                // Flow step 4: Invalid data - activate error case
                handleValidationError(e);
            } catch (DatabaseConnectionException e) {
                // Exit condition: Connection interruption
                handleDatabaseError(e);
            }
        }
        
        /**
         * Collect modified data from user (Flow step 3)
         * @return Map containing modified field values
         */
        private Map<String, String> collectModifiedData() {
            Map<String, String> modifiedData = new HashMap<>();
            
            System.out.println("\nEnter new data (press Enter to keep current value):");
            
            System.out.print("Full Name [" + currentTourist.getFullName() + "]: ");
            String fullName = scanner.nextLine().trim();
            modifiedData.put("fullName", fullName.isEmpty() ? currentTourist.getFullName() : fullName);
            
            System.out.print("Email [" + currentTourist.getEmail() + "]: ");
            String email = scanner.nextLine().trim();
            modifiedData.put("email", email.isEmpty() ? currentTourist.getEmail() : email);
            
            System.out.print("Phone Number [" + currentTourist.getPhoneNumber() + "]: ");
            String phone = scanner.nextLine().trim();
            modifiedData.put("phone", phone.isEmpty() ? currentTourist.getPhoneNumber() : phone);
            
            System.out.print("Nationality [" + currentTourist.getNationality() + "]: ");
            String nationality = scanner.nextLine().trim();
            modifiedData.put("nationality", nationality.isEmpty() ? currentTourist.getNationality() : nationality);
            
            return modifiedData;
        }
        
        /**
         * Validate modified data and ask for confirmation (Flow step 4)
         * @param modifiedData data to validate
         * @throws ValidationException if data is invalid
         */
        private void validateAndConfirm(Map<String, String> modifiedData) throws ValidationException {
            // Validate all data
            Map<String, String> errors = DataValidator.validateAllData(
                modifiedData.get("fullName"),
                modifiedData.get("email"),
                modifiedData.get("phone"),
                modifiedData.get("nationality")
            );
            
            // If there are validation errors, throw exception to activate error case
            if (!errors.isEmpty()) {
                throw new ValidationException("Data validation failed", errors);
            }
            
            // Display modified data for confirmation
            System.out.println("\n=== Modified Data for Confirmation ===");
            System.out.println("Full Name: " + modifiedData.get("fullName"));
            System.out.println("Email: " + modifiedData.get("email"));
            System.out.println("Phone Number: " + modifiedData.get("phone"));
            System.out.println("Nationality: " + modifiedData.get("nationality"));
        }
        
        /**
         * Ask user to confirm changes (Flow step 5)
         * @return true if user confirms, false otherwise
         */
        private boolean confirmChanges() {
            System.out.print("\nDo you want to save these changes? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            return confirmation.equals("yes") || confirmation.equals("y");
        }
        
        /**
         * Save modified data to database (Flow step 6)
         * @param modifiedData data to save
         * @throws DatabaseConnectionException if connection fails
         */
        private void saveModifiedData(Map<String, String> modifiedData) throws DatabaseConnectionException {
            // Create updated tourist object
            Tourist updatedTourist = new Tourist(
                currentTourist.getUsername(),
                currentTourist.getPassword(),
                modifiedData.get("fullName"),
                modifiedData.get("email"),
                modifiedData.get("phone"),
                modifiedData.get("nationality")
            );
            
            // Update in database
            boolean success = TouristDatabase.updateTourist(updatedTourist);
            
            if (success) {
                // Update current tourist reference
                currentTourist = updatedTourist;
                System.out.println("Data saved successfully to database.");
            } else {
                System.out.println("Failed to save data. Tourist not found in database.");
            }
        }
        
        /**
         * Handle validation errors (Error case)
         * @param e validation exception
         */
        private void handleValidationError(ValidationException e) {
            System.out.println("\n=== Validation Errors ===");
            System.out.println(e.getMessage());
            
            Map<String, String> errors = e.getErrors();
            for (Map.Entry<String, String> error : errors.entrySet()) {
                System.out.println("- " + error.getKey() + ": " + error.getValue());
            }
            
            System.out.println("\nPlease correct the errors and try again.");
        }
        
        /**
         * Handle database connection errors
         * @param e database exception
         */
        private void handleDatabaseError(DatabaseConnectionException e) {
            System.out.println("\n=== Database Error ===");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please try again later or contact system administrator.");
        }
    }
    
    /**
     * Main method - entry point of the application
     */
    public static void main(String[] args) {
        ModifyTouristDataApp app = new ModifyTouristDataApp();
        app.run();
    }
}