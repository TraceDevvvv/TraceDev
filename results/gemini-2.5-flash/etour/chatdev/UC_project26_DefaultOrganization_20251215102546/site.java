'''
Represents a Site in the system.
Each site has a unique ID and a name.
'''
package com.chatdev.models;
public class Site {
    private int id;
    private String name;
    /**
     * Constructs a new Site object.
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     */
    public Site(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Gets the ID of the site.
     * @return The site's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the site.
     * @param id The new ID for the site.
     */
    public void setId(int id) {
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
     * @param name The new name for the site.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns a string representation of the Site object, primarily its name.
     * This is useful for displaying Site objects in UI components like JList.
     * @return A string representing the site.
     */
    @Override
    public String toString() {
        return "Site ID: " + id + ", Name: " + name;
    }
}