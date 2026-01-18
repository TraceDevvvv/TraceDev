import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
/**
 * Service for sending email notifications to parents about absence changes.
 */
public class EmailService {
    private String smtpHost;
    private String smtpPort;
    private String username;
    private String password;
    public EmailService(String smtpHost, String smtpPort, String username, String password) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
    }
    /**
     * Send email notification to parent about absence change.
     */
    public void sendAbsenceChangeNotification(String parentEmail, String studentName, 
                                             String date, String changeType) throws CustomException {
        try {
            // For demo purposes, simulate email sending without actual SMTP
            System.out.println("\n[EMAIL SIMULATION]");
            System.out.println("To: " + parentEmail);
            System.out.println("Subject: Absence Update for " + studentName);
            System.out.println("Body: Dear Parent,\nThis is to notify you that the absence record for " + 
                             studentName + " on " + date + " has been " + changeType + ".\nSincerely,\nSchool Administration");
            System.out.println("[END EMAIL SIMULATION]\n");
            // In a real implementation, you would use actual SMTP:
            /*
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(parentEmail));
            message.setSubject("Absence Update for " + studentName);
            message.setText("Dear Parent,\n\nThis is to notify you that the absence record for " + 
                          studentName + " on " + date + " has been " + changeType + ".\n\nSincerely,\nSchool Administration");
            Transport.send(message);
            */
        } catch (Exception e) {
            throw new CustomException("Failed to send email notification", e);
        }
    }
}