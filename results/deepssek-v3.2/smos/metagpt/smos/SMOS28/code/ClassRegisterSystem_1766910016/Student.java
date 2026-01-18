import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Student class representing a student in the class register system.
 * Tracks attendance, status, arrival time, and notes for each day.
 * 
 * This class is part of the ViewDetailsSingleRegister use case implementation.
 */
public class Student {
    private final String name;
    private final String studentId;
    private final Map<LocalDate, AttendanceRecord> dailyAttendance;
    private final Map<LocalDate, String> dailyNotes;
    
    /**
     * Constructor to create a new Student
     * @param name The student's full name
     * @param studentId The unique student identifier
     */
    public Student(String name, String studentId) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        
        this.name = name.trim();
        this.studentId = studentId.trim();
        this.dailyAttendance = new HashMap<>();
        this.dailyNotes = new HashMap<>();
    }
    
    /**
     * Sets the attendance information for a specific date
     * @param date The date of attendance
     * @param status The attendance status (PRESENT, ABSENT, LATE)
     * @param arrivalTime The arrival time (null if absent)
     * @param notes Additional notes about the attendance
     */
    public void setAttendance(LocalDate date, AttendanceStatus status, LocalTime arrivalTime, String notes) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Attendance status cannot be null");
        }
        
        // Validate arrival time based on status
        if ((status == AttendanceStatus.PRESENT || status == AttendanceStatus.LATE) && arrivalTime == null) {
            throw new IllegalArgumentException("Arrival time cannot be null for present or late status");
        }
        if (status == AttendanceStatus.ABSENT && arrivalTime != null) {
            throw new IllegalArgumentException("Arrival time must be null for absent status");
        }
        
        // Create or update attendance record
        AttendanceRecord record = new AttendanceRecord(date, status, arrivalTime);
        dailyAttendance.put(date, record);
        
        // Store notes if provided
        if (notes != null && !notes.trim().isEmpty()) {
            dailyNotes.put(date, notes.trim());
        } else if (notes != null) {
            // Remove existing notes if empty string is provided
            dailyNotes.remove(date);
        }
    }
    
    /**
     * Gets the student's name
     * @return The student's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the student's ID
     * @return The student's ID
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Gets today's attendance status for this student
     * @return Today's attendance status, or ABSENT if no record exists
     */
    public AttendanceStatus getTodaysStatus() {
        LocalDate today = LocalDate.now();
        AttendanceRecord record = dailyAttendance.get(today);
        return (record != null) ? record.getStatus() : AttendanceStatus.ABSENT;
    }
    
    /**
     * Gets today's arrival time for this student
     * @return Today's arrival time, or null if absent or no record
     */
    public LocalTime getTodaysArrivalTime() {
        LocalDate today = LocalDate.now();
        AttendanceRecord record = dailyAttendance.get(today);
        return (record != null) ? record.getArrivalTime() : null;
    }
    
    /**
     * Gets today's notes for this student
     * @return Today's notes, or empty string if none
     */
    public String getTodaysNotes() {
        LocalDate today = LocalDate.now();
        return dailyNotes.getOrDefault(today, "");
    }
    
    /**
     * Gets attendance status for a specific date
     * @param date The date to check
     * @return Attendance status for the date, or ABSENT if no record exists
     */
    public AttendanceStatus getStatusForDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        AttendanceRecord record = dailyAttendance.get(date);
        return (record != null) ? record.getStatus() : AttendanceStatus.ABSENT;
    }
    
    /**
     * Gets arrival time for a specific date
     * @param date The date to check
     * @return Arrival time for the date, or null if absent or no record
     */
    public LocalTime getArrivalTimeForDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        AttendanceRecord record = dailyAttendance.get(date);
        return (record != null) ? record.getArrivalTime() : null;
    }
    
    /**
     * Gets notes for a specific date
     * @param date The date to check
     * @return Notes for the date, or empty string if none
     */
    public String getNotesForDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        return dailyNotes.getOrDefault(date, "");
    }
    
    /**
     * Checks if the student has attendance record for a specific date
     * @param date The date to check
     * @return true if attendance record exists, false otherwise
     */
    public boolean hasAttendanceRecord(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        return dailyAttendance.containsKey(date);
    }
    
    /**
     * Adds or updates notes for a specific date
     * @param date The date for the notes
     * @param notes The notes to add/update
     */
    public void setNotes(LocalDate date, String notes) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        if (notes != null && !notes.trim().isEmpty()) {
            dailyNotes.put(date, notes.trim());
        } else if (notes != null) {
            // Remove existing notes if empty string is provided
            dailyNotes.remove(date);
        }
    }
    
    /**
     * Gets the attendance record for a specific date
     * @param date The date to get record for
     * @return AttendanceRecord for the date, or null if no record exists
     */
    public AttendanceRecord getAttendanceRecord(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        
        return dailyAttendance.get(date);
    }
    
    /**
     * Returns a string representation of the student
     * @return String containing student name and ID
     */
    @Override
    public String toString() {
        return "Student{name='" + name + "', id='" + studentId + "'}";
    }
}