package com.example.dto;

import java.util.List;

/**
 * Data Transfer Object for updating teaching assignments.
 */
public class UpdateAssignmentsRequestDTO {
    private String teacherId;
    private String academicYear;
    private String classId;
    private List<String> assignmentsToAdd;
    private List<String> assignmentsToRemove;

    public UpdateAssignmentsRequestDTO() {}

    public UpdateAssignmentsRequestDTO(String teacherId, String academicYear, String classId,
                                       List<String> assignmentsToAdd, List<String> assignmentsToRemove) {
        this.teacherId = teacherId;
        this.academicYear = academicYear;
        this.classId = classId;
        this.assignmentsToAdd = assignmentsToAdd;
        this.assignmentsToRemove = assignmentsToRemove;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public List<String> getAssignmentsToAdd() {
        return assignmentsToAdd;
    }

    public void setAssignmentsToAdd(List<String> assignmentsToAdd) {
        this.assignmentsToAdd = assignmentsToAdd;
    }

    public List<String> getAssignmentsToRemove() {
        return assignmentsToRemove;
    }

    public void setAssignmentsToRemove(List<String> assignmentsToRemove) {
        this.assignmentsToRemove = assignmentsToRemove;
    }
}