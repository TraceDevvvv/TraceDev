'''
Site.java
Data model representing a cultural heritage site.
'''
package culturalheritage.search;
public class Site {
    private String id;
    private String name;
    private String description;
    private String category;
    private String location;
    private double rating;
    private int visitors;
    private boolean available;
    public Site(String id, String name, String description, String category, 
                String location, double rating, int visitors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.location = location;
        this.rating = rating;
        this.visitors = visitors;
        this.available = true;
    }
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getLocation() { return location; }
    public double getRating() { return rating; }
    public int getVisitors() { return visitors; }
    public boolean isAvailable() { return available; }
    // Setters
    public void setAvailable(boolean available) { this.available = available; }
    @Override
    public String toString() {
        return String.format("Site{name='%s', category='%s', location='%s', rating=%.1f}", 
                            name, category, location, rating);
    }
}