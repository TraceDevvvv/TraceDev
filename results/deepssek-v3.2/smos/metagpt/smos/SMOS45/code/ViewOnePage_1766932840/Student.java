/**
 * Student.java
 * Class representing a student with ID, first name, and last name.
 * This class provides methods to access student information.
 */
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    
    /**
     * Constructor to create a new Student object.
     * 
     * @param studentId The unique identifier for the student
     * @param firstName The student's first name
     * @param lastName The student's last name
     */
    public Student(String studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /**
     * Gets the student's ID.
     * 
     * @return The student ID
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Gets the student's first name.
     * 
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Gets the student's last name.
     * 
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Gets the student's full name (first name + last name).
     * 
     * @return The full name of the student
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Sets the student's ID.
     * 
     * @param studentId The new student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * Sets the student's first name.
     * 
     * @param firstName The new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Sets the student's last name.
     * 
     * @param lastName The new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Returns a string representation of the student.
     * 
     * @return String representation in format: "Student{id=..., name=...}"
     */
    @Override
    public String toString() {
        return "Student{id=" + studentId + ", name=" + getFullName() + "}";
    }
    
    /**
     * Checks if this student is equal to another object.
     * Two students are considered equal if they have the same student ID.
     * 
     * @param obj The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId.equals(student.studentId);
    }
    
    /**
     * Returns a hash code value for the student.
     * 
     * @return Hash code based on student ID
     */
    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}