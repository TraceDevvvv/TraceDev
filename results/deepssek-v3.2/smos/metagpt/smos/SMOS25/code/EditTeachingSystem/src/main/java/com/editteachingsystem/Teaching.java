package com.editteachingsystem;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Teaching class representing a teaching entity in the system.
 * Contains properties, validation methods, and business logic for teaching management.
 */
public class Teaching {
    private String teachingId;
    private String courseCode;
    private String courseName;
    private String instructorId;
    private String instructorName;
    private String schedule;
    private String location;
    private int maxStudents;
    private int currentEnrollment;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String status; // ACTIVE, INACTIVE, CANCELLED
    
    // Validation patterns
    private static final Pattern TEACHING_ID_PATTERN = Pattern.compile("^TEA-[0-9]{6}$");
    private static final Pattern COURSE_CODE_PATTERN = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
    private static final Pattern SCHEDULE_PATTERN = Pattern.compile("^[MTWRFSU]+\\s+[0-9]{1,2}:[0-9]{2}\\s*-\\s*[0-9]{1,2}:[0-9]{2}$");
    
    /**
     * Default constructor
     */
    public Teaching() {
        this.teachingId = "";
        this.courseCode = "";
        this.courseName = "";
        this.instructorId = "";
        this.instructorName = "";
        this.schedule = "";
        this.location = "";
        this.maxStudents = 0;
        this.currentEnrollment = 0;
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
        this.status = "ACTIVE";
    }
    
    /**
     * Parameterized constructor
     */
    public Teaching(String teachingId, String courseCode, String courseName, 
                   String instructorId, String instructorName, String schedule, 
                   String location, int maxStudents, int currentEnrollment, String status) {
        this.teachingId = teachingId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.schedule = schedule;
        this.location = location;
        this.maxStudents = maxStudents;
        this.currentEnrollment = currentEnrollment;
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
        this.status = (status != null) ? status : "ACTIVE";
    }
    
    // Getters and Setters with validation
    
    public String getTeachingId() {
        return teachingId;
    }
    
    public void setTeachingId(String teachingId) {
        if (validateTeachingId(teachingId)) {
            this.teachingId = teachingId;
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Invalid Teaching ID format. Expected format: TEA-XXXXXX");
        }
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        if (validateCourseCode(courseCode)) {
            this.courseCode = courseCode;
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Invalid Course Code format. Expected format: ABC123");
        }
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        if (courseName != null && !courseName.trim().isEmpty() && courseName.length() <= 100) {
            this.courseName = courseName.trim();
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Course name cannot be empty and must be less than 100 characters");
        }
    }
    
    public String getInstructorId() {
        return instructorId;
    }
    
