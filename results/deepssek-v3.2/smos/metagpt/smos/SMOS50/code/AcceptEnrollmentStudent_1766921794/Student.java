package com.smos.enrollment;

import java.io.Serializable;

/**
 * Student class representing a student in the enrollment system
 * Contains personal information and enrollment status
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean isActive;
    private String enrollmentStatus; // PENDING, ACCEPTED, REJECTED
    private String registrationDate;
    
    /**
     * Default constructor
     */
    public Student() {
        this.isActive = false;
        this.enrollmentStatus = "PENDING";
    }
    
    /**
     * Parameterized constructor for creating a new student
     */
    public Student(String studentId, String firstName, String lastName, 
                   String email, String phoneNumber, String registrationDate) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.isActive = false;
        this.enrollmentStatus = "PENDING";
    }
    
    // Getters and Setters
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }
    
    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }
    
    public String getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    /**
     * Activates the student account
     * Changes enrollment status to ACCEPTED and sets active flag to true
     */
    public void activateStudent() {
        this.isActive = true;
        this.enrollmentStatus = "ACCEPTED";
    }
    
    /**
     * Rejects the student enrollment
     * Sets enrollment status to REJECTED
     */
    public void rejectStudent() {
        this.enrollmentStatus = "REJECTED";
        this.isActive = false;
    }
    
    /**
     * Checks if the student enrollment is pending approval
     * @return true if status is PENDING, false otherwise
     */
    public boolean isPending() {
        return "PENDING".equals(enrollmentStatus);
    }
    
    /**
     * Checks if the student enrollment has been accepted
     * @return true if status is ACCEPTED, false otherwise
     */
    public boolean isAccepted() {
        return "ACCEPTED".equals(enrollmentStatus);
    }
    
    /**
     * Checks if the student enrollment has been rejected
     * @return true if status is REJECTED, false otherwise
     */
    public boolean isRejected() {
        return "REJECTED".equals(enrollmentStatus);
    }
    
    @Override
    public String toString() {
        return "Student ID: " + studentId + 
               "\nName: " + getFullName() + 
               "\nEmail: " + email + 
               "\nPhone: " + phoneNumber + 
               "\nStatus: " + enrollmentStatus + 
               "\nActive: " + (isActive ? "Yes" : "No") + 
               "\nRegistration Date: " + registrationDate;
    }
}