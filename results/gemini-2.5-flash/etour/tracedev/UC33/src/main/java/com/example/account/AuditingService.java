package com.example.account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Service for auditing system events. (Added to satisfy REQ-016)
 * In a real application, this would persist audit logs to a file, database, or dedicated logging system.
 */
public class AuditingService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Records an audit event.
     *
     * @param event The name or type of the event (e.g., "User Login", "Account Created").
     * @param details A map of key-value pairs providing additional context for the event.
     */
    public void audit(String event, Map<String, String> details) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println("[AUDIT][" + timestamp + "] Event: " + event + ", Details: " + details);
    }
}