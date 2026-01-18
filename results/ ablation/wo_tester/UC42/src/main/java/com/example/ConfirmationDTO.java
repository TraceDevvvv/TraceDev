package com.example;

/**
 * ConfirmationDTO class for confirmation messages.
 * Used to indicate whether a confirmation is required from the operator.
 */
public class ConfirmationDTO {
    private String message;
    private boolean requiresConfirmation;

    /**
     * Constructor to create a ConfirmationDTO.
     * @param message The confirmation message.
     * @param requiresConfirmation Whether confirmation is required.
     */
    public ConfirmationDTO(String message, boolean requiresConfirmation) {
        this.message = message;
        this.requiresConfirmation = requiresConfirmation;
    }

    // Getters.
    public String getMessage() {
        return message;
    }

    public boolean isRequiresConfirmation() {
        return requiresConfirmation;
    }
}