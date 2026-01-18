package com.example.dto;

import java.util.List;

/**
 * Data Transfer Object for Teacher details.
 */
public class TeacherDetailsDTO {
    private String teacherId;
    private String teacherName;
    private List<TeachingAssignmentDTO> currentAssignments;

    public TeacherDetailsDTO() {}

    public TeacherDetailsDTO(String teacherId, String teacherName, List<TeachingAssignmentDTO> currentAssignments) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.currentAssignments = currentAssignments;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<TeachingAssignmentDTO> getCurrentAssignments() {
        return currentAssignments;
    }

    public void setCurrentAssignments(List<TeachingAssignmentDTO> currentAssignments) {
        this.currentAssignments = currentAssignments;
    }
}