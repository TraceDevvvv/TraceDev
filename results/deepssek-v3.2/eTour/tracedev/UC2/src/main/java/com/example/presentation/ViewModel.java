package com.example.presentation;

import java.util.List;

/**
 * View model for rendering results (success or error).
 */
public class ViewModel {
    private boolean success;
    private String message;
    private List<String> errors;
    private Object data;
    
    public ViewModel(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public ViewModel(boolean success, String message, List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
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
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}