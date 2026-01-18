package com.example.presentation;

import com.example.application.AuthenticationService;
import com.example.application.TouristAccountService;
import com.example.application.AuthenticationResult;
import com.example.domain.TouristData;

import java.util.Date; // For TouristForm birthDate

/**
 * Presentation Layer: Handles HTTP requests related to tourist account management.
 * Acts as an entry point for user interactions.
 */
public class TouristController {

    private final TouristAccountService touristAccountService;
    private final TouristView touristView;
    private final AuthenticationService authenticationService; // Added based on sequence diagram REQ-001

    /**
     * Constructor for TouristController.
     * @param touristAccountService The application service for tourist account operations.
     * @param touristView The view component for displaying information to the user.
     * @param authenticationService The service responsible for user authentication.
     */
    public TouristController(TouristAccountService touristAccountService, TouristView touristView, AuthenticationService authenticationService) {
        this.touristAccountService = touristAccountService;
        this.touristView = touristView;
        this.authenticationService = authenticationService;
    }

    /**
     * Simulates the authentication process.
     * @param credentials Placeholder for authentication credentials.
     * @return An AuthenticationResult indicating success or failure.
     */
    public AuthenticationResult authenticateTourist(Credentials credentials) {
        System.out.println("Controller: Authenticating tourist...");
        AuthenticationResult result = authenticationService.authenticate(credentials);
        if (!result.isSuccess()) {
            touristView.displayError("Authentication failed. Please login.");
        }
        return result;
    }

    /**
     * Displays the form for editing tourist data.
     * Corresponds to `displayEditForm(touristId: String)` in the class diagram.
     * @param touristId The ID of the tourist whose data is to be edited.
     * @return A TouristView object, which would typically be rendered by the browser.
     */
    public TouristView displayEditForm(String touristId) {
        System.out.println("Controller: Displaying edit form for touristId: " + touristId);
        // Assuming authentication check is done prior to calling this in a real app
        // Here, we just check if a valid touristId is provided.
        if (touristId == null || touristId.isEmpty()) {
            touristView.displayError("Invalid tourist ID provided.");
            return touristView;
        }

        TouristData touristData = touristAccountService.loadTouristData(touristId);

        if (touristData == null) {
            // Error already handled by service (e.g., ETOUR or data not found)
            touristView.displayError("Server connection interrupted. Please try again later."); // Generic error for ETOUR or other load failures
        } else {
            touristView.render(touristData);
        }
        return touristView;
    }

    /**
     * Submits the edited form data for processing.
     * Corresponds to `submitEditForm(touristId: String, formData: TouristForm)` in the class diagram.
     * @param touristId The ID of the tourist whose data is being updated.
     * @param formData The form data submitted by the user.
     * @return A message string indicating the outcome, or a redirect URL in a real web app.
     */
    public String submitEditForm(String touristId, TouristForm formData) {
        System.out.println("Controller: Submitting edit form for touristId: " + touristId);
        // 5. Browser -> Controller : submitEditForm
        // 6. Controller -> Service : updateTouristAccount
        boolean updateInitiatedSuccessfully = touristAccountService.updateTouristAccount(touristId, formData);

        if (updateInitiatedSuccessfully) {
            // Flow 6 continues: System checks and initially processes modified information.
            // Controller asks for confirmation.
            // Controller -> TouristView : displayConfirmationPrompt
            requestConfirmationDisplay("Confirm data changes?");
            return "Confirmation Pending"; // Indicate that confirmation is needed
        } else {
            // Invalid data or other issues before confirmation
            // Service would have returned false if validation failed or an error occurred.
            // Controller -> Browser : displayError
            touristView.displayError("Invalid data provided. Please correct it.");
            return "Update Failed: Invalid Data";
        }
    }

    /**
     * Requests the TouristView to display a confirmation prompt to the user.
     * Corresponds to `requestConfirmationDisplay(message: String)` in the class diagram.
     * @param message The confirmation message to display.
     */
    public void requestConfirmationDisplay(String message) {
        System.out.println("Controller: Requesting confirmation display: " + message);
        boolean promptDisplayed = touristView.displayConfirmationPrompt(message); // Capture return value to satisfy sequence diagram M38
        // The 'promptDisplayed' value is captured, acknowledging the return message from TouristView.
        // In a real application, this might be used for logging or further state management.
    }

    /**
     * Handles the user's response to a confirmation prompt.
     * Corresponds to `handleConfirmationResponse(touristId: String, confirmed: boolean)` in the class diagram.
     * @param touristId The ID of the tourist whose data is being confirmed.
     * @param confirmed True if the user confirmed, false otherwise.
     * @return A message string indicating the final outcome.
     */
    public String handleConfirmationResponse(String touristId, boolean confirmed) {
        System.out.println("Controller: Handling confirmation response for touristId: " + touristId + ", confirmed: " + confirmed);
        // Tourist confirms (Flow 9. continues) or cancels (Exit Condition 2)
        // Browser -> Controller : handleConfirmationResponse
        // Controller -> Service : processConfirmationResult
        boolean finalUpdateSuccess = touristAccountService.processConfirmationResult(touristId, confirmed);

        if (confirmed) {
            if (finalUpdateSuccess) {
                // Save successful
                // Controller -> Browser : displaySuccess
                touristView.displaySuccess("Data successfully updated.");
                return "Update Success";
            } else {
                // Save failed (e.g., ETOUR during save)
                // Controller -> Browser : displayError
                touristView.displayError("Server connection interrupted during save. Please try again later.");
                return "Update Failed: Server Connection Interrupted";
            }
        } else {
            // Tourist cancels (Exit Condition 2)
            // Controller -> Browser : displayMessage
            touristView.displayMessage("Operation cancelled.");
            return "Operation Cancelled";
        }
    }

    // --- Helper classes for demonstration ---
    /**
     * Placeholder for authentication credentials.
     */
    public static class Credentials {
        public String username;
        public String password;

        public Credentials(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}