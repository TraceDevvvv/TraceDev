package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a user confirmation.
 */
public class Confirmation {
    public boolean isConfirmed;
    public String confirmationId;
    public LocalDateTime timestamp;
    public boolean acknowledgedByUser;

    public Confirmation(boolean isConfirmed, String confirmationId, boolean acknowledgedByUser) {
        this.isConfirmed = isConfirmed;
        this.confirmationId = confirmationId;
        this.acknowledgedByUser = acknowledgedByUser;
        this.timestamp = LocalDateTime.now();
    }
}