package com.example.touristapp.infrastructure;

import com.example.touristapp.domain.AccountStatus;

import java.time.LocalDateTime;

/**
 * Service for logging audit events.
 * In a real application, this would persist logs to a database or a dedicated logging system.
 * Here, it simply prints to the console.
 */
public class AuditLogService {

    /**
     * Logs an event related to a tourist account status change.
     * @param eventName The name of the event (e.g., "Account Status Changed").
     * @param accountId The ID of the tourist account involved.
     * @param operatorId The ID of the operator who initiated the change.
     * @param status The new status of the account.
     */
    public void logEvent(String eventName, String accountId, String operatorId, AccountStatus status) {
        System.out.println(String.format(
            "[AUDIT] %s | Event: %s | Account ID: %s | Operator ID: %s | New Status: %s",
            LocalDateTime.now(), eventName, accountId, operatorId, status
        ));
    }
}