import com.example.application.*;
import com.example.domain.TouristData;
import com.example.infrastructure.ITouristRepository;
import com.example.infrastructure.TouristRepositoryImpl;
import com.example.presentation.TouristController;
import com.example.presentation.TouristForm;
import com.example.presentation.TouristView;

import java.util.Date;
import java.util.Scanner;

/**
 * Main class to demonstrate the interaction between different layers and components.
 * This acts as the "Browser" and "Tourist" actor in the sequence diagram,
 * simulating user input and system responses.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Initialize all components
        ExternalErrorHandler externalErrorHandler = new ExternalErrorHandler();
        ErroredUseCaseActivator erroredUseCaseActivator = new ErroredUseCaseActivator();
        ValidationService validationService = new ValidationService();
        TouristRepositoryImpl touristRepository = new TouristRepositoryImpl(); // Concrete implementation
        AuthenticationService authenticationService = new AuthenticationService();

        TouristAccountService touristAccountService = new TouristAccountService(
                touristRepository, validationService, erroredUseCaseActivator, externalErrorHandler);
        TouristView touristView = new TouristView();
        TouristController touristController = new TouristController(
                touristAccountService, touristView, authenticationService);

        Scanner scanner = new Scanner(System.in);
        String touristId = null;
        String sessionId = null;

        System.out.println("--- Starting Tourist Account Modification Simulation ---");

        // Simulate Tourist -> Auth : authenticate (REQ-001)
        System.out.println("\nSimulating Authentication:");
        TouristController.Credentials credentials = new TouristController.Credentials("testuser", "password");
        AuthenticationResult authResult = touristController.authenticateTourist(credentials);

        if (authResult.isSuccess()) {
            touristId = authResult.getTouristId();
            sessionId = authResult.getSessionId();
            System.out.println("Authentication successful. Tourist ID: " + touristId + ", Session ID: " + sessionId);

            // Ensure the tourist is authenticated before proceeding (implicit in sequence diagram)
            if (!authenticationService.isAuthenticated(sessionId)) {
                System.err.println("Error: Session not actually authenticated after successful login.");
                scanner.close();
                return;
            }

            // Simulate initial display of the form
            System.out.println("\n--- Step 1: Display Edit Form ---");
            // Browser -> Controller : displayEditForm(touristId)
            touristController.displayEditForm(touristId);

            // Simulate user interaction: editing fields and submitting
            System.out.println("\n--- Step 2: Edit and Submit Form ---");
            System.out.println("Enter new Name (e.g., 'John P. Doe'):");
            String newName = scanner.nextLine();
            System.out.println("Enter new Email (e.g., 'john.p.doe@newemail.com'):");
            String newEmail = scanner.nextLine();
            System.out.println("Enter new Phone Number (e.g., '555-123-4567'):");
            String newPhoneNumber = scanner.nextLine();
            // Assuming birthDate is not commonly editable in forms or is edited via a date picker
            // For simplicity, we'll keep the existing birth date or set a dummy one.
            TouristData currentData = touristAccountService.loadTouristData(touristId); // Load current for birthdate
            Date newBirthDate = (currentData != null) ? currentData.getBirthDate() : new Date(90, 0, 1); // Default if not found

            TouristForm updatedFormData = new TouristForm(newName, newEmail, newPhoneNumber, newBirthDate);
            System.out.println("Browser: Submitting form data: " + updatedFormData);

            // Browser -> Controller : submitEditForm(touristId, formData)
            String submitResult = touristController.submitEditForm(touristId, updatedFormData);
            System.out.println("Controller Response after submit: " + submitResult);

            if ("Confirmation Pending".equals(submitResult)) {
                // Simulate user confirmation response
                System.out.println("\n--- Step 3: Handle Confirmation ---");
                System.out.println("Do you confirm the changes? (yes/no)");
                String confirmationInput = scanner.nextLine();
                boolean confirmed = confirmationInput.trim().equalsIgnoreCase("yes");

                // Browser -> Controller : handleConfirmationResponse(touristId, confirmed)
                String confirmationResult = touristController.handleConfirmationResponse(touristId, confirmed);
                System.out.println("Controller Response after confirmation: " + confirmationResult);

                if ("Update Success".equals(confirmationResult)) {
                    System.out.println("\n--- Final Status: Data Updated ---");
                    // Display the updated data to verify
                    TouristData finalData = touristAccountService.loadTouristData(touristId);
                    if (finalData != null) {
                        System.out.println("Updated Tourist Data: " + finalData);
                    }
                } else if ("Operation Cancelled".equals(confirmationResult)) {
                    System.out.println("\n--- Final Status: Operation Cancelled ---");
                } else {
                    System.out.println("\n--- Final Status: Update Failed (During Save) ---");
                }
            } else {
                System.out.println("\n--- Final Status: Update Failed (During Validation/Preparation) ---");
            }

            // --- Demonstrate ETOUR Scenario (Connection Interruption) ---
            System.out.println("\n--- Simulating ETOUR (Repository Connection Failure) ---");
            touristRepository.setSimulateConnectionFailure(true); // Enable simulation

            System.out.println("\nAttempting to load data with ETOUR enabled:");
            touristController.displayEditForm(touristId); // This should now fail and display an error

            System.out.println("\nAttempting to save data with ETOUR enabled (e.g., after validation):");
            // Simulate another submit and confirmation attempt
            TouristForm etourFormData = new TouristForm("ETOUR Test", "etour@test.com", "111-222-3333", new Date());
            touristAccountService.updateTouristAccount(touristId, etourFormData); // This will succeed in preparing, but save will fail
            touristController.handleConfirmationResponse(touristId, true); // This save will trigger ETOUR

            touristRepository.setSimulateConnectionFailure(false); // Disable simulation for cleanup

        } else {
            System.out.println("Authentication failed. Exiting simulation.");
        }

        scanner.close();
        System.out.println("\n--- Simulation Ended ---");
    }
}