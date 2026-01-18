package com.example.service;

import com.example.dto.RegistrationFormDTO;
import com.example.domain.User;
import com.example.exception.ConnectionInterruptedException;
import com.example.repository.IUserRepository;
import com.example.validation.IValidationService;
import com.example.password.IPasswordService;

/**
 * Implementation of registration service
 * Implements sequence diagram flow
 */
public class RegistrationService implements IRegistrationService {
    private IUserRepository userRepository;
    private IValidationService validationService;
    private IPasswordService passwordService;

    public RegistrationService(IUserRepository repo, 
                              IValidationService validator) {
        this.userRepository = repo;
        this.validationService = validator;
    }

    public RegistrationService(IUserRepository repo, 
                              IValidationService validator, 
                              IPasswordService pwdService) {
        this.userRepository = repo;
        this.validationService = validator;
        this.passwordService = pwdService;
    }

    @Override
    public boolean registerUser(RegistrationFormDTO formData) throws ConnectionInterruptedException {
        // Step 1: Validate form data (as per sequence diagram)
        if (!validationService.validateFormData(formData)) {
            return false; // Validation failed
        }
        
        // Step 2: Validate password confirmation (explicit check as per sequence diagram)
        if (!validationService.validatePassword(formData.getPassword(), 
                                               formData.getConfirmationPassword())) {
            return false; // Password validation failed
        }
        
        // Step 3: Validate unique username
        if (!validationService.validateUniqueUsername(formData.getUsername())) {
            return false; // Username already exists
        }
        
        // Step 4: Validate unique email
        if (!validationService.validateUniqueEmail(formData.getEmail())) {
            return false; // Email already exists
        }
        
        try {
            // Step 5: Hash password (as per sequence diagram requirement)
            String hashedPassword = passwordService != null ? 
                passwordService.hashPassword(formData.getPassword()) : 
                formData.getPassword(); // Fallback if passwordService not provided
            
            // Step 6: Create User domain object
            User user = new User(
                formData.getName(),
                formData.getSurname(),
                formData.getMobilePhone(),
                formData.getEmail(),
                formData.getUsername(),
                hashedPassword
            );
            
            // Step 7: Save user to repository
            boolean saveResult = userRepository.save(user);
            
            return saveResult;
            
        } catch (Exception e) {
            // Handle any unexpected exceptions
            // In case of connection issues, throw ConnectionInterruptedException
            if (e instanceof ConnectionInterruptedException) {
                throw (ConnectionInterruptedException) e;
            }
            return false;
        }
    }
}