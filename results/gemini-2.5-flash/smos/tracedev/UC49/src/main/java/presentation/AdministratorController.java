
package presentation;

import application.RegistrationService;
import dataaccess.NetworkConnectionException;
import dataaccess.IRegistrationRepository; // Added import for IRegistrationRepository
import shared.AuthenticationService;

/**
 * Controller for Administrator-related actions, handling incoming requests
 * and orchestrating the interaction between Presentation, Application, and Shared layers.
 */
public class AdministratorController {
    private RegistrationService registrationService;
    private UIService uiService;
    private AuthenticationService authenticationService;

    /**
     * Constructs an AdministratorController with necessary service dependencies.
     * @param registrationService The service for managing registration logic.
     * @param uiService The service for displaying UI messages.
     * @param authenticationService The service for handling user authentication.
     */
    public AdministratorController(RegistrationService registrationService, UIService uiService, AuthenticationService authenticationService) {
        this.registrationService = registrationService;
        this.uiService = uiService;
        this.authenticationService = authenticationService;
    }

    /**
     * Handles the request to view pending registration requests.
     * This method orchestrates the full flow as described in the sequence diagram.
     *
     * @param adminId The ID of the administrator making the request (for authentication check).
     * @return A string representing the UI output (list of requests or an error message).
     */
    public String viewRegistrationRequests(String adminId) {
        // Simulates the "Web Browser" clicking "View list request list" and forwarding to Controller.

        // Entry Condition: Administrator IS logged in to the system
        // UI -> Auth : isAuthenticated(adminId)
        if (!authenticationService.isAuthenticated(adminId)) {
            System.out.println("[UI] Access Denied for user: " + adminId);
            return "Access Denied: Administrator not logged in.";
        }

        System.out.println("[Controller] GET /registrations/pending (viewRegistrationRequests()) called.");

        // 1. System displays the list of registrations yet to be activated.
        // Controller -> ViewModel : <<create>>
        RegistrationListViewModel viewModel = new RegistrationListViewModel();

        try {
            // Controller -> ViewModel : loadPendingRequests(registrationService : RegistrationService)
            viewModel.loadPendingRequests(registrationService);

            // Controller -> View : <<create>>(viewModel : RegistrationListViewModel)
            RegistrationListView view = new RegistrationListView(viewModel);

            // Controller -> View : displayRequests()
            String output = view.displayRequests();

            // View --> UI : Rendered HTML (list of pending registrations)
            System.out.println("[UI] Displays list of pending registrations to Administrator.");
            return output;

        } catch (NetworkConnectionException e) {
            // connection interrupted / network error
            System.out.println("[Controller] Caught NetworkConnectionException: " + e.getMessage());
            // Controller -> Controller : handleNetworkError(...)
            handleNetworkError("Connection to SMOS server interrupted: " + e.getMessage());
            // UI --> Administrator : Displays error message
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Handles network errors by delegating to the UIService to display a message.
     * @param errorMessage The error message to be displayed to the user.
     */
    private void handleNetworkError(String errorMessage) {
        System.out.println("[Controller] Handling network error.");
        // Controller -> UIService : showErrorMessage(...)
        uiService.showErrorMessage(errorMessage);
    }

    // Main method to demonstrate the full flow
    public static void main(String[] args) {
        // Setup the dependency graph
        IRegistrationRepository repository = new dataaccess.RegistrationRepositoryImpl();
        RegistrationService appService = new RegistrationService(repository);
        UIService uiService = new UIService();
        AuthenticationService authService = new AuthenticationService();
        AdministratorController controller = new AdministratorController(appService, uiService, authService);

        System.out.println("\n--- Scenario 1: Successful request (Administrator logged in) ---\n");
        String output1 = controller.viewRegistrationRequests("admin123");
        System.out.println("\nAdministrator sees:\n" + output1);

        System.out.println("\n--- Scenario 2: Administrator NOT logged in ---\n");
        String output2 = controller.viewRegistrationRequests(""); // Simulate not logged in
        System.out.println("\nAdministrator sees:\n" + output2);

        System.out.println("\n--- Scenario 3: Network Connection Interrupted (Administrator logged in) ---\n");
        // Simulate a network error for the next call
        dataaccess.RegistrationRepositoryImpl.simulateNetworkError = true;
        String output3 = controller.viewRegistrationRequests("admin123");
        System.out.println("\nAdministrator sees:\n" + output3);
        // Reset network error simulation
        dataaccess.RegistrationRepositoryImpl.simulateNetworkError = false;

        System.out.println("\n--- Scenario 4: Successful request again (after network error reset) ---\n");
        String output4 = controller.viewRegistrationRequests("admin123");
        System.out.println("\nAdministrator sees:\n" + output4);
    }
}
