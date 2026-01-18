package com.example.domain;

/**
 * Port interface for sending notifications about conventions to an external agency.
 * This is a "Port" in the Hexagonal Architecture, defining the contract for external communication.
 */
public interface AgencyNotificationPort {
    /**
     * Sends a notification about a specific convention to the external agency.
     *
     * @param convention The Convention entity to notify about.
     * @return true if the notification was sent successfully, false otherwise.
     * @throws EtoURConnectionException if there is a connection issue with the external system (REQ-003).
     */
    boolean notifyConvention(Convention convention) throws EtoURConnectionException;
}