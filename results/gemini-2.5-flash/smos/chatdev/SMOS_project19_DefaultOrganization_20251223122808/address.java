'''
Contains the Address data model.
'''
package com.chatdev.model;
/**
 * Represents an Address entity with various details.
 * This class serves as a data model (POJO - Plain Old Java Object).
 * Fields are declared final to ensure immutability after construction,
 * enhancing thread safety and predictability.
 */
public class Address {
    private final String id; // Make fields final for immutability
    private final String name; // e.g., Home, Office, Warehouse
    private final String street;
    private final String city;
    private final String zipCode;
    private final String country;
    /**
     * Constructs a new Address object.
     *
     * @param id The unique identifier for the address.
     * @param name A user-friendly name for the address (e.g., "Main Office").
     * @param street The street address.
     * @param city The city of the address.
     * @param zipCode The postal or zip code.
     * @param country The country of the address.
     */
    public Address(String id, String name, String street, String city, String zipCode, String country) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }
    /**
     * Returns the unique ID of the address.
     * @return The address ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the address.
     * @return The address name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the street of the address.
     * @return The street address.
     */
    public String getStreet() {
        return street;
    }
    /**
     * Returns the city of the address.
     * @return The city.
     */
    public String getCity() {
        return city;
    }
    /**
     * Returns the zip code of the address.
     * @return The zip code.
     */
    public String getZipCode() {
        return zipCode;
    }
    /**
     * Returns the country of the address.
     * @return The country.
     */
    public String getCountry() {
        return country;
    }
    /**
     * Overrides the toString method to provide a meaningful string representation,
     * which is often used for displaying in UI components like JList.
     * @return The name of the address.
     */
    @Override
    public String toString() {
        return name; // Display address name in JList
    }
}