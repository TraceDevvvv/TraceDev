/**
 * Service for sending email notifications to parents.
 * Includes both real email sending capability and simulation mode for demonstration.
 * IMPORTANT: To enable real email functionality (instead of simulation mode),
 * you need to add JavaMail API to your classpath.
 */
import java.util.Properties;
public class EmailService {
    private boolean useSimulationMode;
    /**
     * Constructor for EmailService.
     * @param useSimulationMode if true, simulates email sending without actual SMTP server
     */
    public EmailService(boolean useSimulationMode) {
        this.useSimulationMode = useSimulationMode;
    }
    /**
     * Default constructor - uses simulation mode for safe demonstration
     */
    public EmailService() {
        this(true); // Default to simulation mode for safety
    }
    /**
     * Sends an absence notification email to a student's parent.
     * If simulation mode is enabled, simulates the email sending process.
     * @param student the absent student
     * @throws Exception if there's an error sending the email and not in simulation mode
     */
    public void sendAbsenceNotification(Student student) throws Exception {
        if (useSimulationMode) {
            simulateSendAbsenceNotification(student);
            return;
        }
        // Try to send real email, but fall back to simulation if JavaMail is not available
        try {
            Class.forName("javax.mail.Session");
            sendRealEmailNotification(student);
        } catch (ClassNotFoundException e) {
            System.err.println("JavaMail API not found. Falling back to simulation mode.");
            simulateSendAbsenceNotification(student);
            throw new Exception(
                "Real email sending requires JavaMail API. " +
                "Please add javax.mail:javax.mail-api and com.sun.mail:javax.mail to your classpath, " +
                "or use simulation mode.",
                e
            );
        }
    }
    /**
     * Actually sends email using configured SMTP server.
     * This method uses reflection to avoid compilation errors if JavaMail is not available.
     * @param student the absent student
     * @throws Exception if there's an error sending the email
     */
    private void sendRealEmailNotification(Student student) throws Exception {
        // These should be configured from properties file or environment variables
        String host = System.getProperty("smtp.host", "smtp.example.com");
        String port = System.getProperty("smtp.port", "587");
        String username = System.getProperty("smtp.username", "");
        String password = System.getProperty("smtp.password", "");
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalStateException(
                "SMTP credentials not configured. Please set smtp.username and smtp.password system properties."
            );
        }
        String to = student.getParentEmail();
        String from = username;
        String subject = "Absence Notification: " + student.getFullName();
        String body = createEmailBody(student);
        // Use reflection to avoid compilation dependency on javax.mail
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.connectiontimeout", "5000");
            props.put("mail.smtp.timeout", "5000");
            // Create session using reflection
            Class<?> sessionClass = Class.forName("javax.mail.Session");
            Class<?> authenticatorClass = Class.forName("javax.mail.Authenticator");
            Class<?> passwordAuthClass = Class.forName("javax.mail.PasswordAuthentication");
            Object authenticator = authenticatorClass.getDeclaredConstructor().newInstance();
            Object passwordAuth = passwordAuthClass.getDeclaredConstructor(String.class, String.class)
                .newInstance(username, password);
            java.lang.reflect.Method getInstanceMethod = sessionClass.getMethod("getInstance", 
                Properties.class, authenticatorClass);
            Object session = getInstanceMethod.invoke(null, props, authenticator);
            // Create and send message using reflection
            Class<?> messageClass = Class.forName("javax.mail.Message");
            Class<?> mimeMessageClass = Class.forName("javax.mail.internet.MimeMessage");
            Class<?> addressClass = Class.forName("javax.mail.internet.InternetAddress");
            Class<?> transportClass = Class.forName("javax.mail.Transport");
            Object message = mimeMessageClass.getConstructor(sessionClass).newInstance(session);
            // Set sender
            java.lang.reflect.Method setFromMethod = messageClass.getMethod("setFrom", addressClass);
            Object fromAddress = addressClass.getConstructor(String.class).newInstance(from);
            setFromMethod.invoke(message, fromAddress);
            // Set recipient
            java.lang.reflect.Method setRecipientsMethod = messageClass.getMethod("setRecipients", 
                messageClass.getDeclaredClasses()[0], String.class);
            Object recipientType = messageClass.getDeclaredField("RECIPIENT_TYPE_TO").get(null);
            setRecipientsMethod.invoke(message, recipientType, to);
            // Set subject and body
            java.lang.reflect.Method setSubjectMethod = messageClass.getMethod("setSubject", String.class);
            java.lang.reflect.Method setTextMethod = messageClass.getMethod("setText", String.class);
            setSubjectMethod.invoke(message, subject);
            setTextMethod.invoke(message, body);
            // Send email
            java.lang.reflect.Method sendMethod = transportClass.getMethod("send", messageClass);
            sendMethod.invoke(null, message);
            System.out.println("Email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email to " + to + ": " + e.getMessage());
            throw new Exception("Failed to send email notification for " + student.getFullName() + 
                              ". Please check SMTP configuration and ensure JavaMail API is available.", e);
        }
    }
    /**
     * Creates the email body content for absence notification.
     * @param student the absent student
     * @return formatted email body
     */
    private String createEmailBody(Student student) {
        return String.format(
            "Dear Parent/Guardian,\n\n" +
            "This is to inform you that %s was marked as absent from %s today.\n\n" +
            "If this is unexpected or if you have any questions, please contact the school office.\n\n" +
            "Best regards,\n" +
            "ATA School System\n\n" +
            "Note: This is an automated notification. Please do not reply to this email.",
            student.getFullName(), student.getClassName()
        );
    }
    /**
     * Simulates sending an email notification for demonstration purposes.
     * @param student the absent student
     */
    private void simulateSendAbsenceNotification(Student student) {
        System.out.println("=== Email Notification (Simulation) ===");
        System.out.println("To: " + student.getParentEmail());
        System.out.println("Subject: Absence Notification: " + student.getFullName());
        System.out.println("Body:\n" + createEmailBody(student));
        System.out.println("=== Email Simulation Complete ===\n");
        try {
            Thread.sleep(300); // Shorter delay for better user experience
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Checks if email service is in simulation mode.
     * @return true if using simulation mode
     */
    public boolean isSimulationMode() {
        return useSimulationMode;
    }
    /**
     * Sets the simulation mode for email service.
     * @param useSimulationMode true to use simulation mode
     */
    public void setSimulationMode(boolean useSimulationMode) {
        this.useSimulationMode = useSimulationMode;
    }
}