'''
Represents a Class entity with properties like ID, name, and description.
This acts as a simple data model for the application.
'''
public class ClassModel {
    private String id;
    private String name;
    private String description;
    /**
     * Constructs a new ClassModel instance.
     * @param id A unique identifier for the class.
     * @param name The name of the class.
     * @param description A brief description of the class.
     */
    public ClassModel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    // Getters for class properties
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    // Setter for description (example, though not strictly needed for this use case)
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Description: " + description;
    }
}