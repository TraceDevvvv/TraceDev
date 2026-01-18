package com.example.error;

/**
 * Displays errors to the user. Implements the ErrorObserver interface.
 */
public class UIErrorDisplay implements ErrorObserver {

    /**
     * Updates the display with the error from the subject.
     * @param subject the subject containing the error message
     */
    @Override
    public void update(ErrorNotificationSubject subject) {
        displayError(subject.getErrorMessage());
    }

    /**
     * Displays an error message to the user.
     * Simulates UI interaction.
     * @param message the error message to display
     */
    public void displayError(String message) {
        System.out.println("[UI Error Display]: " + message);
    }

    /**
     * Simulates the user confirming they have read the error.
     * Called from the user in the sequence diagram.
     */
    public void confirmReading() {
        System.out.println("[UI Error Display]: User confirmed reading the error.");
    }

    /**
     * Returns control after error is read.
     * Corresponds to sequence diagram message "return control".
     */
    public void returnControl() {
        System.out.println("[UI Error Display]: Returning control to the system.");
    }
}