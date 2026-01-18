package com.example.notification;

import com.example.application.ports.EmailService;
import com.example.domain.Student;
import com.example.domain.events.AbsenceModifiedEvent;

/**
 * Handler that sends email notifications when an absence is modified.
 */
public class EmailNotificationHandler {
    private EmailService emailService;

    public EmailNotificationHandler(EmailService emailService) {
        this.emailService = emailService;
    }

    public void handle(AbsenceModifiedEvent event) {
        // In a complete implementation we would retrieve the student via repository.
        // For simplicity we assume the student is passed or available.
        // Here we simulate retrieving parent email.
        // This handler expects the student to be provided externally (via constructor or method).
        // We'll simulate email sending.
        String to = "parent@example.com"; // would be fetched from student
        String subject = "Absence Modified";
        String body = String.format("Absence %s for student %s changed from %s to %s on %s",
                event.getAbsenceId(), event.getStudentId(),
                event.getOldStatus(), event.getNewStatus(), event.getDate());
        boolean success = emailService.sendEmail(to, subject, body);
        if (success) {
            System.out.println("EmailNotificationHandler: email sent to " + to);
        } else {
            System.out.println("EmailNotificationHandler: email sending failed.");
        }
    }

    // New method for sequence diagram message
    public void getParentEmailFromStudent(Student student) {
        System.out.println("EmailNotificationHandler: get parent email from student " + student.getId());
    }
}