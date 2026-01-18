package com.example.dto;

/**
 * Data Transfer Object for raw address data retrieved from the SMOS system.
 * Represents the structure of data directly obtained from the external source
 * before being transformed into domain entities.
 */
public class SMOSAddressData {
    private String rawId;
    private String rawStreet;
    private String rawCity;
    private String rawZip; // Corresponds to postalCode

    /**
     * Constructs a new SMOSAddressData object.
     * @param rawId The raw ID from SMOS.
     * @param rawStreet The raw street from SMOS.
     * @param rawCity The raw city from SMOS.
     * @param rawZip The raw zip/postal code from SMOS.
     */
    public SMOSAddressData(String rawId, String rawStreet, String rawCity, String rawZip) {
        this.rawId = rawId;
        this.rawStreet = rawStreet;
        this.rawCity = rawCity;
        this.rawZip = rawZip;
    }

    public String getRawId() {
        return rawId;
    }

    public String getRawStreet() {
        return rawStreet;
    }

    public String getRawCity() {
        return rawCity;
    }

    public String getRawZip() {
        return rawZip;
    }

    @Override
    public String toString() {
        return "SMOSAddressData{" +
               "rawId='" + rawId + '\'' +
               ", rawStreet='" + rawStreet + '\'' +
               ", rawCity='" + rawCity + '\'' +
               ", rawZip='" + rawZip + '\'' +
               '}';
    }
}