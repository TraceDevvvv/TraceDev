package com.example;

/**
 * Data Transfer Object (DTO) for displaying Site information.
 * This class is used to transfer a simplified view of Site data to the UI.
 */
public class SiteDTO {
    public String id;
    public String name;

    /**
     * Constructs a new SiteDTO.
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     */
    public SiteDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}