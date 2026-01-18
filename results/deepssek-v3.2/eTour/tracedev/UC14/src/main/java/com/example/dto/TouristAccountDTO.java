package com.example.dto;

/**
 * Data Transfer Object for TouristAccount.
 */
public class TouristAccountDTO {
    private String accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String agencyReference;

    public TouristAccountDTO() {
    }

    public TouristAccountDTO(String accountId, String firstName, String lastName, String email, String agencyReference) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.agencyReference = agencyReference;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    @Override
    public String toString() {
        return "TouristAccountDTO [accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName +
                ", email=" + email + ", agencyReference=" + agencyReference + "]";
    }
}