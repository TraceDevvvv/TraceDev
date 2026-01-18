package com.example.domain;

public class Student {
    private String studentId;
    private String parentEmail;

    public Student(String studentId, String parentEmail) {
        this.studentId = studentId;
        this.parentEmail = parentEmail;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
}