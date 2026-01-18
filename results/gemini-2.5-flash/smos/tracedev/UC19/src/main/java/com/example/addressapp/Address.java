package com.example.addressapp;

import java.time.LocalDateTime;

/**
 * Represents an Address entity in the system.
 */
public class Address {
    private String id;
    private String name;
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private LocalDateTime lastUpdated; // Added to satisfy requirement QR1

    public Address(String id, String name, String street, String city, String postalCode, String country, LocalDateTime lastUpdated) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.lastUpdated = lastUpdated;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    // Setters (if mutable, typically not for DTOs but for entities in some contexts)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Address{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", street='" + street + '\'' +
               ", city='" + city + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", country='" + country + '\'' +
               ", lastUpdated=" + lastUpdated +
               '}';
    }
}