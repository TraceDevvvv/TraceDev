/**
 * Utility class to simulate sending email notifications to parents.
 * In a real application, this would integrate with an email sending service (e.g., JavaMail API).
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ParentNotifier {
    /**
     * Sends a simulated absence notification email to a parent.
     *
     * @param parentEmail The email address of the parent.
     * @param studentName The name of the absent student.
     * @param className   The name of the class the student was absent from.
     * @param absenceDate The date of the absence.
     */
    public static void sendAbsenceNotification(String parentEmail, String studentName, String className, LocalDate absenceDate) {
        String formattedDate = absenceDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String subject = "Absence Notification for " + studentName + " - " + formattedDate;
        String body = String.format(
                "Dear Parent,\n\n" +
                "This is an automated notification to inform you that your child, %s, " +
                "was marked absent from the class '%s' on %s.\n\n" +
                "Please contact the school administration if you have any questions.\n\n" +
                "Sincerely,\nATA Staff",
                studentName, className, formattedDate
        );
        // Simulate sending email by printing to console
        System.out.println("--- SIMULATED EMAIL NOTIFICATION ---");
        System.out.println("To: " + parentEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
        System.out.println("-------------------------------------\n");
        // In a real application, you would use JavaMail API or a similar library here:
        // try {
        //     // ... MimeMessage setup ...
        //     Transport.send(message);
        //     System.out.println("Email sent to " + parentEmail + " for " + studentName);
        // } catch (MessagingException e) {
        //     System.err.println("Failed to send email to " + parentEmail + ": " + e.getMessage());
        // }
    }
}