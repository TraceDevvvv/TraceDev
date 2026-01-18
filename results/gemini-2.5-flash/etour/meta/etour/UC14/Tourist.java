package com.etour.touristsearch;

/**
 * Represents a Tourist account in the ETOUR system.
 * This class holds basic information about a tourist.
 */
public class Tourist {
    private String touristId;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String phoneNumber;

    /**
     * Constructs a new Tourist object with the specified details.
     *
     * @param touristId The unique identifier for the tourist.
     * @param firstName The first name of the tourist.
     * @param lastName The last name of the tourist.
     * @param email The email address of the tourist.
     * @param country The country of residence of the tourist.
     * @param phoneNumber The phone number of the tourist.
     */
    public Tourist(String touristId, String firstName, String lastName, String email, String country, String phoneNumber) {
        this.touristId = touristId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the unique identifier of the tourist.
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Sets the unique identifier of the tourist.
     * @param touristId The new tourist ID.
     */
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    /**
     * Gets the first name of the tourist.
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the tourist.
     * @param firstName The new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the tourist.
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the tourist.
     * @param lastName The new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the tourist.
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the tourist.
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the country of residence of the tourist.
     * @return The country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of residence of the tourist.
     * @param country The new country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the phone number of the tourist.
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the tourist.
     * @param phoneNumber The new phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the Tourist object.
     * @return A string containing the tourist's details.
     */
    @Override
    public String toString() {
        return "Tourist{" +
               "touristId='" + touristId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", country='" + country + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               '}';
    }
}