import java.util.*;
import java.util.regex.*;

/**
 * Complete Java program for Edit User use case.
 * This program simulates an administrator editing user data with validation and error handling.
 * 
 * Use Case: Edit User
 * Actors: Administrator
 * Description: Changing one or more data concerning a user present in the system
 * 
 * Preconditions:
 * - The user is logged in as an administrator
 * - The user has carried out the case of use "viewdetTailsente" and the system is displaying user details
 * - The user changes one or more information contained in the form of displaying user details
 * 
 * Events sequence:
 * 1. User clicks on the "Edit" button
 * 2. System checks the validity of the data entered and changes the user
 *    - If data is invalid, activates the case of use "Errodati"
 * 
 * Postconditions:
 * - The user has been modified
 * - The data error is notified
 * - The administrator interrupts the connection operation to the SMOS server interrupted
 */

// Custom exception for data validation errors
class DataValidationException extends Exception {
    public DataValidationException(String message) {
        super(message);
    }
}

// Custom exception for SMOS server connection interruption
class SMOSConnectionInterruptedException extends Exception {
    public SMOSConnectionInterruptedException(String message) {
        super(message);
    }
}

// User class representing a user in the system
class User {
    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String department;
    private boolean isActive;
    
    public User(String userId, String username, String email, String phoneNumber, String department, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.isActive = isActive;
    }
    
    // Getters and setters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDepartment() { return department; }
    public boolean isActive() { return isActive; }
    
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setDepartment(String department) { this.department = department; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
    
    // Display user details (simulating the "viewdetTailsente" use case)
    public void displayDetails() {
        System.out.println("\n=== User Details ===");
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Department: " + department);
        System.out.println("Active Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("===================\n");
    }
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", username=" + username + ", email=" + email + 
               ", phoneNumber=" + phoneNumber + ", department=" + department + 
               ", isActive=" + isActive + "]";
    }
}

// Administrator class representing the administrator actor
class Administrator {
    private String adminId;
    private String adminName;
    private boolean isLoggedIn;
    
    public Administrator(String adminId, String adminName) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.isLoggedIn = false;
    }
    
    public String getAdminId() { return adminId; }
    public String getAdminName() { return adminName; }
    public boolean isLoggedIn() { return isLoggedIn; }
    
    public void login() {
        this.isLoggedIn = true;
        System.out.println("Administrator " + adminName + " logged in successfully.");
    }
    
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("Administrator " + adminName + " logged out.");
    }
    
    // Simulate viewing user details (precondition for edit)
    public void viewUserDetails(User user) {
        if (!isLoggedIn) {
            System.out.println("Error: Administrator must be logged in to view user details.");
            return;
        }
        System.out.println("Administrator " + adminName + " is viewing user details...");
        user.displayDetails();
    }
    
    // Simulate clicking the "Edit" button
    public void clickEditButton() {
        if (!isLoggedIn) {
            System.out.println("Error: Administrator must be logged in to edit user.");
            return;
        }
        System.out.println("Administrator " + adminName + " clicked the 'Edit' button.");
    }
}

// Data validator for user input
class UserDataValidator {
    
    // Validate email format
    private static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    // Validate phone number format (simple validation for demonstration)
    private static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        // Allow digits, spaces, hyphens, and parentheses
        String phoneRegex = "^[\\d\\s\\-\\(\\)\\+]{10,20}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    
    // Validate username (non-empty, alphanumeric with underscores)
    private static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        String usernameRegex = "^[a-zA-Z0-9_]{3,50}$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    
    // Validate department (non-empty)
    private static boolean isValidDepartment(String department) {
        return department != null && !department.trim().isEmpty();
    }
    
    // Comprehensive validation of all user data
    public static void validateUserData(String username, String email, String phoneNumber, String department) 
            throws DataValidationException {
        
        List<String> errors = new ArrayList<>();
        
        if (!isValidUsername(username)) {
            errors.add("Username must be 3-50 characters long and contain only letters, numbers, and underscores.");
        }
        
        if (!isValidEmail(email)) {
            errors.add("Email must be in a valid format (e.g., user@example.com).");
        }
        
        if (!isValidPhoneNumber(phoneNumber)) {
            errors.add("Phone number must be 10-20 characters and contain only digits, spaces, hyphens, parentheses, or plus sign.");
        }
        
        if (!isValidDepartment(department)) {
            errors.add("Department cannot be empty.");
        }
        
        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Data validation failed:\n");
            for (String error : errors) {
                errorMessage.append("- ").append(error).append("\n");
            }
            throw new DataValidationException(errorMessage.toString());
        }
    }
}

// System class that handles the edit user flow
class EditUserSystem {
    private Administrator admin;
    private User currentUser;
    private boolean smosServerConnected;
    
    public EditUserSystem(Administrator admin, User user) {
        this.admin = admin;
        this.currentUser = user;
        this.smosServerConnected = true; // Initially connected
    }
    
