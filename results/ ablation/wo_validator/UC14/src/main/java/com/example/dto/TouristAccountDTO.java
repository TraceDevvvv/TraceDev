package com.example.dto;

import com.example.entity.TouristAccount;

/**
 * Data Transfer Object for tourist account.
 * Used to transfer tourist account data between layers.
 */
public class TouristAccountDTO {
    private String id;
    private String name;
    private String email;
    private String agencyId;

    public TouristAccountDTO() {
    }

    /**
     * Constructor that maps a domain entity to DTO.
     * This mapping logic is implied in the class diagram.
     */
    public TouristAccountDTO(TouristAccount touristAccount) {
        this.id = touristAccount.getId();
        this.name = touristAccount.getName();
        this.email = touristAccount.getEmail();
        this.agencyId = touristAccount.getAgencyId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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