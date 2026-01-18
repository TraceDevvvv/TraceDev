import java.util.Objects;

/**
 * Represents a student in the school management system.
 * Each student has a unique ID and a name.
 */
public class Student {
    private final String studentId;
    private final String name;

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
     * Returns the unique ID of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides the toString method to provide a meaningful string representation of the Student object.
     *
     * @return A string containing the student's ID and name.
     */
    @Override
    public String toString() {
        return "Student [ID: " + studentId + ", Name: " + name + "]";
    }

    /**
     * Compares this Student object with the specified object for equality.
     * Two Student objects are considered equal if they have the same studentId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the studentId, ensuring consistency with the equals method.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}