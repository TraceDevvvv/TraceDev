package com.example.tourist;

/**
 * Represents a tourist site with details.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private String location;
    private double rating;
    private int visitCount;
    private boolean isFavorite;

    public Site(String id, String name, String description, String location, double rating, int visitCount, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.rating = rating;
        this.visitCount = visitCount;
        this.isFavorite = isFavorite;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getVisitCount() { return visitCount; }
    public void setVisitCount(int visitCount) { this.visitCount = visitCount; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    @Override
    public String toString() {
        return "Site{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", visitCount=" + visitCount +
                ", isFavorite=" + isFavorite +
                '}';
    }
}