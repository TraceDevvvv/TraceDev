/**
 * Enumeration defining possible attendance statuses for students.
 * Used throughout the class register system to track student presence.
 * 
 * Status definitions:
 * - PRESENT: Student attended class on time
 * - ABSENT: Student did not attend class
 * - LATE: Student attended class but arrived after the scheduled start time
 */
public enum AttendanceStatus {
    PRESENT("Present"),
    ABSENT("Absent"),
    LATE("Late");
    
    private final String displayName;
    
    /**
     * Constructor for AttendanceStatus enum
     * @param displayName The human-readable name for the status
     */
    AttendanceStatus(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Gets the human-readable display name for the status
     * @return The display name string
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the display name when converting to string
     * @return The display name of the status
     */
    @Override
    public String toString() {
        return displayName;
    }
}