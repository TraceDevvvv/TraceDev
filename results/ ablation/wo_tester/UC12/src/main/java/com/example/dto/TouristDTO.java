package com.example.dto;

import com.example.model.Tourist;
import java.util.Date;

/**
 * Data Transfer Object for Tourist.
 */
public class TouristDTO {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String nationality;
    private Date dateOfBirth;

    /**
     * Constructs a TouristDTO from a Tourist entity.
     * This constructor satisfies sequence diagram message m11: "new TouristDTO(tourist)"
     */
    public TouristDTO(Tourist tourist) {
        if (tourist != null) {
            this.id = tourist.getId();
            this.name = tourist.getName();
            this.email = tourist.getEmail();
            this.phoneNumber = tourist.getPhoneNumber();
            this.nationality = tourist.getNationality();
            this.dateOfBirth = tourist.getDateOfBirth();
        }
    }

    /**
     * This method satisfies sequence diagram return message m12: "TouristDTO instance"
     * It returns the current instance, which represents the created TouristDTO.
     */
    public TouristDTO returnTouristDTOInstance() {
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}