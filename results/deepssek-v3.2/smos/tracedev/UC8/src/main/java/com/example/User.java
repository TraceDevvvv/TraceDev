package com.example;

import java.util.Map;

/**
 * Represents a User entity.
 */
public class User {
    private String id;
    private String name;
    private String email;
    private Map<String, Object> otherDetails;

    public User(String id, String name, String email, Map<String, Object> otherDetails) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.otherDetails = otherDetails;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Object> getOtherDetails() {
        return otherDetails;
    }

    /**
     * Updates the user details based on the provided DTO.
     * Only updates fields that are present in the changedFields map.
     */
    public void updateDetails(EditUserDTO dto) {
        if (dto == null) return;
        // Update name if changed
        if (dto.getName() != null) {
            this.name = dto.getName();
        }
        // Update email if changed
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        // Update otherDetails with changed fields
        Map<String, Object> changedFields = dto.getChangedFields();
        if (changedFields != null) {
            for (Map.Entry<String, Object> entry : changedFields.entrySet()) {
                this.otherDetails.put(entry.getKey(), entry.getValue());
            }
        }
    }
}