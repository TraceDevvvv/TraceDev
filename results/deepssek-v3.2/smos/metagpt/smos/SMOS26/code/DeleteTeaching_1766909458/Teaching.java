import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a teaching entity in the system.
 * A teaching has properties like ID, course name, instructor, description, and dates.
 * This class encapsulates all the data related to a teaching.
 */
public class Teaching {
    private int id;
    private String courseName;
    private String instructor;
    private String description;
    private String startDate;
    private String endDate;
    
    /**
     * Constructs a new Teaching object with the specified properties.
     * Validates the input parameters to ensure data integrity.
     * 
     * @param id The unique identifier for the teaching
     * @param courseName The name of the course
     * @param instructor The name of the instructor
     * @param description A description of the teaching
     * @param startDate The start date of the teaching (format: YYYY-MM-DD)
     * @param endDate The end date of the teaching (format: YYYY-MM-DD)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Teaching(int id, String courseName, String instructor, String description, 
                   String startDate, String endDate) {
        // Validate ID
        if (id <= 0) {
            throw new IllegalArgumentException("Teaching ID must be a positive integer.");
        }
        
        // Validate course name
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        
        // Validate instructor
        if (instructor == null || instructor.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be null or empty.");
        }
        
        // Validate description
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        
        // Validate dates
        validateDate(startDate, "Start date");
        validateDate(endDate, "End date");
        
        // Validate that end date is not before start date
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            if (end.isBefore(start)) {
                throw new IllegalArgumentException("End date cannot be before start date.");
            }
        } catch (DateTimeParseException e) {
            // This should not happen because validateDate already checks format
            throw new IllegalArgumentException("Invalid date format: " + e.getMessage());
        }
        
        this.id = id;
        this.courseName = courseName.trim();
        this.instructor = instructor.trim();
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Validates that a date string is in the correct format (YYYY-MM-DD).
     * 
     * @param date The date string to validate
     * @param fieldName The name of the date field (for error messages)
     * @throws IllegalArgumentException if the date is invalid
     */
    private void validateDate(String date, String fieldName) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
        
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                fieldName + " must be in YYYY-MM-DD format. Provided: " + date);
        }
    }
    
    /**
     * Gets the teaching ID.
     * 
     * @return The teaching ID
     */
    public int getId() {
        return id;
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
     * Gets the instructor name.
     * 
     * @return The instructor name
     */
    public String getInstructor() {
        return instructor;
    }
    
    /**
     * Gets the teaching description.
     * 
     * @return The description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Gets the start date.
     * 
     * @return The start date in YYYY-MM-DD format
     */
    public String getStartDate() {
        return startDate;
    }
    
    /**
     * Gets the end date.
     * 
     * @return The end date in YYYY-MM-DD format
     */
    public String getEndDate() {
        return endDate;
    }
    
    /**
     * Sets the description of the teaching.
     * 
     * @param description The new description
     * @throws IllegalArgumentException if description is null
     */
    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        this.description = description;
    }
    
    /**
     * Checks if this teaching is currently active based on today's date.
     * 
     * @return true if today is between start and end date (inclusive), false otherwise
     */
    public boolean isCurrentlyActive() {
        try {
            LocalDate today = LocalDate.now();
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            
            return !today.isBefore(start) && !today.isAfter(end);
        } catch (DateTimeParseException e) {
            // Should not happen if dates were validated at construction
            return false;
        }
    }
    
    /**
     * Returns a string representation of the teaching.
     * 
     * @return A string containing teaching details
     */
    @Override
    public String toString() {
        return "Teaching{" +
               "id=" + id +
               ", courseName='" + courseName + '\'' +
               ", instructor='" + instructor + '\'' +
               ", description='" + (description.length() > 50 ? description.substring(0, 47) + "..." : description) + '\'' +
               ", startDate='" + startDate + '\'' +
               ", endDate='" + endDate + '\'' +
               '}';
    }
    
    /**
     * Compares this teaching with another object for equality.
     * Two teachings are considered equal if they have the same ID.
     * 
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teaching teaching = (Teaching) obj;
        return id == teaching.id;
    }
    
    /**
     * Returns a hash code value for the teaching.
     * Based on the teaching ID.
     * 
     * @return The hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}