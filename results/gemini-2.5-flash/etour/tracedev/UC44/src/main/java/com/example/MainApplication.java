package com.example;

import com.example.application.AuthenticationService;
import com.example.application.ConventionErrorHandler;
import com.example.application.ConventionRequestCommand;
import com.example.application.ConventionService;
import com.example.domain.AgencyNotificationPort;
import com.example.domain.ConventionId;
import com.example.domain.ConventionRepository;
import com.example.domain.ConventionValidator;
import com.example.domain.Credentials;
import com.example.domain.User;
import com.example.infrastructure.ConventionJpaAdapter;
import com.example.infrastructure.ExternalAuthenticationAdapter;
import com.example.infrastructure.HttpAgencyNotificationAdapter;
import com.example.presentation.ConventionRequestDTO;
import com.example.presentation.ConventionResponseDTO;
import com.example.presentation.ConventionRestController;

import java.util.Arrays;

/**
 * Main application class to demonstrate the wiring and functionality of the Hexagonal Architecture.
 * This acts as the "bootstrap" or "composition root" for the application.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("Starting Convention Management System Application...");

        // --- Infrastructure Layer (Adapters) Initialization ---
        ConventionJpaAdapter conventionJpaAdapter = new ConventionJpaAdapter();
        HttpAgencyNotificationAdapter httpAgencyNotificationAdapter = new HttpAgencyNotificationAdapter();
        ExternalAuthenticationAdapter externalAuthenticationAdapter = new ExternalAuthenticationAdapter();

        // --- Domain Layer Port Implementations (as used by Application Layer) ---
        ConventionRepository conventionRepository = conventionJpaAdapter;
        AgencyNotificationPort agencyNotificationPort = httpAgencyNotificationAdapter;
        AuthenticationService authenticationService = externalAuthenticationAdapter; // Used in service constructor, but not in current sequence flows directly

        // --- Domain Layer Utilities ---
        ConventionValidator conventionValidator = new ConventionValidator();

        // --- Application Layer Initialization ---
        ConventionErrorHandler conventionErrorHandler = new ConventionErrorHandler(conventionRepository);
        ConventionService conventionService = new ConventionService(
                conventionRepository,
                agencyNotificationPort,
                conventionValidator,
                authenticationService,
                conventionErrorHandler
        );

        // --- Presentation Layer (Adapter) Initialization ---
        ConventionRestController conventionRestController = new ConventionRestController(conventionService);

        System.out.println("\nApplication components initialized. Simulating user interactions...\n");

        // Simulate REQ-008 Precondition: Operator is authenticated.
        // In a real system, this would be handled by a security filter/gateway before reaching the controller.
        Credentials operatorCredentials = new Credentials("test", "test");
        User authenticatedOperator = authenticationService.authenticate(operatorCredentials);
        if (authenticatedOperator == null) {
            System.err.println("Authentication failed. Cannot proceed with operations.");
            return;
        }
        System.out.println("Operator authenticated: " + authenticatedOperator.getId() + " with roles: " + authenticatedOperator.getRoles());

        // --- SCENARIO 1: Successful Request and Confirmation ---
        System.out.println("\n--- SCENARIO 1: Successful Request and Confirmation ---");
        ConventionRequestDTO requestDTO1 = new ConventionRequestDTO("Agreement details for a new successful convention.");
        ConventionResponseDTO response1 = conventionRestController.requestConventionEndpoint(requestDTO1);
        System.out.println("Request Convention Response: " + response1);

        if (response1.getConventionId() != null) {
            // Operator asks for confirmation and then confirms
            System.out.println("\nOperator confirms convention ID: " + response1.getConventionId());
            ConventionResponseDTO confirmResponse1 = conventionRestController.confirmConventionEndpoint(response1.getConventionId());
            System.out.println("Confirm Convention Response: " + confirmResponse1);
        }

        // --- SCENARIO 2: Request with Invalid Data ---
        System.out.println("\n--- SCENARIO 2: Request with Invalid Data ---");
        ConventionRequestDTO requestDTO2 = new ConventionRequestDTO(null); // Invalid details
        ConventionResponseDTO response2 = conventionRestController.requestConventionEndpoint(requestDTO2);
        System.out.println("Request Convention (Invalid Data) Response: " + response2);

        // --- SCENARIO 3: Request and Confirmation with Agency Notification Failure (Connection Error) ---
        System.out.println("\n--- SCENARIO 3: Request and Confirmation with Agency Notification Failure (Connection Error) ---");
        ConventionRequestDTO requestDTO3 = new ConventionRequestDTO("Agreement details for a convention with notification failure.");
        ConventionResponseDTO response3 = conventionRestController.requestConventionEndpoint(requestDTO3);
        System.out.println("Request Convention Response: " + response3);

        if (response3.getConventionId() != null) {
            // Configure adapter to simulate connection error for next send
            httpAgencyNotificationAdapter.setSimulateConnectionError(true);
            System.out.println("\nOperator confirms convention ID: " + response3.getConventionId() + " (expecting agency error)");
            ConventionResponseDTO confirmResponse3 = conventionRestController.confirmConventionEndpoint(response3.getConventionId());
            System.out.println("Confirm Convention (Agency Error) Response: " + confirmResponse3);
            // Reset simulation for subsequent tests
            httpAgencyNotificationAdapter.setSimulateConnectionError(false);

            // Check if status was updated by error handler (might need direct repo access for assertion)
            System.out.println("Current status in repository for " + response3.getConventionId() + ": " + conventionRepository.findById(new ConventionId(response3.getConventionId())).getStatus());
        }

        // --- SCENARIO 4: Cancel a Convention ---
        System.out.println("\n--- SCENARIO 4: Cancel a Convention ---");
        ConventionRequestDTO requestDTO4 = new ConventionRequestDTO("Agreement details for a convention to be cancelled.");
        ConventionResponseDTO response4 = conventionRestController.requestConventionEndpoint(requestDTO4);
        System.out.println("Request Convention Response: " + response4);

        if (response4.getConventionId() != null) {
            System.out.println("\nOperator cancels convention ID: " + response4.getConventionId());
            ConventionResponseDTO cancelResponse = conventionRestController.cancelConventionEndpoint(response4.getConventionId());
            System.out.println("Cancel Convention Response: " + cancelResponse);

            // Verify status in repository
            System.out.println("Current status in repository for " + response4.getConventionId() + ": " + conventionRepository.findById(new ConventionId(response4.getConventionId())).getStatus());
        }

        System.out.println("\nApplication demonstration finished.");
    }
}