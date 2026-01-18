/*
 * Represents a student with an ID and a name.
 */
public class Student {
    private String studentId;
    private String name;
    /**
     * Constructs a new Student object.
     * @param studentId The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }
    /**
     * Gets the student's ID.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns a string representation of the Student object.
     * @return A string containing the student's ID and name.
     */
    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name;
    }
}