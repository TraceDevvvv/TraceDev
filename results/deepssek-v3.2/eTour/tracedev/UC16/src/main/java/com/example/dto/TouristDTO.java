package com.example.dto;

import com.example.domain.Tourist;

/**
 * Data Transfer Object for Tourist.
 */
public class TouristDTO {
    private String id;
    private String name;
    private String email;

    // Constructor that converts a Tourist entity to TouristDTO
    public TouristDTO(Tourist tourist) {
        this.id = tourist.getId();
        this.name = tourist.getName();
        this.email = tourist.getEmail();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}