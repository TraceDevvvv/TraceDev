package com.example;

/**
 * Represents a banner at a refreshment point.
 */
public class Banner {
    private String agencyName;
    private String bannerId;

    public Banner(String agencyName, String bannerId) {
        this.agencyName = agencyName;
        this.bannerId = bannerId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getBannerId() {
        return bannerId;
    }
    
    @Override
    public String toString() {
        return "Banner[agency=" + agencyName + ", id=" + bannerId + "]";
    }
}