package com.example.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID; // For generating unique IDs

/**
 * Represents an assignment of a specific teaching to a teacher.
 */
public class TeacherAssignment {
    private String id;
    private String teacherId;
    private String teachingId;
    private Date assignedDate;

    public TeacherAssignment(String teacherId, String teachingId) {
        this.id = UUID.randomUUID().toString(); // Generate unique ID
        this.teacherId = teacherId;
        this.teachingId = teachingId;
        this.assignedDate = new Date(); // Set current date upon creation
    }

    // Constructor with ID, useful for loading existing assignments
    public TeacherAssignment(String id, String teacherId, String teachingId, Date assignedDate) {
        this.id = id;
        this.teacherId = teacherId;
        this.teachingId = teachingId;
        this.assignedDate = assignedDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getTeachingId() {
        return teachingId;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    // Setters (if needed for updates, though not explicitly in diagram)
    public void setId(String id) {
        this.id = id;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeachingId(String teachingId) {
        this.teachingId = teachingId;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public String toString() {
        return "TeacherAssignment{" +
               "id='" + id + '\'' +
               ", teacherId='" + teacherId + '\'' +
               ", teachingId='" + teachingId + '\'' +
               ", assignedDate=" + assignedDate +
               '}';
    }

    // For comparison in collections, useful for distinguishing assignments
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherAssignment that = (TeacherAssignment) o;
        return Objects.equals(teacherId, that.teacherId) &&
               Objects.equals(teachingId, that.teachingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, teachingId);
    }
}