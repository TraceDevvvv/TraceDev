import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * AttendanceRecord class representing attendance information for a specific date.
 * Tracks counts of students for present, absent, and late statuses.
 * 
 * This class is used to organize attendance data by date as required by the use case.
 */
public class AttendanceRecord {
    private final LocalDate date;
    private int presentCount;
    private int absentCount;
    private int lateCount;
    
    private AttendanceStatus status; // For individual student records
    private LocalTime arrivalTime;   // For individual student records
    
    /**
     * Constructor for creating an empty attendance record for a specific date.
     * Used for class-wide attendance summaries.
     * 
     * @param date The date for this attendance record
     */
    public AttendanceRecord(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.date = date;
        this.presentCount = 0;
        this.absentCount = 0;
        this.lateCount = 0;
    }
    
    /**
     * Constructor for creating an individual student attendance record.
     * Used when storing attendance for a specific student on a specific date.
     * 
     * @param date The date for this attendance record
     * @param status The attendance status of the student
     * @param arrivalTime The arrival time of the student (null if absent)
     */
    public AttendanceRecord(LocalDate date, AttendanceStatus status, LocalTime arrivalTime) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Attendance status cannot be null");
        }
        
        this.date = date;
        this.status = status;
        this.arrivalTime = arrivalTime;
        
        // Initialize counts to 0 (these will be updated by class-level record)
        this.presentCount = 0;
        this.absentCount = 0;
        this.lateCount = 0;
    }
    
    /**
     * Gets the date of this attendance record.
     * 
     * @return The date of the attendance record
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * Gets the count of present students.
     * 
     * @return The number of present students
     */
    public int getPresentCount() {
        return presentCount;
    }
    
    /**
     * Gets the count of absent students.
     * 
     * @return The number of absent students
     */
    public int getAbsentCount() {
        return absentCount;
    }
    
    /**
     * Gets the count of late students.
     * 
     * @return The number of late students
     */
    public int getLateCount() {
        return lateCount;
    }
    
    /**
     * Gets the attendance status for individual student records.
     * 
     * @return The attendance status, or null if this is a class-wide record
     */
    public AttendanceStatus getStatus() {
        return status;
    }
    
    /**
     * Gets the arrival time for individual student records.
     * 
     * @return The arrival time, or null if absent or class-wide record
     */
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    
    /**
     * Updates the attendance counts based on the status of all students.
     * This method is called by ClassRegister to maintain accurate counts.
     * 
     * @param students The list of all students in the class
     * @param date The date to update counts for
     */
    public void updateCounts(List<Student> students, LocalDate date) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (!this.date.equals(date)) {
            throw new IllegalArgumentException("Date parameter must match record date");
        }
        
        // Reset counts
        this.presentCount = 0;
        this.absentCount = 0;
        this.lateCount = 0;
        
        // Count attendance statuses
        for (Student student : students) {
            if (student.hasAttendanceRecord(date)) {
                AttendanceStatus studentStatus = student.getStatusForDate(date);
                switch (studentStatus) {
                    case PRESENT:
                        presentCount++;
                        break;
                    case ABSENT:
                        absentCount++;
                        break;
                    case LATE:
                        lateCount++;
                        break;
                }
            } else {
                // If no record exists, count as absent
                absentCount++;
            }
        }
    }
    
    /**
     * Increments the present count by 1.
     * Used when manually adjusting counts.
     */
    public void incrementPresent() {
        presentCount++;
    }
    
    /**
     * Increments the absent count by 1.
     * Used when manually adjusting counts.
     */
    public void incrementAbsent() {
        absentCount++;
    }
    
    /**
     * Increments the late count by 1.
     * Used when manually adjusting counts.
     */
    public void incrementLate() {
        lateCount++;
    }
    
    /**
     * Gets the total number of students recorded for this date.
     * 
     * @return The total number of students (present + absent + late)
     */
    public int getTotalCount() {
        return presentCount + absentCount + lateCount;
    }
    
    /**
     * Gets the attendance percentage for this date.
     * 
     * @return Percentage of students present (including late) as a double
     */
    public double getAttendancePercentage() {
        int total = getTotalCount();
        if (total == 0) {
            return 0.0;
        }
        return ((double)(presentCount + lateCount) / total) * 100.0;
    }
    
    /**
     * Checks if this attendance record has valid counts.
     * Valid means all counts are non-negative.
     * 
     * @return true if counts are valid, false otherwise
     */
    public boolean isValid() {
        return presentCount >= 0 && absentCount >= 0 && lateCount >= 0;
    }
    
    /**
     * Returns a string representation of the attendance record.
     * 
     * @return String containing date and counts for present, absent, and late students
     */
    @Override
    public String toString() {
        if (status != null) {
            // Individual student record
            return String.format("AttendanceRecord{date=%s, status=%s, arrivalTime=%s}", 
                date, status, arrivalTime);
        } else {
            // Class-wide record
            return String.format("AttendanceRecord{date=%s, present=%d, absent=%d, late=%d}", 
                date, presentCount, absentCount, lateCount);
        }
    }
    
    /**
     * Creates a summary string of the attendance record suitable for display.
     * 
     * @return Summary string with date and attendance statistics
     */
    public String toSummaryString() {
        int total = getTotalCount();
        double percentage = getAttendancePercentage();
        return String.format("%s: Present=%d, Absent=%d, Late=%d, Total=%d, Attendance=%.1f%%", 
            date, presentCount, absentCount, lateCount, total, percentage);
    }
}