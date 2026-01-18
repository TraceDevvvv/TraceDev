package com.example.domain;

/**
 * Domain entity representing a teaching session.
 */
public class Teaching {
    private int id;
    private String courseName;
    private String instructorName;
    private String schedule;
    private String location;
    private int studentCount;

    public Teaching(int id, String courseName, String instructorName, String schedule, String location, int studentCount) {
        this.id = id;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.schedule = schedule;
        this.location = location;
        this.studentCount = studentCount;
    }

    public int getId() {
        return id;
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