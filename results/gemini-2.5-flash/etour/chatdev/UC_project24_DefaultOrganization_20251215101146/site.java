/*
 * Represents a site with an ID and a name.
 */
package com.chatdev.model;
public class Site {
    private String id;
    private String name;
    /**
     * Constructs a new Site object.
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     */
    public Site(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the ID of the site.
     * @return The site ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the site.
     * @param id The new site ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the name of the site.
     * @return The site name.
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
     * Provides a string representation of the Site object, useful for display in lists.
     * @return The name of the site.
     */
    @Override
    public String toString() {
        return name;
    }
}