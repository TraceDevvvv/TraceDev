package com.example.system;

/**
 * Handles user confirmation prompts.
 */
public class ConfirmationPrompt {
    private String question;

    /**
     * Asks a confirmation question.
     * @param question the question to ask
     * @return true if user confirms, false otherwise
     */
    public boolean ask(String question) {
        this.question = question;
        System.out.println("ConfirmationPrompt: Asking - " + question);
        // Simulating user confirmation for demonstration
        return getConfirmation();
    }

    /**
     * Gets confirmation from user.
     * @return true if confirmed, false otherwise
     */
    public boolean getConfirmation() {
        // Simulating user confirming the prompt
        System.out.println("ConfirmationPrompt: User confirmed.");
        return true;
    }
}