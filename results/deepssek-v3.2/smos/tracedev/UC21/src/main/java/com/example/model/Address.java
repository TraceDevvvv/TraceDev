package com.example.model;

import java.util.Objects;

/**
 * Represents a physical address.
 * Based on the UML class diagram.
 */
public class Address {
    private String addressId;
    private String street;
    private String city;
    private String postalCode;

    public Address(String addressId, String street, String city, String postalCode) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getDetails() {
        return street + ", " + city + " " + postalCode;
    }

    // Additional getters and setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public void setAddressId(String addressId) { this.addressId = addressId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}