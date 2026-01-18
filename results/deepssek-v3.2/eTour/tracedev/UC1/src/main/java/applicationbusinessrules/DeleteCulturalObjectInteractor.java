package applicationbusinessrules;

import frameworksdrivers.ICulturalObjectRepository;
import frameworksdrivers.IdempotencyService;
import enterprisebusinessrules.CulturalObject;

/**
 * Interactor implementing the delete cultural object use case.
 */
public class DeleteCulturalObjectInteractor {
    private ICulturalObjectRepository repository;
    private IdempotencyService idempotencyService;

    public DeleteCulturalObjectInteractor(ICulturalObjectRepository repository, IdempotencyService idempotencyService) {
        this.repository = repository;
        this.idempotencyService = idempotencyService;
    }

    public Object execute(Object command) {
        // Validate command.
        if (!validateCommand(command)) {
            return "ErrorResult: Invalid command.";
        }

        // Check idempotency (though controller already checked, we double-check for safety).
        if (idempotencyService.isDuplicateRequest(getRequestId(command))) {
            return "ErrorResult: Duplicate request.";
        }

        // Find the cultural object.
        CulturalObject culturalObject = repository.findById(getCulturalObjectId(command));
        if (culturalObject == null) {
            return "ErrorResult: Cultural object not found.";
        }

        // Delete the cultural object.
        boolean deleted = culturalObject.delete(); // This triggers repository.delete internally.
        if (!deleted) {
            return "ErrorResult: Deletion failed.";
        }

        // Return success result.
        return "SuccessResult: Deleted";
    }

    /**
     * Validates the command.
     * @param command the command to validate
     * @return true if valid, false otherwise
     */
    protected boolean validateCommand(Object command) {
        return command != null &&
               getRequestId(command) != null && !getRequestId(command).isEmpty() &&
               getCulturalObjectId(command) != null && !getCulturalObjectId(command).isEmpty() &&
               getConfirmedBy(command) != null && !getConfirmedBy(command).isEmpty();
    }

    private String getRequestId(Object command) {
        // Assuming command has a getRequestId method
        try {
            java.lang.reflect.Method method = command.getClass().getMethod("getRequestId");
            return (String) method.invoke(command);
        } catch (Exception e) {
            return null;
        }
    }

    private String getCulturalObjectId(Object command) {
        // Assuming command has a getCulturalObjectId method
        try {
            java.lang.reflect.Method method = command.getClass().getMethod("getCulturalObjectId");
            return (String) method.invoke(command);
        } catch (Exception e) {
            return null;
        }
    }

    private String getConfirmedBy(Object command) {
        // Assuming command has a getConfirmedBy method
        try {
            java.lang.reflect.Method method = command.getClass().getMethod("getConfirmedBy");
            return (String) method.invoke(command);
        } catch (Exception e) {
            return null;
        }
    }
}