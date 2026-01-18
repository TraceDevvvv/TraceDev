'''
Service class responsible for managing disciplinary notes, including
saving them to a data store and sending notifications.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern; // Added for more robust email validation (example)
public class NoteService {
    // In-memory list to simulate a database for disciplinary notes.
    private final List<Note> notes;
    // Dependency on EmailService for sending notifications.
    private final EmailService emailService;
    // Basic email pattern for server-side validation (can be more complex)
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    /**
     * Constructor for NoteService.
     *
     * @param emailService An implementation of EmailService to be used for sending notifications.
     */
    public NoteService(EmailService emailService) {
        this.notes = new ArrayList<>();
        this.emailService = emailService;
    }
    /**
     * Saves a disciplinary note to the system and sends an email notification to the parent.
     * The note is only persisted if the email notification is successfully sent, ensuring atomicity
     * according to the user story's postconditions.
     *
     * @param note The Note object to be saved.
     * @return true if the note was successfully saved and notification sent, false otherwise.
     */
    public boolean saveNote(Note note) {
        // Basic validation: ensure note is not null and has required fields.
        if (note == null ||
            isNullOrEmpty(note.getStudentName()) ||
            note.getDate() == null ||
            isNullOrEmpty(note.getTeacherName()) ||
            isNullOrEmpty(note.getDescription()) ||
            isNullOrEmpty(note.getParentEmail())) {
            System.err.println("Error: Invalid Note data. All required fields must be filled.");
            return false;
        }
        // Additional server-side email validation for robustness
        if (!isValidEmail(note.getParentEmail())) {
            System.err.println("Error: Invalid parent email format: " + note.getParentEmail());
            return false;
        }
        // Check description length server-side as well
        if (note.getDescription().length() > 255) {
             System.err.println("Error: Description exceeds maximum length of 255 characters.");
             return false;
        }
        // Construct email subject and body.
        String subject = "Disciplinary Notice for " + note.getStudentName();
        String body = String.format(
            "Dear Parent/Guardian,\n\n" +
            "This is a disciplinary notice regarding your child, %s.\n\n" +
            "Date of Incident: %s\n" +
            "Teacher: %s\n" +
            "Description: %s\n\n" +
            "Please contact the school if you have any questions.\n\n" +
            "Sincerely,\n" +
            "School Administration",
            note.getStudentName(), note.getDate().toString(), note.getTeacherName(), note.getDescription()
        );
        // Attempt to send notification via email FIRST.
        boolean emailSent = false;
        try {
            emailSent = emailService.sendNotification(note.getParentEmail(), subject, body);
        } catch (Exception e) { // Catch a common base Exception for robustness.
                                // In a real system, you might catch more specific exceptions.
            System.err.println("ERROR: Exception occurred during email notification for " + note.getParentEmail() + ": " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging
            emailSent = false;  // Treat any exception as a failure to send
        }
        if (emailSent) {
            // If email is sent successfully, then save the note to the system.
            notes.add(note);
            System.out.println("Note saved successfully for student: " + note.getStudentName());
            System.out.println("Total notes in system: " + notes.size());
            System.out.println("Email notification sent to parent: " + note.getParentEmail());
            return true;
        } else {
            // If email sending fails, the note is NOT added to the system, ensuring atomicity.
            System.err.println("Failed to send email notification to parent: " + note.getParentEmail() + ". Note not saved.");
            return false;
        }
    }
    /**
     * Retrieves all saved notes.
     * (Added for completeness, though not explicitly used in the InsertNote use case flow).
     * @return A list of all disciplinary notes.
     */
    public List<Note> getAllNotes() {
        return new ArrayList<>(notes); // Return a copy to prevent external modification
    }
    /**
     * Helper method to check if a string is null or empty after trimming whitespace.
     * @param str The string to check.
     * @return true if the string is null or empty, false otherwise.
     */
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    /**
     * Validates an email address against a predefined pattern.
     * @param email The email string to validate.
     * @return true if the email is in a valid format, false otherwise.
     */
    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}