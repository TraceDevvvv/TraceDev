package com.system;

import java.util.Scanner;

/**
 * UserInterface class handling interactions with the user.
 * Methods: displayErrorMessage, requestConfirmation, restoreView, receiveSearchTag.
 */
public class UserInterface {
    private ErrorHandler errorHandler;
    private StateManager stateManager;

    public UserInterface(ErrorHandler errorHandler, StateManager stateManager) {
        this.errorHandler = errorHandler;
        this.stateManager = stateManager;
    }

    public void displayErrorMessage(ErrorNotification notification) {
        System.out.println("Error Notification: " + notification.getMessage());
        System.out.println("Error Type: " + notification.getErrorType());
    }

    public boolean requestConfirmation(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + " (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    public void restoreView(SystemState state) {
        System.out.println("Restoring view to state: " + state.getStateId());
        state.restore();
    }

    public ExistingErrorTagRequest receiveSearchTag(String tag) {
        System.out.println("Received search tag: " + tag);
        return new ExistingErrorTagRequest(tag);
    }

    // Simulate user entering a tag (for main method simulation)
    public void simulateUserInteraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search tag: ");
        String tag = scanner.nextLine();
        ExistingErrorTagRequest request = receiveSearchTag(tag);
        ExistingErrorTagUseCase useCase = new ExistingErrorTagUseCase(errorHandler, stateManager);
        ExistingErrorTagResponse response = useCase.execute(request);
        if (response.isSuccess()) {
            System.out.println("Tag not found, continuing normal flow.");
        } else {
            // Error case: tag already exists
            System.out.println("User views error message.");
            System.out.println("User confirms reading.");
            boolean confirmed = requestConfirmation("Confirm notification read?");
            if (confirmed) {
                errorHandler.confirmNotification("someId");
            }
            SystemState previousState = stateManager.recoverPreviousState();
            restoreView(previousState);
            System.out.println("Control returned to user with previous state.");
        }
    }

    // Method corresponding to message m1, m2: User -> UI, "Enter search tag (already exists)"
    public void enterSearchTag(String tag) {
        ExistingErrorTagRequest request = receiveSearchTag(tag);
        ExistingErrorTagUseCase useCase = new ExistingErrorTagUseCase(errorHandler, stateManager);
        ExistingErrorTagResponse response = useCase.execute(request);
        // Return error notification if needed
        if (!response.isSuccess()) {
            ErrorNotification notification = new ErrorNotification("notif_1", "Error: Tag already exists", "DuplicateTag");
            displayErrorMessage(notification);
        }
    }

    // Method for message m12: User -> UI, "View error message"
    public void viewErrorMessage(ErrorNotification notification) {
        displayErrorMessage(notification);
    }

    // Method for message m14: User -> UI, "Confirm reading notification"
    public boolean confirmReadingNotification(String confirmationId) {
        return errorHandler.confirmNotification(confirmationId);
    }

    // Method for message m21: StateMgr -> UI, "previous SystemState"
    public void receivePreviousSystemState(SystemState state) {
        restoreView(state);
    }

    // Method for message m25: UI -> User, "Continue with search results"
    public void continueWithSearchResults() {
        System.out.println("Continuing with search results.");
    }
}