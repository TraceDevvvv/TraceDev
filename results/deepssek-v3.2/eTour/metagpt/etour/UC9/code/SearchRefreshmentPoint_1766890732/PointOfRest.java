import java.util.List;

/**
 * Represents a point of rest (refreshment point) in the system.
 * This class contains all relevant attributes describing a point of rest.
 */
public class PointOfRest {
    private int id;
    private String name;
    private String location; // Could be extended to a Location class with coordinates
    private List<String> amenities; // List of available amenities (e.g., "WiFi", "Restroom", "Food")
    private double rating; // Average rating (e.g., 1.0 to 5.0)
    private boolean isOpen;

    /**
     * Default constructor.
     */
    public PointOfRest() {
    }

    /**
     * Parameterized constructor to create a PointOfRest with all attributes.
     *
     * @param id unique identifier
     * @param name name of the point
     * @param location location description or address
     * @param amenities list of amenities
     * @param rating average rating
     * @param isOpen whether the point is currently open
     */
    public PointOfRest(int id, String name, String location, List<String> amenities, double rating, boolean isOpen) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
        this.rating = rating;
        this.isOpen = isOpen;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    /**
     * Returns a string representation of the PointOfRest.
     *
     * @return string containing all attributes
     */
    @Override
    public String toString() {
        return "PointOfRest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", amenities=" + amenities +
                ", rating=" + rating +
                ", isOpen=" + isOpen +
                '}';
    }
}