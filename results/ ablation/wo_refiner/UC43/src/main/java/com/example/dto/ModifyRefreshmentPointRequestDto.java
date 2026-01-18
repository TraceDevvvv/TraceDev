package com.example.dto;

/**
 * Data Transfer Object for modification requests of a refreshment point.
 */
public class ModifyRefreshmentPointRequestDto {
    private String id;
    private String name;
    private String location;
    private String operatingHours;
    private String contactInfo;

    public ModifyRefreshmentPointRequestDto() {}

    public ModifyRefreshmentPointRequestDto(String id, String name, String location, String operatingHours, String contactInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.operatingHours = operatingHours;
        this.contactInfo = contactInfo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}