package com.example.entity;

/**
 * Contact information for a student's parent.
 */
public class ParentContact {
    private String parentId;
    private String email;
    private String phone;
    private String studentId;

    public ParentContact() {}

    public ParentContact(String parentId, String email, String phone, String studentId) {
        this.parentId = parentId;
        this.email = email;
        this.phone = phone;
        this.studentId = studentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}