/*
 * DOCSTRING: Simulates sending email notifications to parents.
 * This class prints email content to the console instead of actual sending,
 * and includes a simulated failure mechanism.
 */
package service;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.util.Random;
public class EmailSender {
    private static final double FAILURE_RATE = 0.05; // 5% chance of email sending failure
    private static final int SIMULATED_DELAY_MS = 300; // Simulate email sending delay
    private final Random random = new Random();
    /**
     * Custom exception for email sending issues.
     */
    public static class EmailSendingException extends Exception {
        public EmailSendingException(String message, Throwable cause) {
            super(message, cause);
        }
        public EmailSendingException(String message) {
            super(message);
        }
    }
    /**
     * Simulates sending an absence notification email to a parent.
     *
     * @param recipient The email address of the parent.
     * @param studentName The name of the student.
     * @param absenceDate The date of the absence.
     * @param reason The reason for the absence.
     * @param changeType The type of change (e.g., "added", "cancelled", "modified").
     * @throws EmailSendingException If a simulated email sending error occurs.
     */
    public void sendAbsenceNotification(String recipient, String studentName, LocalDate absenceDate, String reason, String changeType) throws EmailSendingException {
        if (recipient == null || recipient.trim().isEmpty()) {
            throw new EmailSendingException("Recipient email cannot be empty for notification.");
        }
        System.out.println("EmailSender: Attempting to send " + changeType + " absence notification to " + recipient + "...");
        try {
            // Simulate network latency for email server
            TimeUnit.MILLISECONDS.sleep(SIMULATED_DELAY_MS + random.nextInt(200));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new EmailSendingException("Email sending interrupted.", e);
        }
        // Simulate random email sending failure
        if (random.nextDouble() < FAILURE_RATE) {
            throw new EmailSendingException("Simulated email sending failed for " + recipient + ".");
        }
        String subject = String.format("Absence Notification for %s - %s on %s", studentName, changeType.toUpperCase(), absenceDate);
        String body = String.format(
            "Dear Parent,\n\n" +
            "This is a notification regarding your child, %s, on %s.\n" +
            "The absence for %s has been %s.\n" +
            "Reason: %s\n\n" +
            "If you have any questions, please contact the school administration.\n\n" +
            "Sincerely,\nSchool Administration",
            studentName, absenceDate, studentName, changeType, reason
        );
        System.out.println("\n--- SIMULATED EMAIL SENT ---");
        System.out.println("To: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
        System.out.println("----------------------------\n");
        System.out.println("EmailSender: Notification successfully sent to " + recipient + ".");
    }
}