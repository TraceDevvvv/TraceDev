package com.example.service;

import com.example.dto.RegistrationRequest;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.exception.InvalidDataException;
import com.example.exception.DuplicateEmailException;
import com.example.exception.ConnectionException;
import java.util.Date;
import java.util.UUID;

/**
 * Service class for user registration logic.
 * Stereotypes applied: Secure, Transactional.
 */
public class RegistrationService {
    
    private UserRepository userRepository;
    private ValidationService validationService;
    private NotificationService notificationService;
    private LoggingService loggingService;
    private CancellationService cancellationService;
    private ConnectionManager connectionManager;
    
    /**
     * Constructor for RegistrationService.
     * @param userRepository user repository
     * @param validationService validation service
     * @param notificationService notification service
     * @param loggingService logging service (REQ-006)
     * @param cancellationService cancellation service (REQ-013)
     * @param connectionManager connection manager (REQ-014)
     */
    public RegistrationService(
            UserRepository userRepository,
            ValidationService validationService,
            NotificationService notificationService,
            LoggingService loggingService,
            CancellationService cancellationService,
            ConnectionManager connectionManager) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.notificationService = notificationService;
        this.loggingService = loggingService;
        this.cancellationService = cancellationService;
        this.connectionManager = connectionManager;
    }
    
    /**
     * Registers a new user.
     * @param request the registration request
     * @return the created user
     * @throws InvalidDataException if validation fails
     * @throws DuplicateEmailException if email already exists
     * @throws ConnectionException if connection issues occur
     */
    public User registerUser(RegistrationRequest request) 
            throws InvalidDataException, DuplicateEmailException, ConnectionException {
        
        // Step 6.1 from sequence diagram: Check connection
        boolean connectionOk = connectionManager.checkConnection();
        if (!connectionOk) {
            throw new ConnectionException("Connection check failed");
        }
        
        // Step 7-10: Validate data
        boolean isEmailValid = validationService.validateEmail(request.getEmail());
        boolean isPasswordValid = validationService.validatePassword(request.getPassword());
        boolean isUsernameValid = validationService.validateUsername(request.getUsername());
        
        if (!(isEmailValid && isPasswordValid && isUsernameValid)) {
            // Log failed attempt per sequence diagram
            loggingService.logRegistrationAttempt(request.getEmail(), false);
            throw new InvalidDataException("Invalid registration data");
        }
        
        // Step 11-12: Check for duplicate email
        User existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            // Log failed attempt per sequence diagram
            loggingService.logRegistrationAttempt(request.getEmail(), false);
            throw new DuplicateEmailException("Email already exists: " + request.getEmail());
        }
        
        // Step 13-16: Create and save user account
        User newUser = createUserAccount(request);
        User savedUser = userRepository.save(newUser);
        
        // Step 17: Log successful attempt
        loggingService.logRegistrationAttempt(request.getEmail(), true);
        
        // Step 18: Send notification
        notificationService.sendRegistrationSuccessNotification(
            savedUser.getEmail(), savedUser.getUsername());
        
        return savedUser;
    }
    
    /**
     * Validates registration data (private method as per class diagram).
     * @param request the registration request
     * @return true if data is valid
     */
    private boolean validateRegistrationData(RegistrationRequest request) {
        // This method delegates to ValidationService - keeping for class diagram compliance
        return validationService.validateEmail(request.getEmail()) 
            && validationService.validatePassword(request.getPassword())
            && validationService.validateUsername(request.getUsername());
    }
    
    /**
     * Creates a user account (private method as per class diagram).
     * @param request the registration request
     * @return the created user object
     */
    private User createUserAccount(RegistrationRequest request) {
        // Create password hash (simplified for example)
        String passwordHash = hashPassword(request.getPassword());
        // Step 14 from sequence diagram: Create User object
        return new User(request.getUsername(), request.getEmail(), passwordHash);
    }
    
    /**
     * Helper method to hash password.
     * @param password the plain text password
     * @return hashed password
     */
    private String hashPassword(String password) {
        // In a real implementation, use proper password hashing like BCrypt
        return "hashed_" + password; // Simplified for example
    }
}