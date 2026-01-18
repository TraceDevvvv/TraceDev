package com.example.dto;

/**
 * Data Transfer Object for teaching details.
 */
public class TeachingDetailsDTO {
    private String courseName;
    private String instructorName;
    private String schedule;
    private String location;
    private int studentCount;

    public TeachingDetailsDTO(String courseName, String instructorName, String schedule, String location, int studentCount) {
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.schedule = schedule;
        this.location = location;
        this.studentCount = studentCount;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getLocation() {
        return location;
    }

    public int getStudentCount() {
        return studentCount;
    }
}