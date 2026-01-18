/**
 * Location Entity Class
 * Represents a physical location with its properties
 */
public class Location {
    private String id;
    private String name;
    private String address;
    private String type; // e.g., "Park", "Museum", "Restaurant"
    private int capacity;
    public Location(String id, String name, String address, String type, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.capacity = capacity;
    }
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}