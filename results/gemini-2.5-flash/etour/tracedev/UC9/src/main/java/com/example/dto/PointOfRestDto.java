package com.example.dto;

/**
 * Data Transfer Object (DTO) for PointOfRest, specific to the Etour API.
 * Used for transferring data between EtourApiAdapter and EtourPointOfRestRepository.
 */
public class PointOfRestDto {
    public String etourId;
    public String etourName;
    public String etourLocation;
    public String etourDescription;
    public String etourType;

    public PointOfRestDto(String etourId, String etourName, String etourLocation, String etourDescription, String etourType) {
        this.etourId = etourId;
        this.etourName = etourName;
        this.etourLocation = etourLocation;
        this.etourDescription = etourDescription;
        this.etourType = etourType;
    }

    @Override
    public String toString() {
        return "PointOfRestDto{" +
                "etourId='" + etourId + '\'' +
                ", etourName='" + etourName + '\'' +
                ", etourLocation='" + etourLocation + '\'' +
                ", etourDescription='" + etourDescription + '\'' +
                ", etourType='" + etourType + '\'' +
                '}';
    }
}