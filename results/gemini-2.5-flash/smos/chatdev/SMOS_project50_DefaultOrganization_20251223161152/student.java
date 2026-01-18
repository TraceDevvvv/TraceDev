/**
 * Represents a student in the system, including their registration status.
 */
public class Student {
    // Enum to define possible registration statuses for a student
    public enum Status {
        PENDING,  // Student registration is awaiting acceptance
        ACTIVE    // Student registration has been accepted
    }
    private String id;       // Unique identifier for the student
    private String name;     // Name of the student
    private Status status;   // Current registration status of the student
    /**
     * Constructor for the Student class.
     *
     * @param id The unique ID of the student.
     * @param name The name of the student.
     * @param status The initial registration status of the student.
     */
    public Student(String id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    /**
     * Gets the student's ID.
     * @return The student's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the student's registration status.
     * @return The student's status.
     */
    public Status getStatus() {
        return status;
    }
    /**
     * Sets the student's registration status.
     * @param status The new status for the student.
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Override toString to provide a meaningful representation for GUI display.
     * @return A string representation of the student, typically for display in lists.
     */
    @Override
    public String toString() {
        return "ID: " + id + " - Name: " + name + " (" + status + ")";
    }
}