    // Simulate connection to SMOS server
    private void connectToSMOSServer() throws SMOSConnectionInterruptedException {
        if (!smosServerConnected) {
            throw new SMOSConnectionInterruptedException("SMOS server connection is already interrupted.");
        }
        System.out.println("Connected to SMOS server for user data update.");
    }
    
    // Simulate interruption of SMOS server connection (as per postconditions)
    private void interruptSMOSConnection() {
        smosServerConnected = false;
        System.out.println("SMOS server connection interrupted as per postconditions.");
    }
    
    // Main method to handle the edit user use case
    public void editUser(String newUsername, String newEmail, String newPhoneNumber, String newDepartment, boolean newActiveStatus) {
        try {
            // Check preconditions
            if (!admin.isLoggedIn()) {
                System.out.println("Error: Administrator must be logged in to edit user.");
                return;
            }
            
            System.out.println("\n=== Starting Edit User Use Case ===");
            
            // Step 1: Administrator clicks "Edit" button
            admin.clickEditButton();
            
            // Step 2: System checks validity of data
            System.out.println("Validating entered data...");
            UserDataValidator.validateUserData(newUsername, newEmail, newPhoneNumber, newDepartment);
            
            // Connect to SMOS server for update
            connectToSMOSServer();
            
            // Update user data
            System.out.println("Updating user data...");
            currentUser.setUsername(newUsername);
            currentUser.setEmail(newEmail);
            currentUser.setPhoneNumber(newPhoneNumber);
            currentUser.setDepartment(newDepartment);
            currentUser.setActive(newActiveStatus);
            
            // Interrupt SMOS connection as per postconditions
            interruptSMOSConnection();
            
            System.out.println("User data updated successfully!");
            System.out.println("Postconditions met:");
            System.out.println("1. The user has been modified");
            System.out.println("2. SMOS server connection interrupted");
            
            // Display updated user details
            currentUser.displayDetails();
            
        } catch (DataValidationException e) {
            // Handle invalid data (Errodati use case)
            System.out.println("\n=== Error Data (Errodati) Use Case Activated ===");
            System.out.println("Data error notified: " + e.getMessage());
            System.out.println("Postconditions met:");
            System.out.println("1. The data error is notified");
            System.out.println("2. The administrator interrupts the connection operation to the SMOS server interrupted");
            
            // Interrupt SMOS connection on error
            interruptSMOSConnection();
            
        } catch (SMOSConnectionInterruptedException e) {
            System.out.println("SMOS Server Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Getter for current user
    public User getCurrentUser() {
        return currentUser;
    }
}

// Main program class
public class EditUserProgram {
    public static void main(String[] args) {
        System.out.println("=== Edit User Program ===\n");
        
        // Create an administrator
        Administrator admin = new Administrator("ADM001", "John Doe");
        
        // Create a sample user
        User user = new User("USR001", "jane_doe", "jane.doe@example.com", 
                            "(123) 456-7890", "Engineering", true);
        
        // Create the edit user system
        EditUserSystem system = new EditUserSystem(admin, user);
        
        // Simulate the preconditions
        System.out.println("=== Simulating Preconditions ===");
        admin.login();  // Administrator logs in
        admin.viewUserDetails(user);  // Administrator views user details
        
        // Display current user details
        System.out.println("Current user details before edit:");
        user.displayDetails();
        
        // Simulate user changing information in the form
        System.out.println("=== Simulating User Input Changes ===");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter new username (current: " + user.getUsername() + "): ");
        String newUsername = scanner.nextLine();
        
        System.out.print("Enter new email (current: " + user.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        
        System.out.print("Enter new phone number (current: " + user.getPhoneNumber() + "): ");
        String newPhoneNumber = scanner.nextLine();
        
        System.out.print("Enter new department (current: " + user.getDepartment() + "): ");
        String newDepartment = scanner.nextLine();
        
        System.out.print("Enter new active status (true/false) (current: " + user.isActive() + "): ");
        boolean newActiveStatus = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        
        // Execute the edit user use case
        system.editUser(newUsername, newEmail, newPhoneNumber, newDepartment, newActiveStatus);
        
        // Test case with invalid data (to demonstrate error handling)
        System.out.println("\n=== Testing Error Handling with Invalid Data ===");
        System.out.println("Simulating edit with invalid data...");
        
        // Create a new system instance for testing
        User testUser = new User("USR002", "test_user", "test@example.com", 
                                "1234567890", "Sales", true);
        EditUserSystem testSystem = new EditUserSystem(admin, testUser);
        
        // Try to edit with invalid data
        testSystem.editUser("ab", "invalid-email", "short", "", false);
        
        // Clean up
        admin.logout();
        scanner.close();
        
        System.out.println("\n=== Program Execution Complete ===");
    }
}