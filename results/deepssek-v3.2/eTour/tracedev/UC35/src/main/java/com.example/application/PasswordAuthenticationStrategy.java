package com.example.application;

import com.example.interfaces.IAuthenticationStrategy;
import com.example.interfaces.IUserRepository;
import com.example.dtos.CredentialsDTO;
import com.example.dtos.AuthenticationResult;
import com.example.domain.User;
import java.util.Optional;

/**
 * Authentication strategy using username/password.
 */
public class PasswordAuthenticationStrategy implements IAuthenticationStrategy {
    private Object passwordEncoder;
    private IUserRepository userRepository;

    public PasswordAuthenticationStrategy(Object passwordEncoder, IUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticationResult authenticate(CredentialsDTO credentials) {
        try {
            // Find user by username
            Optional<User> userOpt = userRepository.findUserByUsername(credentials.getUsername());
            if (userOpt.isEmpty()) {
                return new AuthenticationResult(false, null, "User not found");
            }

            User user = userOpt.get();
            // Validate password
            boolean passwordValid = user.validatePassword(credentials.getPassword(), passwordEncoder.toString());
            if (passwordValid) {
                return new AuthenticationResult(true, user, null);
            } else {
                return new AuthenticationResult(false, null, "Invalid password");
            }
        } catch (RuntimeException e) {
            // Propagate exception for connection interruption flow
            throw e;
        }
    }
}