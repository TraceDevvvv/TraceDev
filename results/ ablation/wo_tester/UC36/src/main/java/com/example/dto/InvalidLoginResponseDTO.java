package com.example.dto;

/**
 * Data Transfer Object for an invalid login response.
 * Indicates whether confirmation is required and provides recovery state.
 */
public class InvalidLoginResponseDTO {
    private boolean requiresConfirmation;
    private Object recoveryState;

    public InvalidLoginResponseDTO(boolean requiresConfirmation, Object recoveryState) {
        this.requiresConfirmation = requiresConfirmation;
        this.recoveryState = recoveryState;
    }

    public boolean requiresConfirmation() {
        return requiresConfirmation;
    }

    public Object getRecoveryState() {
        return recoveryState;
    }
}