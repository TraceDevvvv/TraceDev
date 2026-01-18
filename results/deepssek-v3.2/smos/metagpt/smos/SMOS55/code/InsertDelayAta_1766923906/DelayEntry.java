import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DelayEntry class representing a delay record for a student.
 * Contains information about the student, delay duration, and timestamp.
 */
public class DelayEntry {
    private String studentId;
    private String studentName;
    private int delayMinutes;
    private String className;
    private LocalDateTime entryDateTime;
    private String entryBy; // ATA staff who entered the data
    
    // Formatter for date-time display
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Constructor for DelayEntry with all parameters
     * @param studentId The unique identifier for the student
     * @param studentName The name of the student
     * @param delayMinutes The number of minutes delayed (must be non-negative)
     * @param className The name of the class
     * @param entryBy The ATA staff who entered the data
     * @throws IllegalArgumentException if delayMinutes is negative
     */
    public DelayEntry(String studentId, String studentName, int delayMinutes, 
                      String className, String entryBy) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }
        
        this.studentId = studentId;
        this.studentName = studentName;
        this.delayMinutes = delayMinutes;
        this.className = className;
        this.entryBy = entryBy;
        this.entryDateTime = LocalDateTime.now();
    }
    
    /**
     * Constructor for DelayEntry with minimal parameters
     * @param studentId The unique identifier for the student
     * @param studentName The name of the student
     * @param delayMinutes The number of minutes delayed (must be non-negative)
     * @param entryBy The ATA staff who entered the data
     */
    public DelayEntry(String studentId, String studentName, int delayMinutes, String entryBy) {
        this(studentId, studentName, delayMinutes, "ATA Class", entryBy);
    }
    
    /**
     * Gets the student ID
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
     * Gets the student name
     * @return The student name
     */
    public String getStudentName() {
        return studentName;
    }
    
    /**
     * Sets the student name
     * @param studentName The new student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    /**
     * Gets the delay minutes
     * @return The number of delay minutes
     */
    public int getDelayMinutes() {
        return delayMinutes;
    }
    
    /**
     * Sets the delay minutes
     * @param delayMinutes The number of delay minutes (must be non-negative)
     * @throws IllegalArgumentException if delayMinutes is negative
     */
    public void setDelayMinutes(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }
        this.delayMinutes = delayMinutes;
    }
    
    /**
     * Gets the class name
     * @return The class name
     */
    public String getClassName() {
        return className;
    }
    
    /**
     * Sets the class name
     * @param className The new class name
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
    /**
     * Gets the entry date and time
     * @return The entry date and time as LocalDateTime
     */
    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }
    
    /**
     * Sets the entry date and time
     * @param entryDateTime The new entry date and time
     */
    public void setEntryDateTime(LocalDateTime entryDateTime) {
        this.entryDateTime = entryDateTime;
    }
    
    /**
     * Gets the formatted entry date and time string
     * @return Formatted date-time string
     */
    public String getFormattedDateTime() {
        return entryDateTime.format(FORMATTER);
    }
    
    /**
     * Gets the ATA staff who entered the data
     * @return The staff identifier
     */
    public String getEntryBy() {
        return entryBy;
    }
    
    /**
     * Sets the ATA staff who entered the data
     * @param entryBy The new staff identifier
     */
    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }
    
    /**
     * Checks if the delay is valid (non-negative minutes)
     * @return True if valid, false otherwise
     */
    public boolean isValid() {
        return delayMinutes >= 0 && studentId != null && !studentId.trim().isEmpty();
    }
    
    /**
     * Gets a summary of the delay entry
     * @return Summary string
     */
    public String getSummary() {
        return String.format("Student: %s (%s) - Delay: %d minutes - Entered by: %s at %s",
                studentName, studentId, delayMinutes, entryBy, getFormattedDateTime());
    }
    
    /**
     * Converts the delay entry to a CSV format string
     * @return CSV formatted string
     */
    public String toCSVString() {
        return String.format("%s,%s,%d,%s,%s,%s",
                studentId, studentName, delayMinutes, className, entryBy, getFormattedDateTime());
    }
    
    /**
     * Gets a string representation of the delay entry
     * @return String representation
     */
    @Override
    public String toString() {
        return "DelayEntry{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", delayMinutes=" + delayMinutes +
                ", className='" + className + '\'' +
                ", entryBy='" + entryBy + '\'' +
                ", entryDateTime=" + getFormattedDateTime() +
                '}';
    }
    
    /**
     * Creates a delay entry from a CSV string
     * @param csvLine The CSV formatted string
     * @return A new DelayEntry object
     * @throws IllegalArgumentException if the CSV format is invalid
     */
    public static DelayEntry fromCSVString(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length < 6) {
            throw new IllegalArgumentException("Invalid CSV format for DelayEntry");
        }
        
        String studentId = parts[0];
        String studentName = parts[1];
        int delayMinutes = Integer.parseInt(parts[2]);
        String className = parts[3];
        String entryBy = parts[4];
        String dateTimeStr = parts[5];
        
        DelayEntry entry = new DelayEntry(studentId, studentName, delayMinutes, className, entryBy);
        entry.setEntryDateTime(LocalDateTime.parse(dateTimeStr, FORMATTER));
        return entry;
    }
    
    /**
     * Creates a copy of this delay entry
     * @return A new DelayEntry object with the same values
     */
    public DelayEntry copy() {
        DelayEntry copy = new DelayEntry(studentId, studentName, delayMinutes, className, entryBy);
        copy.setEntryDateTime(entryDateTime);
        return copy;
    }
}