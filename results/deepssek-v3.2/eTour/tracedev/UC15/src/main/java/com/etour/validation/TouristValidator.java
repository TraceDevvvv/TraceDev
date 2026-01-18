package com.etour.validation;

import com.etour.dto.TouristDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates TouristDTO data.
 */
public class TouristValidator {
    public List<String> validate(TouristDTO touristDto) {
        List<String> errors = new ArrayList<>();

        if (touristDto.getName() == null || touristDto.getName().trim().isEmpty()) {
            errors.add("Name is required.");
        }

        if (touristDto.getEmail() == null || touristDto.getEmail().trim().isEmpty()) {
            errors.add("Email is required.");
        } else if (!touristDto.getEmail().contains("@")) {
            errors.add("Email must be valid.");
        }

        if (touristDto.getPhoneNumber() == null || touristDto.getPhoneNumber().trim().isEmpty()) {
            errors.add("Phone number is required.");
        } else if (!touristDto.getPhoneNumber().matches("\\d+")) {
            errors.add("Phone number must contain only digits.");
        }

        return errors;
    }
}