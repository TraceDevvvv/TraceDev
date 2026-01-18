package com.example.pointofrest;

/**
 * The controller handles user interactions related to viewing PointOfRest details.
 * It acts as an intermediary between the UI (PointOfRestView) and the application service.
 */
public class PointOfRestController {

    private PointOfRestApplicationService applicationService;
    private AuthenticationService authenticationService; // Added to satisfy requirement REQ-001
    private PointOfRestView view; // Reference to the view for displaying results

    /**
     * Constructor for PointOfRestController.
     *
     * @param applicationService The application service to delegate business logic.
     * @param authenticationService The service to check user authentication.
     * @param view The UI component to interact with.
     */
    public PointOfRestController(PointOfRestApplicationService applicationService, AuthenticationService authenticationService, PointOfRestView view) {
        this.applicationService = applicationService;
        this.authenticationService = authenticationService;
        this.view = view;
        // The view needs to know about the controller to send events back
        this.view.setController(this);
    }

    /**
     * Handles the request to view PointOfRest details.
     * This method is triggered by a UI event (e.g., button click).
     *
     * @param id The unique identifier of the PointOfRest to view.
     */
    public void viewPointOfRestDetails(String id) {
        System.out.println("\n[PointOfRestController] Received request to view details for ID: " + id);

        // REQ-001: Entry Conditions: Agency IS logged in.
        if (!authenticationService.isAuthenticated()) {
            view.displayErrorMessage("Access Denied: Agency Operator is not logged in.");
            return;
        }

        // Optional: Check if a specific user is logged in
        AgencyOperator operator = authenticationService.getLoggedInUser();
        if (operator == null) {
            view.displayErrorMessage("Authentication Error: Could not retrieve logged-in user details.");
            return;
        }
        System.out.println("[PointOfRestController] Agency Operator '" + operator.username + "' is logged in.");


        try {
            // Delegate to application service to get the DTO
            PointOfRestDetailsDto dto = applicationService.getPointOfRestDetails(id);

            // Display the details using the view
            view.displayPointOfRestDetails(dto);

        } catch (Exception e) {
            // Handle exceptions from the application service or repository layers
            System.err.println("[PointOfRestController] Caught exception: " + e.getMessage());
            view.displayErrorMessage("Failed to retrieve Point of Rest details: " + e.getMessage());
        }
    }

    /**
     * Main method to demonstrate the application flow, simulating user interaction.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // --- Setup the application context (Dependency Injection) ---
        System.out.println("--- Application Setup ---");
        EtourServiceAdapter etourAdapter = new EtourServiceAdapter();
        IPointOfRestRepository pointOfRestRepository = new EtourPointOfRestRepository(etourAdapter);
        PointOfRestApplicationService applicationService = new PointOfRestApplicationService(pointOfRestRepository);
        AuthenticationService authenticationService = new AuthenticationService();
        PointOfRestView pointOfRestView = new PointOfRestView();
        PointOfRestController controller = new PointOfRestController(applicationService, authenticationService, pointOfRestView);
        System.out.println("--- Application Setup Complete ---\n");

        String selectedPointOfRestId = "POR123"; // ID for a successful scenario
        String anotherPointOfRestId = "POR456"; // Another ID for successful scenario
        String nonExistentPointOfRestId = "POR999"; // ID that won't be found
        
        System.out.println("--- Scenario 1: Successful retrieval of Point Of Rest Details ---");
        // Simulate user actions from the sequence diagram
        pointOfRestView.selectPointOfRest(selectedPointOfRestId); // m2
        pointOfRestView.activateViewDetails(); // m4

        System.out.println("\n--- Scenario 2: Successful retrieval of another Point Of Rest Details ---");
        pointOfRestView.selectPointOfRest(anotherPointOfRestId);
        pointOfRestView.activateViewDetails();

        System.out.println("\n--- Scenario 3: Point Of Rest not found in ETOUR ---");
        etourAdapter.setSimulateConnectionFailure(false); // Ensure connection is fine
        pointOfRestView.selectPointOfRest(nonExistentPointOfRestId);
        pointOfRestView.activateViewDetails();

        System.out.println("\n--- Scenario 4: ETOUR Server Connection Interruption ---");
        etourAdapter.setSimulateConnectionFailure(true); // Simulate connection failure
        pointOfRestView.selectPointOfRest(selectedPointOfRestId); // Try to view details for an existing ID
        pointOfRestView.activateViewDetails();

        System.out.println("\n--- Scenario 5: Agency Operator Not Authenticated ---");
        authenticationService.setAuthenticated(false); // Simulate unauthenticated state
        etourAdapter.setSimulateConnectionFailure(false); // Reset connection flag
        pointOfRestView.selectPointOfRest(selectedPointOfRestId);
        pointOfRestView.activateViewDetails();

        System.out.println("\n--- End of Demonstration ---");
    }
}