package com.example.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Object for site details.
 */
public class SiteDetailsDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;
    private double rating;
    private String openingHours;
    private BigDecimal admissionFee;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getHistoricalPeriod() { return historicalPeriod; }
    public void setHistoricalPeriod(String historicalPeriod) { this.historicalPeriod = historicalPeriod; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public BigDecimal getAdmissionFee() { return admissionFee; }
    public void setAdmissionFee(BigDecimal admissionFee) { this.admissionFee = admissionFee; }
}