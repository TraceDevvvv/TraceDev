package com.example.dto;

import java.io.Serializable;

/**
 * DTO (Data Transfer Object) for Tourist data.
 * Maps to TouristEntity.
 */
public class TouristDTO implements Serializable {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    // ... other fields as needed

    public TouristDTO() {
    }

    public TouristDTO(String userId, String name, String email, String phone, String address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}