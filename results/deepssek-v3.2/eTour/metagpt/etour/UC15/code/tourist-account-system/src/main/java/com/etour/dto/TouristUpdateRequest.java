package com.etour.dto;

import com.etour.entity.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Data Transfer Object for updating Tourist accounts.
 * All fields are optional to support partial updates.
 * Used when an agency operator modifies tourist data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TouristUpdateRequest {
    
    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
    private String firstName;
    
    @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters")
    private String lastName;
    
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;
    
    @Pattern(regexp = "^\\+?[0-9\\-\\(\\)\\s]*$", message = "Phone number format is invalid")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
    private Address address;
    
    @Size(max = 100, message = "Nationality must not exceed 100 characters")
    private String nationality;
    
    @Size(max = 50, message = "Passport number must not exceed 50 characters")
    private String passportNumber;
    
    private Boolean active;
    
    /**
     * Checks if this update request is empty (all fields are null).
     * 
     * @return true if all fields are null, false otherwise
     */
    public boolean isEmpty() {
        return firstName == null &&
               lastName == null &&
               email == null &&
               phoneNumber == null &&
               dateOfBirth == null &&
               address == null &&
               nationality == null &&
               passportNumber == null &&
               active == null;
    }
    
    /**
     * Checks if this update request contains any changes for the given tourist.
     * 
     * @param touristDTO The current tourist data to compare against
     * @return true if there are actual changes, false otherwise
     */
    public boolean hasChanges(TouristDTO touristDTO) {
        if (touristDTO == null) {
            return !isEmpty();
        }
        
        // Compare each field if it's specified in the request
        if (firstName != null && !firstName.equals(touristDTO.getFirstName())) {
            return true;
        }
        if (lastName != null && !lastName.equals(touristDTO.getLastName())) {
            return true;
        }
        if (email != null && !email.equals(touristDTO.getEmail())) {
            return true;
        }
        if (phoneNumber != null && !phoneNumber.equals(touristDTO.getPhoneNumber())) {
            return true;
        }
        if (dateOfBirth != null && !dateOfBirth.equals(touristDTO.getDateOfBirth())) {
            return true;
        }
        if (address != null) {
            Address currentAddress = touristDTO.getAddress();
            if (currentAddress == null || !address.equals(currentAddress)) {
                return true;
            }
        }
        if (nationality != null && !nationality.equals(touristDTO.getNationality())) {
            return true;
        }
        if (passportNumber != null && !passportNumber.equals(touristDTO.getPassportNumber())) {
            return true;
        }
        if (active != null && !active.equals(touristDTO.getActive())) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Validates that at least one field is being updated.
     * 
     * @return true if at least one field is not null, false otherwise
     */
    public boolean hasUpdates() {
        return !isEmpty();
    }
    
    /**
     * Gets a summary of what fields are being updated.
     * 
     * @return String listing updated fields
     */
    public String getUpdateSummary() {
        StringBuilder summary = new StringBuilder("Update fields: ");
        boolean first = true;
        
        if (firstName != null) {
            if (!first) summary.append(", ");
            summary.append("firstName");
            first = false;
        }
        if (lastName != null) {
            if (!first) summary.append(", ");
            summary.append("lastName");
            first = false;
        }
        if (email != null) {
            if (!first) summary.append(", ");
            summary.append("email");
            first = false;
        }
        if (phoneNumber != null) {
            if (!first) summary.append(", ");
            summary.append("phoneNumber");
            first = false;
        }
        if (dateOfBirth != null) {
            if (!first) summary.append(", ");
            summary.append("dateOfBirth");
            first = false;
        }
        if (address != null) {
            if (!first) summary.append(", ");
            summary.append("address");
            first = false;
        }
        if (nationality != null) {
            if (!first) summary.append(", ");
            summary.append("nationality");
            first = false;
        }
        if (passportNumber != null) {
            if (!first) summary.append(", ");
            summary.append("passportNumber");
            first = false;
        }
        if (active != null) {
            if (!first) summary.append(", ");
            summary.append("active");
            first = false;
        }
        
        if (first) {
            summary.append("none");
        }
        
        return summary.toString();
    }
    
    /**
     * Creates a map of changed values for audit logging.
     * 
     * @param original The original tourist data
     * @return Map of field names to new values (only changed fields)
     */
    public java.util.Map<String, Object> getChangedValuesMap(TouristDTO original) {
        java.util.Map<String, Object> changes = new java.util.HashMap<>();
        
        if (original == null) {
            return changes;
        }
        
        if (firstName != null && !firstName.equals(original.getFirstName())) {
            changes.put("firstName", firstName);
        }
        if (lastName != null && !lastName.equals(original.getLastName())) {
            changes.put("lastName", lastName);
        }
        if (email != null && !email.equals(original.getEmail())) {
            changes.put("email", email);
        }
        if (phoneNumber != null && !phoneNumber.equals(original.getPhoneNumber())) {
            changes.put("phoneNumber", phoneNumber);
        }
        if (dateOfBirth != null && !dateOfBirth.equals(original.getDateOfBirth())) {
            changes.put("dateOfBirth", dateOfBirth);
        }
        if (address != null) {
            Address originalAddress = original.getAddress();
            if (originalAddress == null || !address.equals(originalAddress)) {
                changes.put("address", address);
            }
        }
        if (nationality != null && !nationality.equals(original.getNationality())) {
            changes.put("nationality", nationality);
        }
        if (passportNumber != null && !passportNumber.equals(original.getPassportNumber())) {
            changes.put("passportNumber", passportNumber);
        }
        if (active != null && !active.equals(original.getActive())) {
            changes.put("active", active);
        }
        
        return changes;
    }
}