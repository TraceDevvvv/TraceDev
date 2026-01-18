/**
 * Represents a data model for a cultural heritage site.
 * This class holds information about a site, such as its name, description, location, and category.
 * It's a simple Plain Old Java Object (POJO) used to encapsulate data.
 */
public class CulturalSite {
    private String name;
    private String description;
    private String location; // e.g., "Rome, Italy"
    private String category; // e.g., "Museum", "Archaeological Site", "Historical Building"
    /**
     * Constructs a new CulturalSite object.
     * @param name The name of the cultural site.
     * @param description A brief description of the site.
     * @param location The geographical location of the site.
     * @param category The category of the site (e.g., Museum, Park).
     */
    public CulturalSite(String name, String description, String location, String category) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
    }
    // --- Getters for all properties ---
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public String getCategory() {
        return category;
    }
    /**
     * Provides a string representation of the CulturalSite object.
     * This is useful for displaying results in a list or console.
     * @return A formatted string containing the site's name, category, and location.
     */
    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, category, location);
    }
}