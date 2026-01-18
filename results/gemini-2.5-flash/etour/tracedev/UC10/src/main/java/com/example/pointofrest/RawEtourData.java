package com.example.pointofrest;

/**
 * Represents raw data fetched directly from the external ETOUR service.
 * This data typically needs to be mapped to internal domain entities (e.g., PointOfRest).
 */
public class RawEtourData {
    public String etourId;
    public String etourName;
    public String etourAddress;
    public String etourType;
    public String etourDescription;
    public String etourContact;

    public RawEtourData() {
        // Default constructor
    }

    public RawEtourData(String etourId, String etourName, String etourAddress, String etourType, String etourDescription, String etourContact) {
        this.etourId = etourId;
        this.etourName = etourName;
        this.etourAddress = etourAddress;
        this.etourType = etourType;
        this.etourDescription = etourDescription;
        this.etourContact = etourContact;
    }

    @Override
    public String toString() {
        return "RawEtourData{" +
               "etourId='" + etourId + '\'' +
               ", etourName='" + etourName + '\'' +
               ", etourAddress='" + etourAddress + '\'' +
               ", etourType='" + etourType + '\'' +
               ", etourDescription='" + etourDescription + '\'' +
               ", etourContact='" + etourContact + '\'' +
               '}';
    }
}