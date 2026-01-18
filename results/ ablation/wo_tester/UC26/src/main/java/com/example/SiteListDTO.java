package com.example;

import java.util.List;

/**
 * Data Transfer Object containing list of sites.
 */
public class SiteListDTO {
    private List<SiteDTO> sites;

    public SiteListDTO(List<SiteDTO> sites) {
        this.sites = sites;
    }

    public List<SiteDTO> getSites() {
        return sites;
    }

    public void setSites(List<SiteDTO> sites) {
        this.sites = sites;
    }
}