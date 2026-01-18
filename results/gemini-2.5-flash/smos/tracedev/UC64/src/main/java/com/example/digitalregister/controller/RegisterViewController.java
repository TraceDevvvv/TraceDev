package com.example.digitalregister.controller;

import com.example.digitalregister.service.RegisterService;
import com.example.digitalregister.service.AuthenticationService;
import com.example.digitalregister.view.RegisterView;
import com.example.digitalregister.model.DigitalRegister;
import com.example.digitalregister.exceptions.DataAccessError;

import java.util.List;

/**
 * Handles user interactions for viewing digital registers.
 * It acts as the "control" part of the MVC pattern, orchestrating between the view and the service layer.
 */
public class RegisterViewController {

    private final RegisterService registerService; // Composition: RegisterViewController *-- RegisterService
    private final RegisterView registerView;       // Composition: RegisterViewController *-- RegisterView
    private final AuthenticationService authenticationService; // Association: RegisterViewController ..> AuthenticationService

    private static final String CURRENT_USER_ID = "DirectionUser"; // Dummy user ID for authentication checks

    /**
     * Constructs a RegisterViewController with necessary dependencies.
     * @param registerService The service handling business logic for registers.
     * @param registerView The view handling display logic for registers.
     * @param authenticationService The service to check user authentication status.
     */
    public RegisterViewController(RegisterService registerService, RegisterView registerView, AuthenticationService authenticationService) {
        this.registerService = registerService;
        this.registerView = registerView;
        this.authenticationService = authenticationService;
    }

    /**
     * Handles the event when the user clicks "Digital Register".
     * This is the entry point for the sequence diagram's main flow.
     * Entry Conditions: Direction IS logged in to the system.
     * Flow of Events 1: Direction clicks on "Digital Register" button.
     */
    public void handleDigitalRegisterClick() {
        System.out.println("\n[RegisterViewController] User clicked 'Digital Register'.");

        // Check login status (Entry Condition)
        if (!authenticationService.isLoggedIn(CURRENT_USER_ID)) {
            registerView.displayErrorMessage("User is not logged in. Please log in to proceed.");
            return;
        }

        // Controller -> View : displayAcademicYearSelection()
        registerView.displayAcademicYearSelection();
        // The flow then expects user input via getSelectedAcademicYearId, which will call handleAcademicYearSelection
    }

    /**
     * Handles the event when the user selects an academic year.
     * Flow of Events 3: Direction selects the academic year.
     * @param yearId The ID of the selected academic year.
     */
    public void handleAcademicYearSelection(String yearId) {
        System.out.println("[RegisterViewController] User selected academic year: " + yearId);

        if (yearId == null || yearId.trim().isEmpty()) {
            // This case might happen if getSelectedAcademicYearId in view returns null on cancellation
            registerView.displayErrorMessage("Academic year selection is invalid.");
            return;
        }

        try {
            // Controller -> Service : getRegistersByAcademicYear(yearId)
            List<DigitalRegister> registers = registerService.getRegistersByAcademicYear(yearId);

            // Controller -> View : displayRegisters(registers)
            registerView.displayRegisters(registers);
            // Exit Condition: The system is viewing the list of registers.
        } catch (DataAccessError e) {
            // Handles 'Connection to SMOS server IS interrupted' exit condition
            System.err.println("[RegisterViewController] Data access error: " + e.getMessage());
            // Controller -> View : displayErrorMessage(...)
            registerView.displayErrorMessage("Failed to retrieve registers. " + e.getMessage());
        }
    }

    /**
     * Handles the event when the user cancels an operation.
     * Exit Conditions: Direction interrupts the operation.
     */
    public void handleCancellation() {
        System.out.println("[RegisterViewController] Operation cancelled by user.");
        registerView.displayErrorMessage("Operation cancelled.");
        registerView.resetCancellation(); // Reset cancellation state in view
        // In a real application, this might involve stopping background tasks or navigating back.
    }
}