package com.example.model;

import com.example.dto.TouristProfileDTO;
import java.util.Date;

/**
 * Entity class representing tourist profile in the database.
 * Contains persistence-related fields.
 */
public class TouristProfileEntity {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Date modifiedAt;

    // Constructors
    public TouristProfileEntity() {
        this.modifiedAt = new Date();
    }

    public TouristProfileEntity(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.modifiedAt = new Date();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.modifiedAt = new Date();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.modifiedAt = new Date();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        this.modifiedAt = new Date();
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    /**
     * Convert entity to DTO.
     * Step 2 of Sequence Diagram: Service calls entity.toDTO()
     */
    public TouristProfileDTO toDTO() {
        return new TouristProfileDTO(name, email, phone);
    }

    /**
     * Update entity fields from DTO.
     */
    public void updateFromDTO(TouristProfileDTO dto) {
        if (dto.getName() != null) {
            this.name = dto.getName();
        }
        if (dto.getEmail() != null) {
            this.email = dto.getEmail();
        }
        if (dto.getPhone() != null) {
            this.phone = dto.getPhone();
        }
        this.modifiedAt = new Date();
    }
}