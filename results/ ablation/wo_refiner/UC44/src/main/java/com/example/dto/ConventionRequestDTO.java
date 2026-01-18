package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for convention request data.
 */
public class ConventionRequestDTO {
    private int pointOfRestId;
    private int agencyId;
    private Date startDate;
    private Date endDate;
    private String terms;

    // Getters
    public int getPointOfRestId() {
        return pointOfRestId;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getTerms() {
        return terms;
    }

    // Setters (assumed for UI to populate)
    public void setPointOfRestId(int pointOfRestId) {
        this.pointOfRestId = pointOfRestId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}