package com.example;

/**
 * Represents the result of a banner verification.
 * Contains status and message information.
 */
public class VerificationResult {
    private boolean canAddBanner;
    private String message;
    private String errorCode;

    /**
     * Constructor for VerificationResult.
     * @param canAddBanner Indicates if a banner can be added.
     * @param message The message describing the result.
     */
    public VerificationResult(boolean canAddBanner, String message) {
        this.canAddBanner = canAddBanner;
        this.message = message;
        this.errorCode = null; // No error code by default
    }

    /**
     * Constructor for VerificationResult with error code.
     * @param canAddBanner Indicates if a banner can be added.
     * @param message The message describing the result.
     * @param errorCode The error code in case of failure.
     */
    public VerificationResult(boolean canAddBanner, String message, String errorCode) {
        this.canAddBanner = canAddBanner;
        this.message = message;
        this.errorCode = errorCode;
    }

    public boolean isCanAddBanner() {
        return canAddBanner;
    }

    public void setCanAddBanner(boolean status) {
        this.canAddBanner = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String code) {
        this.errorCode = code;
    }
}