package presentation;

import application.RegistrationService;
import presentation.dto.RegistrationFormDTO;
import presentation.dto.RegistrationResultDTO;

import java.util.Map;

/**
 * Presentation Layer: Handles HTTP requests for registration.
 * Acts as the entry point for visitor registration.
 */
public class RegistrationController {
    // Dependency: RegistrationService
    private final RegistrationService registrationService;

    /**
     * Constructor for RegistrationController.
     * Injects the RegistrationService dependency.
     *
     * @param registrationService The service responsible for registration logic.
     */
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Simulates displaying the registration form to the user.
     * Modified to satisfy R4 and recommendation 2.
     */
    public void displayRegistrationForm() {
        System.out.println("--- Displaying Registration Form ---");
        System.out.println("Please fill in your details to register.");
        // In a real application, this would render an HTML form or a UI component.
    }

    /**
     * Handles the submission of the registration form.
     * Calls the RegistrationService to process the registration request.
     * Modified to satisfy recommendation 5.
     *
     * @param formDTO The data transfer object containing registration details from the form.
     * @return A RegistrationResultDTO indicating success or failure and any messages/errors.
     */
    public RegistrationResultDTO submitRegistration(RegistrationFormDTO formDTO) {
        System.out.println("\n--- Registration Form Submitted ---");
        System.out.println("Received registration data for username: " + formDTO.getUsername());

        // Delegate the registration logic to the Application Layer (RegistrationService)
        RegistrationResultDTO resultDTO = registrationService.registerVisitor(formDTO);

        // Simulate displaying the result back to the UI
        displayRegistrationResult(resultDTO);

        return resultDTO;
    }

    /**
     * Simulates displaying the registration result back to the UI.
     * This method would typically interact with the view layer to render the result.
     *
     * @param resultDTO The result of the registration attempt.
     */
    private void displayRegistrationResult(RegistrationResultDTO resultDTO) {
        System.out.println("\n--- Displaying Registration Result ---");
        if (resultDTO.getSuccess()) {
            System.out.println("SUCCESS: " + resultDTO.getMessage());
            displayRegistrationConfirmation(); // m26
        } else {
            System.out.println("FAILURE: " + resultDTO.getMessage());
            displayErrors(resultDTO.getErrors()); // m13
        }
    }

    /**
     * Displays a registration confirmation message to the UI.
     * Corresponds to messages m26 (displayRegistrationConfirmation) and m27 (showRegistrationConfirmation) in the sequence diagram.
     */
    private void displayRegistrationConfirmation() {
        System.out.println("UI: Showing registration confirmation.");
    }

    /**
     * Displays validation errors to the UI.
     * Corresponds to messages m13 (displayErrors) and m14 (showValidationErrors) in the sequence diagram.
     *
     * @param errors A map of field names to their specific error messages.
     */
    private void displayErrors(Map<String, String> errors) {
        if (!errors.isEmpty()) {
            System.out.println("Validation Errors:");
            errors.forEach((field, error) ->
                    System.out.println(" - " + field + ": " + error)
            );
        }
        System.out.println("UI: Showing validation errors.");
    }
}