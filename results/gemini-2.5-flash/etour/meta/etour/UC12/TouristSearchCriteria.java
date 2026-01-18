package com.etour.searchtourist;

/**
 * Defines the criteria for searching tourist accounts.
 * This class allows specifying various fields to filter tourist results.
 */
public class TouristSearchCriteria {
    private String touristId;
    private String firstName;
    private String lastName;
    private String email;
    private String country;

    /**
     * Default constructor for TouristSearchCriteria.
     */
    public TouristSearchCriteria() {
    }

    /**
     * Constructs a new TouristSearchCriteria with specified search parameters.
     * Any parameter can be null or empty to indicate it's not part of the search criteria.
     *
     * @param touristId The tourist ID to search for (can be partial or exact).
     * @param firstName The first name to search for (can be partial).
     * @param lastName The last name to search for (can be partial).
     * @param email The email address to search for (can be partial).
     * @param country The country to search for (can be partial).
     */
    public TouristSearchCriteria(String touristId, String firstName, String lastName, String email, String country) {
        this.touristId = touristId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
    }

    /**
     * Returns the tourist ID search criterion.
     *
     * @return The tourist ID criterion.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Sets the tourist ID search criterion.
     *
     * @param touristId The tourist ID criterion to set.
     */
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    /**
     * Returns the first name search criterion.
     *
     * @return The first name criterion.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name search criterion.
     *
     * @param firstName The first name criterion to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name search criterion.
     *
     * @return The last name criterion.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name search criterion.
     *
     * @param lastName The last name criterion to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email search criterion.
     *
     * @return The email criterion.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email search criterion.
     *
     * @param email The email criterion to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the country search criterion.
     *
     * @return The country criterion.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country search criterion.
     *
     * @param country The country criterion to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Checks if the criteria object is empty (all fields are null or empty).
     *
     * @return true if all criteria fields are null or empty, false otherwise.
     */
    public boolean isEmpty() {
        return (touristId == null || touristId.trim().isEmpty()) &&
               (firstName == null || firstName.trim().isEmpty()) &&
               (lastName == null || lastName.trim().isEmpty()) &&
               (email == null || email.trim().isEmpty()) &&
               (country == null || country.trim().isEmpty());
    }

    /**
     * Overrides the toString method to provide a string representation of the TouristSearchCriteria object.
     *
     * @return A string representation of the search criteria.
     */
    @Override
    public String toString() {
        return "TouristSearchCriteria{" +
               "touristId='" + touristId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", country='" + country + '\'' +
               '}';
    }
}