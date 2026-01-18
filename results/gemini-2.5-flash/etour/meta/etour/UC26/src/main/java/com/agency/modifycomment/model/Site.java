package com.agency.modifycomment.model;

import java.util.Objects;

/**
 * Represents a Site in the system.
 * Each site has a unique ID and a name.
 */
public class Site {
    private String id;
    private String name;

    /**
     * Constructs a new Site with the specified ID and name.
     *
     * @param id   The unique identifier of the site.
     * @param name The name of the site.
     */
    public Site(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the site.
     *
     * @return The site ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the site.
     *
     * @param id The new site ID.
     */
    public void setId(String id) {
        this.id = id;
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
     * Sets the name of the site.
     *
     * @param name The new site name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Site objects are considered equal if their IDs are the same.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
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
     * The hash code is based on the site's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the Site object.
     *
     * @return A string containing the site's ID and name.
     */
    @Override
    public String toString() {
        return "Site{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}