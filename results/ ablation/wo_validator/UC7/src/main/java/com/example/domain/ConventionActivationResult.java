package com.example.domain;

import java.util.Date;

/**
 * Result object for convention activation operation.
 * Contains success status, message, timestamp, and optionally data for form display.
 */
public class ConventionActivationResult {
    private final boolean success;
    private final String message;
    private final Date activationTimestamp;
    private final ConventionDataDTO dataForForm; // Optional data for form display

    public ConventionActivationResult(boolean success, String message, Date activationTimestamp, ConventionDataDTO dataForForm) {
        this.success = success;
        this.message = message;
        this.activationTimestamp = activationTimestamp;
        this.dataForForm = dataForForm;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Date getActivationTimestamp() {
        return activationTimestamp;
    }

    public ConventionDataDTO getDataForForm() {
        return dataForForm;
    }
}