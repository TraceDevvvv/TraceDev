// File: TeachingEntity.java
/**
 * Represents a Teaching entity, extending AbstractEntity.
 * Includes specific attribute for instructor name.
 */
public class TeachingEntity extends AbstractEntity {
    // + instructorName : String
    public String instructorName;

    /**
     * Constructs a TeachingEntity with the specified details.
     *
     * @param id             The unique identifier for the teaching instance.
     * @param name           The name of the teaching instance (e.g., "Calculus I Lecture").
     * @param instructorName The name of the instructor.
     */
    public TeachingEntity(String id, String name, String instructorName) {
        super(id, name);
        this.instructorName = instructorName;
    }

    /**
     * Returns the type of this entity, which is "Teaching".
     *
     * @return The string "Teaching".
     */
    @Override
    public String getType() {
        return "Teaching";
    }

    /**
     * Provides a string representation of the TeachingEntity.
     *
     * @return A string combining the teaching's id, name, and instructor name.
     */
    @Override
    public String toString() {
        return "TeachingEntity{id='" + id + "', name='" + name + "', instructorName='" + instructorName + "'}";
    }
}