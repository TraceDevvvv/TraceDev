package com.example.school;

/**
 * Data Transfer Object (DTO) to convey error information to the view.
 * Added to satisfy requirement ExC2.
 */
public class ErrorViewModel {
    private final String errorMessage;

    /**
     * Constructs an ErrorViewModel with a specific error message.
     * @param errorMessage The message describing the error.
     */
    public ErrorViewModel(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message.
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}