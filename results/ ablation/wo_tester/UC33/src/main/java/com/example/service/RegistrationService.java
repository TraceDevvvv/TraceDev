package com.example.service;

import com.example.dto.RegistrationRequestDTO;
import com.example.dto.ValidationResult;
import com.example.model.Account;

/**
 * Interface for registration service.
 */
public interface RegistrationService {
    /**
     * Validates the registration data (sequence diagram message m10).
     * @param dto the registration request DTO
     * @return validation result
     */
    ValidationResult validateRegistrationData(RegistrationRequestDTO dto);

    /**
     * Creates a new account (sequence diagram message m23).
     * @param dto the registration request DTO
     * @return the created account
     */
    Account createAccount(RegistrationRequestDTO dto);

    /**
     * Confirms a transaction (sequence diagram message m19).
     * @param confirmationId the confirmation ID
     * @return true if confirmed, false otherwise
     */
    boolean confirmTransaction(String confirmationId);

    /**
     * Sends a notification (sequence diagram message m26).
     * @param message the notification message
     */
    void sendNotification(String message);
}