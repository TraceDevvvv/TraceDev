package com.example.boundary;

import com.example.entities.Justice;
import com.example.control.ModifyJusticeController;
import com.example.connection.ConnectionHandler;
import java.util.Map;

/**
 * Boundary class representing the form for changing justice details.
 * Interacts with the administrator and controller.
 */
public class JusticeChangeForm {
    private Map<String, Object> formData;
    private ConnectionHandler connectionHandler;
    private ModifyJusticeController controller;

    /**
     * Constructor.
     */
    public JusticeChangeForm(ModifyJusticeController controller, ConnectionHandler connectionHandler) {
        this.controller = controller;
        this.connectionHandler = connectionHandler;
        // Start monitoring for interruptions
        startInterruptionMonitoring();
    }

    /**
     * Display justice details on the form.
     */
    public void displayJusticeDetails(Justice justice) {
        // Implementation would populate form fields with justice data
        System.out.println("Displaying justice details for ID: " + justice.getId());
    }

    /**
     * Returns current form data.
     */
    public Map<String, Object> getFormData() {
        return formData;
    }

    /**
     * Called when the Save button is clicked by the administrator.
     */
    public void onSaveButtonClicked() {
        // In a real application, formData would be extracted from UI fields
        // For this example, we assume formData is already populated
        com.example.control.ModifyJusticeController.Response response = controller.handleSaveRequest(formData);
        if (response.isSuccess()) {
            displaySuccessConfirmation(response.getMessage());
            returnToRegistryScreen();
        } else {
            displayErrorMessage(response.getMessage());
        }
    }

    /**
     * Handle connection interruption.
     * Called by ConnectionHandler when interruption is detected.
     */
    public void handleInterruption() {
        System.out.println("Connection interrupted. Handling interruption...");
        showConnectionLostMessage();
        connectionHandler.clearSession();
        // Additional cleanup if needed
    }

    /**
     * Notify the form that an interruption has been detected.
     */
    public void interruptionDetected() {
        handleInterruption();
    }

    /**
     * Display success confirmation.
     */
    public void displaySuccessConfirmation(String message) {
        System.out.println("Success: " + message);
    }

    /**
     * Display error message.
     */
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Show connection lost message to the administrator.
     */
    public void showConnectionLostMessage() {
        System.out.println("Connection to SMOS server lost. Please check your connection and try again.");
    }

    /**
     * Return to registry screen (after success).
     */
    public void returnToRegistryScreen() {
        System.out.println("Returning to registry screen.");
    }

    /**
     * Clear session data.
     */
    public void clearSessionData() {
        System.out.println("Clearing session data.");
        formData.clear();
    }

    /**
     * Start a background thread to monitor for interruptions.
     */
    private void startInterruptionMonitoring() {
        Thread monitorThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Check every second
                    if (connectionHandler.detectInterruption()) {
                        interruptionDetected();
                        break;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        monitorThread.setDaemon(true);
        monitorThread.start();
    }
}