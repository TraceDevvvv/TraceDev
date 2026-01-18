package com.example.entity;

import com.example.dto.TouristDTO;

/**
 * Entity class representing a Tourist.
 */
public class Tourist {
    private String touristId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Tourist() {
    }

    public Tourist(String touristId, String name, String email, String phone, String address) {
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
     * Updates this entity from a TouristDTO.
     * @param dto the TouristDTO containing new data
     */
    public void updateFromDTO(TouristDTO dto) {
        if (dto == null) {
            return;
        }
        this.setTouristId(dto.getTouristId());
        this.setName(dto.getName());
        this.setEmail(dto.getEmail());
        this.setPhone(dto.getPhone());
        this.setAddress(dto.getAddress());
    }
}