package com.example.service;

import java.util.Map;

/**
 * Service for validating point data.
 */
public class ValidationService {
    
    /**
     * Validates point data according to business rules (REQ-FLOW-006).
     * @param data The data to validate.
     * @return true if data is valid, false otherwise.
     */
    public boolean validatePointData(Map<String, String> data) {
        // Check each field that is present
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            if (isEmptyField(value)) {
                System.out.println("Validation failed: " + key + " is empty.");
                return false;
            }
            
            // Field-specific validation
            switch (key) {
                case "contactInfo":
                    if (!validateContactInfo(value)) {
                        System.out.println("Validation failed: Invalid contact info format.");
                        return false;
                    }
                    break;
                case "operatingHours":
                    if (!validateHoursFormat(value)) {
                        System.out.println("Validation failed: Invalid operating hours format.");
                        return false;
                    }
                    break;
                case "name":
                case "address":
                    // Basic validation only (non-empty already checked)
                    if (value.trim().length() < 2) {
                        System.out.println("Validation failed: " + key + " is too short.");
                        return false;
                    }
                    break;
            }
        }
        
        return true;
    }
    
    /**
     * Checks if a field is empty.
     * @param value The field value.
     * @return true if empty, false otherwise.
     */
    public boolean isEmptyField(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    /**
     * Validates contact information format.
     * @param contact The contact info to validate.
     * @return true if valid, false otherwise.
     */
    public boolean validateContactInfo(String contact) {
        // Simple validation: should contain at least one digit and be reasonable length
        return contact != null && 
               contact.length() >= 5 && 
               contact.matches(".*\\d+.*");
    }
    
    /**
     * Validates operating hours format.
     * @param hours The hours string to validate.
     * @return true if valid, false otherwise.
     */
    public boolean validateHoursFormat(String hours) {
        // Simple validation: should contain AM/PM or 24h format indicator
        return hours != null && 
               (hours.toUpperCase().contains("AM") || 
                hours.toUpperCase().contains("PM") ||
                hours.contains("-")); // Example format: "9AM-10PM" or "09:00-22:00"
    }
}