'''
Represents a tourist site with properties like ID, name, and location.
'''
package com.chatdev.tourism;
import java.util.Objects;
public class Site {
    private String id;
    private String name;
    private String location;
    /**
     * Constructs a new Site object.
     *
     * @param id The unique identifier for the site.
     * @param name The name of the site.
     * @param location The geographical location of the site.
     */
    public Site(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
    /**
     * Returns the unique ID of the site.
     * @return The site's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the site.
     * @return The site's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the location of the site.
     * @return The site's location.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Compares this Site object to the specified object.
     * The result is true if and only if the argument is not null
     * and is a Site object that has the same ID as this object.
     * @param o The object to compare with.
     * @return true if the IDs are equal; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(id, site.id);
    }
    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * Returns a string representation of the Site object.
     * @return A string containing the site's name and location.
     */
    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}