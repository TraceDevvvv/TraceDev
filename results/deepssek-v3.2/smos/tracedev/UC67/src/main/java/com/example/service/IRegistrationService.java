package com.example.service;

import com.example.dto.RegistrationFormDTO;
import com.example.exception.ConnectionInterruptedException;

/**
 * Interface for registration service
 * As per class diagram
 */
public interface IRegistrationService {
    /**
     * Registers a new user
     * @param formData the registration form data
     * @return true if registration successful, false otherwise
     * @throws ConnectionInterruptedException if connection is interrupted
     */
    boolean registerUser(RegistrationFormDTO formData) throws ConnectionInterruptedException;
}