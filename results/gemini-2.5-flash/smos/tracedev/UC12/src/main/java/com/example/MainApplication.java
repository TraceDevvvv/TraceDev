
package com.example;

import com.example.application.AuthorizationService;
import com.example.application.ParentStudentService;
import com.example.application.StudentParentValidator;
import com.example.exception.AuthorizationException;
import com.example.exception.RepositoryAccessException;
import com.example.exception.ServiceException;
import com.example.exception.ValidationException;
import com.example.infrastructure.ParentStudentRepository;
import com.example.presentation.ParentStudentManagementForm;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class to demonstrate the Parent-Student Association Management use case.
 * This class simulates the Administrator actor and orchestrates the interactions
 * as defined in the sequence diagram.
 */
public class MainApplication {

    public static void main(String[] args) {
        // 1. Initialize Infrastructure Layer components
        ParentStudentRepository repository = new ParentStudentRepository();

        // 2. Initialize Application Layer components
        StudentParentValidator validator = new StudentParentValidator();
        AuthorizationService authorizationService = new AuthorizationService();
        ParentStudentService service = new ParentStudentService(repository, validator, authorizationService);

        // 3. Initialize Presentation Layer components
        ParentStudentManagementForm form = new ParentStudentManagementForm(service);

        Scanner scanner = new Scanner(System.in);
        String adminParentId = "P1"; // Default parent ID for demonstration
        String adminId = "adminUser123"; // Default admin ID for demonstration

        System.out.println("--- Starting Parent-Student Association Management Simulation ---");
        System.out.println("Simulated Admin ID: " + adminId);
        System.out.println("Default Parent ID for management: " + adminParentId);
        System.out.println("\n--- Scenario 1: Successful Retrieval and Update ---");
        try {
            // Admin -> Form : requestChildManagementForm(parentId)
            // Form -> Service : getAssociatedStudentIds(parentId)
            // Service -> Repository -> DB
            List<String> currentAssociatedStudentIds = service.getAssociatedStudentIds(adminParentId);

            // Form -> Admin : displayForm(parentId, currentAssociatedStudentIds)
            form.displayForm(adminParentId, currentAssociatedStudentIds);

            // Admin -> Form : selectStudents(newSelectedStudentIds)
            // Simulate admin modifying associations
            List<String> newSelectedStudentIds = Arrays.asList("S1", "S3", "S4"); // Add S4, keep S1, remove S2
            form.simulateStudentSelection(newSelectedStudentIds);

            // Admin -> Form : onClickSendButton()
            form.onClickSendButton();

        } catch (ServiceException e) {
            form.displayError("Operation failed during initial load: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during initial load: " + e.getMessage());
        }

        System.out.println("\n--- Scenario 2: Simulation of Connection Interruption during Retrieval ---");
        repository.setSimulateConnectionLoss(true); // Turn on connection loss simulation
        try {
            form.displayForm("P_temp", service.getAssociatedStudentIds("P_temp"));
        } catch (ServiceException e) {
            // This catch block directly represents:
            // Service -[#red]> Form : throws ServiceException("Failed to retrieve students due to connection loss")
            // Form -[#red]> Admin : displayError("Connection interrupted. Please try again.")
            form.displayError("Connection interrupted during initial display form. " + e.getMessage());
        } finally {
            repository.setSimulateConnectionLoss(false); // Turn off for next scenarios
        }

        System.out.println("\n--- Scenario 3: Invalid Parent ID during update ---");
        adminParentId = "P1"; // Reset parent ID
        try {
            List<String> currentAssociatedStudentIds = service.getAssociatedStudentIds(adminParentId);
            form.displayForm(adminParentId, currentAssociatedStudentIds);

            form.simulateStudentSelection(Arrays.asList("S1", "S5")); // Some valid students
            // Simulate invalid parentId for onClickSendButton()
            form.displayForm("", currentAssociatedStudentIds); // This will update the internal parentId of the form for the next action
            form.onClickSendButton(); // This will trigger ValidationException internally
            // Reset for next scenarios
            form.displayForm(adminParentId, currentAssociatedStudentIds); // Reset the internal parentId of the form
        } catch (ServiceException e) {
            form.displayError("Operation failed: " + e.getMessage());
        }

        System.out.println("\n--- Scenario 4: Unauthorized Access during update ---");
        try {
            List<String> currentAssociatedStudentIds = service.getAssociatedStudentIds("P_restricted");
            form.displayForm("P_restricted", currentAssociatedStudentIds);

            form.simulateStudentSelection(Arrays.asList("S10", "S11")); // Select some students

            // Simulate changing the admin context to an unauthorized one
            // This is a bit of a hack since adminId is hardcoded in Service currently
            // A more robust solution would pass adminId to manageStudentAssociations
            // (Self-correction: The CD doesn't include adminId in manageStudentAssociations,
            // so we'll simulate by creating a new service with a 'restricted' auth service or by
            // mocking/setting internal state of the authorizationService directly if it were injectable.
            // For this example, let's make a direct call from Main to illustrate the failure,
            // bypassing `form.onClickSendButton` as it doesn't pass adminId.)
            System.out.println("[Main] Simulating unauthorized admin trying to manage P_restricted...");
            try {
                // Directly call service to simulate admin.id change in the SD.
                // In a real app, form.onClickSendButton() would eventually call with the current user's adminId
                AuthorizationService tempAuthService = new AuthorizationService();
                ParentStudentService tempServiceForUnauthorized = new ParentStudentService(repository, validator, tempAuthService);
                tempAuthService.authorizeAssociationManagement("unauthorizedAdmin", "P_restricted"); // This should fail if `P_restricted` logic is there
            } catch (AuthorizationException e) {
                form.displayError("Expected unauthorized access caught: " + e.getMessage());
            }

            // Also test with 'unauthorizedAdmin' for a regular parent if that's a rule
            System.out.println("[Main] Simulating 'unauthorizedAdmin' for parent P1...");
            try {
                AuthorizationService tempAuthService = new AuthorizationService();
                ParentStudentService tempServiceForUnauthorized = new ParentStudentService(repository, validator, tempAuthService);
                // Call manageStudentAssociations directly with a dummy adminId to trigger authorization
                // This assumes ParentStudentService uses the adminId that was passed to authorizationService
                // (which it currently doesn't, it uses a hardcoded "adminUser123").
                // For this scenario, let's explicitly call authorize for P1 with "unauthorizedAdmin".
                tempAuthService.authorizeAssociationManagement("unauthorizedAdmin", "P1"); // This would fail if "unauthorizedAdmin" is generally not allowed.
            } catch (AuthorizationException e) {
                form.displayError("Expected unauthorized access caught (unauthorizedAdmin for P1): " + e.getMessage());
            }

        } catch (ServiceException e) {
            form.displayError("Operation failed for unauthorized scenario setup: " + e.getMessage());
        }

        System.out.println("\n--- Simulation Complete ---");
        scanner.close();
    }
}
