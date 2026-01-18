/**
 * Represents the detailed view of a class register for a specific date.
 * Contains a map of student ID to their attendance entry for that day.
 * Note: The attendanceEntries map is created as unmodifiable in the constructor,
 * but for internal updates within RegisterService, a mutable map can be used
 * for persistence simulation before constructing a new ClassRegisterDetails.
 */
package com.chatdev.register.model;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class ClassRegisterDetails {
    private String registerId;
    private LocalDate date;
    private Map<String, AttendanceEntry> attendanceEntries; // Key: studentId
    /**
     * Constructs a new ClassRegisterDetails object.
     *
     * @param registerId The unique ID of the class register.
     * @param date The date for which these details are valid.
     * @param attendanceEntries A map containing attendance entries for each student.
     */
    public ClassRegisterDetails(String registerId, LocalDate date, Map<String, AttendanceEntry> attendanceEntries) {
        this.registerId = registerId;
        this.date = date;
        // Create an unmodifiable map to prevent external modification
        // Internally, RegisterService will manage mutable map and recreate this object.
        this.attendanceEntries = Collections.unmodifiableMap(new HashMap<>(attendanceEntries));
    }
    /**
     * Returns the unique ID of the class register.
     * @return The register ID.
     */
    public String getRegisterId() {
        return registerId;
    }
    /**
     * Returns the date for which these details are applicable.
     * @return The LocalDate object for the register.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Returns an unmodifiable map of attendance entries.
     * The key is the student ID and the value is the AttendanceEntry object.
     * @return An unmodifiable map of attendance entries.
     */
    public Map<String, AttendanceEntry> getAttendanceEntries() {
        return attendanceEntries;
    }
    /**
     * Retrieves a specific attendance entry by student ID.
     * @param studentId The ID of the student.
     * @return The AttendanceEntry for the specified student, or null if not found.
     */
    public AttendanceEntry getAttendanceEntryForStudent(String studentId) {
        return attendanceEntries.get(studentId);
    }
}