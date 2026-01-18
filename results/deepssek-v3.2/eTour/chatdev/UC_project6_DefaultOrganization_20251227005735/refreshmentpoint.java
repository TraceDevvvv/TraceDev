'''
Model class representing a refreshment point in the system.
Contains all the properties of a refreshment point that would be
retrieved from the SearchCulturalHeritage use case.
'''
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private String type;
    private String description;
    /**
     * Constructor for creating a refreshment point.
     * 
     * @param id Unique identifier for the refreshment point
     * @param name Name of the refreshment point
     * @param location Physical location
     * @param type Type of refreshment (cafe, restaurant, etc.)
     * @param description Additional details
     */
    public RefreshmentPoint(int id, String name, String location, String type, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
    }
    // Getter and setter methods
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Override toString for display in list
     */
    @Override
    public String toString() {
        return name + " - " + location + " (" + type + ")";
    }
}