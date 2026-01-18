'''
Core system class managing disciplinary notes and administrator operations.
Simulates database and notification functionality.
'''
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class NoteSystem {
    private boolean adminLoggedIn = false;
    private Map<String, Note> noteArchive = new HashMap<>();
    private Random random = new Random();
    /**
     * Constructor initializes with sample data for testing.
     */
    public NoteSystem() {
        // Initialize sample notes
        noteArchive.put("NOTE001", new Note("NOTE001", "John Doe", "Late submission", "2023-10-15", "parent1@email.com", "+1234567890"));
        noteArchive.put("NOTE002", new Note("NOTE002", "Jane Smith", "Class disruption", "2023-10-20", "parent2@email.com", "+0987654321"));
    }
    /**
     * Authenticates administrator login.
     * 
     * @param username administrator username
     * @param password administrator password
     * @return true if login successful
     */
    public boolean loginAdmin(String username, String password) {
        // Simple authentication simulation
        adminLoggedIn = "admin".equals(username) && "admin123".equals(password);
        return adminLoggedIn;
    }
    /**
     * Logs out current administrator.
     */
    public void logout() {
        adminLoggedIn = false;
    }
    /**
     * Checks if an administrator is currently logged in.
     * 
     * @return true if administrator is logged in
     */
    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }
    /**
     * Loads a note for potential deletion.
     * 
     * @param noteId the ID of the note to load
     * @return Note object if found, null otherwise
     */
    public Note loadNoteForDeletion(String noteId) {
        return noteArchive.get(noteId);
    }
    /**
     * Deletes a note and sends notification to parents.
     * Implements the Event Sequence: sends notification then eliminates data.
     * 
     * @param noteId the ID of the note to delete
     * @return true if deletion and notification successful
     */
    public boolean deleteNoteWithNotification(String noteId) {
        Note note = noteArchive.get(noteId);
        if (note == null) {
            return false;
        }
        // 1. Send notification to parents
        boolean notificationSent = sendParentNotification(note);
        // 2. Eliminate the data from the archive
        noteArchive.remove(noteId);
        return notificationSent;
    }
    /**
     * Simulates sending notification to student's parents.
     * 
     * @param note the note being deleted
     * @return true if notification "sent" successfully
     */
    private boolean sendParentNotification(Note note) {
        // Simulate notification sending
        System.out.println("Notification sent for note deletion:");
        System.out.println("  To: " + note.getParentEmail() + ", " + note.getParentPhone());
        System.out.println("  Student: " + note.getStudentName());
        System.out.println("  Reason: Note '" + note.getDescription() + "' has been deleted.");
        // Simulate occasional failure (e.g., SMOS server issues)
        return random.nextDouble() > 0.1; // 90% success rate
    }
    /**
     * Simulates interruption scenarios: admin cancel or server disconnect.
     * 
     * @return true if interruption occurs
     */
    public boolean simulateInterruption() {
        // 10% chance of interruption
        return random.nextDouble() < 0.1;
    }
}