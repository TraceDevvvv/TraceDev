package com.example.domain;

/**
 * Domain entity representing a Tourist Account.
 */
public class TouristAccount {
    private String accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String agencyReference;

    public TouristAccount(String accountId, String firstName, String lastName, String email, String agencyReference) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.agencyReference = agencyReference;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAgencyReference() {
        return agencyReference;
    }
}