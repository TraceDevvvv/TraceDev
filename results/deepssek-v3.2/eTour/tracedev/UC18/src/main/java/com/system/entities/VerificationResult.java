package com.system.entities;

/**
 * Result of a banner compliance verification.
 */
public class VerificationResult {
    private boolean compliant;
    private String message;

    public VerificationResult(boolean compliant, String message) {
        this.compliant = compliant;
        this.message = message;
    }

    public boolean isCompliant() {
        return compliant;
    }

    public String getMessage() {
        return message;
    }
}