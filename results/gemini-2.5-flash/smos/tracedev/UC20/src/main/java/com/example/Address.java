package com.example;

/**
 * Represents an Address entity in the domain layer.
 */
public class Address {
    private String id;
    private String street;
    private String city;
    private String zipCode;

    /**
     * Constructs a new Address object.
     *
     * @param id The unique identifier of the address.
     * @param street The street name and number.
     * @param city The city of the address.
     * @param zipCode The zip code of the address.
     */
    public Address(String id, String street, String city, String zipCode) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    // Getters for all attributes
    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "Address{" +
               "id='" + id + '\'' +
               ", street='" + street + '\'' +
               ", city='" + city + '\'' +
               ", zipCode='" + zipCode + '\'' +
               '}';
    }
}