package com.example.infrastructure.validation;

import com.example.application.dto.ModifyTouristDTO;

/**
 * Validates tourist data for correctness.
 * Implements validation rules for name, email, phone, and nationality.
 */
public class TouristValidator {

    public boolean validateName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }

    public boolean validateEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean validatePhone(String phone) {
        return phone != null && phone.matches("\\+?[0-9\\-\\s]+");
    }

    public boolean validateNationality(String nationality) {
        return nationality != null && !nationality.trim().isEmpty();
    }

    /**
     * Validates all fields in the DTO.
     */
    public boolean validateAll(ModifyTouristDTO dto) {
        return validateName(dto.getName()) &&
               validateEmail(dto.getEmail()) &&
               validatePhone(dto.getPhoneNumber()) &&
               validateNationality(dto.getNationality());
    }
}