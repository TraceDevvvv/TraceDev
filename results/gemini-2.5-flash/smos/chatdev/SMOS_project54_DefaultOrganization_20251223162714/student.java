/**
 * Represents a student in the system.
 * Contains basic information about a student, including their ID, name, parent's email,
 * and a transient status for the current absence selection.
 */
public class Student {
    private String id;
    private String name;
    private String parentEmail;
    private boolean present; // Default to true (present) for current session
    /**
     * Constructs a new Student object.
     *
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     * @param parentEmail The email address of the student's parent for notifications.
     */
    public Student(String id, String name, String parentEmail) {
        this.id = id;
        this.name = name;
        this.parentEmail = parentEmail;
        this.present = true; // By default, a student is considered present
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
     * Gets the parent's email address.
     * @return The parent's email address.
     */
    public String getParentEmail() {
        return parentEmail;
    }
    /**
     * Checks if the student is marked as present for the current session.
     * @return true if present, false if absent.
     */
    public boolean isPresent() {
        return present;
    }
    /**
     * Sets the presence status of the student for the current session.
     * @param present true if the student is present, false if absent.
     */
    public void setPresent(boolean present) {
        this.present = present;
    }
    /**
     * Provides a string representation of the Student object.
     * @return A string containing the student's ID and name.
     */
    @Override
    public String toString() {
        return id + " - " + name;
    }
}