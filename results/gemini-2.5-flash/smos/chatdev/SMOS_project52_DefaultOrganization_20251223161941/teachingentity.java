/**
 * Represents a 'teaching' entity in the system.
 */
package com.chatdev.entities;
public class TeachingEntity {
    private String teachingId;
    private String courseName;
    private String instructor;
    private String semester;
    // Constructor
    public TeachingEntity(String teachingId, String courseName, String instructor, String semester) {
        this.teachingId = teachingId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.semester = semester;
    }
    // Getters
    public String getTeachingId() {
        return teachingId;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getInstructor() {
        return instructor;
    }
    public String getSemester() {
        return semester;
    }
    // Override toString() for display in UI components
    @Override
    public String toString() {
        return "Teaching ID: " + teachingId + ", Course: " + courseName + ", Instructor: " + instructor + ", Semester: " + semester;
    }
}