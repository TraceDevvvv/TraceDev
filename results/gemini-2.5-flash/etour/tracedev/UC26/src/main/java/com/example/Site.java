package com.example;

/**
 * Represents a Site entity.
 * This is a domain model class.
 */
public class Site {
    private String id;
    private String name;
    private String description;

    /**
     * Constructs a new Site instance.
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     * @param description A brief description of the site.
     */
    public Site(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the site.
     * @return The site's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the site.
     * @param id The new site ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the site.
     * @return The site's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the site.
     * @param name The new site name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the site.
     * @return The site's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the site.
     * @param description The new site description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}