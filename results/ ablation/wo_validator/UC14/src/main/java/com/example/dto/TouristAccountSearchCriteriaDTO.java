package com.example.dto;

/**
 * Data Transfer Object for tourist account search criteria.
 * Contains fields to filter tourist accounts by name, email, and agency ID.
 */
public class TouristAccountSearchCriteriaDTO {
    private String name;
    private String email;
    private String agencyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}