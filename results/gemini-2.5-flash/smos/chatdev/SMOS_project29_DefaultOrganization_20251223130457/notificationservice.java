'''
Service class for sending notifications, simulating email communication.
In a real-world application, this would interact with an actual email sending API
(e.g., JavaMail API, SendGrid, etc.). For this example, it prints to the console.
'''
package com.chatdev.absencetracker.service;
import com.chatdev.absencetracker.model.Student;
import java.time.LocalDate;
public class NotificationService {
    '''
    Simulates sending a notification email to a student's parent regarding
    absence or delay.
    @param student The student for whom the notification is being sent.
    @param date The date of the absence or delay.
    @param isAbsent True if the student was absent.
    @param isDelayed True if the student was delayed.
    '''
    public void sendNotification(Student student, LocalDate date, boolean isAbsent, boolean isDelayed) {
        String subject = "";
        String body = "";
        // Construct email subject and body based on absence and/or delay status
        if (isAbsent && isDelayed) {
            subject = "Absence and Delay Notification for " + student.getName();
            body = String.format("Dear Parent,\n\nThis is to inform you that your child, %s (ID: %s), was absent and also delayed on %s.\n\nPlease contact the school if you have any questions.\n\nSincerely,\nSchool Administration",
                                 student.getName(), student.getId(), date.toString());
        } else if (isAbsent) {
            subject = "Absence Notification for " + student.getName();
            body = String.format("Dear Parent,\n\nThis is to inform you that your child, %s (ID: %s), was absent on %s.\n\nPlease contact the school if you have any questions.\n\nSincerely,\nSchool Administration",
                                 student.getName(), student.getId(), date.toString());
        } else if (isDelayed) {
            subject = "Delay Notification for " + student.getName();
            body = String.format("Dear Parent,\n\nThis is to inform you that your child, %s (ID: %s), was delayed on %s.\n\nPlease contact the school if you have any questions.\n\nSincerely,\nSchool Administration",
                                 student.getName(), student.getId(), date.toString());
        } else {
            // If neither absent nor delayed, no notification needs to be sent.
            // This case might be hit if the system calls with an entry that was previously absent/delayed
            // but is now marked present.
            System.out.println("No notification sent for " + student.getName() + " on " + date + " as they are marked present.");
            return;
        }
        // Simulate sending email by printing to console
        System.out.println("--- SIMULATING EMAIL SEND ---");
        System.out.println("To: " + student.getParentEmail());
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
        System.out.println("-----------------------------\n");
    }
}