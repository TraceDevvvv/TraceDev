import java.util.Objects;

/**
 * Represents a refreshment point at a convention.
 * This class stores information about a refreshment point including its ID, name,
 * location, current number of banners, and maximum allowed number of banners.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private int currentBanners;
    private int maxAllowedBanners;

    /**
     * Default constructor.
     * Initializes with default values.
     */
    public RefreshmentPoint() {
        this.id = "";
        this.name = "";
        this.location = "";
        this.currentBanners = 0;
        this.maxAllowedBanners = 5; // Default maximum of 5 banners
    }

    /**
     * Parameterized constructor.
     * 
     * @param id Unique identifier for the refreshment point
     * @param name Name of the refreshment point
     * @param location Location at the convention
     * @param currentBanners Current number of banners displayed
     * @param maxAllowedBanners Maximum number of banners allowed
     * @throws IllegalArgumentException if maxAllowedBanners is negative or 
     *         currentBanners is negative or exceeds maxAllowedBanners
     */
    public RefreshmentPoint(String id, String name, String location, 
                            int currentBanners, int maxAllowedBanners) {
        setId(id);
        setName(name);
        setLocation(location);
        setMaxAllowedBanners(maxAllowedBanners);
        setCurrentBanners(currentBanners); // Must be set after maxAllowedBanners for validation
    }

    // Getters and Setters with validation

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? "" : location;
    }

    public int getCurrentBanners() {
        return currentBanners;
    }

    /**
     * Sets the current number of banners.
     * 
     * @param currentBanners The number of banners currently displayed
     * @throws IllegalArgumentException if currentBanners is negative or 
     *         exceeds maxAllowedBanners
     */
    public void setCurrentBanners(int currentBanners) {
        if (currentBanners < 0) {
            throw new IllegalArgumentException("Current banners cannot be negative");
        }
        if (currentBanners > maxAllowedBanners) {
            throw new IllegalArgumentException(
                "Current banners cannot exceed maximum allowed banners (" 
                + maxAllowedBanners + ")");
        }
        this.currentBanners = currentBanners;
    }

    public int getMaxAllowedBanners() {
        return maxAllowedBanners;
    }

    /**
     * Sets the maximum allowed number of banners.
     * 
     * @param maxAllowedBanners The maximum number of banners allowed
     * @throws IllegalArgumentException if maxAllowedBanners is negative
     */
    public void setMaxAllowedBanners(int maxAllowedBanners) {
        if (maxAllowedBanners < 0) {
            throw new IllegalArgumentException("Maximum allowed banners cannot be negative");
        }
        this.maxAllowedBanners = maxAllowedBanners;
    }

    /**
     * Checks if another banner can be added to this refreshment point.
     * 
     * @return true if current banners are less than maximum allowed, false otherwise
     */
    public boolean canAddBanner() {
        return currentBanners < maxAllowedBanners;
    }

    /**
     * Attempts to add a banner to this refreshment point.
     * 
     * @return true if banner was added successfully, false if maximum limit reached
     */
    public boolean addBanner() {
        if (!canAddBanner()) {
            return false;
        }
        currentBanners++;
        return true;
    }

    /**
     * Removes a banner from this refreshment point.
     * 
     * @return true if banner was removed successfully, false if no banners to remove
     */
    public boolean removeBanner() {
        if (currentBanners <= 0) {
            return false;
        }
        currentBanners--;
        return true;
    }

    /**
     * Returns the number of banners that can still be added.
     * 
     * @return Available banner slots
     */
    public int getAvailableSlots() {
        return maxAllowedBanners - currentBanners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", currentBanners=" + currentBanners +
                ", maxAllowedBanners=" + maxAllowedBanners +
                ", availableSlots=" + getAvailableSlots() +
                '}';
    }
}