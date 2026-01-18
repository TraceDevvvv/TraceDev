'''
A concrete implementation of EmailService that simulates sending emails by printing to the console.
This is useful for development and testing without needing a real email server.
'''
public class ConsoleEmailService implements EmailService {
    /**
     * Simulates sending an email by printing out the recipient, subject, and body to the console.
     *
     * @param recipientEmail The email address of the recipient.
     * @param subject The subject line of the email.
     * @param body The main content/body of the email.
     * @return Always returns true to indicate a simulated successful sending.
     */
    @Override
    public boolean sendNotification(String recipientEmail, String subject, String body) {
        System.out.println("--- SIMULATING EMAIL NOTIFICATION ---");
        System.out.println("To: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
        System.out.println("-------------------------------------\n");
        // In a real application, this would involve connecting to an SMTP server
        // and handling potential exceptions. For this use case, we just simulate.
        return true;
    }
}