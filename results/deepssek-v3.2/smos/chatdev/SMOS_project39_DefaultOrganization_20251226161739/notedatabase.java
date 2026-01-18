'''
NoteDatabase.java
Simulates a database of notes. In a real application this would connect to SMOS server.
Here we provide a mock implementation that returns a note by ID.
'''
import java.util.HashMap;
import java.util.Map;
public class NoteDatabase {
    private Map<Integer, Note> noteMap;
    public NoteDatabase() {
        noteMap = new HashMap<>();
        // Seed with sample notes
        noteMap.put(1, new Note(1, "John Doe", "Completed assignment on time", "Dr. Smith", "2023-10-05"));
        noteMap.put(2, new Note(2, "Jane Smith", "Excellent participation in class", "Prof. Johnson", "2023-10-06"));
        noteMap.put(3, new Note(3, "Alice Brown", "Needs improvement in homework", "Dr. Williams", "2023-10-07"));
    }
    /**
     * Fetches a note by its ID.
     * Returns null if not found (simulating an edge case).
     */
    public Note getNoteById(int id) {
        return noteMap.get(id);
    }
    /**
     * Gets all notes from the database.
     * Used to populate the notes list in the main application.
     */
    public Note[] getAllNotes() {
        return noteMap.values().toArray(new Note[0]);
    }
}