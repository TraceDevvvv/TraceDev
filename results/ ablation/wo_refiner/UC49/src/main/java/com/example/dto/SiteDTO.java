package com.example.dto;

import com.example.model.Site;

/**
 * Data Transfer Object for Site.
 * Used to transfer site data to the display layer.
 */
public class SiteDTO {
    public String siteId;
    public String name;
    public String description;

    /**
     * Constructor that creates a DTO from a Site entity.
     * Added per requirement 8.
     *
     * @param s the Site entity
     */
    public SiteDTO(Site s) {
        this.siteId = s.getId();
        this.name = s.getName();
        this.description = s.getDescription();
    }

    @Override
    public String toString() {
        return "SiteDTO{siteId='" + siteId + "', name='" + name + "', description='" + description + "'}";
    }
}