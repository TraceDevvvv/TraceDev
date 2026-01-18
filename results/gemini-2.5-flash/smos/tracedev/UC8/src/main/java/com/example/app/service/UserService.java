package com.example.app.service;

import com.example.app.dto.UserFormDto;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;

/**
 * Implements the business logic for user management.
 * This class corresponds to the 'UserService' in the UML Class Diagram.
 * It orchestrates calls to UserRepository and ValidationService.
 */
public class UserService {
    // Associations: UserService orchestrates UserRepository, delegates validation to ValidationService
    private UserRepository userRepository;
    private ValidationService validationService;

    /**
     * Constructor for UserService.
     * @param userRepository The repository for user persistence.
     * @param validationService The service for validating user data.
     */
    public UserService(UserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    /**
     * Updates user data after validation.
     * This method implements the core logic of the sequence diagram for updating a user.
     * @param userData The UserFormDto containing the data to update.
     * @return true if the user was successfully updated, false otherwise.
     */
    public boolean updateUser(UserFormDto userData) {
        System.out.println("UserService: Attempting to update user: " + userData.getUsername());

        // Sequence Diagram: Service -> Validator : validate(userData : UserFormDto)
        boolean isValid = validationService.validate(userData);

        if (!isValid) {
            System.out.println("UserService: Validation failed for user: " + userData.getUsername());
            // Sequence Diagram: Validator --> Service : validationResult(false)
            // Sequence Diagram: Service --> Controller : updateResult(false, "Invalid user data.")
            return false; // Return false indicating validation failure
        }

        System.out.println("UserService: User data is valid.");
        // Sequence Diagram: Validator --> Service : validationResult(true)

        // Note in SD: Converts UserFormDto to User entity.
        User userEntity = convertDtoToEntity(userData);

        // Assumption: For an update, we first retrieve the existing user to preserve sensitive data like passwordHash.
        // In a real scenario, password updates would be a separate flow.
        User existingUser = userRepository.findById(userEntity.getId());
        if (existingUser != null) {
            // Update fields that are allowed to be updated via the DTO
            existingUser.setUsername(userEntity.getUsername());
            existingUser.setEmail(userEntity.getEmail());
            existingUser.setFirstName(userEntity.getFirstName());
            existingUser.setLastName(userEntity.getLastName());
            // passwordHash remains unchanged by this DTO update flow
            userEntity = existingUser; // Use the updated existing user entity
        } else {
            // If user not found, treat as a new user or throw an error.
            // For this update scenario, we assume the user must exist.
            System.err.println("UserService: User with ID " + userEntity.getId() + " not found for update.");
            return false;
        }

        // Sequence Diagram: Service -> Repository : save(userEntity : User)
        User savedUser = userRepository.save(userEntity);

        if (savedUser != null) {
            System.out.println("UserService: User updated successfully: " + savedUser.getUsername());
            // Sequence Diagram: Repository --> Service : savedUser(User)
            // Sequence Diagram: Service --> Controller : updateResult(true, "User updated successfully.")
            return true;
        } else {
            System.err.println("UserService: Failed to save user: " + userEntity.getUsername());
            return false;
        }
    }

    /**
     * Converts a UserFormDto object to a User entity object.
     * This is a private helper method as per the UML Class Diagram.
     * @param dto The UserFormDto to convert.
     * @return A User entity.
     */
    private User convertDtoToEntity(UserFormDto dto) {
        // Assumption: passwordHash is not part of UserFormDto for update,
        // so it might be fetched from an existing user or set to a placeholder if it's a new user.
        // For update, we'll assume it's set later or maintained from existing entity.
        // For now, setting a placeholder. The `updateUser` method handles merging with existing.
        return new User(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getFirstName(), dto.getLastName(), "DUMMY_HASH_FROM_DTO_CONVERSION");
    }
}