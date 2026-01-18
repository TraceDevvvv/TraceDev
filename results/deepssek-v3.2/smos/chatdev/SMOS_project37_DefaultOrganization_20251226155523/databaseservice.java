/**
 * Service for database operations related to disciplinary notes
 * Simulates database connectivity and operations
 */
import java.util.ArrayList;
import java.util.List;
public class DatabaseService {
    private List<Note> notesDatabase;
    public DatabaseService() {
        notesDatabase = new ArrayList<>();
        System.out.println("Database connection initialized");
    }
    /**
     * Saves a disciplinary note to the database
     * @param note The note to save
     * @throws Exception if there's an error saving
     */
    public void saveNote(Note note) throws Exception {
        // Validate note
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        if (note.getStudent() == null || note.getStudent().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name is required");
        }
        // Simulate database save operation
        try {
            // Simulate database delay
            Thread.sleep(300);
            // Add to in-memory list (simulating database)
            notesDatabase.add(note);
            System.out.println("Note saved to database: " + note);
            System.out.println("Total notes in database: " + notesDatabase.size());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Exception("Database operation interrupted");
        } catch (Exception e) {
            throw new Exception("Database error: " + e.getMessage());
        }
    }
    /**
     * Retrieves all notes from the database
     * @return List of all notes
     */
    public List<Note> getAllNotes() {
        return new ArrayList<>(notesDatabase);
    }
}