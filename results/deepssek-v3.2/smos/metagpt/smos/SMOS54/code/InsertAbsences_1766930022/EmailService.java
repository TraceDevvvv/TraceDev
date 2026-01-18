import java.util.Random;

/**
 * EmailService simulates sending absence notification emails to parents.
 * Handles connection interruptions to the SMTP server.
 */
public class EmailService {
    private static final String SMTP_SERVER = "SMOS_SERVER"; // Simulated SMTP server
    private boolean serverConnected = true;
    private Random random = new Random();

    /**
     * Constructor for EmailService.
     * Initializes connection to the SMTP server.
     */
    public EmailService() {
        System.out.println("[EmailService] Initializing connection to " + SMTP_SERVER + "...");
        // Simulate server connection - 90% chance of successful connection
        serverConnected = random.nextDouble() > 0.1; // 10% chance of connection failure
        if (!serverConnected) {
            System.out.println("[EmailService] WARNING: Connection to " + SMTP_SERVER + " failed!");
        } else {
            System.out.println("[EmailService] Connected to " + SMTP_SERVER + " successfully.");
        }
    }

    /**
     * Sends an absence notification email to the parent of a student.
     * 
     * @param studentId The ID of the absent student
     * @param studentName The name of the absent student
     * @param parentEmail The email address of the parent
     * @return true if email was sent successfully, false otherwise
     * @throws EmailException if connection to SMTP server is interrupted
     */
    public boolean sendAbsenceNotification(int studentId, String studentName, String parentEmail) throws EmailException {
        if (!serverConnected) {
            throw new EmailException("Connection to " + SMTP_SERVER + " interrupted. Cannot send email.");
        }

        // Simulate occasional connection drops during email sending (5% chance)
        if (random.nextDouble() < 0.05) {
            serverConnected = false;
            throw new EmailException("Connection to " + SMTP_SERVER + " lost during email transmission.");
        }

        // Simulate email sending process
        System.out.println("[EmailService] Sending absence notification for student: " + studentName + " (ID: " + studentId + ")");
        System.out.println("[EmailService] Recipient: " + parentEmail);
        
        // Simulate email content
        String subject = "Absence Notification for " + studentName;
        String body = "Dear Parent,\n\nYour child " + studentName + " (ID: " + studentId + 
                     ") was marked absent today.\n\nPlease contact the school if you have any questions.\n\nBest regards,\nATA School";
        
        // Simulate sending delay
        try {
            Thread.sleep(100); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new EmailException("Email sending interrupted by user.", e);
        }

        // Simulate 95% success rate for email delivery
        boolean success = random.nextDouble() > 0.05;
        
        if (success) {
            System.out.println("[EmailService] ✓ Email sent successfully to " + parentEmail);
        } else {
            System.out.println("[EmailService] ✗ Failed to send email to " + parentEmail + " (delivery failed)");
        }
        
        return success;
    }

    /**
     * Checks if the SMTP server connection is currently active.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isServerConnected() {
        return serverConnected;
    }

    /**
     * Attempts to reconnect to the SMTP server.
     * 
     * @return true if reconnection successful, false otherwise
     */
    public boolean reconnect() {
        System.out.println("[EmailService] Attempting to reconnect to " + SMTP_SERVER + "...");
        // Simulate reconnection - 70% chance of success
        serverConnected = random.nextDouble() > 0.3;
        if (serverConnected) {
            System.out.println("[EmailService] ✓ Reconnected to " + SMTP_SERVER + " successfully.");
        } else {
            System.out.println("[EmailService] ✗ Failed to reconnect to " + SMTP_SERVER);
        }
        return serverConnected;
    }

    /**
     * Custom exception for email-related errors.
     */
    public static class EmailException extends Exception {
        public EmailException(String message) {
            super(message);
        }

        public EmailException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}