package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for activation results.
 */
public class ActivationResultDTO {
    public boolean success;
    public String message;
    public String conventionId;
    public Date activationTime;
    public boolean notificationSent;

    public ActivationResultDTO(boolean success, String message, String conventionId, Date activationTime, boolean notificationSent) {
        this.success = success;
        this.message = message;
        this.conventionId = conventionId;
        this.activationTime = activationTime;
        this.notificationSent = notificationSent;
    }

    // Default constructor for flexibility
    public ActivationResultDTO() {}
}