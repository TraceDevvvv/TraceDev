/**
 * Student class representing a student in the ATA system.
 * Contains student information including ID, name, and attendance status.
 */
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private boolean isPresent;
    private boolean hasDelay;
    private int delayMinutes;
    
    /**
     * Constructor for Student class
     * @param studentId The unique identifier for the student
     * @param firstName The first name of the student
     * @param lastName The last name of the student
     * @param isPresent Whether the student is present in class
     */
    public Student(String studentId, String firstName, String lastName, boolean isPresent) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isPresent = isPresent;
        this.hasDelay = false;  // Initially no delay
        this.delayMinutes = 0;  // Initially 0 delay minutes
    }
    
    /**
     * Gets the student's full ID
     * @return The student ID
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Sets the student ID
     * @param studentId The new student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * Gets the student's first name
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets the student's first name
     * @param firstName The new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Gets the student's last name
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Sets the student's last name
     * @param lastName The new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Gets the student's full name
     * @return The full name (first + last)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Checks if the student is present
     * @return True if the student is present, false otherwise
     */
    public boolean isPresent() {
        return isPresent;
    }
    
    /**
     * Sets the student's presence status
     * @param isPresent The new presence status
     */
    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }
    
    /**
     * Checks if the student has a delay
     * @return True if the student has a delay, false otherwise
     */
    public boolean hasDelay() {
        return hasDelay;
    }
    
    /**
     * Sets the student's delay status
     * @param hasDelay The new delay status
     */
    public void setHasDelay(boolean hasDelay) {
        this.hasDelay = hasDelay;
        // If no delay is set, reset delay minutes to 0
        if (!hasDelay) {
            this.delayMinutes = 0;
        }
    }
    
    /**
     * Gets the delay minutes for the student
     * @return The number of delay minutes
     */
    public int getDelayMinutes() {
        return delayMinutes;
    }
    
    /**
     * Sets the delay minutes for the student
     * @param delayMinutes The number of delay minutes (must be non-negative)
     * @throws IllegalArgumentException if delayMinutes is negative
     */
    public void setDelayMinutes(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }
        this.delayMinutes = delayMinutes;
        // If delay minutes are set, automatically mark as having delay
        if (delayMinutes > 0) {
            this.hasDelay = true;
        }
    }
    
    /**
     * Gets a string representation of the student
     * @return String representation
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + getFullName() + '\'' +
                ", isPresent=" + isPresent +
                ", hasDelay=" + hasDelay +
                ", delayMinutes=" + delayMinutes +
                '}';
    }
    
    /**
     * Creates a copy of this student
     * @return A new Student object with the same values
     */
    public Student copy() {
        Student copy = new Student(studentId, firstName, lastName, isPresent);
        copy.setHasDelay(hasDelay);
        copy.setDelayMinutes(delayMinutes);
        return copy;
    }
}