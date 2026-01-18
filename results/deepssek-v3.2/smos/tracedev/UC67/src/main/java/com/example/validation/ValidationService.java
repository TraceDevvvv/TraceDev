package com.example.validation;

import com.example.dto.RegistrationFormDTO;
import com.example.repository.IUserRepository;
import com.example.domain.User;

/**
 * Implementation of validation service
 */
public class ValidationService implements IValidationService {
    private IUserRepository userRepository;

    public ValidationService(IUserRepository repo) {
        this.userRepository = repo;
    }

    @Override
    public boolean validateFormData(RegistrationFormDTO formData) {
        // Use DTO's validate method and add additional validations
        if (!formData.validate()) {
            return false;
        }
        
        // Additional validations can be added here
        // For example: email format, phone number format, etc.
        
        // Validate email format (basic check)
        String email = formData.getEmail();
        if (email == null || !email.contains("@") || !email.contains(".")) {
            return false;
        }
        
        // Validate password length
        String password = formData.getPassword();
        if (password == null || password.length() < 8) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean validatePassword(String password, String confirmation) {
        // Check if passwords match and meet requirements
        if (password == null || confirmation == null) {
            return false;
        }
        
        // Check if passwords match
        if (!password.equals(confirmation)) {
            return false;
        }
        
        // Additional password strength requirements
        if (password.length() < 8) {
            return false;
        }
        
        // Check for at least one digit and one letter
        boolean hasDigit = false;
        boolean hasLetter = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        
        return hasDigit && hasLetter;
    }

    @Override
    public boolean validateUniqueUsername(String username) {
        // Check if username already exists in repository
        User existingUser = userRepository.findByUsername(username);
        return existingUser == null; // Return true if username is unique
    }

    @Override
    public boolean validateUniqueEmail(String email) {
        // Check if email already exists in repository
        User existingUser = userRepository.findByEmail(email);
        return existingUser == null; // Return true if email is unique
    }
}