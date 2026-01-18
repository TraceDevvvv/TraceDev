package com.example.model;

import java.util.Date;

/**
 * Represents a logout confirmation request.
 */
public class LogoutConfirmation {
    public String userId;
    public Date confirmationTime;
    public boolean isConfirmed;

    /**
     * Constructor as specified in the class diagram.
     * @param userId The user ID.
     * @param timestamp The time of confirmation request.
     */
    public LogoutConfirmation(String userId, Date timestamp) {
        this.userId = userId;
        this.confirmationTime = timestamp;
        this.isConfirmed = false;
    }

    /**
     * Confirms the logout request.
     */
    public void confirm() {
        this.isConfirmed = true;
    }

    /**
     * Checks if the confirmation has expired (older than 5 minutes).
     * @return true if expired, false otherwise.
     */
    public boolean isExpired() {
        long now = System.currentTimeMillis();
        long confirmationTimestamp = confirmationTime.getTime();
        long fiveMinutesInMillis = 5 * 60 * 1000;
        return (now - confirmationTimestamp) > fiveMinutesInMillis;
    }
}