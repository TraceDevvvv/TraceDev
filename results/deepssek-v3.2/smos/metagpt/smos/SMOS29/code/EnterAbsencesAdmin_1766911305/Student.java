import java.util.Objects;

/**
 * Student class representing a student in the system.
 * Contains student details including ID, name, parent email, and absence status.
 * This class follows proper encapsulation with getters/setters and validation.
 */
public class Student {
    private String id;
    private String name;
    private String parentEmail;
    private boolean isAbsent;
    
    /**
     * Constructor for creating a Student with all properties.
     * 
     * @param id Unique identifier for the student
     * @param name Full name of the student
     * @param parentEmail Email address of the parent/guardian
     * @param isAbsent Initial absence status (true if absent, false if present)
     */
    public Student(String id, String name, String parentEmail, boolean isAbsent) {
        setId(id);
        setName(name);
        setParentEmail(parentEmail);
        this.isAbsent = isAbsent;
    }
    
    /**
     * Constructor for creating a Student without initial absence status.
     * Defaults to present (isAbsent = false).
     * 
     * @param id Unique identifier for the student
     * @param name Full name of the student
     * @param parentEmail Email address of the parent/guardian
     */
    public Student(String id, String name, String parentEmail) {
        this(id, name, parentEmail, false);
    }
    
    /**
     * Gets the student's unique ID.
     * 
     * @return Student ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the student's unique ID with validation.
     * 
     * @param id Student ID (cannot be null or empty)
     * @throws IllegalArgumentException if id is null or empty
     */
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        this.id = id.trim();
    }
    
    /**
     * Gets the student's full name.
     * 
     * @return Student name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the student's name with validation.
     * 
     * @param name Student name (cannot be null or empty)
     * @throws IllegalArgumentException if name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        this.name = name.trim();
    }
    
    /**
     * Gets the parent's email address for notifications.
     * 
     * @return Parent email address
     */
    public String getParentEmail() {
        return parentEmail;
    }
    
    /**
     * Sets the parent's email address with basic validation.
     * Note: In a production system, more comprehensive email validation would be used.
     * 
     * @param parentEmail Parent email address
     * @throws IllegalArgumentException if email is null, empty, or doesn't contain '@'
     */
    public void setParentEmail(String parentEmail) {
        if (parentEmail == null || parentEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent email cannot be null or empty");
        }
        String trimmedEmail = parentEmail.trim();
        if (!trimmedEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email format: must contain '@'");
        }
        this.parentEmail = trimmedEmail;
    }
    
    /**
     * Checks if the student is currently marked as absent.
     * 
     * @return true if student is absent, false if present
     */
    public boolean isAbsent() {
        return isAbsent;
    }
    
    /**
     * Sets the student's absence status.
     * 
     * @param isAbsent true to mark as absent, false to mark as present
     */
    public void setAbsent(boolean isAbsent) {
        this.isAbsent = isAbsent;
    }
    
    /**
     * Marks the student as absent.
     * Convenience method equivalent to setAbsent(true).
     */
    public void markAbsent() {
        this.isAbsent = true;
    }
    
    /**
     * Marks the student as present.
     * Convenience method equivalent to setAbsent(false).
     */
    public void markPresent() {
        this.isAbsent = false;
    }
    
    /**
     * Returns a string representation of the Student object.
     * Useful for debugging and logging purposes.
     * 
     * @return String representation of the student
     */
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                ", isAbsent=" + isAbsent +
                '}';
    }
    
    /**
     * Checks if this Student is equal to another object.
     * Equality is based on student ID only, as ID should be unique.
     * 
     * @param obj The object to compare with
     * @return true if objects are equal (same ID), false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(id, student.id);
    }
    
    /**
     * Generates hash code based on student ID.
     * 
     * @return Hash code of the student
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}