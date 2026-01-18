/**
 * Represents a site for which a tourist has issued feedback.
 */
package com.chatdev.etour;
/**
 * Represents a site for which a tourist has issued feedback.
 * This class stores basic information about a site, such as its name and location.
 */
public class Site {
    private String name;
    private String location; // Optional: Added for richer data representation
    /**
     * Constructs a new Site object.
     * @param name The name of the site.
     * @param location The geographical location of the site.
     */
    public Site(String name, String location) {
        this.name = name;
        this.location = location;
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
     * @param name The new name for the site.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the location of the site.
     * @return The site's location.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Sets the location of the site.
     * @param location The new location for the site.
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Provides a string representation of the Site object, useful for display in UI components.
     * @return A string combining the site's name and location.
     */
    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}