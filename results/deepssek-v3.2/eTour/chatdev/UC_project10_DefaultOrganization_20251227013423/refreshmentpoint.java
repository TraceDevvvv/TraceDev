/**
 * RefreshmentPoint represents a point of rest with various attributes.
 * This data model class is used in the ViewRefreshmentPointCard use case.
 */
import java.util.List;
import java.util.Arrays;
public class RefreshmentPoint {
    private String name;
    private String location;
    private String type;
    private double rating;
    private String description;
    private List<String> facilities;
    public RefreshmentPoint(String name, String location, String type, 
                           double rating, String description, List<String> facilities) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.rating = rating;
        this.description = description;
        this.facilities = facilities;
    }
    // Getter methods
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public double getRating() { return rating; }
    public String getDescription() { return description; }
    public List<String> getFacilities() { return facilities; }
    /**
     * Creates a sample refreshment point for demonstration purposes.
     * @return A sample refreshment point
     */
    public static RefreshmentPoint createSamplePoint() {
        return new RefreshmentPoint(
            "Mountain View Cafe",
            "123 Alpine Road, Mountain Peak",
            "Cafe",
            4.5,
            "A cozy cafe offering panoramic mountain views, serving fresh coffee " +
            "and homemade pastries. Perfect for hikers and travelers.",
            Arrays.asList("WiFi", "Restrooms", "Outdoor Seating", "Parking", "Wheelchair Access")
        );
    }
}