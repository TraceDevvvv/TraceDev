package com.etour.registration.ports;

/**
 * Port/Interface for sending notifications.
 */
public interface NotificationPort {
    boolean sendConfirmation(String accountId, String email);
}