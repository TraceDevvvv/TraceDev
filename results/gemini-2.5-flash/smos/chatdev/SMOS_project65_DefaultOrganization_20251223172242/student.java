/*
 * Represents a student with a unique ID and name.
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
     * @return The student ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the student's ID.
     * @param id The new student ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the student's name.
     * @return The student name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the student's name.
     * @param name The new student name.
     */
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name; // For easy display in UI components if needed
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}