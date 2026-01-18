package com.system.school.notification;

import com.system.school.note.DisciplinaryNote;
import com.system.school.student.Student;

/**
 * Service responsible for sending notifications to student parents.
 * This class simulates the process of sending emails or SMS messages.
 */
public class NotificationService {

    /**
     * Sends a notification to the parents of a student regarding a disciplinary note.
     * This method simulates sending an email or SMS.
     *
     * @param student The student whose parents need to be notified.
     * @param note The disciplinary note that is being deleted or corrected.
     * @param messageType A string indicating the type of message, e.g., "Note Deletion", "Correction".
     * @return true if the notification was "sent" successfully, false otherwise.
     */
    public boolean sendParentNotification(Student student, DisciplinaryNote note, String messageType) {
        if (student == null) {
            System.err.println("Error: Cannot send notification. Student object is null.");
            return false;
        }
        if (note == null) {
            System.err.println("Error: Cannot send notification. DisciplinaryNote object is null.");
            return false;
        }
        if (messageType == null || messageType.trim().isEmpty()) {
            System.err.println("Error: Cannot send notification. Message type is null or empty.");
            return false;
        }

        String parentEmail = student.getParentEmail();
        String parentPhoneNumber = student.getParentPhoneNumber();

        if (parentEmail == null || parentEmail.trim().isEmpty()) {
            System.err.println("Warning: Student " + student.getFullName() + " (ID: " + student.getStudentId() + ") has no parent email. Cannot send email notification.");
        }
        if (parentPhoneNumber == null || parentPhoneNumber.trim().isEmpty()) {
            System.err.println("Warning: Student " + student.getFullName() + " (ID: " + student.getStudentId() + ") has no parent phone number. Cannot send SMS notification.");
        }

        // Simulate sending an email
        if (parentEmail != null && !parentEmail.trim().isEmpty()) {
            System.out.println("--- Sending Email Notification ---");
            System.out.println("To: " + parentEmail);
            System.out.println("Subject: Important: " + messageType + " Regarding " + student.getFullName());
            System.out.println("Dear Parent of " + student.getFullName() + ",");
            System.out.println("This is an automated notification from the school system.");
            System.out.println("Regarding the disciplinary note (ID: " + note.getNoteId() + ") issued on " + note.getDateIssued() + " for the reason: '" + note.getReason() + "'.");
            System.out.println("This note has been " + messageType.toLowerCase() + " from our records.");
            System.out.println("Please contact the school administration if you have any questions.");
            System.out.println("Sincerely,\nSchool Administration");
            System.out.println("----------------------------------");
        }

        // Simulate sending an SMS
        if (parentPhoneNumber != null && !parentPhoneNumber.trim().isEmpty()) {
            System.out.println("--- Sending SMS Notification ---");
            System.out.println("To: " + parentPhoneNumber);
            System.out.println("Message: School Alert: Disciplinary note for " + student.getFullName() + " (ID: " + note.getNoteId() + ") has been " + messageType.toLowerCase() + ". Contact school for details.");
            System.out.println("----------------------------------");
        }

        if ((parentEmail == null || parentEmail.trim().isEmpty()) && (parentPhoneNumber == null || parentPhoneNumber.trim().isEmpty())) {
            System.err.println("Error: No valid contact information (email or phone) found for parents of student " + student.getFullName() + " (ID: " + student.getStudentId() + "). Notification failed.");
            return false; // Indicate failure if no contact info was available
        }

        System.out.println("Notification for student " + student.getFullName() + " regarding note " + note.getNoteId() + " " + messageType.toLowerCase() + " simulated successfully.");
        return true; // Simulate success if at least one notification attempt was made
    }
}