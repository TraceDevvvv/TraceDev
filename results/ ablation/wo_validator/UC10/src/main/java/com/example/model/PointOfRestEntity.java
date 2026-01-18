package com.example.model;

import java.util.List;

/**
 * Data entity representing the structure as stored in external system (ETOUR Server).
 */
public class PointOfRestEntity {
    private String _id;
    private String name;
    private Address location;
    private List<String> amenities;

    public PointOfRestEntity() {}

    public PointOfRestEntity(String _id, String name, Address location, List<String> amenities) {
        this._id = _id;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Address getLocation() { return location; }
    public void setLocation(Address location) { this.location = location; }

    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }

    /**
     * Maps this data entity to the domain model PointOfRest.
     * Assumption: Full address is built from location fields.
     */
    public PointOfRest toDomainModel() {
        String fullAddress = location != null ? location.toString() : "";
        return new PointOfRest(_id, name, fullAddress, amenities);
    }

    /**
     * Inner class representing address details from the external system.
     */
    public static class Address {
        private String street;
        private String city;
        private String zip;
        private String country;

        public Address() {}

        public Address(String street, String city, String zip, String country) {
            this.street = street;
            this.city = city;
            this.zip = zip;
            this.country = country;
        }

        @Override
        public String toString() {
            return String.format("%s, %s, %s, %s", street, city, zip, country);
        }
    }
}