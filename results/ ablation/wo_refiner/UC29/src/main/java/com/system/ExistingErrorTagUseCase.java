package com.system;

/**
 * ExistingErrorTagUseCase class implementing the use case for existing error tag.
 * Implements UseCaseInteractor.
 * Methods: execute.
 */
public class ExistingErrorTagUseCase implements UseCaseInteractor {
    private ErrorHandler errorHandler;
    private StateManager stateManager;

    public ExistingErrorTagUseCase(ErrorHandler errorHandler, StateManager stateManager) {
        this.errorHandler = errorHandler;
        this.stateManager = stateManager;
    }

    @Override
    public Object execute(Object input) {
        if (input instanceof ExistingErrorTagRequest) {
            return execute((ExistingErrorTagRequest) input);
        }
        return null;
    }

    public ExistingErrorTagResponse execute(ExistingErrorTagRequest request) {
        // Assuming tag already exists for simulation.
        // In real scenario, check if tag exists in system.
        boolean tagExists = true; // Simulating tag exists.
        if (tagExists) {
            ErrorData errorData = new ErrorData(request.getTag(), "ERR001");
            errorHandler.handleError(errorData);
            stateManager.saveState();
            stateManager.saveStateToStore(stateManager.getState());
            ErrorNotification notification = new ErrorNotification("notif_1", "Error: Tag already exists", "DuplicateTag");
            return new ExistingErrorTagResponse(false, stateManager.getState());
        } else {
            return new ExistingErrorTagResponse(true, null);
        }
    }
}