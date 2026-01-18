package com.example.entity;

/**
 * Domain entity representing a tourist account.
 * Contains core attributes of a tourist account.
 */
public class TouristAccount {
    private String id;
    private String name;
    private String email;
    private String agencyId;

    public TouristAccount(String id, String name, String email, String agencyId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.agencyId = agencyId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAgencyId() {
        return agencyId;
    }
}