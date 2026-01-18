/**
 * Model class representing a student in the system.
 * Contains student information including contact details for parents.
 */
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String className;
    private String parentEmail;
    private boolean isPresent;
    /**
     * Constructor for creating a Student object.
     * @param id unique identifier for the student
     * @param firstName student's first name
     * @param lastName student's last name
     * @param className name of the class the student belongs to
     * @param parentEmail email address of the student's parent
     */
    public Student(String id, String firstName, String lastName, 
                   String className, String parentEmail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.parentEmail = parentEmail;
        this.isPresent = true; // Default to present as per requirements
    }
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getParentEmail() { return parentEmail; }
    public void setParentEmail(String parentEmail) { this.parentEmail = parentEmail; }
    public boolean isPresent() { return isPresent; }
    public void setPresent(boolean isPresent) { this.isPresent = isPresent; }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    @Override
    public String toString() {
        return getFullName() + " (" + className + ")";
    }
}