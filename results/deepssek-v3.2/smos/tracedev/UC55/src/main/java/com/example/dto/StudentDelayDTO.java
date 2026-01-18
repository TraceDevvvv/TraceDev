package com.example.dto;

/**
 * Data Transfer Object for student delay information.
 * Contains student ID and their delay time.
 */
public class StudentDelayDTO {
    private String studentId;
    private int delayTime;

    public StudentDelayDTO(String studentId, int delayTime) {
        this.studentId = studentId;
        this.delayTime = delayTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}