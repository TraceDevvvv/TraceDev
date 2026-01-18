// File: ClassEntity.java
/**
 * Represents a Class entity, extending AbstractEntity.
 * Includes specific attribute for course code.
 */
public class ClassEntity extends AbstractEntity {
    // + courseCode : String
    public String courseCode;

    /**
     * Constructs a ClassEntity with the specified details.
     *
     * @param id         The unique identifier for the class.
     * @param name       The name of the class.
     * @param courseCode The course code of the class.
     */
    public ClassEntity(String id, String name, String courseCode) {
        super(id, name);
        this.courseCode = courseCode;
    }

    /**
     * Returns the type of this entity, which is "Class".
     *
     * @return The string "Class".
     */
    @Override
    public String getType() {
        return "Class";
    }

    /**
     * Provides a string representation of the ClassEntity.
     *
     * @return A string combining the class's id, name, and course code.
     */
    @Override
    public String toString() {
        return "ClassEntity{id='" + id + "', name='" + name + "', courseCode='" + courseCode + "'}";
    }
}