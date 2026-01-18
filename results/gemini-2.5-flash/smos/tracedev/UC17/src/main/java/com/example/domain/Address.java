package com.example.domain;

/**
 * Represents a domain entity for an Address.
 * This class holds the core business data for an address.
 */
public class Address {
    private String id;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    /**
     * Constructs a new Address object.
     * @param id The unique identifier for the address.
     * @param street The street name and number.
     * @param city The city.
     * @param postalCode The postal code.
     * @param country The country.
     */
    public Address(String id, String street, String city, String postalCode, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "Address{" +
               "id='" + id + '\'' +
               ", street='" + street + '\'' +
               ", city='" + city + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}