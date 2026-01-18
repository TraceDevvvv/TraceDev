/**
 * Represents a student in the system.
 */
public class Student {
    private String id;
    private String name;
    /**
     * Constructs a new Student object.
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the student's ID.
     * @return The student's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Overrides the toString method to provide a user-friendly representation for display
     * in UI components like JComboBox.
     * @return The student's name.
     */
    @Override
    public String toString() {
        return name;
    }
}