package com.tourmanagement.dto;

import java.util.Date;

public class TouristDto {
    public String touristId;
    public String fullName;
    public String email;
    public String phoneNumber;
    public Date createdAt;

    // Default constructor
    public TouristDto() {
    }

    // Constructor with parameters
    public TouristDto(String touristId, String fullName, String email, String phoneNumber, Date createdAt) {
        this.touristId = touristId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}