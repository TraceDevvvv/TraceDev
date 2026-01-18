package com.etour.searchtourist;

import java.util.Objects;

/**
 * Represents a tourist account in the ETOUR system.
 * This class holds basic information about a tourist.
 */
public class Tourist {
    private String touristId;
    private String firstName;
    private String lastName;
    private String email;
    private String country;

    /**
     * Default constructor for Tourist.
     */
    public Tourist() {
    }

    /**
     * Constructs a new Tourist with specified details.
     *
     * @param touristId The unique identifier for the tourist.
     * @param firstName The first name of the tourist.
     * @param lastName The last name of the tourist.
     * @param email The email address of the tourist.
     * @param country The country of residence of the tourist.
     */
    public Tourist(String touristId, String firstName, String lastName, String email, String country) {
        this.touristId = touristId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
    }

    /**
     * Returns the unique identifier of the tourist.
     *
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Sets the unique identifier of the tourist.
     *
     * @param touristId The tourist ID to set.
     */
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    /**
     * Returns the first name of the tourist.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the tourist.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the tourist.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the tourist.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address of the tourist.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the tourist.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the country of residence of the tourist.
     *
     * @return The country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of residence of the tourist.
     *
     * @param country The country to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Overrides the toString method to provide a string representation of the Tourist object.
     *
     * @return A string representation of the Tourist.
     */
    @Override
    public String toString() {
        return "Tourist{" +
               "touristId='" + touristId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", country='" + country + '\'' +
               '}';
    }

    /**
     * Overrides the equals method to compare Tourist objects based on their touristId.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(touristId, tourist.touristId);
    }

    /**
     * Overrides the hashCode method to generate a hash code based on the touristId.
     *
     * @return The hash code of the Tourist object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(touristId);
    }
}