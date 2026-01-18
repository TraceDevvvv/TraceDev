import java.time.LocalDate;
import java.util.Objects;

/**
 * AbsenceRecord class representing an absence record for a student on a specific date.
 * Tracks whether the student was absent, present, or late.
 * This class is immutable to ensure data integrity.
 */
public class AbsenceRecord {
    /**
     * Enum representing the possible attendance statuses.
     */
    public enum AttendanceStatus {
        PRESENT("Present"),
        ABSENT("Absent"),
        LATE("Late");
        
        private final String displayName;
        
        AttendanceStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    private final LocalDate date;
    private final Student student;
    private final AttendanceStatus status;
    private final String notes;
    
    /**
     * Constructor for creating an AbsenceRecord with all properties.
     * 
     * @param date The date of the attendance record (cannot be null)
     * @param student The student this record belongs to (cannot be null)
     * @param status The attendance status (cannot be null)
     * @param notes Optional notes about the attendance (can be null or empty)
     * @throws IllegalArgumentException if any required parameter is null
     */
    public AbsenceRecord(LocalDate date, Student student, AttendanceStatus status, String notes) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Attendance status cannot be null");
        }
        
        this.date = date;
        this.student = student;
        this.status = status;
        this.notes = (notes == null) ? "" : notes.trim();
    }
    
    /**
     * Constructor for creating an AbsenceRecord without notes.
     * 
     * @param date The date of the attendance record
     * @param student The student this record belongs to
     * @param status The attendance status
     */
    public AbsenceRecord(LocalDate date, Student student, AttendanceStatus status) {
        this(date, student, status, null);
    }
    
    /**
     * Gets the date of this attendance record.
     * 
     * @return The attendance date
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * Gets the student associated with this record.
     * 
     * @return The student
     */
    public Student getStudent() {
        return student;
    }
    
    /**
     * Gets the attendance status.
     * 
     * @return The attendance status
     */
    public AttendanceStatus getStatus() {
        return status;
    }
    
    /**
     * Gets any notes associated with this attendance record.
     * 
     * @return Notes (empty string if no notes)
     */
    public String getNotes() {
        return notes;
    }
    
    /**
     * Checks if the student was absent on this date.
     * 
     * @return true if status is ABSENT, false otherwise
     */
    public boolean isAbsent() {
        return status == AttendanceStatus.ABSENT;
    }
    
    /**
     * Checks if the student was late on this date.
     * 
     * @return true if status is LATE, false otherwise
     */
    public boolean isLate() {
        return status == AttendanceStatus.LATE;
    }
    
    /**
     * Checks if the student was present on this date.
     * 
     * @return true if status is PRESENT, false otherwise
     */
    public boolean isPresent() {
        return status == AttendanceStatus.PRESENT;
    }
    
    /**
     * Creates a new AbsenceRecord with updated notes.
     * Since this class is immutable, this method returns a new instance.
     * 
     * @param newNotes The new notes to include
     * @return A new AbsenceRecord with the updated notes
     */
    public AbsenceRecord withNotes(String newNotes) {
        return new AbsenceRecord(this.date, this.student, this.status, newNotes);
    }
    
    /**
     * Creates a new AbsenceRecord with updated status.
     * Since this class is immutable, this method returns a new instance.
     * 
     * @param newStatus The new attendance status
     * @return A new AbsenceRecord with the updated status
     */
    public AbsenceRecord withStatus(AttendanceStatus newStatus) {
        return new AbsenceRecord(this.date, this.student, newStatus, this.notes);
    }
    
    /**
     * Returns a formatted string representation of the absence record.
     * 
     * @return Formatted string with date, student name, status, and notes
     */
    @Override
    public String toString() {
        String noteInfo = notes.isEmpty() ? "" : ", Notes: '" + notes + "'";
        return String.format("AbsenceRecord[Date: %s, Student: %s, Status: %s%s]",
                date, student.getName(), status.getDisplayName(), noteInfo);
    }
    
    /**
     * Checks if this AbsenceRecord is equal to another object.
     * Equality is based on date and student ID.
     * 
     * @param obj The object to compare with
     * @return true if objects are equal (same date and student), false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbsenceRecord that = (AbsenceRecord) obj;
        return Objects.equals(date, that.date) && 
               Objects.equals(student.getId(), that.student.getId());
    }
    
    /**
     * Generates hash code based on date and student ID.
     * 
     * @return Hash code of the absence record
     */
    @Override
    public int hashCode() {
        return Objects.hash(date, student.getId());
    }
    
    /**
     * Creates a summary string suitable for logging or display.
     * 
     * @return A summary of the absence record
     */
    public String toSummaryString() {
        return String.format("%s - %s: %s", 
                date, student.getName(), status.getDisplayName());
    }
    
    /**
     * Creates a detailed string with all information.
     * 
     * @return Detailed string representation
     */
    public String toDetailedString() {
        return String.format("Date: %s%nStudent: %s (ID: %s)%nStatus: %s%nParent Email: %s%nNotes: %s",
                date, student.getName(), student.getId(), status.getDisplayName(),
                student.getParentEmail(), notes.isEmpty() ? "No notes" : notes);
    }
}