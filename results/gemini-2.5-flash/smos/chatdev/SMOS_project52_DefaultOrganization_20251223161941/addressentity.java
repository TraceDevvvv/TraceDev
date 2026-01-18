/**
 * Represents an 'address' entity in the system.
 */
package com.chatdev.entities;
public class AddressEntity {
    private String addressId;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    // Constructor
    public AddressEntity(String addressId, String street, String city, String zipCode, String country) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }
    // Getters
    public String getAddressId() {
        return addressId;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getCountry() {
        return country;
    }
    // Override toString() for display in UI components
    @Override
    public String toString() {
        return "Address ID: " + addressId + ", Street: " + street + ", City: " + city + ", Zip: " + zipCode + ", Country: " + country;
    }
}