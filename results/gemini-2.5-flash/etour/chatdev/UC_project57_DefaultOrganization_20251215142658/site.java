package model;
/*
 * Represents a tourist site that can be found through the advanced search.
 * This is a simple POJO (Plain Old Java Object) for data representation.
 */
public class Site {
    private final String name;
    private final String description;
    private final String location;
    /**
     * Constructs a new Site object.
     *
     * @param name The name of the tourist site.
     * @param description A brief description of the site.
     * @param location The geographical location of the site.
     */
    public Site(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }
    /**
     * @return The name of the site.
     */
    public String getName() {
        return name;
    }
    /**
     * @return The description of the site.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return The location of the site.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Provides a string representation of the Site object, useful for displaying results.
     *
     * @return A formatted string with site name, description, and location.
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: " + description + "\nLocation: " + location + "\n";
    }
}