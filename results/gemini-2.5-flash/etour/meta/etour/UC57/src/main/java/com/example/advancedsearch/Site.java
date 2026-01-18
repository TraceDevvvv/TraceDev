package com.example.advancedsearch;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a tourist site with its details, including location, rating, and amenities.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private Location location;
    private String category;
    private double averageRating; // Rating out of 5
    private List<String> amenities;
    private boolean hasAccessibility;

    /**
     * Constructs a new Site object.
     *
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     * @param description A brief description of the site.
     * @param location The geographical location of the site.
     * @param category The category of the site (e.g., "Museum", "Park", "Restaurant").
     * @param averageRating The average rating of the site (0.0 to 5.0).
     * @param amenities A list of amenities available at the site.
     * @param hasAccessibility True if the site has accessibility features, false otherwise.
     */
    public Site(String id, String name, String description, Location location, String category,
                double averageRating, List<String> amenities, boolean hasAccessibility) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        setAverageRating(averageRating); // Use setter to validate rating
        this.amenities = amenities != null ? new ArrayList<>(amenities) : new ArrayList<>();
        this.hasAccessibility = hasAccessibility;
    }

    /**
     * Gets the unique identifier of the site.
     *
     * @return The site ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the site.
     *
     * @return The site name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the site.
     *
     * @return The site description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the geographical location of the site.
     *
     * @return The site's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the category of the site.
     *
     * @return The site category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the average rating of the site.
     *
     * @return The average rating.
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Sets the average rating of the site.
     *
     * @param averageRating The new average rating. Must be between 0.0 and 5.0.
     */
    public void setAverageRating(double averageRating) {
        if (averageRating < 0.0 || averageRating > 5.0) {
            throw new IllegalArgumentException("Average rating must be between 0.0 and 5.0.");
        }
        this.averageRating = averageRating;
    }

    /**
     * Gets the list of amenities available at the site.
     *
     * @return A list of amenities.
     */
    public List<String> getAmenities() {
        return new ArrayList<>(amenities); // Return a copy to prevent external modification
    }

    /**
     * Checks if the site has accessibility features.
     *
     * @return True if accessible, false otherwise.
     */
    public boolean hasAccessibility() {
        return hasAccessibility;
    }

    @Override
    public String toString() {
        return "Site{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", location=" + location +
               ", category='" + category + '\'' +
               ", averageRating=" + averageRating +
               ", amenities=" + amenities +
               ", hasAccessibility=" + hasAccessibility +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Double.compare(site.averageRating, averageRating) == 0 &&
               hasAccessibility == site.hasAccessibility &&
               Objects.equals(id, site.id) &&
               Objects.equals(name, site.name) &&
               Objects.equals(description, site.description) &&
               Objects.equals(location, site.location) &&
               Objects.equals(category, site.category) &&
               Objects.equals(amenities, site.amenities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, location, category, averageRating, amenities, hasAccessibility);
    }
}