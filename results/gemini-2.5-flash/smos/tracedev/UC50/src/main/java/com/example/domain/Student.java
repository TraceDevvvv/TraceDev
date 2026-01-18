package com.example.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a student in the system.
 * This class includes a static factory method for creating new Student instances.
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private Date enrollmentDate;

    /**
     * Private constructor to enforce creation via the static factory method.
     * @param studentId The unique ID for the student.
     * @param name The student's name.
     * @param email The student's email address.
     * @param enrollmentDate The date when the student was enrolled.
     */
    private Student(String studentId, String name, String email, Date enrollmentDate) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * Static factory method to create a new Student instance.
     * The sequence diagram specifies `create(generateStudentId(), registrationRequest.getStudentName(), registrationRequest.getStudentEmail(), currentDate())`.
     * @param studentId The unique ID for the student.
     * @param name The student's name.
     * @param email The student's email.
     * @param enrollmentDate The date of enrollment.
     * @return A new Student object.
     */
    public static Student create(String studentId, String name, String email, Date enrollmentDate) {
        System.out.println("Creating new Student: ID=" + studentId + ", Name=" + name);
        return new Student(studentId, name, email, enrollmentDate);
    }

    // --- Getters ---

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    // For debugging or logging purposes
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", enrollmentDate=" + enrollmentDate +
               '}';
    }
}