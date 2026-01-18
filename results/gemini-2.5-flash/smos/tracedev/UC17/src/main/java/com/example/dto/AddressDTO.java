package com.example.dto;

import com.example.domain.Address;

/**
 * Data Transfer Object for Address information.
 * Used to transfer address data between layers, often representing a simplified view
 * or a specific aggregation of data from the domain entity.
 */
public class AddressDTO {
    public String id;
    public String street;
    public String city;
    public String postalCode;
    public String country;

    /**
     * Constructs an AddressDTO from an Address domain entity.
     * @param address The domain Address object.
     */
    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.country = address.getCountry();
    }

    // Default constructor for potential deserialization or manual creation
    public AddressDTO() {}

    @Override
    public String toString() {
        return "AddressDTO{" +
               "id='" + id + '\'' +
               ", street='" + street + '\'' +
               ", city='" + city + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}