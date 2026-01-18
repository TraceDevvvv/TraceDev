package com.example.insertdelayadmin.service;

import com.example.insertdelayadmin.exception.AuthenticationException;
import com.example.insertdelayadmin.model.Administrator;
import com.example.insertdelayadmin.model.LoginRequest;
import com.example.insertdelayadmin.model.LoginResponse;
import com.example.insertdelayadmin.repository.AdministratorRepository;
import com.example.insertdelayadmin.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for user authentication and JWT token management.
 * It interacts with the AdministratorRepository to validate user credentials
 * and uses JwtUtil to generate and validate JWT tokens.
 */
@Service
public class AuthService {

    private final AdministratorRepository administratorRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder for secure password handling

    /**
     * Constructor for AuthService, injecting required dependencies.
     *
     * @param administratorRepository Repository for Administrator entities.
     * @param jwtUtil Utility for JWT token operations.
     * @param passwordEncoder Encoder for hashing and verifying passwords.
     */
    @Autowired
    public AuthService(AdministratorRepository administratorRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.administratorRepository = administratorRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates an administrator based on the provided login request.
     * If authentication is successful, a JWT token is generated and returned.
     *
     * @param request The {@link LoginRequest} containing username and password.
     * @return A {@link LoginResponse} containing the JWT token, username, and role.
     * @throws AuthenticationException if authentication fails (e.g., invalid username or password).
     */
    public LoginResponse login(LoginRequest request) {
        // 1. Find the administrator by username
        Optional<Administrator> adminOptional = administratorRepository.findByUsername(request.getUsername());

        if (adminOptional.isEmpty()) {
            throw new AuthenticationException("Invalid username or password.");
        }

        Administrator administrator = adminOptional.get();

        // 2. Validate the password using the injected PasswordEncoder
        // The system design's Administrator class has an 'authenticate' method,
        // but for a Spring Security context, using PasswordEncoder is the standard and secure approach.
        // We will use passwordEncoder.matches(rawPassword, encodedPassword)
        if (!passwordEncoder.matches(request.getPassword(), administrator.getPasswordHash())) {
            throw new AuthenticationException("Invalid username or password.");
        }

        // 3. Generate a JWT token for the authenticated administrator
        String token = jwtUtil.generateToken(administrator.getUsername(), administrator.getRole());

        // 4. Return the LoginResponse
        return new LoginResponse(token, administrator.getUsername(), administrator.getRole());
    }

    /**
     * Validates a given JWT token.
     * This method delegates the validation logic to the JwtUtil.
     *
     * @param token The JWT token string to validate.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}