package com.example.app.application;

import com.example.app.dataaccess.UserRepository;
import com.example.app.domain.PasswordHasher;
import com.example.app.domain.User;
import com.example.app.domain.UserValidator;
import com.example.app.dtos.UserCreationRequestDTO;
import com.example.app.dtos.UserCreationResultDTO;

import java.util.List;
import java.util.UUID;

/**
 * Service responsible for orchestrating the user creation process.
 * It interacts with domain serv and repositories to validate, hash, and persist user data.
 */
public class UserCreationService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordHasher passwordHasher;

    public UserCreationService(UserRepository userRepository, UserValidator userValidator, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordHasher = passwordHasher;
    }

    /**
     * Creates a new user based on the provided request DTO.
     * Implements the core logic of the user creation sequence from the sequence diagram.
     *
     * @param request The DTO containing the data for the new user.
     * @return A UserCreationResultDTO indicating success or failure with validation errors.
     */
    public UserCreationResultDTO createUser(UserCreationRequestDTO request) {
        System.out.println("[UserCreationService] Attempting to create user with login: " + request.login);

        // 1. Validate the incoming request DTO
        List<String> validationErrors = userValidator.validateUserCreation(request);
        System.out.println("[UserCreationService] UserValidator returned validationResult (m16)."); // m16 trace
        if (!validationErrors.isEmpty()) {
            System.out.println("[UserCreationService] User creation failed due to validation errors.");
            UserCreationResultDTO result = new UserCreationResultDTO(validationErrors);
            System.out.println("[UserCreationService] UserCreationResultDTO returned (failure) (m23)."); // m23 trace
            return result;
        }

        // Check if user with this login already exists
        if (userRepository.findByLogin(request.login) != null) {
            validationErrors.add("User with login '" + request.login + "' already exists.");
            System.out.println("[UserCreationService] User creation failed: login already exists.");
            UserCreationResultDTO result = new UserCreationResultDTO(validationErrors);
            System.out.println("[UserCreationService] UserCreationResultDTO returned (failure) (m23)."); // m23 trace
            return result;
        }


        // 2. Hash the password
        String hashedPassword = passwordHasher.hashPassword(request.password);
        System.out.println("[UserCreationService] PasswordHasher returned hashedPassword (m18)."); // m18 trace
        System.out.println("[UserCreationService] Password hashed successfully.");

        // 3. Create a new User domain object
        User newUser = new User(
                UUID.randomUUID(),
                request.name,
                request.surname,
                request.email,
                request.cell,
                request.login,
                hashedPassword
        );
        System.out.println("[UserCreationService] User object created (m19)."); // m19 trace
        System.out.println("[UserCreationService] User domain object created.");

        // 4. Save the user to the repository
        userRepository.save(newUser);
        System.out.println("[UserCreationService] User " + newUser.getLogin() + " saved to repository.");

        // 5. Return success result
        System.out.println("[UserCreationService] User creation successful for login: " + newUser.getLogin());
        UserCreationResultDTO result = new UserCreationResultDTO(newUser.getLogin());
        System.out.println("[UserCreationService] UserCreationResultDTO returned (success) (m22)."); // m22 trace
        return result;
    }
}