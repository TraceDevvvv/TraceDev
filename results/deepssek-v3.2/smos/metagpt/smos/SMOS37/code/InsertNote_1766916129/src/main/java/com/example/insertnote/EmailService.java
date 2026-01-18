package com.example.insertnote;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class for sending email notifications to parents.
 * Simulates email sending with appropriate error handling and retry logic.
 * In a real application, this would integrate with an email service provider.
 */
public class EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());
    
    // Configuration constants
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long RETRY_DELAY_MS = 1000;
    private static final String EMAIL_SUBJECT = "Disciplinary Note Notification";
    private static final String EMAIL_FROM = "school@example.edu";
    
    /**
     * Sends a notification email to the parent regarding a disciplinary note.
     * This method simulates email sending and includes error handling.
     * 
     * @param note The disciplinary note for which to send notification
     * @return true if email was sent successfully, false otherwise
     * @throws IllegalArgumentException if note is null
     */
    public boolean sendNotificationToParent(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        
        try {
            // Validate note before processing
            note.validate();
            
            // Get parent email from note (in real system, this would be from database)
            String parentEmail = note.getParentEmail();
            
            LOGGER.info("Preparing to send email to parent: " + parentEmail);
            
            // Attempt to send email with retry logic
            for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
                try {
                    LOGGER.info("Email send attempt " + attempt + " of " + MAX_RETRY_ATTEMPTS);
                    
                    // Simulate email sending (in real system, this would use JavaMail or similar)
                    boolean success = simulateEmailSending(note, parentEmail);
                    
                    if (success) {
                        LOGGER.info("Email sent successfully to " + parentEmail);
                        logEmailDetails(note, parentEmail);
                        return true;
                    } else {
                        LOGGER.warning("Email sending failed on attempt " + attempt);
                        
                        // If not the last attempt, wait before retrying
                        if (attempt < MAX_RETRY_ATTEMPTS) {
                            LOGGER.info("Retrying in " + RETRY_DELAY_MS + "ms...");
                            TimeUnit.MILLISECONDS.sleep(RETRY_DELAY_MS);
                        }
                    }
                } catch (InterruptedException e) {
                    LOGGER.log(Level.WARNING, "Email sending interrupted", e);
                    Thread.currentThread().interrupt();
                    return false;
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Unexpected error during email sending attempt " + attempt, e);
                    
                    // For unexpected errors, we still retry unless it's the last attempt
                    if (attempt == MAX_RETRY_ATTEMPTS) {
                        LOGGER.severe("All email sending attempts failed");
                        return false;
                    }
                    
                    try {
                        TimeUnit.MILLISECONDS.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return false;
                    }
                }
            }
            
            LOGGER.severe("Failed to send email after " + MAX_RETRY_ATTEMPTS + " attempts");
            return false;
            
        } catch (IllegalStateException e) {
            LOGGER.log(Level.SEVERE, "Cannot send email: Note validation failed", e);
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in email sending process", e);
            return false;
        }
    }
    
    /**
     * Simulates the actual email sending process.
     * In a real implementation, this would use JavaMail API or similar.
     * 
     * @param note The disciplinary note
     * @param parentEmail The parent's email address
     * @return true if simulation succeeds, false if simulation fails
     */
    private boolean simulateEmailSending(Note note, String parentEmail) {
        // Simulate network/email service issues with a 10% failure rate
        if (Math.random() < 0.1) {
            LOGGER.warning("Simulated email service failure for: " + parentEmail);
            return false;
        }
        
        // Simulate email construction and sending
        String emailBody = constructEmailBody(note);
        
        // In a real system, this would be:
        // Properties props = new Properties();
        // props.put("mail.smtp.host", "smtp.example.com");
        // Session session = Session.getInstance(props);
        // Message message = new MimeMessage(session);
        // message.setFrom(new InternetAddress(EMAIL_FROM));
        // message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(parentEmail));
        // message.setSubject(EMAIL_SUBJECT);
        // message.setText(emailBody);
        // Transport.send(message);
        
        // For simulation, just log the email details
        LOGGER.fine("Simulated email sent:");
        LOGGER.fine("  From: " + EMAIL_FROM);
        LOGGER.fine("  To: " + parentEmail);
        LOGGER.fine("  Subject: " + EMAIL_SUBJECT);
        LOGGER.fine("  Body: " + emailBody.substring(0, Math.min(100, emailBody.length())) + "...");
        
        // Simulate sending delay
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        return true;
    }
    
    /**
     * Constructs the email body for the disciplinary note notification.
     * 
     * @param note The disciplinary note
     * @return Formatted email body
     */
    private String constructEmailBody(Note note) {
        return "Dear Parent/Guardian,\n\n" +
               "This is to inform you that a disciplinary note has been recorded for your child.\n\n" +
               "Details:\n" +
               "  Student: " + note.getStudent() + "\n" +
               "  Date: " + note.getDate() + "\n" +
               "  Teacher: " + note.getTeacher() + "\n" +
               "  Description: " + note.getDescription() + "\n\n" +
               "Please contact the school administration if you have any questions.\n\n" +
               "Sincerely,\n" +
               "School Administration\n" +
               "---\n" +
               "This is an automated notification. Please do not reply to this email.";
    }
    
    /**
     * Logs email details for auditing purposes.
     * 
     * @param note The disciplinary note
     * @param parentEmail The parent's email address
     */
    private void logEmailDetails(Note note, String parentEmail) {
        LOGGER.info("Email audit log:");
        LOGGER.info("  Student: " + note.getStudent());
        LOGGER.info("  Parent Email: " + parentEmail);
        LOGGER.info("  Date Sent: " + java.time.LocalDateTime.now());
        LOGGER.info("  Note Date: " + note.getDate());
        LOGGER.info("  Teacher: " + note.getTeacher());
    }
    
    /**
     * Validates an email address format (basic validation).
     * 
     * @param email The email address to validate
     * @return true if email format appears valid, false otherwise
     */
    public boolean isValidEmailFormat(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Basic email format validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }
    
    /**
     * Gets the configured maximum retry attempts.
     * 
     * @return Maximum number of retry attempts
     */
    public int getMaxRetryAttempts() {
        return MAX_RETRY_ATTEMPTS;
    }
    
    /**
     * Gets the retry delay in milliseconds.
     * 
     * @return Retry delay in milliseconds
     */
    public long getRetryDelayMs() {
        return RETRY_DELAY_MS;
    }
}