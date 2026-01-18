package com.example.application;

import com.example.interfaces.IAuthenticatorService;
import com.example.interfaces.ILogger;
import com.example.dtos.CredentialsDTO;
import com.example.dtos.AuthToken;

/**
 * Decorator that adds logging to the authentication service.
 */
public class LoggingDecorator implements IAuthenticatorService {
    private IAuthenticatorService decoratedService;
    private ILogger logger;

    public LoggingDecorator(IAuthenticatorService decoratedService, ILogger logger) {
        this.decoratedService = decoratedService;
        this.logger = logger;
    }

    @Override
    public AuthToken authenticate(CredentialsDTO credentials) {
        logger.info("Authentication starting for user: " + credentials.getUsername());
        long startTime = System.currentTimeMillis();
        try {
            AuthToken token = decoratedService.authenticate(credentials);
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Authentication completed successfully for user: " + credentials.getUsername() + " in " + duration + " ms");
            return token;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("Authentication failed for user: " + credentials.getUsername() + " after " + duration + " ms - " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void logout(String token) {
        logger.info("Logout starting for token: " + token);
        decoratedService.logout(token);
        logger.info("Logout completed for token: " + token);
    }
}