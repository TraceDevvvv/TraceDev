package com.etour.registration;

import com.etour.registration.adapters.AccountRepositoryImpl;
import com.etour.registration.adapters.EmailNotificationAdapter;
import com.etour.registration.application.CreateAccountUseCaseController;
import com.etour.registration.application.RegistrationDataValidator;
import com.etour.registration.domain.CreateAccountCommand;
import com.etour.registration.web.RegistrationController;
import com.etour.registration.web.RegistrationFormDTO;

/**
 * Main application to demonstrate the registration flow.
 */
public class MainApp {
    public static void main(String[] args) {
        // Setup dependencies (simplified)
        Object dataSource = new Object();
        Object mailClient = new Object();

        AccountRepositoryImpl repo = new AccountRepositoryImpl(dataSource);
        EmailNotificationAdapter notifier = new EmailNotificationAdapter(mailClient);
        RegistrationDataValidator validator = new RegistrationDataValidator();

        CreateAccountUseCaseController useCase = new CreateAccountUseCaseController(validator, repo, notifier);
        RegistrationController controller = new RegistrationController(useCase);

        // Simulate Guest User actions
        System.out.println("=== Guest User Registration Demo ===");

        // Step 1: Show form
        RegistrationFormDTO form = controller.showRegistrationForm();
        System.out.println("1. Form displayed.");

        // Step 2: User fills form (simulate)
        form.setUsername("john_doe");
        form.setEmail("john@example.com");
        form.setPassword("password123");
        form.setConfirmPassword("password123");

        // Step 3: Submit registration
        System.out.println("3. Submitting registration...");
        String result = controller.submitRegistration(form);
        System.out.println("Result: " + result);

        // Simulate confirmation (step 7)
        System.out.println("7. User confirms operation...");
        controller.confirmOperation();

        // Simulate cancel scenario
        System.out.println("\n--- Simulating cancellation ---");
        String cancelResult = controller.cancelOperation();
        System.out.println(cancelResult);
    }
}