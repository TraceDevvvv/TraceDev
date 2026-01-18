// Site.java
package com.etour.insertpreferencesite;

/**
 * Represents a tourist site with a name and description.
 */
public class Site {
    private String name;
    private String description;

    /**
     * Constructs a new Site object.
     *
     * @param name The name of the site.
     * @param description The description of the site.
     */
    public Site(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of the site.
     *
     * @return The name of the site.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the site.
     *
     * @param name The new name of the site.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the site.
     *
     * @return The description of the site.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the site.
     *
     * @param description The new description of the site.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the Site object.
     *
     * @return A string containing the site's name and description.
     */
    @Override
    public String toString() {
        return "Site [Name: " + name + ", Description: " + description + "]";
    }

    /**
     * Compares this Site object to the specified object. The result is true if and only if the argument is not null
     * and is a Site object that represents the same sequence of characters as this object.
     *
     * @param obj The object to compare this Site against.
     * @return true if the given object represents a Site equivalent to this site, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Site site = (Site) obj;
        return name.equals(site.name) && description.equals(site.description);
    }

    /**
     * Returns a hash code for this Site object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}