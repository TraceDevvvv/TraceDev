package com.newsagency.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of a validation operation.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;
    private String message;
    
    public ValidationResult() {
        this.isValid = false;
        this.errors = new ArrayList<>();
        this.message = "";
    }
    
    public ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.errors = new ArrayList<>();
        this.message = message;
    }
    
    public boolean getIsValid() {
        return isValid;
    }
    
    public boolean isValid() {
        return isValid;
    }
    
    public void setValid(boolean valid) {
        this.isValid = valid;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}