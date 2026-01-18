package com.example;

import com.example.controller.ChangePasswordUseCaseController;
import com.example.validation.PasswordConfirmationStrategy;
import com.example.validation.PasswordValidationStrategy;
import com.example.error.ErrorNotificationSubject;
import com.example.error.UIErrorDisplay;
import com.example.service.PasswordChangeService;
import com.example.repository.UserRepository;
import com.example.model.User;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        PasswordValidationStrategy validator = new PasswordConfirmationStrategy();
        ErrorNotificationSubject errorSubject = new ErrorNotificationSubject();
        UIErrorDisplay errorDisplay = new UIErrorDisplay();
        errorSubject.attach(errorDisplay);

        UserRepository userRepository = new UserRepository();
        // Create a sample user for testing
        User user = new User("user123");
        userRepository.save(user);

        PasswordChangeService passwordService = new PasswordChangeService(userRepository);
        ChangePasswordUseCaseController controller = new ChangePasswordUseCaseController(
                validator, errorSubject, passwordService, userRepository);

        // Simulate the sequence diagram
        System.out.println("=== Starting Password Change Use Case ===");
        // Entry condition: button pressed
        controller.pressPasswordChangeButton();

        // Loop: user retries until success (simulated with one failure then success)
        System.out.println("\n--- Attempt 1: Failure due to mismatch ---");
        controller.handlePasswordChange("user123", "newPass", "wrongConfirmation");
        // Simulate user reading error and confirming
        errorDisplay.confirmReading();

        System.out.println("\n--- Attempt 2: Success ---");
        controller.handlePasswordChange("user123", "newPass", "newPass");

        System.out.println("\n=== Use Case Finished ===");
    }
}