package com.example.touristmgmt;

/**
 * Controller class responsible for handling the tourist deletion flow.
 * Orchestrates interactions between the UI, repository, and other serv.
 */
public class TouristDeletionController {
    private ITouristRepository touristRepository;
    private NotificationService notificationService;
    private ETOURService etourService;
    private TouristManagementUI touristManagementUI; // Reference back to UI for callbacks
    private AuthenticationService authenticationService; // Added for R3

    /**
     * Constructor for TouristDeletionController.
     *
     * @param touristRepository The repository for tourist data.
     * @param notificationService The service for sending notifications.
     * @param etourService The service for ETOUR interactions.
     * @param touristManagementUI The UI component for displaying prompts and results.
     * @param authenticationService The service for authenticating users.
     */
    public TouristDeletionController(ITouristRepository touristRepository,
                                     NotificationService notificationService,
                                     ETOURService etourService,
                                     TouristManagementUI touristManagementUI,
                                     AuthenticationService authenticationService) {
        this.touristRepository = touristRepository;
        this.notificationService = notificationService;
        this.etourService = etourService;
        this.touristManagementUI = touristManagementUI;
        this.authenticationService = authenticationService; // Inject AuthenticationService
    }

    /**
     * Initiates the deletion flow for a specific tourist.
     * This method implements the core logic described in the sequence diagram.
     *
     * @param touristId The ID of the tourist to be deleted.
     */
    public void initiateDeletionFlow(String touristId) {
        System.out.println("\nController: initiateDeletionFlow received for tourist ID: " + touristId);
        // Step 3: Agency Operator activates a feature for disposal.

        // Pre-check: Ensure operator is logged in (R3)
        // For simplicity, assuming a dummy operator ID.
        if (!authenticationService.isLoggedIn("agencyOperator123")) {
            touristManagementUI.displayOperationResult("Authentication failed. Operator not logged in.");
            return;
        }

        // Retrieve tourist details to display in confirmation prompt
        Tourist touristToDelete = touristRepository.findById(touristId);
        if (touristToDelete == null) {
            touristManagementUI.displayOperationResult("Error: Tourist with ID " + touristId + " not found.");
            return;
        }

        String touristName = touristToDelete.getName();
        boolean confirmed = touristManagementUI.showConfirmationPrompt("Confirm deletion of tourist '" + touristName + "' (ID: " + touristId + ")?");

        if (confirmed) {
            // Step 5: Agency Operator confirms the operation.
            System.out.println("Controller: Deletion confirmed by user for tourist ID: " + touristId);

            // Step 6: System deletes the selected tourist data.
            touristRepository.delete(touristId);

            // Exit Condition: Notification system confirms elimination.
            notificationService.sendDeletionNotification(touristId);

            // Exit Condition: Connection to the ETOUR server IS interrupted.
            etourService.disconnect();

            touristManagementUI.displayOperationResult("Tourist '" + touristName + "' (ID: " + touristId + ") deleted successfully.");
        } else {
            // Agency Operator cancels the operation.
            System.out.println("Controller: Deletion cancelled by user for tourist ID: " + touristId);
            touristManagementUI.displayOperationResult("Deletion of tourist '" + touristName + "' (ID: " + touristId + ") cancelled.");
        }
    }
}