package com.absenceapp;

/**
 * Concrete implementation of INotificationService for sending email notifications.
 */
public class EmailNotificationService implements INotificationService {
    private Object emailClient; // Simulated email client

    public EmailNotificationService() {
        this.emailClient = new Object(); // Initialize simulated client
    }

    /**
     * Sends a notification about an absence modification to the parent via email.
     *
     * @param student The student related to the absence.
     * @param parentEmail The email address of the parent.
     * @param absence The absence record that was modified.
     */
    @Override
    public void sendAbsenceModificationNotification(Student student, String parentEmail, Absence absence) {
        System.out.println("Notifier: Sending email notification for student " + student.getName() + " to " + parentEmail);
        System.out.println("Notifier: Absence ID: " + absence.getId() + ", Type: " + absence.getType() + ", Status: " + absence.getStatus());
        System.out.println("Notifier: Email content: 'Dear parent, your child " + student.getName() + " has an absence on " + absence.getDate() + " with type " + absence.getType() + " and status " + absence.getStatus() + ". Reason: " + absence.getReason() + "'");
        // Simulate email sending delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Notifier: Email sent successfully.");
    }
}