/**
 * Student class representing a pupil in a class.
 * Contains student information and absence status.
 */
public class Student {
    private int id;
    private String name;
    private String parentEmail;
    private boolean isAbsent; // true if absent, false if present (default)

    /**
     * Constructor to create a Student object.
     * @param id Student ID
     * @param name Student name
     * @param parentEmail Parent's email address for notifications
     */
    public Student(int id, String name, String parentEmail) {
        this.id = id;
        this.name = name;
        this.parentEmail = parentEmail;
        this.isAbsent = false; // Default: student is present
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public boolean isAbsent() {
        return isAbsent;
    }

    /**
     * Sets the absence status of the student.
     * @param absent true if absent, false if present
     */
    public void setAbsent(boolean absent) {
        isAbsent = absent;
    }

    /**
     * Returns a string representation of the student for display.
     * @return formatted string with student details
     */
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Email: %s, Absent: %s",
                id, name, parentEmail, isAbsent ? "Yes" : "No");
    }
}