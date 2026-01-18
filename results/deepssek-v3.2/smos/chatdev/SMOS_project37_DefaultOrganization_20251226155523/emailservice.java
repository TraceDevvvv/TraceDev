/**
 * Service for sending email notifications to parents
 * Simulates email sending functionality
 */
public class EmailService {
    /**
     * Sends a notification email to the parent regarding a disciplinary note
     * @param note The disciplinary note to notify about
     */
    public void sendNotificationToParent(Note note) {
        String parentEmail = note.getParentEmail();
        String subject = "Disciplinary Note: " + note.getStudent();
        String body = "Dear Parent/Guardian,\n\n" +
                     "This is to inform you that a disciplinary note has been issued for " + 
                     note.getStudent() + ".\n\n" +
                     "Details:\n" +
                     "Date: " + note.getDate() + "\n" +
                     "Teacher: " + note.getTeacher() + "\n" +
                     "Description: " + note.getDescription() + "\n\n" +
                     "Please contact the school administration if you have any questions.\n\n" +
                     "Sincerely,\nSchool Administration";
        // In a real implementation, this would use JavaMail API or similar
        // For this example, we'll simulate sending
        simulateEmailSending(parentEmail, subject, body);
        System.out.println("Email notification sent to: " + parentEmail);
    }
    /**
     * Simulates sending an email
     * In a real implementation, this would connect to an SMTP server
     */
    private void simulateEmailSending(String to, String subject, String body) {
        // Simulate email sending delay
        try {
            Thread.sleep(500); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Log the email details (in real app, this would actually send)
        System.out.println("=== SIMULATED EMAIL ===");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
        System.out.println("=== EMAIL SENT ===");
    }
}