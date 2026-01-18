/**
 * Represents a student in the system.
 * Contains student details including parent email for notifications.
 */
public class Student {
    private String id;
    private String name;
    private String parentEmail;
    public Student(String id, String name, String parentEmail) {
        this.id = id;
        this.name = name;
        this.parentEmail = parentEmail;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getParentEmail() { return parentEmail; }
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}