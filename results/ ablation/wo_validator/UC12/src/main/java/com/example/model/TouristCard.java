package com.example.model;

import java.util.Date;
import java.util.List;

/**
 * Represents a Tourist Card associated with a Tourist.
 */
public class TouristCard {
    private int touristId;
    private String cardNumber;
    private Date issueDate;
    private Date expirationDate;
    private CardStatus status;
    private List<String> selectedDestinations;

    public TouristCard() {
        // default constructor
    }

    public int getTouristId() {
        return touristId;
    }

    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public List<String> getSelectedDestinations() {
        return selectedDestinations;
    }

    public void setSelectedDestinations(List<String> selectedDestinations) {
        this.selectedDestinations = selectedDestinations;
    }
}