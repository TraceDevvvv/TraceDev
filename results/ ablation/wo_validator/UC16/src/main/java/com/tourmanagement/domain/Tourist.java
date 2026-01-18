package com.tourmanagement.domain;

import java.util.Date;

public class Tourist {
    private String touristId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Date createdAt;

    public Tourist(String touristId, String fullName, String email, String phoneNumber, Date createdAt) {
        this.touristId = touristId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

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

    // Maps a Tourist entity to a TouristDto
    public com.tourmanagement.dto.TouristDto toDto() {
        return new com.tourmanagement.dto.TouristDto(
            this.touristId,
            this.fullName,
            this.email,
            this.phoneNumber,
            this.createdAt
        );
    }
}