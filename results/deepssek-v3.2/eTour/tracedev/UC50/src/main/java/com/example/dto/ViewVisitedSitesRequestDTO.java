package com.example.dto;

/**
 * Request DTO for viewing visited sites.
 */
public class ViewVisitedSitesRequestDTO {
    public String touristId;

    public ViewVisitedSitesRequestDTO() {
    }

    public ViewVisitedSitesRequestDTO(String touristId) {
        this.touristId = touristId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
}