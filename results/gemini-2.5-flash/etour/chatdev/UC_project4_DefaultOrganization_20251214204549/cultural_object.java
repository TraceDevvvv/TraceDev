'''
This class defines the data model for a cultural object.
It holds properties such as ID, name, type, location, and description.
'''
/**
 * Represents a cultural object with various attributes such as ID, name, type, location, and description.
 * This is a simple POJO (Plain Old Java Object) for data representation.
 */
public class CulturalObject {
    private String id;
    private String name;
    private String type;
    private String location;
    private String description;
    /**
     * Constructor to create a new CulturalObject.
     *
     * @param id The unique identifier for the cultural object.
     * @param name The name of the cultural object.
     * @param type The type or category of the cultural object (e.g., painting, sculpture, building).
     * @param location The physical location of the cultural object.
     * @param description A brief description of the cultural object.
     */
    public CulturalObject(String id, String name, String type, String location, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.description = description;
    }
    // --- Getters for all properties ---
    /**
     * Gets the ID of the cultural object.
     * @return The cultural object's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the name of the cultural object.
     * @return The cultural object's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the type of the cultural object.
     * @return The cultural object's type.
     */
    public String getType() {
        return type;
    }
    /**
     * Gets the location of the cultural object.
     * @return The cultural object's location.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Gets the description of the cultural object.
     * @return The cultural object's description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Provides a string representation of the CulturalObject.
     * @return A string containing all properties of the cultural object.
     */
    @Override
    public String toString() {
        return "CulturalObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}