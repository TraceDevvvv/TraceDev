package com.addressmanagement;

/**
 * Represents a physical address with street, city, state, and zip code.
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;

    /**
     * Constructs a new Address object.
     *
     * @param street The street name and number.
     * @param city The city name.
     * @param state The state name.
     * @param zipCode The zip code.
     */
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * Returns the street of the address.
     *
     * @return The street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street of the address.
     *
     * @param street The new street.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the city of the address.
     *
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the address.
     *
     * @param city The new city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the state of the address.
     *
     * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the address.
     *
     * @param state The new state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the zip code of the address.
     *
     * @return The zip code.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the address.
     *
     * @param zipCode The new zip code.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns a string representation of the Address object.
     *
     * @return A formatted string containing the address details.
     */
    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zipCode;
    }
}