/**
 * Represents a tourist site with its details.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private String location;
    private double rating;
    /**
     * Constructs a new Site object.
     *
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     * @param description A brief description of the site.
     * @param location The geographical location of the site.
     * @param rating The average rating of the site (e.g., out of 5 stars).
     */
    public Site(String id, String name, String description, String location, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.rating = rating;
    }
    /**
     * Gets the ID of the site.
     * @return The site ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the name of the site.
     * @return The site name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the description of the site.
     * @return The site description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the location of the site.
     * @return The site location.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Gets the rating of the site.
     * @return The site rating.
     */
    public double getRating() {
        return rating;
    }
    /**
     * Provides a string representation of the Site object.
     * @return A string containing all site details.
     */
    @Override
    public String toString() {
        return "ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Location: " + location + "\n" +
               "Rating: " + rating + "/5.0\n" +
               "Description: " + description;
    }
}