'''
Interface for sending email notifications.
This allows for different implementations of email sending (e.g., real email, console output, mock).
'''
public interface EmailService {
    /**
     * Sends an email notification to the specified recipient with the given subject and body.
     *
     * @param recipientEmail The email address of the recipient.
     * @param subject The subject line of the email.
     * @param body The main content/body of the email.
     * @return true if the email was "sent" successfully, false otherwise (e.g., invalid recipient).
     */
    boolean sendNotification(String recipientEmail, String subject, String body);
}