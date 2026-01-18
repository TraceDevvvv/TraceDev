package com.example.studentdelay.domain;

/**
 * Service for handling notifications, e.g., to parents about student delays.
 * This is a mock implementation for demonstration purposes.
 */
public class NotificationService {

    /**
     * Sends a notification to the parent of the specified student regarding a delay.
     *
     * @param student The student involved in the delay.
     * @param delayRecord The delay record details.
     */
    public void sendParentNotification(Student student, DelayRecord delayRecord) {
        // In a real application, this would integrate with an email or SMS service.
        System.out.println("Notification Service: Sending parent notification for student " + student.getName() +
                           " (" + student.getStudentId() + ") to " + student.getParentContactInfo() +
                           ". Reason: " + delayRecord.getReason() + " on " + delayRecord.getDelayDate());
        // Simulate sending a notification.
    }
}