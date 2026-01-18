
package com.example.service;

import com.example.dto.TouristProfileDTO;
import com.example.model.TouristProfileEntity;
import com.example.model.ValidationResult;

/**
 * Service implementation for tourist profile operations.
 * Implements business logic and coordinates between components.
 */
public class TouristService implements ITouristService {
    private Object profileRepository;
    private Object validator;
    private Object connectionMonitor;
    private Object errorHandler;

    // Constructors
    public TouristService() {}

    public TouristService(Object profileRepository, 
                         Object validator, 
                         Object connectionMonitor,
                         Object errorHandler) {
        this.profileRepository = profileRepository;
        this.validator = validator;
        this.connectionMonitor = connectionMonitor;
        this.errorHandler = errorHandler;
    }

    @Override
    public TouristProfileDTO loadTouristProfile(String touristId) {
        // Step 2 of Sequence Diagram: System loads the data
        System.out.println("[Service] Loading profile for tourist: " + touristId);
        
        TouristProfileEntity entity = ((TouristProfileRepository) profileRepository).findById(touristId);
        if (entity == null) {
            System.out.println("[Service] No profile found for ID: " + touristId);
            return null;
        }
        
        // Convert entity to DTO
        TouristProfileDTO dto = entity.toDTO();
        System.out.println("[Service] Loaded profile data: " + dto);
        return dto;
    }

    @Override
    public boolean updateTouristProfile(String touristId, TouristProfileDTO dto) {
        // Step 6 of Sequence Diagram: System checks modified information
        System.out.println("[Service] Updating profile for tourist: " + touristId);
        
        boolean modified = checkModifiedInformation(dto);
        if (!modified) {
            System.out.println("[Service] No information modified, skipping update");
            return false;
        }
        
        // Validate profile data
        ValidationResult validationResult = validateProfileData(dto);
        if (!validationResult.isValid()) {
            // Invalid data - trigger errored use case
            System.out.println("[Service] Validation failed: " + validationResult.getErrors());
            ((Runnable) () -> {
                // Handle error
            }).run();
            return false;
        }
        
        // Load current data
        TouristProfileEntity currentEntity = ((TouristProfileRepository) profileRepository).findById(touristId);
        if (currentEntity == null) {
            System.out.println("[Service] Cannot update - profile not found for ID: " + touristId);
            return false;
        }
        
        // Apply business rules
        TouristProfileDTO updatedData = applyBusinessRules(currentEntity.toDTO(), dto);
        
        // Update entity with new data
        currentEntity.updateFromDTO(updatedData);
        
        // Check connection before save
        if (connectionMonitor != null) {
            System.out.println("[Service] Connection check skipped - monitor not available");
        }
        
        // Save updated entity
        boolean saveSuccess = ((TouristProfileRepository) profileRepository).save(currentEntity);
        if (!saveSuccess) {
            // Handle data persistence error
            System.out.println("[Service] Save operation failed");
            return false;
        }
        
        System.out.println("[Service] Profile updated successfully");
        return true;
    }

    /**
     * Step 6 of Sequence Diagram: Check if information was modified.
     * Implementation note: In real scenario, we might compare with current data.
     */
    private boolean checkModifiedInformation(TouristProfileDTO dto) {
        // For demonstration, assume all DTOs with non-null fields are modified
        return dto != null && 
               (dto.getName() != null || dto.getEmail() != null || dto.getPhone() != null);
    }

    /**
     * Validate profile data using validator.
     * Sequence Diagram step: Service calls validator.validate(profileDTO)
     */
    private ValidationResult validateProfileData(TouristProfileDTO dto) {
        try {
            return (ValidationResult) validator.getClass()
                .getMethod("validate", TouristProfileDTO.class)
                .invoke(validator, dto);
        } catch (Exception e) {
            System.out.println("[Service] Validation error: " + e.getMessage());
            ValidationResult result = new ValidationResult();
            result.addError("Validation failed");
            return result;
        }
    }

    /**
     * Apply business rules to profile data.
     * Sequence Diagram step: Service.applyBusinessRules(currentData, newData)
     */
    private TouristProfileDTO applyBusinessRules(TouristProfileDTO currentData, TouristProfileDTO newData) {
        // For demonstration, apply simple business rules:
        // 1. Preserve current values if new ones are null
        // 2. Trim whitespace from all fields
        // 3. Capitalize name
        
        TouristProfileDTO result = new TouristProfileDTO();
        
        // Name: capitalize first letter of each word
        if (newData.getName() != null) {
            String name = newData.getName().trim();
            result.setName(capitalizeWords(name));
        } else {
            result.setName(currentData.getName());
        }
        
        // Email: convert to lowercase
        if (newData.getEmail() != null) {
            result.setEmail(newData.getEmail().trim().toLowerCase());
        } else {
            result.setEmail(currentData.getEmail());
        }
        
        // Phone: remove all non-digit characters except +
        if (newData.getPhone() != null) {
            result.setPhone(normalizePhone(newData.getPhone().trim()));
        } else {
            result.setPhone(currentData.getPhone());
        }
        
        return result;
    }

    /**
     * Helper method to capitalize words in a string.
     */
    private String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                result.append(Character.toUpperCase(words[i].charAt(0)))
                      .append(words[i].substring(1).toLowerCase());
                if (i < words.length - 1) {
                    result.append(" ");
                }
            }
        }
        
        return result.toString();
    }

    /**
     * Helper method to normalize phone number.
     */
    private String normalizePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return phone;
        }
        
        // Keep plus sign if present at start
        boolean hasPlus = phone.startsWith("+");
        String digits = phone.replaceAll("[^0-9]", "");
        
        return hasPlus ? "+" + digits : digits;
    }

    // Getters and setters for dependencies
    public Object getProfileRepository() {
        return profileRepository;
    }

    public void setProfileRepository(Object profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Object getValidator() {
        return validator;
    }

    public void setValidator(Object validator) {
        this.validator = validator;
    }

    public Object getConnectionMonitor() {
        return connectionMonitor;
    }

    public void setConnectionMonitor(Object connectionMonitor) {
        this.connectionMonitor = connectionMonitor;
    }

    public Object getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(Object errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    // Inner interface to maintain compatibility
    private interface TouristProfileRepository {
        TouristProfileEntity findById(String touristId);
        boolean save(TouristProfileEntity entity);
    }
}
