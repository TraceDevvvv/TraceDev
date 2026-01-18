package com.example.adapters.serv;

import com.example.business.ValidationService;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of ValidationService.
 */
public class ValidationServiceImpl implements ValidationService {
    @Override
    public List<String> validateAccountData(String username, String email, String phone) {
        List<String> errors = new ArrayList<>();
        if (username == null || username.trim().isEmpty()) {
            errors.add("Username cannot be empty");
        }
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email cannot be empty");
        } else if (!email.contains("@")) {
            errors.add("Email must contain '@'");
        }
        if (phone == null || phone.trim().isEmpty()) {
            errors.add("Phone cannot be empty");
        } else if (!phone.matches("\\+?[0-9\\-\\s]+")) {
            errors.add("Phone must contain only digits, spaces, dashes, and optional '+'");
        }
        return errors;
    }
}