package infrastructure;

import application.*;
import domain.ConventionSubmittedEvent;
import domain.User;

import java.time.LocalDateTime;

/**
 * REST controller (or similar) for convention operations.
 * Orchestrates use cases, enforces authentication (EC-001) and ETOUR connectivity (EC-002).
 */
public class ConventionController {
    private final SubmitConventionHandler submitConventionHandler;
    private final GetConventionFormHandler getConventionFormHandler;
    private final AuthenticationService authenticationService;
    private final ETOURServiceClient etourServiceClient;

    public ConventionController(SubmitConventionHandler submitConventionHandler,
                                GetConventionFormHandler getConventionFormHandler,
                                AuthenticationService authenticationService,
                                ETOURServiceClient etourServiceClient) {
        this.submitConventionHandler = submitConventionHandler;
        this.getConventionFormHandler = getConventionFormHandler;
        this.authenticationService = authenticationService;
        this.etourServiceClient = etourServiceClient;
    }

    /**
     * Retrieves convention form data for a restaurant.
     * Enforces authentication (EC-001) and checks ETOUR connection (EC-002).
     * @param restaurantId the restaurant identifier
     * @param authToken the authentication token
     * @return ConventionFormDTO with form data, or null if error
     */
    public ConventionFormDTO getConventionForm(String restaurantId, String authToken) {
        // Check authentication (EC-001)
        if (!authenticationService.validateToken(authToken)) {
            System.out.println("401 Unauthorized: Invalid authentication token.");
            return null;
        }

        // Check ETOUR connection (EC-002)
        if (!etourServiceClient.checkConnection()) {
            System.out.println("503 Service Unavailable: ETOUR server is unreachable.");
            return null;
        }

        // Proceed with query
        GetConventionFormQuery query = new GetConventionFormQuery(restaurantId);
        return getConventionFormHandler.handle(query);
    }

    public boolean submitConvention(SubmitConventionCommand command, String authToken) {
        // Check authentication (EC-001)
        if (!authenticationService.validateToken(authToken)) {
            System.out.println("401 Unauthorized: Invalid authentication token.");
            return false;
        }

        // Check ETOUR connection (EC-002)
        if (!etourServiceClient.checkConnection()) {
            System.out.println("503 Service Unavailable: ETOUR server is unreachable.");
            return false;
        }

        try {
            submitConventionHandler.handle(command);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("400 Bad Request: " + e.getMessage());
            return false;
        }
    }
}