package com.example.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a student's enrollment record.
 * This class includes a static factory method for creating new Enrollment instances.
 */
public class Enrollment {
    private String enrollmentId;
    private String studentId;
    private Date dateAccepted;
    private String acceptedBy; // e.g., the ID of the administrator who accepted the enrollment

    /**
     * Private constructor to enforce creation via the static factory method.
     * @param enrollmentId The unique ID for this enrollment record.
     * @param studentId The ID of the student being enrolled.
     * @param dateAccepted The date when the enrollment was accepted.
     * @param acceptedBy The ID of the administrator who accepted the enrollment.
     */
    private Enrollment(String enrollmentId, String studentId, Date dateAccepted, String acceptedBy) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.dateAccepted = dateAccepted;
        this.acceptedBy = acceptedBy;
    }

    /**
     * Static factory method to create a new Enrollment instance.
     * The sequence diagram specifies `create(generateEnrollmentId(), newStudent.getStudentId(), currentDate(), administratorId)`.
     * @param enrollmentId The unique ID for the enrollment.
     * @param studentId The ID of the student associated with this enrollment.
     * @param dateAccepted The date this enrollment was accepted.
     * @param acceptedBy The ID of the administrator who accepted it.
     * @return A new Enrollment object.
     */
    public static Enrollment create(String enrollmentId, String studentId, Date dateAccepted, String acceptedBy) {
        System.out.println("Creating new Enrollment: ID=" + enrollmentId + ", Student ID=" + studentId + ", Accepted by=" + acceptedBy);
        return new Enrollment(enrollmentId, studentId, dateAccepted, acceptedBy);
    }

    // --- Getters ---

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getDateAccepted() {
        return dateAccepted;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    // For debugging or logging purposes
    @Override
    public String toString() {
        return "Enrollment{" +
               "enrollmentId='" + enrollmentId + '\'' +
               ", studentId='" + studentId + '\'' +
               ", dateAccepted=" + dateAccepted +
               ", acceptedBy='" + acceptedBy + '\'' +
               '}';
    }
}