package com.etour.login.service;

import com.etour.login.dto.LoginRequestDTO;
import com.etour.login.dto.LoginResponseDTO;
import com.etour.login.exception.ETOURConnectionException;
import com.etour.login.model.User;
import com.etour.login.repository.UserRepository;
import com.etour.login.security.PasswordEncoder;
import java.util.Optional;

/**
 * Implementation of LoginService.
 * Handles the authentication logic including validation, user lookup, and password verification.
 */
public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO authenticate(LoginRequestDTO credentials) throws ETOURConnectionException {
        // Simulate potential connection exception
        if (Math.random() < 0.1) { // 10% chance to simulate connection error
            throw new ETOURConnectionException("Network/Server Failure");
        }

        // Validate input data first (Req 009)
        if (!validateData(credentials)) {
            return new LoginResponseDTO(false, null, "Invalid data");
        }

        // Find user by username
        Optional<User> userOptional = userRepository.findByUsername(credentials.getUsername());
        if (userOptional.isEmpty()) {
            return new LoginResponseDTO(false, null, "Invalid credentials");
        }

        User user = userOptional.get();
        
        // Verify password
        boolean passwordMatches = user.validatePassword(credentials.getPassword(), passwordEncoder);
        if (!passwordMatches) {
            return new LoginResponseDTO(false, null, "Invalid credentials");
        }

        // Authentication successful
        return new LoginResponseDTO(true, user.getUserId());
    }

    /**
     * Validates the login request data.
     * Added to satisfy requirement REQ-009.
     *
     * @param request the login request DTO
     * @return true if data is valid, false otherwise
     */
    protected boolean validateData(LoginRequestDTO request) {
        return request != null &&
               request.getUsername() != null && !request.getUsername().trim().isEmpty() &&
               request.getPassword() != null && !request.getPassword().trim().isEmpty();
    }
}