/**
 * Entity.java
 * Represents a system entity with type, name, and description.
 */
public class Entity {
    private String type;
    private String name;
    private String description;
    public Entity(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return "[" + type + "] " + name + " - " + description;
    }
}