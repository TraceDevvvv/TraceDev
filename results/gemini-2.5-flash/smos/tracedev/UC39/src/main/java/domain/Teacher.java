package domain;

/**
 * Represents a Teacher entity in the domain layer.
 */
public class Teacher {
    // Attributes as specified in the Class Diagram
    private String id;
    private String name;

    /**
     * Constructs a new Teacher object.
     * @param id The unique identifier for the teacher.
     * @param name The name of the teacher.
     */
    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters for the attributes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}