package com.example.dto;

import java.io.Serializable;

/**
 * Data Transfer Object representing tourist profile information.
 * Used to transfer profile data between layers.
 */
public class TouristProfileDTO implements Serializable {
    private String name;
    private String email;
    private String phone;

    // Default constructor
    public TouristProfileDTO() {}

    // Parameterized constructor
    public TouristProfileDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
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

    @Override
    public String toString() {
        return "TouristProfileDTO{name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TouristProfileDTO that = (TouristProfileDTO) obj;
        return java.util.Objects.equals(name, that.name) &&
               java.util.Objects.equals(email, that.email) &&
               java.util.Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, email, phone);
    }
}