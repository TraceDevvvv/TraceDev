package com.example.system;

/**
 * Displays error messages to the user.
 */
public class ErrorMessageWindow {
    private String message;

    /**
     * Displays the error message.
     * @param message the error message to display
     */
    public void display(String message) {
        this.message = message;
        System.out.println("ErrorMessageWindow: Displaying - " + message);
    }

    public void display() {
        if (message != null) {
            System.out.println("ErrorMessageWindow: Displaying - " + message);
        }
    }

    /**
     * Checks if the error was noticed by user.
     * @return true if noticed, false otherwise
     */
    public boolean isNoticed() {
        // Simulating user noticing the error
        return true;
    }
}