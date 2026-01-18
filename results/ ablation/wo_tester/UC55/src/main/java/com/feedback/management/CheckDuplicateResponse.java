package com.feedback.management;

public class CheckDuplicateResponse {
    private boolean isDuplicate;
    private Feedback existingFeedback;
    private String message;

    public CheckDuplicateResponse(boolean isDuplicate, Feedback existingFeedback) {
        this.isDuplicate = isDuplicate;
        this.existingFeedback = existingFeedback;
        this.message = (isDuplicate) ? "Duplicate feedback found." : "No duplicate feedback found.";
    }

    public CheckDuplicateResponse(boolean isDuplicate, Feedback existingFeedback, String message) {
        this.isDuplicate = isDuplicate;
        this.existingFeedback = existingFeedback;
        this.message = message;
    }

    public boolean isDuplicate() {
        return isDuplicate;
    }

    public Feedback getExistingFeedback() {
        return existingFeedback;
    }

    public String getMessage() {
        return message;
    }
}