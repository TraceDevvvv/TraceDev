package com.example.business.result;

import java.util.Collections;
import java.util.List;

/**
 * Result object returned after executing update account use case.
 */
public class UpdateAccountResult {
    private boolean success;
    private String message;
    private List<String> errors;
    // Additional field to carry current data for pre‑populating the form (from sequence diagram step 6)
    private java.util.Map<String, String> currentData;

    public UpdateAccountResult(boolean success, String message, List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors != null ? errors : Collections.emptyList();
        this.currentData = null;
    }

    public UpdateAccountResult(boolean success, String message, List<String> errors, java.util.Map<String, String> currentData) {
        this.success = success;
        this.message = message;
        this.errors = errors != null ? errors : Collections.emptyList();
        this.currentData = currentData;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public java.util.Map<String, String> getCurrentData() {
        return currentData;
    }

    public void setCurrentData(java.util.Map<String, String> currentData) {
        this.currentData = currentData;
    }
}