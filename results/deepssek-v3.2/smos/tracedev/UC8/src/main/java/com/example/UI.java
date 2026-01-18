
package com.example;

import java.util.Map;
import java.util.List;

/**
 * UI matching sequence diagram participant "UI".
 */
public class UI {
    private UserInterface userInterface;

    public UI(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    /**
     * Display user details (from previous viewdetTailsente use case) (message m3).
     */
    public void displayUserDetails(User user) {
        userInterface.displayUserDetails(user);
    }

    /**
     * Clicks "Edit" button (submits form) (message m4).
     */
    public void clicksEditButton(String userId, Map<String, Object> changes) {
        userInterface.onEditButtonClicked(userId, changes);
    }

    /**
     * Display data error notification (message m24).
     */
    public void displayDataErrorNotification(List<String> errors) {
        userInterface.showErrorMessage(errors);
    }

    /**
     * Cancel operation (message m25).
     */
    public void cancelOperation() {
        userInterface.cancelOperation();
    }

    /**
     * Operation cancelled (message m28).
     */
    public void operationCancelled() {
        System.out.println("Operation cancelled");
    }

    /**
     * Display connection error (message m44).
     */
    public void displayConnectionError(String error) {
        System.out.println("Connection error: " + error);
    }

    /**
     * Display success message / updated user details (message m51).
     */
    public void displaySuccessMessage(String message) {
        userInterface.showSuccessMessage(message);
    }
}
