'''
Represents a student in the system.
Contains student information and academic details.
'''
package com.chatdev.reportcardsystem.model;
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String className;
    private String academicYear;
    public Student(String studentId, String firstName, String lastName,
                   String className, String academicYear) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.academicYear = academicYear;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    public String getClassName() {
        return className;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    @Override
    public String toString() {
        return studentId + " - " + getFullName();
    }
}