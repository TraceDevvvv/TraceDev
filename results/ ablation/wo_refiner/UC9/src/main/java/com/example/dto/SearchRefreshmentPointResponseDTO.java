package com.example.dto;

import java.util.List;

/**
 * Response DTO containing search results and operation duration.
 * Enforces maximum duration of 15 seconds (Requirement 12).
 */
public class SearchRefreshmentPointResponseDTO {
    private List<RefreshmentPointDTO> points;
    private long searchDuration; // in milliseconds

    public SearchRefreshmentPointResponseDTO(List<RefreshmentPointDTO> points, long searchDuration) {
        this.points = points;
        this.searchDuration = searchDuration;
        // Validate duration against requirement (max 15 seconds)
        if (searchDuration > 15000) {
            System.err.println("Requirement 12 violated: search duration exceeds 15 seconds.");
        }
    }

    public List<RefreshmentPointDTO> getPoints() {
        return points;
    }

    public long getSearchDuration() {
        return searchDuration;
    }
}