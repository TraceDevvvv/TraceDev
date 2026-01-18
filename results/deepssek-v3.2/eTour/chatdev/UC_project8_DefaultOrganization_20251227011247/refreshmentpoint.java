'''
Data model class representing a refreshment point (point of rest).
Encapsulates all attributes of a refreshment point with validation.
'''
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private String status; // "Open", "Closed", "Under Maintenance"
    private int capacity;
    private String operatingHours;
    /**
     * Constructs a RefreshmentPoint with given attributes.
     * @param id Unique identifier
     * @param name Name of refreshment point
     * @param location Physical address
     * @param status Operational status
     * @param capacity Maximum capacity
     * @param operatingHours Operating hours description
     */
    public RefreshmentPoint(int id, String name, String location, String status, int capacity, String operatingHours) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
        this.capacity = capacity;
        this.operatingHours = operatingHours;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public String getStatus() {
        return status;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getOperatingHours() {
        return operatingHours;
    }
    // Setters with basic validation
    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }
    public void setLocation(String location) {
        if (location != null && !location.trim().isEmpty()) {
            this.location = location.trim();
        }
    }
    public void setStatus(String status) {
        if (status != null && 
            (status.equals("Open") || status.equals("Closed") || status.equals("Under Maintenance"))) {
            this.status = status;
        }
    }
    public void setCapacity(int capacity) {
        if (capacity > 0 && capacity <= 1000) {
            this.capacity = capacity;
        }
    }
    public void setOperatingHours(String operatingHours) {
        if (operatingHours != null && !operatingHours.trim().isEmpty()) {
            this.operatingHours = operatingHours.trim();
        }
    }
    /**
     * Returns a formatted string representation of the refreshment point.
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("RefreshmentPoint[id=%d, name='%s', location='%s', status='%s', capacity=%d, hours='%s']",
            id, name, location, status, capacity, operatingHours);
    }
    /**
     * Checks if the refreshment point is currently active.
     * @return true if status is "Open"
     */
    public boolean isActive() {
        return "Open".equals(status);
    }
    /**
     * Validates all fields of the refreshment point.
     * @return true if all data is valid, false otherwise
     */
    public boolean isValid() {
        return id > 0 &&
               name != null && !name.trim().isEmpty() &&
               location != null && !location.trim().isEmpty() &&
               status != null && (status.equals("Open") || status.equals("Closed") || status.equals("Under Maintenance")) &&
               capacity > 0 && capacity <= 1000 &&
               operatingHours != null && !operatingHours.trim().isEmpty();
    }
}