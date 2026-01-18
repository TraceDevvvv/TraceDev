package com.example;

import java.util.Scanner;

/**
 * Controller class that handles user interactions related to banner operations.
 * Annotated with <<Controller>> stereotype.
 */
public class OperatorController {
    // - bannerVerificationService : BannerVerificationService
    private final BannerVerificationService bannerVerificationService;
    // - systemStateService : SystemStateService
    private final SystemStateService systemStateService;

    // A mock NotificationService to fulfill the requirement of displaying messages directly to the operator
    // as shown in the sequence diagram.
    private final NotificationService operatorNotificationService;

    /**
     * Constructs an OperatorController with necessary service dependencies.
     *
     * @param bannerVerificationService The service for verifying banner allowance.
     * @param systemStateService The utility service for managing system state.
     */
    public OperatorController(
            BannerVerificationService bannerVerificationService,
            SystemStateService systemStateService,
            NotificationService operatorNotificationService) {
        this.bannerVerificationService = bannerVerificationService;
        this.systemStateService = systemStateService;
        this.operatorNotificationService = operatorNotificationService;
    }

    /**
     * Handles the operator's request to check banner allowance for a refreshment point.
     * This method orchestrates the verification process and handles the different outcomes.
     * Note R3: Entry Condition: Agency HAS intent to add a new banner.
     * Note R12: Exit Condition: System returns control to user interaction.
     * Note R8: Post-condition of 'requestBannerCheck' flow (if limit exceeded): System ends the operation input.
     *
     * @param refreshmentPointId The ID of the refreshment point to check.
     */
    public void requestBannerCheck(String refreshmentPointId) {
        System.out.println("\n[OperatorController] Operator requests banner check for RP: " + refreshmentPointId);
        System.out.println("Note: Entry Conditions met: Refreshment Point selected, Convention data available.");

        // OperatorController -> BannerVerificationService: verifyBannerAllowance(refreshmentPointId)
        VerificationResult result = bannerVerificationService.verifyBannerAllowance(refreshmentPointId);

        // Handle different verification results
        switch (result) {
            case CONNECTION_ERROR:
                // alt result == CONNECTION_ERROR
                // OperatorController -> SystemStateService: recoverPreviousState()
                systemStateService.recoverPreviousState();
                // OperatorController --> Operator: displayError(...)
                operatorNotificationService.displayNotification("[OperatorController] Operation failed due to connection error for RP " + refreshmentPointId + ".");
                break;
            case LIMIT_EXCEEDED:
                // alt result == LIMIT_EXCEEDED
                // OperatorController --> Operator: displayNotification(...)
                operatorNotificationService.displayNotification("[OperatorController] Banner limit exceeded. Cannot add new banner to RP " + refreshmentPointId + ".");
                // Simulating Operator confirming notification and system state recovery.
                // In a real UI, this would typically involve waiting for user input or an event.
                System.out.println("Operator: (Acknowledge message) >> Press Enter to acknowledge notification.");
                new Scanner(System.in).nextLine(); // Simulate user acknowledgement
                acknowledgeNotification(); // Operator -> OperatorController: acknowledgeNotification()
                // OperatorController -> SystemStateService: recoverPreviousState()
                systemStateService.recoverPreviousState();
                // OperatorController --> Operator: controlReturned()
                operatorNotificationService.displayNotification("[OperatorController] Control returned to operator after LIMIT_EXCEEDED handling.");
                break;
            case LIMIT_OK:
                // alt result == LIMIT_OK
                // OperatorController -> Operator: displayMessage(...)
                operatorNotificationService.displayNotification("[OperatorController] Banner can be added to RP " + refreshmentPointId + ".");
                // OperatorController --> Operator: controlReturned()
                operatorNotificationService.displayNotification("[OperatorController] Control returned to operator after LIMIT_OK handling.");
                break;
            default:
                // Should not happen with current enum
                operatorNotificationService.displayNotification("[OperatorController] An unexpected verification result occurred.");
                systemStateService.recoverPreviousState();
                break;
        }
    }

