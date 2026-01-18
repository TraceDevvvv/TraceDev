package com.example.application;

import com.example.application.boundary.TagSearchForm;
import com.example.application.controller.InsertTagSearchController;
import com.example.application.dto.TagSearchDTO;
import com.example.application.service.*;
import com.example.application.repository.JpaTagRepository;
import com.example.application.repository.TagRepository;

import java.time.LocalDateTime;

/**
 * Main class to run the application and simulate the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Create serv
        TagRepository tagRepository = new JpaTagRepository();
        NotificationService notificationService = new NotificationService();
        ErrorHandler errorHandler = new ErrorHandler();
        AuthenticationService authService = new AuthenticationService();
        ServerConnector serverConnector = new ServerConnector("http://etour-server.example.com");

        // Create TagSearchService
        TagSearchService tagSearchService = new TagSearchService(
                tagRepository,
                notificationService,
                errorHandler,
                authService,
                serverConnector
        );

        // Simulate Agency Operator
        String operatorId = "operator123"; // Simulate logged-in user

        // Create controller
        InsertTagSearchController controller = new InsertTagSearchController(
                tagSearchService,
                authService,
                operatorId
        );

        // Create form
        TagSearchForm form = new TagSearchForm(controller);

        // Simulate sequence diagram flow
        System.out.println("=== Starting Tag Search Insertion Flow ===");

        // AO: Check login status (actor action)
        // m1: AuthenticationService receives login check (implied)
        // m2: Return Login status
        boolean loggedIn = authService.isLoggedIn(operatorId);
        System.out.println("Login status: " + loggedIn);

        if (!loggedIn) {
            errorHandler.showLoginError();
            return;
        }

        // m3: AO to Form - Access insert tag search functionality
        // m4: Form to AO - Display form
        form.display();

        // m5: AO to Form - Fill form with required information
        // m6: AO to Form - Submit form
        TagSearchDTO formData = form.getFormData();
        System.out.println("Form data collected - Tag: " + formData.getTag() + 
                         ", Operator: " + formData.getOperatorId());

        // m7: Form to Controller - submitForm(formData)
        // m9: Controller to Service - insertTagSearch(dto)
        Result result = form.submitForm(formData);

        // Display final result (m33)
        form.displayResult(result);

        System.out.println("=== Flow Completed ===");
    }
}