package domain;

/**
 * Represents a Student entity in the domain layer.
 */
public class Student {
    // Attributes as specified in the Class Diagram
    private String id;
    private String name;

    /**
     * Constructs a new Student object.
     * @param id The unique identifier for the student.
     * @param name The name of the student.
     */
    public Student(String id, String name) {
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
        return "Student{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}