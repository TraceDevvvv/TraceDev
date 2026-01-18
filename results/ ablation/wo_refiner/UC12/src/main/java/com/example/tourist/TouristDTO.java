package com.example.tourist;

/**
 * Data Transfer Object for Tourist data.
 * Used to transfer data between layers.
 */
public class TouristDTO {
    private String id;
    private String name;
    private String contactInfo;

    public TouristDTO(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // Constructor as per sequence diagram m21: new TouristDTO(Tourist)
    public TouristDTO(Tourist tourist) {
        this.id = tourist.getId();
        this.name = tourist.getName();
        this.contactInfo = tourist.getContactInfo();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return "TouristDTO{id='" + id + "', name='" + name + "', contactInfo='" + contactInfo + "'}";
    }
}