package JustificationViewer_1766393399;

/**
 * Represents a student in the school system.
 * Contains basic information such as student ID and name.
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
        // Validate input to ensure studentId and name are not null or empty.
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
     * Provides a string representation of the Student object.
     *
     * @return A string containing the student's ID and name.
     */
    @Override
    public String toString() {
        return "Student [ID: " + studentId + ", Name: " + name + "]";
    }

    /**
     * Compares this Student object to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that has the same studentId as this object.
     *
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}