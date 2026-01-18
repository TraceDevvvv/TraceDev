import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
/**
 * Handles sending email notifications to parents of absent students.
 * In a production environment, this would use proper SMTP configuration.
 */
public class EmailSender {
    private String smtpHost;
    private String smtpPort;
    private String username;
    private String password;
    private boolean useSSL;
    // Default constructor with sample configuration
    public EmailSender() {
        // These would ideally come from configuration files or environment variables
        this.smtpHost = "smtp.example.com";
        this.smtpPort = "587";
        this.username = "admin@school.edu";
        this.password = "password";
        this.useSSL = false;
    }
    /**
     * Sends an absence notification email to a parent.
     * @param toEmail Parent's email address
     * @param studentName Name of the absent student
     * @param date Date of absence
     * @return true if email was sent successfully, false otherwise
     */
    public boolean sendAbsenceNotification(String toEmail, String studentName, String date) {
        try {
            // Setup mail server properties
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", String.valueOf(useSSL));
            // Create session with authenticator
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Attendance Notification: " + studentName + " absent on " + date);
            String emailBody = "Dear Parent,\n\n" +
                    "This is to inform you that your child " + studentName + 
                    " was marked absent on " + date + ".\n\n" +
                    "If you believe this is an error, please contact the school administration.\n\n" +
                    "Regards,\nSchool Administration";
            message.setText(emailBody);
            // Send the email
            Transport.send(message);
            System.out.println("Email successfully sent to: " + toEmail);
            return true;
        } catch (MessagingException e) {
            System.err.println("Failed to send email to " + toEmail + ": " + e.getMessage());
            // In a real application, you might want to log this error or retry
            return false;
        }
    }
    /**
     * Simulated email sender for demonstration purposes (when SMTP isn't configured).
     * This is what's currently being used in AttendanceGUI.
     */
    public boolean sendSimulatedNotification(String toEmail, String studentName, String date) {
        System.out.println("=== SIMULATED EMAIL NOTIFICATION ===");
        System.out.println("To: " + toEmail);
        System.out.println("Subject: Attendance Notification: " + studentName + " absent on " + date);
        System.out.println("Body: Dear Parent,\nYour child " + studentName + 
                          " was marked absent on " + date + ".\n\nSchool Administration");
        System.out.println("=== END SIMULATION ===");
        return true;
    }
}