package com.example.app;

import com.example.app.model.User;
import com.example.app.dto.UserFormDto;
import com.example.app.view.UserView;
import com.example.app.controller.UserController;
import com.example.app.service.UserService;
import com.example.app.service.ValidationService;
import com.example.app.repository.UserRepositoryImpl;
import com.example.app.infrastructure.Database;

/**
 * Main application class to demonstrate the flow.
 * It simulates the Administrator's interaction with the system.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Setup Infrastructure Layer
        Database database = new Database();

        // 2. Setup Data Persistence Layer
        // Initialize with some dummy user data for demonstration
        User initialUser = new User("user123", "johndoe", "john.doe@example.com", "John", "Doe", "hashedPassword123");
        database.persist(initialUser); // Add initial user to database
        System.out.println("--- Initial User in DB: " + initialUser.getUsername());

        UserRepositoryImpl userRepository = new UserRepositoryImpl(database);

        // 3. Setup Application (Service) Layer
        ValidationService validationService = new ValidationService();
        UserService userService = new UserService(userRepository, validationService);

        // 4. Setup Presentation Layer
        UserController userController = new UserController(userService);
        UserView userView = new UserView(userController);
        // Link UserController back to UserView for feedback, as per SD (to resolve circular dependency for messages)
        userController.setUserView(userView); // ADDED: Ensures controller can call view methods for feedback

        // --- Simulate Administrator Interaction ---

        System.out.println("\n--- Scenario 1: Administrator changes user data (Valid) ---");
        // Entry Conditions: Admin logged in, user details displayed (simulated by initial user and getUserInput)
        // m1: Admin (note) Entry Conditions: Admin logged in, user details displayed.
        userView.displayUserDetails(userView.getUserInputForDisplay("user123")); // Simulate initial display

        // Admin modifies fields in the UI. (m3: View note)
        // Admin submits the form with updated data.
        UserFormDto validUserData = userView.getUserInput(); // Simulate Administrator modifying fields (part of m2 implicitly)
        // m2: Admin -> View : modifyUserDetailsForm(updatedData)
        userView.modifyUserDetailsForm(validUserData); // Simulate Admin submitting the form

        System.out.println("\n--- Scenario 2: Administrator tries to change user data (Invalid - empty username) ---");
        // Simulate a scenario where validation should fail (e.g., empty username)
        UserFormDto invalidUserData = new UserFormDto("user123", "", "jane.doe@example.com", "Jane", "Doe");
        // m2: Admin -> View : modifyUserDetailsForm(updatedData)
        userView.modifyUserDetailsForm(invalidUserData);
        // The displayValidationError will be called by the controller if validation fails.
        // No explicit check needed here for `successInvalid` because `modifyUserDetailsForm` already delegates and feedback is handled by the Controller calling View.

        System.out.println("\n--- Application simulation finished ---");
        // m21: Admin (note) Exit Condition: The user has been modified.
    }
}