    /**
     * Simulates the operator acknowledging a notification.
     * In a real application, this would be triggered by a UI event.
     */
    public void acknowledgeNotification() {
        System.out.println("[OperatorController] Operator acknowledged notification.");
    }

    // --- Main method for demonstration purposes ---
    public static void main(String[] args) {
        // Setup Dependencies (Mock implementations for demonstration)
        // Dummy Convention data
        Convention conventionA = new Convention(5); // Convention A allows 5 banners
        Convention conventionB = new Convention(2); // Convention B allows 2 banners

        // Dummy RefreshmentPoint data
        RefreshmentPoint rp1 = new RefreshmentPoint("RP001", "Main Entrance", 3, conventionA); // 3 < 5, should be OK
        RefreshmentPoint rp2 = new RefreshmentPoint("RP002", "Food Court", 5, conventionA);    // 5 >= 5, should be EXCEEDED
        RefreshmentPoint rp3 = new RefreshmentPoint("RP003", "Exhibition Hall", 1, conventionB); // 1 < 2, should be OK
        RefreshmentPoint rp4 = new RefreshmentPoint("RP004", "Connection Fail", 0, conventionA); // For connection test

        // Mock Repository Implementations
        ConventionRepository mockConventionRepository = new ConventionRepository() {
            @Override
            public Convention findByRefreshmentPointId(String rpId) throws ETOURServiceException {
                // In this mock, the Convention is always associated with the RP, so we don't need
                // to explicitly find Convention by RP ID. It's usually retrieved *with* the RP.
                // This method might be more relevant if Convention was a standalone lookup.
                // For demonstration, let's assume it returns conventionA for most RPs, or throws error.
                if ("RP_NO_CONVENTION".equals(rpId)) {
                    throw new ETOURServiceException("No convention found for RP ID: " + rpId);
                }
                return conventionA; // Default mock
            }
        };

        RefreshmentPointRepository mockRefreshmentPointRepository = new RefreshmentPointRepository() {
            @Override
            public RefreshmentPoint findById(String rpId) throws ETOURServiceException {
                if ("RP004".equals(rpId)) {
                    // Simulate a connection error for RP004
                    throw new ETOURServiceException("Database connection failed for RP: " + rpId);
                }
                switch (rpId) {
                    case "RP001": return rp1;
                    case "RP002": return rp2;
                    case "RP003": return rp3;
                    default: return null; // Simulate RP not found
                }
            }
        };

        NotificationService mockNotificationService = new NotificationService() {
            @Override
            public void displayNotification(String message) {
                System.out.println("[Notification] " + message);
            }
        };

        ErrorHandlerService errorHandlerService = new ErrorHandlerService();
        SystemStateService systemStateService = new SystemStateService();

        // Instantiate the Application Service
        BannerVerificationService bannerVerificationService = new BannerVerificationService(
                mockConventionRepository,
                mockRefreshmentPointRepository,
                mockNotificationService,
                errorHandlerService
        );

        // Instantiate the Controller
        OperatorController controller = new OperatorController(
                bannerVerificationService,
                systemStateService,
                mockNotificationService // Use the mock notification service for operator direct messages
        );

        // Simulate Operator interactions for different scenarios
        System.out.println("--- Scenario 1: Banner Limit OK (RP001) ---");
        controller.requestBannerCheck("RP001");

        System.out.println("\n--- Scenario 2: Banner Limit Exceeded (RP002) ---");
        controller.requestBannerCheck("RP002");

        System.out.println("\n--- Scenario 3: Another Banner Limit OK (RP003) ---");
        controller.requestBannerCheck("RP003");

        System.out.println("\n--- Scenario 4: Connection Error (RP004) ---");
        controller.requestBannerCheck("RP004");

        System.out.println("\n--- Scenario 5: Refreshment Point Not Found (RP999) ---");
        // Assumption: If findById returns null, it's caught as ETOURServiceException
        // in BannerVerificationService, thus treated as CONNECTION_ERROR.
        controller.requestBannerCheck("RP999");
    }
}