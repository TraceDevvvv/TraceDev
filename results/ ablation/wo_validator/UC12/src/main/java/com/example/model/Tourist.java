package com.example.model;

import java.util.Date;
import java.util.List;

/**
 * Represents a Tourist in the system.
 */
public class Tourist {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
    private TouristCard touristCard; // composition relationship

    public Tourist() {
        // default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public TouristCard getTouristCard() {
        return touristCard;
    }

    public void setTouristCard(TouristCard touristCard) {
        this.touristCard = touristCard;
    }

    /**
     * Computes the full name of the tourist.
     * @return concatenated first name and last name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}