package com.example.usereditor;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom exception for data validation errors.
 * This exception is thrown when user input does not meet the required criteria.
 */
class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}

/**
 * Enum to represent different roles a user can have.
 * This helps in defining permissions and system behavior based on user roles.
 */
enum UserRole {
    ADMIN,
    REGULAR_USER
}

/**
 * Represents a User entity in the system.
 * Contains basic user information such as ID, username, email, password, and role.
 */
class User {
    private String id;
    private String username;
    private String email;
    private String password; // In a real system, this would be hashed
    private UserRole role;

    /**
     * Constructor for creating a new User object.
     *
     * @param id       Unique identifier for the user.
     * @param username The user's chosen username.
     * @param email    The user's email address.
     * @param password The user's password (plaintext for this example).
     * @param role     The role of the user (e.g., ADMIN, REGULAR_USER).
     */
    public User(String id, String username, String email, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    // --- Setters ---
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Overrides the toString method to provide a user-friendly representation of the User object.
     *
     * @return A string containing the user's details.
     */
    @Override
    public String toString() {
        return "User ID: " + id +
                "\n  Username: " + username +
                "\n  Email: " + email +
                "\n  Role: " + role;
    }

    /**
     * Overrides equals method for comparing User objects based on their ID.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    /**
     * Overrides hashCode method consistent with the equals method.
     *
     * @return The hash code for the User object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

/**
 * Service class responsible for managing user-related operations.
 * This includes retrieving, validating, and updating user data.
 * It simulates a data store using a HashMap.
 */
class UserService {
    // A simple in-memory database simulation using a HashMap
    private Map<String, User> users;

    /**
     * Constructor for UserService.
     * Initializes the user data store with some dummy users, including an administrator.
     */
    public UserService() {
        this.users = new HashMap<>();
        // Add some dummy users for testing
        users.put("user001", new User("user001", "admin_alex", "alex@example.com", "adminpass", UserRole.ADMIN));
        users.put("user002", new User("user002", "john_doe", "john.doe@example.com", "password123", UserRole.REGULAR_USER));
        users.put("user003", new User("user003", "jane_smith", "jane.smith@example.com", "securepass", UserRole.REGULAR_USER));
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User getUserById(String userId) {
        return users.get(userId);
    }

    /**
     * Validates the data of a user object.
     * Checks for non-empty fields, valid email format, and uniqueness of username/email.
     *
     * @param userToValidate The User object containing the data to validate.
     * @throws ValidationException If any validation rule is violated.
     */
    public void validateUserData(User userToValidate) throws ValidationException {
        if (userToValidate == null) {
            throw new ValidationException("User data cannot be null.");
        }
        if (userToValidate.getUsername() == null || userToValidate.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty.");
        }
        if (userToValidate.getEmail() == null || userToValidate.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty.");
        }

        // Basic email format validation
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher emailMatcher = emailPattern.matcher(userToValidate.getEmail());
        if (!emailMatcher.matches()) {
            throw new ValidationException("Invalid email format.");
        }

        // Check for uniqueness of username and email (excluding the user being edited)
        for (User existingUser : users.values()) {
            if (!existingUser.getId().equals(userToValidate.getId())) { // Don't compare with itself
                if (existingUser.getUsername().equalsIgnoreCase(userToValidate.getUsername())) {
                    throw new ValidationException("Username '" + userToValidate.getUsername() + "' already exists.");
                }
                if (existingUser.getEmail().equalsIgnoreCase(userToValidate.getEmail())) {
                    throw new ValidationException("Email '" + userToValidate.getEmail() + "' already exists.");
                }
            }
        }
    }

    /**
     * Edits an existing user's information.
     * This method first validates the new data and then updates the user in the data store.
     *
     * @param updatedUser The User object containing the updated information.
     * @return The updated User object.
     * @throws ValidationException If the updated user data is invalid.
     * @throws IllegalArgumentException If the user to be updated does not exist.
     */
    public User editUser(User updatedUser) throws ValidationException, IllegalArgumentException {
        if (updatedUser == null || updatedUser.getId() == null) {
            throw new IllegalArgumentException("Updated user object or its ID cannot be null.");
        }

        // Ensure the user actually exists before attempting to edit
        if (!users.containsKey(updatedUser.getId())) {
            throw new IllegalArgumentException("User with ID '" + updatedUser.getId() + "' not found for editing.");
        }

        // Validate the updated data
        validateUserData(updatedUser);

        // Update the user in the map
        users.put(updatedUser.getId(), updatedUser);
        return updatedUser;
    }
}

/**
 * Main class to simulate the Administrator's interaction with the system
 * for editing user details.
 * This class acts as the entry point and orchestrates the use case flow.
 */
public class AdminUserEditor {

    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isAdminLoggedIn = false; // Simulates admin login state

    /**
     * Simulates the administrator login process.
     * For this example, it's a simple check. In a real system, this would involve
     * authentication against a database.
     *
     * @param username The username provided by the administrator.
     * @param password The password provided by the administrator.
     * @return true if login is successful, false otherwise.
     */
    private static boolean loginAdmin(String username, String password) {
        // In a real system, this would query a database and verify credentials
        // For this simulation, we'll use the dummy admin_alex user
        User adminUser = userService.getUserById("user001"); // Assuming user001 is the admin
        if (adminUser != null && adminUser.getRole() == UserRole.ADMIN &&
            adminUser.getUsername().equals(username) && adminUser.getPassword().equals(password)) {
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        }
        System.out.println("Invalid administrator credentials.");
        return false;
    }

    /**
     * Displays the details of a given user.
     *
     * @param user The User object whose details are to be displayed.
     */
    private static void displayUserDetails(User user) {
        if (user != null) {
            System.out.println("\n--- Current User Details ---");
            System.out.println(user);
            System.out.println("----------------------------");
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * Main method where the program execution begins.
     * It simulates the "Edit user" use case step-by-step.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the User Management System (Admin Panel)");

        // Precondition 1: The user is logged in as an administrator
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Enter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        if (!loginAdmin(adminUsername, adminPassword)) {
            System.out.println("Access denied. Exiting program.");
            scanner.close();
            return;
        }
        isAdminLoggedIn = true;

        // Precondition 2: The user has carried out the case of use \"viewdetTailsente\"
        // and the system is Displaying a user details
        System.out.println("\n--- View User Details for Editing ---");
        System.out.print("Enter the ID of the user to edit: ");
        String userIdToEdit = scanner.nextLine();

        User userToEdit = userService.getUserById(userIdToEdit);

        if (userToEdit == null) {
            System.out.println("Error: User with ID '" + userIdToEdit + "' not found.");
            System.out.println("Administrator interrupts the connection operation to the SMOS server.");
            scanner.close();
            return;
        }

        displayUserDetails(userToEdit);

        // Event Sequence: User clicks \"Edit\" button (simulated by proceeding)
        System.out.println("\n--- Edit User Information ---");
        System.out.println("Enter new details (leave blank to keep current value):");

        // Create a temporary User object to hold potential updates
        // We copy the existing user's data first
        User updatedUser = new User(
            userToEdit.getId(),
            userToEdit.getUsername(),
            userToEdit.getEmail(),
            userToEdit.getPassword(), // Password is not typically edited directly via this form
            userToEdit.getRole()
        );

        System.out.print("New Username [" + userToEdit.getUsername() + "]: ");
        String newUsername = scanner.nextLine();
        if (!newUsername.trim().isEmpty()) {
            updatedUser.setUsername(newUsername.trim());
        }

        System.out.print("New Email [" + userToEdit.getEmail() + "]: ");
        String newEmail = scanner.nextLine();
        if (!newEmail.trim().isEmpty()) {
            updatedUser.setEmail(newEmail.trim());
        }

        System.out.print("New Role (ADMIN/REGULAR_USER) [" + userToEdit.getRole() + "]: ");
        String newRoleStr = scanner.nextLine();
        if (!newRoleStr.trim().isEmpty()) {
            try {
                UserRole newRole = UserRole.valueOf(newRoleStr.trim().toUpperCase());
                updatedUser.setRole(newRole);
            } catch (IllegalArgumentException e) {
                System.out.println("Warning: Invalid role entered. Keeping current role: " + userToEdit.getRole());
            }
        }

        // Event Sequence: System checks validity and updates
        try {
            User resultUser = userService.editUser(updatedUser);
            System.out.println("\n--- User Successfully Updated ---");
            displayUserDetails(resultUser);
            System.out.println("Postcondition: The user has been modified.");
        } catch (ValidationException e) {
            // Activates the case of \"Errodati\" use
            System.out.println("\n--- Data Validation Error ---");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Postcondition: The data error is notified (activates 'Errodati' use case).");
        } catch (IllegalArgumentException e) {
            System.out.println("\n--- System Error ---");
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("\nPostcondition: The administrator interrupts the connection operation to the SMOS server interrupted.");
            scanner.close();
            System.out.println("Program terminated.");
        }
    }
}