package com.etour.model;

import java.util.List;

/**
 * Contains detailed information about a refreshment point
 */
public class RefreshmentPointDetails {
    private String pointId;
    private String name;
    private String address;
    private String type;
    private String coordinates;
    private int capacity;
    private List<String> amenities;
    private String openingHours;

    public RefreshmentPointDetails(String pointId, String name, String address, String type,
                                   String coordinates, int capacity, List<String> amenities, String openingHours) {
        this.pointId = pointId;
        this.name = name;
        this.address = address;
        this.type = type;
        this.coordinates = coordinates;
        this.capacity = capacity;
        this.amenities = amenities;
        this.openingHours = openingHours;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String toString() {
        return "RefreshmentPointDetails{" +
                "pointId='" + pointId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", capacity=" + capacity +
                ", amenities=" + amenities +
                ", openingHours='" + openingHours + '\'' +
                '}';
    }
}