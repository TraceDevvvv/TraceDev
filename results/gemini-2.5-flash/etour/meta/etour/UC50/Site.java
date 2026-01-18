// Site.java
package com.etour.visitedsites;

/**
 * Represents a tourist site.
 * This class stores basic information about a site, such as its ID and name.
 */
public class Site {
    private String siteId;
    private String name;

    /**
     * Constructs a new Site object.
     *
     * @param siteId The unique identifier for the site.
     * @param name The name of the site.
     */
    public Site(String siteId, String name) {
        // Validate inputs to ensure they are not null or empty
        if (siteId == null || siteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Site ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Site name cannot be null or empty.");
        }
        this.siteId = siteId;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the site.
     *
     * @return The site ID.
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Returns the name of the site.
     *
     * @return The site name.
     */
    public String getName() {
        return name;
    }

    /**
     * Provides a string representation of the Site object.
     *
     * @return A string containing the site ID and name.
     */
    @Override
    public String toString() {
        return "Site ID: " + siteId + ", Name: " + name;
    }

    /**
     * Compares this Site object to the specified object. The result is true if and only if
     * the argument is not null and is a Site object that represents the same site ID as this object.
     *
     * @param obj The object to compare this Site against.
     * @return true if the given object represents a Site equivalent to this site, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Site site = (Site) obj;
        return siteId.equals(site.siteId);
    }

    /**
     * Returns a hash code for this Site object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return siteId.hashCode();
    }
}