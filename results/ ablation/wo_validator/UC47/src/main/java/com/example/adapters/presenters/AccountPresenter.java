package com.example.adapters.presenters;

import java.util.List;
import java.util.Map;

/**
 * Presenter for formatting responses from the use case to the web interface.
 */
public class AccountPresenter {
    public Map<String, Object> presentSuccess(String message) {
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", message);
        return response;
    }

    public Map<String, Object> presentSuccess(String message, Map<String, String> data) {
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    public Map<String, Object> presentError(List<String> errors) {
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("success", false);
        response.put("errors", errors);
        return response;
    }
}