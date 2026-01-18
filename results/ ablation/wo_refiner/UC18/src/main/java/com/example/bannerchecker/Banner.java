package com.example.bannerchecker;

/**
 * Represents a Banner as defined in the Class Diagram.
 * Includes an ID and a reference to the refreshment point it belongs to.
 */
public class Banner {
    public String bannerId;
    public String refreshmentPointId;

    public Banner(String bannerId, String refreshmentPointId) {
        this.bannerId = bannerId;
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    /**
     * Determines if the banner is valid.
     * For simplicity, a banner is considered valid if it has non-null and non-empty IDs.
     * This logic can be extended based on business requirements.
     * @return true if the banner is valid, false otherwise.
     */
    public boolean isValid() {
        return bannerId != null && !bannerId.trim().isEmpty()
                && refreshmentPointId != null && !refreshmentPointId.trim().isEmpty();
    }
}