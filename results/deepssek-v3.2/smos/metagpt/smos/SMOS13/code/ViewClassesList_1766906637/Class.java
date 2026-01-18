import java.io.Serializable;

/**
 * Model class representing a Class entity in the system.
 * This class encapsulates information about an academic class including
 * its code, course name, instructor, and student capacity.
 * 
 * The class implements Serializable to support potential persistence
 * or network transmission requirements.
 */
public class Class implements Serializable {
    
    // Class properties with private access for encapsulation
    private String code;          // Unique class code (e.g., CS101)
    private String courseName;    // Full name of the course (e.g., Introduction to Computer Science)
    private String instructor;    // Name of the instructor
    private int capacity;         // Maximum number of students allowed
    
    // Constants for validation
    private static final int MIN_CAPACITY = 1;
    private static final int MAX_CAPACITY = 200;
    
    /**
     * Default constructor - creates an empty Class object.
     * Useful for serialization and deserialization.
     */
    public Class() {
        this("", "", "", MIN_CAPACITY);
    }
    
    /**
     * Parameterized constructor for creating a Class with all properties.
     * 
     * @param code The unique class code (e.g., "CS101")
     * @param courseName The full name of the course
     * @param instructor The name of the instructor
     * @param capacity The maximum student capacity
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Class(String code, String courseName, String instructor, int capacity) {
        setCode(code);
        setCourseName(courseName);
        setInstructor(instructor);
        setCapacity(capacity);
    }
    
    /**
     * Copy constructor to create a deep copy of another Class object.
     * 
     * @param other The Class object to copy
     * @throws IllegalArgumentException if the other object is null
     */
    public Class(Class other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy null Class object");
        }
        this.code = other.code;
        this.courseName = other.courseName;
        this.instructor = other.instructor;
        this.capacity = other.capacity;
    }
    
    // Getters and Setters with validation
    
    /**
     * Gets the class code.
     * 
     * @return The class code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Sets the class code with validation.
     * 
     * @param code The class code to set (cannot be null or empty)
     * @throws IllegalArgumentException if code is null or empty
     */
    public void setCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Class code cannot be null or empty");
        }
        this.code = code.trim().toUpperCase(); // Store in uppercase for consistency
    }
    
    /**
     * Gets the course name.
     * 
     * @return The course name
     */
    public String getCourseName() {
        return courseName;
    }
    
    /**
     * Sets the course name with validation.
     * 
     * @param courseName The course name to set (cannot be null or empty)
     * @throws IllegalArgumentException if courseName is null or empty
     */
    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }
        this.courseName = courseName.trim();
    }
    
    /**
     * Gets the instructor name.
     * 
     * @return The instructor name
     */
    public String getInstructor() {
        return instructor;
    }
    
    /**
     * Sets the instructor name with validation.
     * 
     * @param instructor The instructor name to set (cannot be null or empty)
     * @throws IllegalArgumentException if instructor is null or empty
     */
    public void setInstructor(String instructor) {
        if (instructor == null || instructor.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be null or empty");
        }
        this.instructor = instructor.trim();
    }
    
    /**
     * Gets the student capacity.
     * 
     * @return The student capacity
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Sets the student capacity with validation.
     * 
     * @param capacity The capacity to set (must be between MIN_CAPACITY and MAX_CAPACITY)
     * @throws IllegalArgumentException if capacity is out of valid range
     */
    public void setCapacity(int capacity) {
        if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException(
                String.format("Capacity must be between %d and %d", MIN_CAPACITY, MAX_CAPACITY));
        }
        this.capacity = capacity;
    }
    
    /**
     * Checks if the class has available seats.
     * 
     * @param currentEnrollment The current number of enrolled students
     * @return true if there are available seats, false otherwise
     * @throws IllegalArgumentException if currentEnrollment is negative or exceeds capacity
     */
    public boolean hasAvailableSeats(int currentEnrollment) {
        if (currentEnrollment < 0) {
            throw new IllegalArgumentException("Current enrollment cannot be negative");
        }
        if (currentEnrollment > capacity) {
            throw new IllegalArgumentException("Current enrollment exceeds capacity");
        }
        return currentEnrollment < capacity;
    }
    
    /**
     * Calculates the number of available seats.
     * 
     * @param currentEnrollment The current number of enrolled students
     * @return The number of available seats
     */
    public int getAvailableSeats(int currentEnrollment) {
        if (!hasAvailableSeats(currentEnrollment)) {
            return 0;
        }
        return capacity - currentEnrollment;
    }
    
    /**
     * Checks if this class is similar to another class based on code.
     * 
     * @param otherCode The class code to compare with
     * @return true if the codes match (case-insensitive), false otherwise
     */
    public boolean isSameClass(String otherCode) {
        if (otherCode == null) {
            return false;
        }
        return this.code.equalsIgnoreCase(otherCode.trim());
    }
    
    /**
     * Checks if this class is similar to another class based on code.
     * 
     * @param other The Class object to compare with
     * @return true if the other class is not null and codes match, false otherwise
     */
    public boolean isSameClass(Class other) {
        if (other == null) {
            return false;
        }
        return isSameClass(other.code);
    }
    
    /**
     * Generates a summary string for the class.
     * 
     * @return A formatted summary string
     */
    public String getSummary() {
        return String.format("%s - %s (Instructor: %s, Capacity: %d)", 
            code, courseName, instructor, capacity);
    }
    
    /**
     * Compares this Class with another object for equality.
     * Two Class objects are considered equal if they have the same code
     * (case-insensitive comparison).
     * 
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Class other = (Class) obj;
        return this.code.equalsIgnoreCase(other.code);
    }
    
    /**
     * Generates hash code based on the class code (case-insensitive).
     * 
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return code.toUpperCase().hashCode();
    }
    
    /**
     * Returns a string representation of the Class object.
     * Includes all properties for debugging and display purposes.
     * 
     * @return A formatted string representation
     */
    @Override
    public String toString() {
        return String.format("Class[Code='%s', Course='%s', Instructor='%s', Capacity=%d]", 
            code, courseName, instructor, capacity);
    }
}