    public void setInstructorId(String instructorId) {
        if (instructorId != null && !instructorId.trim().isEmpty() && instructorId.length() <= 20) {
            this.instructorId = instructorId.trim();
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Instructor ID cannot be empty and must be less than 20 characters");
        }
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public void setInstructorName(String instructorName) {
        if (instructorName != null && !instructorName.trim().isEmpty() && instructorName.length() <= 50) {
            this.instructorName = instructorName.trim();
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Instructor name cannot be empty and must be less than 50 characters");
        }
    }
    
    public String getSchedule() {
        return schedule;
    }
    
    public void setSchedule(String schedule) {
        if (validateSchedule(schedule)) {
            this.schedule = schedule;
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Invalid Schedule format. Expected format: MWF 9:00-10:30");
        }
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        if (location != null && !location.trim().isEmpty() && location.length() <= 50) {
            this.location = location.trim();
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Location cannot be empty and must be less than 50 characters");
        }
    }
    
    public int getMaxStudents() {
        return maxStudents;
    }
    
    public void setMaxStudents(int maxStudents) {
        if (maxStudents > 0 && maxStudents <= 500) {
            this.maxStudents = maxStudents;
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Max students must be between 1 and 500");
        }
    }
    
    public int getCurrentEnrollment() {
        return currentEnrollment;
    }
    
    public void setCurrentEnrollment(int currentEnrollment) {
        if (currentEnrollment >= 0 && currentEnrollment <= maxStudents) {
            this.currentEnrollment = currentEnrollment;
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Current enrollment must be between 0 and max students (" + maxStudents + ")");
        }
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        if (status != null && (status.equals("ACTIVE") || status.equals("INACTIVE") || status.equals("CANCELLED"))) {
            this.status = status;
            updateLastModified();
        } else {
            throw new IllegalArgumentException("Status must be one of: ACTIVE, INACTIVE, CANCELLED");
        }
    }
    
    /**
     * Updates the last modified timestamp
     */
    private void updateLastModified() {
        this.lastModifiedDate = LocalDateTime.now();
    }
    
    // Validation methods
    
    /**
     * Validates the teaching ID format
     * @param teachingId the teaching ID to validate
     * @return true if valid, false otherwise
     */
    public static boolean validateTeachingId(String teachingId) {
        return teachingId != null && TEACHING_ID_PATTERN.matcher(teachingId).matches();
    }
    
    /**
     * Validates the course code format
     * @param courseCode the course code to validate
     * @return true if valid, false otherwise
     */
    public static boolean validateCourseCode(String courseCode) {
        return courseCode != null && COURSE_CODE_PATTERN.matcher(courseCode).matches();
    }
    
    /**
     * Validates the schedule format
     * @param schedule the schedule to validate
     * @return true if valid, false otherwise
     */
    public static boolean validateSchedule(String schedule) {
        return schedule != null && !schedule.trim().isEmpty() && schedule.length() <= 50;
    }
    
    /**
     * Validates all teaching data for completeness and correctness
     * @return true if all data is valid, false otherwise
     */
    public boolean validateAllData() {
        try {
            if (!validateTeachingId(teachingId)) return false;
            if (!validateCourseCode(courseCode)) return false;
            if (courseName == null || courseName.trim().isEmpty()) return false;
            if (instructorId == null || instructorId.trim().isEmpty()) return false;
            if (instructorName == null || instructorName.trim().isEmpty()) return false;
            if (!validateSchedule(schedule)) return false;
            if (location == null || location.trim().isEmpty()) return false;
            if (maxStudents <= 0 || maxStudents > 500) return false;
            if (currentEnrollment < 0 || currentEnrollment > maxStudents) return false;
            if (!status.equals("ACTIVE") && !status.equals("INACTIVE") && !status.equals("CANCELLED")) return false;
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Creates a copy of this teaching object
     * @return a new Teaching object with the same data
     */
    public Teaching copy() {
        Teaching copy = new Teaching();
        copy.teachingId = this.teachingId;
        copy.courseCode = this.courseCode;
        copy.courseName = this.courseName;
        copy.instructorId = this.instructorId;
        copy.instructorName = this.instructorName;
        copy.schedule = this.schedule;
        copy.location = this.location;
        copy.maxStudents = this.maxStudents;
        copy.currentEnrollment = this.currentEnrollment;
        copy.createdDate = this.createdDate;
        copy.lastModifiedDate = LocalDateTime.now();
        copy.status = this.status;
        return copy;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Teaching ID: %s\n" +
            "Course: %s - %s\n" +
            "Instructor: %s (%s)\n" +
            "Schedule: %s\n" +
            "Location: %s\n" +
            "Enrollment: %d/%d\n" +
            "Status: %s\n" +
            "Last Modified: %s",
            teachingId, courseCode, courseName, instructorName, instructorId,
            schedule, location, currentEnrollment, maxStudents, status,
            lastModifiedDate.toString()
        );
    }
    
    /**
     * Returns a summary string for display in lists
     * @return summary string
     */
    public String toSummaryString() {
        return String.format("%s | %s - %s | %s | %s", 
            teachingId, courseCode, courseName, instructorName, status);
    }
}