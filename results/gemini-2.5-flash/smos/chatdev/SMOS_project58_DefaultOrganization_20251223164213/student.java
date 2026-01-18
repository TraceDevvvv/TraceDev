/*
 * Represents a student.
 * This class holds basic student information like ID and name.
 * It overrides toString for proper display in GUI components.
 */
class Student {
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
     * Returns the student's name.
     * @return The student name.
     */
    public String getName() {
        return name;
    }
    /**
     * Provides a string representation of the student, suitable for display.
     * @return The student's name.
     */
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
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