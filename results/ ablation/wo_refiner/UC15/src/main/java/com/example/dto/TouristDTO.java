package com.example.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Tourist data.
 * Added applyModifications method to satisfy requirement REQ-011.
 */
public class TouristDTO {
    private String touristId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public TouristDTO() {
    }

    public TouristDTO(String touristId, String name, String email, String phone, String address) {
        this.touristId = touristId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String id) {
        this.touristId = id;
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

    /**
     * Applies modifications from another TouristDTO to this one.
     * Added to satisfy requirement REQ-011.
     * @param modifiedData the TouristDTO containing modified data
     */
    public void applyModifications(TouristDTO modifiedData) {
        if (modifiedData.getName() != null && !modifiedData.getName().trim().isEmpty()) {
            this.setName(modifiedData.getName());
        }
        if (modifiedData.getEmail() != null && !modifiedData.getEmail().trim().isEmpty()) {
            this.setEmail(modifiedData.getEmail());
        }
        if (modifiedData.getPhone() != null && !modifiedData.getPhone().trim().isEmpty()) {
            this.setPhone(modifiedData.getPhone());
        }
        if (modifiedData.getAddress() != null && !modifiedData.getAddress().trim().isEmpty()) {
            this.setAddress(modifiedData.getAddress());
        }
    }

    @Override
    public String toString() {
        return "TouristDTO{" +
                "touristId='" + touristId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouristDTO that = (TouristDTO) o;
        return Objects.equals(touristId, that.touristId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(touristId);
    }
}