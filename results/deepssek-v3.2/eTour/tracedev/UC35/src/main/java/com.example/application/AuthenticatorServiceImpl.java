
package com.example.application;

import com.example.interfaces.IAuthenticatorService;
import com.example.interfaces.IAuthenticationStrategy;
import com.example.interfaces.IUserRepository;
import com.example.interfaces.ITokenService;
import com.example.interfaces.ILogger;
import com.example.interfaces.IAuditor;
import com.example.dtos.CredentialsDTO;
import com.example.dtos.AuthToken;
import com.example.dtos.AuthenticationResult;
import com.example.domain.LoginAttempt;
import com.example.domain.AuditEvent;
import java.time.LocalDateTime;

/**
 * Core implementation of the authentication service.
 */
public class AuthenticatorServiceImpl implements IAuthenticatorService {
    private IAuthenticationStrategy validationStrategy;
    private IUserRepository userRepository;
    private ITokenService tokenService;
    private ILogger logger;
    private IAuditor auditor;

    public AuthenticatorServiceImpl(IAuthenticationStrategy validationStrategy,
                                    IUserRepository userRepository,
                                    ITokenService tokenService,
                                    ILogger logger,
                                    IAuditor auditor) {
        this.validationStrategy = validationStrategy;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.logger = logger;
        this.auditor = auditor;
    }

    @Override
    public AuthToken authenticate(CredentialsDTO credentials) {
        logger.info("Authentication attempt for user: " + credentials.getUsername());
        try {
            // Delegate to the strategy
            AuthenticationResult authResult = validationStrategy.authenticate(credentials);

            if (authResult.isSuccess()) {
                // Generate token
                AuthToken token = tokenService.generateToken(authResult.getUser());
                // Log successful attempt
                logLoginAttempt(credentials.getUsername(), true);
                // Save login attempt
                userRepository.save(new LoginAttempt(credentials.getUsername(), LocalDateTime.now(), true, getClientIp()));
                auditor.audit(new AuditEvent("LOGIN_SUCCESS", credentials.getUsername()));
                return token;
            } else {
                // Log failed attempt
                logLoginAttempt(credentials.getUsername(), false);
                userRepository.save(new LoginAttempt(credentials.getUsername(), LocalDateTime.now(), false, getClientIp()));
                auditor.audit(new AuditEvent("LOGIN_FAILURE", credentials.getUsername()));
                throw new RuntimeException("Invalid credentials");
            }
        } catch (Exception e) {
            // Rethrow as appropriate exception
            if (e instanceof RuntimeException && e.getMessage().contains("Invalid credentials")) {
                throw e;
            } else if (e instanceof RuntimeException && e.getMessage().contains("Database")) {
                // Sequence diagram: Connection Interrupted flow
                throw new RuntimeException("Database connection failed");
            } else {
                throw new RuntimeException("Connection error during authentication");
            }
        }
    }

    @Override
    public void logout(String token) {
        // In a real implementation, token would be invalidated (e.g., added to a blacklist)
        logger.info("Logout for token: " + token);
        auditor.audit(new AuditEvent("LOGOUT", tokenService.extractUserId(token)));
    }

    /**
     * Logs a login attempt.
     *
     * @param username the username attempted
     * @param success  whether the attempt was successful
     */
    private void logLoginAttempt(String username, boolean success) {
        String message = String.format("Login attempt for user %s: %s", username, success ? "SUCCESS" : "FAILURE");
        logger.info(message);
    }

    // Helper method to simulate client IP
    private String getClientIp() {
        return "127.0.0.1";
    }
}
