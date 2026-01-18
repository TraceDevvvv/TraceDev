package com.example.dto;

import java.util.List;

/**
 * Response DTO containing list of visited sites with feedback.
 */
public class VisitedSitesResponseDTO {
    public List<SiteDTO> sites;

    public VisitedSitesResponseDTO() {
    }

    public VisitedSitesResponseDTO(List<SiteDTO> sites) {
        this.sites = sites;
    }

    public List<SiteDTO> getSites() {
        return sites;
    }

    public void setSites(List<SiteDTO> sites) {
        this.sites = sites;
    }
}