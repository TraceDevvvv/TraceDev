package com.example.validation;

import com.example.dto.TouristDTO;
import java.util.regex.Pattern;

/**
 * Concrete validator implementing ITouristValidator using Strategy pattern.
 */
public class TouristDataValidator implements ITouristValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^[0-9\\-\\+\\s]*$";

    @Override
    public ValidationResult validate(TouristDTO tourist) {
        ValidationResult result = new ValidationResult();
        
        if (tourist == null) {
            result.addError("Tourist data is null");
            return result;
        }
        
        if (tourist.getName() == null || tourist.getName().trim().isEmpty()) {
            result.addError("Name is required");
        }
        
        if (tourist.getEmail() == null || tourist.getEmail().trim().isEmpty()) {
            result.addError("Email is required");
        } else if (!Pattern.matches(EMAIL_REGEX, tourist.getEmail())) {
            result.addError("Email format is invalid");
        }
        
        if (tourist.getPhone() != null && !tourist.getPhone().trim().isEmpty()) {
            if (!Pattern.matches(PHONE_REGEX, tourist.getPhone())) {
                result.addError("Phone number contains invalid characters");
            }
        }
        
        return result;
    }
}