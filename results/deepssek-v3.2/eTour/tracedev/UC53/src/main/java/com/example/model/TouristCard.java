package com.example.model;

/**
 * Represents a Tourist Card owned by a tourist.
 */
public class TouristCard {
    private String cardId;
    private String touristId;
    private String currentSiteId;

    public TouristCard(String cardId, String touristId) {
        this.cardId = cardId;
        this.touristId = touristId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getCurrentSiteId() {
        return currentSiteId;
    }

    public void setCurrentSiteId(String currentSiteId) {
        this.currentSiteId = currentSiteId;
    }

    /**
     * Checks if the card is at a given site (Entry Condition #2).
     */
    public boolean isAtSite(String siteId) {
        return siteId != null && siteId.equals(currentSiteId);
    }
}