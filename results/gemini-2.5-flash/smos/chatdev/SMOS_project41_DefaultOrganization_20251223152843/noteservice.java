/*
Manages a collection of DisciplinaryNote objects, simulating data persistence and interactions with a server.
It provides methods to add, retrieve, and update notes, and includes a mechanism to simulate server connection issues.
*/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
/**
 * Service class for managing DisciplinaryNote objects.
 * This class simulates a data store (in-memory list) and potential server interactions.
 */
public class NoteService {
    private List<DisciplinaryNote> notes;
    private Random random; // For simulating server interruption
    public NoteService() {
        this.notes = new ArrayList<>();
        this.random = new Random();
        initializeTestData(); // Populate with some initial notes
    }
    /**
     * Initializes the service with some sample disciplinary notes.
     */
    private void initializeTestData() {
        notes.add(new DisciplinaryNote("Alice Smith", "Disrupted class during lecture.", "Mr. Johnson", LocalDate.of(2023, 10, 26)));
        notes.add(new DisciplinaryNote("Bob Johnson", "Failed to submit homework on time for three consecutive weeks.", "Ms. Davis", LocalDate.of(2023, 10, 20)));
        notes.add(new DisciplinaryNote("Charlie Brown", "Unauthorized use of mobile phone during exam.", "Mr. Lee", LocalDate.of(2023, 11, 01)));
        notes.add(new DisciplinaryNote("Diana Prince", "Argument with classmates leading to physical altercation.", "Mrs. Kent", LocalDate.of(2023, 11, 05)));
    }
    /**
     * Retrieves all disciplinary notes currently stored.
     *
     * @return An unmodifiable list of all disciplinary notes.
     */
    public List<DisciplinaryNote> getAllNotes() {
        return Collections.unmodifiableList(notes);
    }
    /**
     * Retrieves a disciplinary note by its unique ID.
     *
     * @param id The ID of the note to retrieve.
     * @return An Optional containing the DisciplinaryNote if found, or empty if not found.
     */
    public Optional<DisciplinaryNote> getNoteById(int id) {
        return notes.stream()
                    .filter(note -> note.getId() == id)
                    .findFirst();
    }
    /**
     * Adds a new disciplinary note to the collection.
     * This method simulates a server call and can throw a server interruption exception.
     *
     * @param note The DisciplinaryNote object to add.
     * @throws ServerInterruptionException If the simulated server connection is interrupted.
     */
    public void addNote(DisciplinaryNote note) throws ServerInterruptionException {
        _simulateServerInteraction(); // Simulate network/server call
        notes.add(note);
    }
    /**
     * Updates an existing disciplinary note.
     * This method simulates a server call and can throw a server interruption exception.
     * It finds the note by ID and updates its fields.
     *
     * @param updatedNote The DisciplinaryNote object containing the updated data.
     * @return true if the note was found and updated, false otherwise.
     * @throws ServerInterruptionException If the simulated server connection is interrupted.
     */
    public boolean updateNote(DisciplinaryNote updatedNote) throws ServerInterruptionException {
        _simulateServerInteraction(); // Simulate network/server call
        Optional<DisciplinaryNote> existingNoteOpt = getNoteById(updatedNote.getId());
        if (existingNoteOpt.isPresent()) {
            DisciplinaryNote existingNote = existingNoteOpt.get();
            // Update all editable fields
            existingNote.setStudentName(updatedNote.getStudentName());
            existingNote.setDescription(updatedNote.getDescription());
            existingNote.setTeacherName(updatedNote.getTeacherName());
            existingNote.setNoteDate(updatedNote.getNoteDate());
            return true;
        }
        return false;
    }
    /**
     * Simulates a potential server interaction.
     * There's a 10% chance this method will throw a ServerInterruptionException,
     * mimicking a network or backend issue.
     *
     * @throws ServerInterruptionException If the simulated connection is interrupted.
     */
    private void _simulateServerInteraction() throws ServerInterruptionException {
        // Simulate a delay for server interaction
        try {
            Thread.sleep(random.nextInt(300) + 100); // 100-400 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // 10% chance of server interruption
        if (random.nextInt(10) == 0) {
            throw new ServerInterruptionException("Connection to the SMOS server interrupted. Please try again.");
        }
    }
    /**
     * Custom exception to simulate server connection issues.
     */
    static class ServerInterruptionException extends Exception {
        public ServerInterruptionException(String message) {
            super(message);
        }
    }
}