package com.system;

/**
 * ExistingErrorTagResponse class representing a response.
 * Attributes: success, restoredState.
 * Method: isSuccess.
 */
public class ExistingErrorTagResponse {
    private boolean success;
    private SystemState restoredState;

    public ExistingErrorTagResponse(boolean success, SystemState restoredState) {
        this.success = success;
        this.restoredState = restoredState;
    }

    public boolean isSuccess() {
        return success;
    }

    public SystemState getRestoredState() {
        return restoredState;
    }

    // Method for message m7: Handler -> UseCase, "Error notification created"
    public void setErrorNotificationCreated() {
        // This method can be used to set a flag or perform actions when error notification is created
    }

    // Method for message m24: UseCase -> UI, "Tag not found, continue normal flow"
    public void tagNotFoundContinueFlow() {
        // This method can be used to indicate the tag was not found
    }
}