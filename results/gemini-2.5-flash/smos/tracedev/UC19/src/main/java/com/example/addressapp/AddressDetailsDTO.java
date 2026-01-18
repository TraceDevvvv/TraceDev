package com.example.addressapp;

/**
 * Data Transfer Object for displaying Address details.
 */
public class AddressDetailsDTO {
    private String id;
    private String name;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public AddressDetailsDTO(String id, String name, String street, String city, String postalCode, String country) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    /**
     * Creates an AddressDetailsDTO from an Address entity.
     * @param address The Address entity to convert.
     * @return A new AddressDetailsDTO instance.
     */
    public static AddressDetailsDTO fromAddress(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressDetailsDTO(
            address.getId(),
            address.getName(),
            address.getStreet(),
            address.getCity(),
            address.getPostalCode(),
            address.getCountry()
        );
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

    @Override
    public String toString() {
        return "AddressDetailsDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", street='" + street + '\'' +
               ", city='" + city + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}