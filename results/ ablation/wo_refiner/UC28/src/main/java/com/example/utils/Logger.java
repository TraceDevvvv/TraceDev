package com.example.utils;

import com.example.agency.AgencyOperator;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Utility class for logging.
 * Added to satisfy requirement REQ-012
 */
public class Logger {
    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logDeletionSuccess(List<Long> tagIds) {
        System.out.println(LocalDateTime.now() + " [INFO] Successfully deleted tags with IDs: " + tagIds);
    }

    public void logError(Exception error) {
        System.out.println(LocalDateTime.now() + " [ERROR] " + error.getClass().getSimpleName() + ": " + error.getMessage());
    }

    public void logAuthentication(AgencyOperator user) {
        System.out.println(LocalDateTime.now() + " [INFO] User authenticated: " + user.getName() + " (ID: " + user.getId() + ")");
    }

    /**
     * Log authentication failure.
     * Added to satisfy requirement REQ-004
     * @param token the invalid token
     */
    public void logAuthenticationFailure(String token) {
        System.out.println(LocalDateTime.now() + " [WARN] Authentication failure for token: " + token);
    }
}