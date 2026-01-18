package com.example.domain;

/**
 * Represents a Tourist entity from the domain.
 * Contains tourist ID and card ID (requirement 4: tourist card).
 */
public class Tourist {
    private String touristId;
    private String cardId;

    public Tourist(String touristId, String cardId) {
        this.touristId = touristId;
        this.cardId = cardId;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getCardId() {
        return cardId;
    }
}