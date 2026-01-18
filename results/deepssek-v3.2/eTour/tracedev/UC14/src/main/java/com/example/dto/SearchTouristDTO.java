package com.example.dto;

/**
 * Data Transfer Object for search criteria.
 */
public class SearchTouristDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String agencyReference;

    public SearchTouristDTO() {
    }

    public SearchTouristDTO(String firstName, String lastName, String email, String agencyReference) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.agencyReference = agencyReference;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencyReference() {
        return agencyReference;
    }

    public void setAgencyReference(String agencyReference) {
        this.agencyReference = agencyReference;
    }

    // Helper method to check if all criteria are empty (for potential query building)
    public boolean isEmpty() {
        return (firstName == null || firstName.isEmpty()) &&
               (lastName == null || lastName.isEmpty()) &&
               (email == null || email.isEmpty()) &&
               (agencyReference == null || agencyReference.isEmpty());
    }
}