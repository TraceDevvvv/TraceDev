package com.example.dto;

/**
 * Data Transfer Object for Student information.
 * Used to transfer student data between layers, including delay information.
 */
public class StudentDTO {
    private String studentId;
    private String studentName;
    private boolean isPresent;
    private int delayTime;

    public StudentDTO(String studentId, String studentName, boolean isPresent, int delayTime) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.isPresent = isPresent;
        this.delayTime = delayTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}