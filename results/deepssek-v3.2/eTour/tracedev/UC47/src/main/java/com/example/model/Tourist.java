package com.example.model;

import com.example.dto.TouristProfileDTO;

/**
 * Entity representing a tourist user.
 * Contains personal information and authentication details.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;

    // Constructors
    public Tourist() {}

    public Tourist(String id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
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
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Update tourist profile.
     * Sequence Diagram step: Tourist updates profile
     */
    public boolean updateProfile(TouristProfileDTO profileDto) {
        System.out.println("[Tourist] Updating profile with provided DTO");
        
        if (profileDto == null) {
            return false;
        }
        
        // Update profile fields from DTO
        boolean updated = false;
        
        if (profileDto.getName() != null && !profileDto.getName().equals(this.name)) {
            this.name = profileDto.getName();
            updated = true;
        }
        
        if (profileDto.getEmail() != null && !profileDto.getEmail().equals(this.email)) {
            this.email = profileDto.getEmail();
            updated = true;
        }
        
        if (profileDto.getPhone() != null && !profileDto.getPhone().equals(this.phone)) {
            this.phone = profileDto.getPhone();
            updated = true;
        }
        
        return updated;
    }

    /**
     * Get profile data as DTO.
     * Used for transferring profile information.
     */
    public TouristProfileDTO getProfileData() {
        return new TouristProfileDTO(name, email, phone);
    }
}