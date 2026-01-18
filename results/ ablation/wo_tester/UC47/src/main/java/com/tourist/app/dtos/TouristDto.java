package com.tourist.app.dtos;

/**
 * Data Transfer Object for Tourist.
 */
public class TouristDto {
    private String touristId;
    private String name;
    private String email;
    private String phoneNumber;

    /**
     * Default constructor.
     */
    public TouristDto() {
    }

    /**
     * Parameterized constructor.
     * @param id the tourist id
     * @param name the tourist name
     * @param email the tourist email
     * @param phone the tourist phone number
     */
    public TouristDto(String id, String name, String email, String phone) {
        this.touristId = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phone;
    }

    // Getters and setters

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}