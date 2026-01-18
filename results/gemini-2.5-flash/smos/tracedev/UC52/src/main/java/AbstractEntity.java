// File: AbstractEntity.java
/**
 * Abstract base class for all entities in the system.
 * Provides common attributes like id and name, and an abstract method for getting the entity type.
 */
public abstract class AbstractEntity {
    // + id : String
    public String id;
    // + name : String
    public String name;

    /**
     * Constructs an AbstractEntity with the given id and name.
     *
     * @param id   The unique identifier of the entity.
     * @param name The name of the entity.
     */
    public AbstractEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Abstract method to get the type of the entity.
     * Concrete subclasses must implement this method.
     *
     * @return A string representing the type of the entity (e.g., "Class", "Teaching").
     */
    public abstract String getType();

    /**
     * Provides a string representation of the AbstractEntity.
     *
     * @return A string combining the entity's type, id, and name.
     */
    @Override
    public String toString() {
        return getType() + "{id='" + id + "', name='" + name + "'}";
    }
}