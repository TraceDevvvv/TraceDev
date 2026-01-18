package com.example.dto;

import java.util.List;

/**
 * Data Transfer Object for Tourist Card.
 */
public class TouristCardDTO {
    public int touristId;
    public String touristName;
    public String cardNumber;
    public String issueDate;
    public String expirationDate;
    public String status;
    public List<String> selectedDestinations;

    public TouristCardDTO() {
        // default constructor
    }

    // Getters and setters
    public int getTouristId() {
        return touristId;
    }

    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSelectedDestinations() {
        return selectedDestinations;
    }

    public void setSelectedDestinations(List<String> selectedDestinations) {
        this.selectedDestinations = selectedDestinations;
    }
}