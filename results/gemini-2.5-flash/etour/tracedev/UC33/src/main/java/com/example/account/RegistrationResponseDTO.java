package com.example.account;

/**
 * Data Transfer Object (DTO) for account registration responses.
 * Carries data from the use case back to the UI, indicating success or failure.
 */
public class RegistrationResponseDTO {
    private boolean success;
    private String message;
    private String accountId;

    /**
     * Constructs a new RegistrationResponseDTO for a successful operation.
     *
     * @param success True if the operation was successful.
     * @param message A message describing the outcome.
     * @param accountId The ID of the newly created account.
     */
    public RegistrationResponseDTO(boolean success, String message, String accountId) {
        this.success = success;
        this.message = message;
        this.accountId = accountId;
    }

    /**
     * Constructs a new RegistrationResponseDTO for a failed operation.
     *
     * @param success False if the operation failed.
     * @param message A message describing the error.
     */
    public RegistrationResponseDTO(boolean success, String message) {
        this(success, message, null);
    }

    // Getters for the properties
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccountId() {
        return accountId;
    }
}