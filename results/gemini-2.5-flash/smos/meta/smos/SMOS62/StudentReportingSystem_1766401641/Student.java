import java.util.Objects;

/**
 * Represents a student in the reporting system.
 * This class holds basic information about a student.
 */
public class Student {
    private String studentId;
    private String name;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String studentId, String name) {
        // Validate inputs to ensure they are not null or empty
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.studentId = studentId;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the full name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name The new name for the student.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Overrides the equals method to compare Student objects based on their studentId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this Student object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return A string containing the student's ID and name.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}