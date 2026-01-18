package com.example.pointofrest;

/**
 * Data Transfer Object (DTO) for displaying PointOfRest details.
 * This class is used to transfer data from the application service to the UI layer,
 * potentially hiding internal entity details or formatting data for presentation.
 */
public class PointOfRestDetailsDto {
    public String id;
    public String name;
    public String address;
    public String type;
    public String description;
    public String contactInfo;

    public PointOfRestDetailsDto() {
        // Default constructor
    }

    public PointOfRestDetailsDto(String id, String name, String address, String type, String description, String contactInfo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.description = description;
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "PointOfRestDetailsDto{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", type='" + type + '\'' +
               ", description='" + description + '\'' +
               ", contactInfo='" + contactInfo + '\'' +
               '}';
    }
}