/**
 * Data model class representing a refreshment point.
 * Encapsulates the attributes of a point of rest.
 */
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private String status; // e.g., "Open", "Closed", "Under Maintenance"
    private int capacity;
    private String operatingHours;
    /**
     * Constructs a RefreshmentPoint with the given attributes.
     * @param id Unique identifier for the point.
     * @param name Name of the refreshment point.
     * @param location Physical address or location description.
     * @param status Operational status.
     * @param capacity Maximum number of people it can accommodate.
     * @param operatingHours String describing operating hours.
     */
    public RefreshmentPoint(int id, String name, String location, String status, int capacity, String operatingHours) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
        this.capacity = capacity;
        this.operatingHours = operatingHours;
    }
    // Getters and Setters
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String getOperatingHours() {
        return operatingHours;
    }
    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }
    @Override
    public String toString() {
        return name + " (" + location + ")";
    }
}