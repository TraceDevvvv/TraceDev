package com.example.validation;

import com.example.dto.RegistrationFormDTO;

/**
 * Interface for validation serv
 */
public interface IValidationService {
    /**
     * Validates form data
     * @param formData the form data to validate
     * @return true if validation passes
     */
    boolean validateFormData(RegistrationFormDTO formData);
    
    /**
     * Validates password and confirmation match
     * @param password the password
     * @param confirmation the confirmation password
     * @return true if passwords match and meet requirements
     */
    boolean validatePassword(String password, String confirmation);
    
    /**
     * Validates if username is unique
     * @param username the username to check
     * @return true if username is unique
     */
    boolean validateUniqueUsername(String username);
    
    /**
     * Validates if email is unique
     * @param email the email to check
     * @return true if email is unique
     */
    boolean validateUniqueEmail(String email);
}