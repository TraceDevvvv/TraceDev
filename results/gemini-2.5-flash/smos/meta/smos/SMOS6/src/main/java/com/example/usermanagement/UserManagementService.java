package com.example.usermanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the business logic for user management, specifically for adding new users.
 * This service orchestrates validation and persistence operations.
 */
public class UserManagementService {

    /**
     * Attempts to add a new user to the system after performing necessary validations.
     * This method simulates the "System" actions described in the use case,
     * including displaying the form (implicitly by accepting parameters),
     * making checks on data validity, and entering the new user into the archive.
     *
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param email The email address of the user.
     * @param cell The cell phone number of the user.
     * @param login The unique login username for the user.
     * @param password The password for the user's account.
     * @param confirmPassword The confirmation password, which must match the password.
     * @return A list of error messages. If the list is empty, the user was successfully created.
     *         If the list contains messages, it indicates validation failures or a login conflict.
     */
    public List<String> addNewUser(String name, String surname, String email, String cell, String login, String password, String confirmPassword) {
        List<String> errors = new ArrayList<>();

        // 1. Create a User object from the provided data.
        // Note: The password here is plain text, in a real system it would be hashed before storing.
        User newUser = new User(name, surname, email, cell, login, password);

        // 2. Make checks on the validity of the data entered using UserValidator.
        List<String> validationErrors = UserValidator.validateUser(newUser, confirmPassword);
        if (!validationErrors.isEmpty()) {
            // If data is not valid, activate the case of "Errodati" use (return errors).
            errors.addAll(validationErrors);
            return errors; // Return immediately if basic validation fails.
        }

        // 3. Check if the login is already taken. This is a business rule check beyond basic format validation.
        if (UserRepository.isLoginTaken(login)) {
            errors.add("Login '" + login + "' is already taken. Please choose a different login.");
            return errors; // Return if login is not unique.
        }

        // 4. Enter the new user in the archive (simulated by UserRepository).
        // At this point, all validations passed and login is unique.
        boolean userAdded = UserRepository.addUser(newUser);

        if (userAdded) {
            // Postcondition: A new user has been created.
            // In a real application, further actions like logging the event or sending notifications might occur.
            System.out.println("User '" + login + "' successfully created.");
        } else {
            // This case should ideally not be reached if isLoginTaken is checked first,
            // but it's a safeguard for potential race conditions or unexpected repository behavior.
            errors.add("Failed to add user due to an internal error. Please try again.");
        }

        // Return the list of errors. If empty, success.
        return errors;
    }

    /**
     * Simulates the "viewing the list of users" precondition.
     * In a real application, this would fetch and display users from the repository.
     * For this use case, it's a placeholder to acknowledge the precondition.
     */
    public void viewUsersList() {
        System.out.println("Administrator is viewing the list of users in the system.");
        // In a real system, this would involve fetching users from UserRepository
        // and preparing them for display.
        // Example: UserRepository.getAllUsers().forEach(System.out::println);
    }

    /**
     * Simulates the "administrator interrupts the connection to the SMOS server interrupted" postcondition.
     * This is a placeholder for external system interaction.
     */
    public void interruptSMOSConnection() {
        System.out.println("Administrator interrupts the connection to the SMOS server.");
        // Logic to actually interrupt connection would go here.
    }
}