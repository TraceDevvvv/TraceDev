package com.example.system;

/**
 * Abstract presentation layer class that defines the interface for user interaction.
 */
public abstract class PresentationLayer {
    /**
     * Displays a message to the user.
     * @param message the message to display
     */
    public abstract void displayToUser(String message);

    /**
     * Gets input from the user.
     * @param prompt the prompt to show the user
     * @return the user's input as a string
     */
    public abstract String getInputFromUser(String prompt);

    /**
     * Gets confirmation from the user.
     * @param message the confirmation question
     * @return true if user confirms, false otherwise
     */
    public abstract boolean confirmFromUser(String message);
}