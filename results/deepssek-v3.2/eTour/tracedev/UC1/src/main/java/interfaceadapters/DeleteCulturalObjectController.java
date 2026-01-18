
package interfaceadapters;

import applicationbusinessrules.IDeleteCulturalObjectUseCase;
import applicationbusinessrules.DeleteCulturalObjectCommand;
import frameworksdrivers.IdempotencyService;
import frameworksdrivers.IAuthenticationService;

/**
 * Controller handling delete cultural object requests.
 */
public class DeleteCulturalObjectController {
    private IDeleteCulturalObjectUseCase useCase;
    private IdempotencyService idempotencyService;
    private IAuthenticationService authService;

    public DeleteCulturalObjectController(IDeleteCulturalObjectUseCase useCase,
                                          IdempotencyService idempotencyService,
                                          IAuthenticationService authService) {
        this.useCase = useCase;
        this.idempotencyService = idempotencyService;
        this.authService = authService;
    }

    /**
     * Handles a delete request.
     * @param requestId the idempotency request ID
     * @param culturalObjectId the cultural object ID
     * @return a ResponseEntity (simulated as a string for simplicity)
     */
    public String handleDeleteRequest(String requestId, String culturalObjectId) {
        // Input validation.
        if (!validateInput(requestId, culturalObjectId)) {
            return "HTTP 400 Bad Request";
        }

        // Check authentication (assume current user ID is "user1").
        if (!authService.isUserLogged("user1")) {
            return "HTTP 401 Unauthorized";
        }

        // Idempotency check.
        if (idempotencyService.isDuplicateRequest(requestId)) {
            idempotencyService.registerRequest(requestId);
            return "HTTP 409 Conflict / \"Request already processed\"";
        }

        // Register request as processed.
        idempotencyService.registerRequest(requestId);

        // Create command (assume confirmed by current user).
        DeleteCulturalObjectCommand command = new DeleteCulturalObjectCommand(requestId, culturalObjectId, "user1");

        // Execute use case.
        Object result = useCase.execute(command);

        // Handle result.
        if (result instanceof Boolean && (Boolean) result) {
            return "HTTP 200 OK + Successful elimination";
        } else {
            return "HTTP 500 Internal Server Error: " + result.toString();
        }
    }

    /**
     * Handles cancel request.
     * @param requestId the request ID
     * @return a ResponseEntity (simulated as a string)
     */
    public String handleCancel(String requestId) {
        // Simplified: just acknowledge cancel.
        // In real implementation, might log cancellation.
        return "Cancel Acknowledged";
    }

    /**
     * Validates input parameters.
     * @param requestId the request ID
     * @param culturalObjectId the cultural object ID
     * @return true if valid, false otherwise
     */
    private boolean validateInput(String requestId, String culturalObjectId) {
        return requestId != null && !requestId.isEmpty() &&
               culturalObjectId != null && !culturalObjectId.isEmpty();
    }

    /**
     * Input validation method corresponding to note m9 in sequence diagram.
     * This method performs user logged in check and ID format check.
     * @param userId the user ID
     * @param objectId the cultural object ID
     * @return true if validation passes, false otherwise
     */
    public boolean validateInputDetails(String userId, String objectId) {
        if (userId == null || userId.isEmpty()) {
            return false;
        }
        if (objectId == null || objectId.isEmpty()) {
            return false;
        }
        // ID format check: simple check that it's not empty and contains only alphanumeric and hyphens.
        if (!objectId.matches("[a-zA-Z0-9-]+")) {
            return false;
        }
        return true;
    }
}
