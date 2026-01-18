package com.example.disciplinarynote.infrastructure;

import com.example.disciplinarynote.domain.DisciplinaryNote;
import com.example.disciplinarynote.domain.Student; // For simulation purposes
import com.example.disciplinarynote.domain.Teacher; // For simulation purposes
import com.example.disciplinarynote.dto.DisciplinaryNoteDetailsDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Concrete implementation of IDisciplinaryNoteRepository.
 * Uses an in-memory map for simulation purposes.
 * Can simulate connection errors for R11.
 */
public class DisciplinaryNoteRepositoryImpl implements IDisciplinaryNoteRepository {
    // In-memory store for demonstration
    private final Map<String, DisciplinaryNote> notes = new HashMap<>();
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Teacher> teachers = new HashMap<>();
    private final DataSource dataSource;
    private final Random random = new Random(); // For simulating connection errors

    // Flag to control connection error simulation for testing R11
    private boolean simulateConnectionError = false;

    /**
     * Constructs a new DisciplinaryNoteRepositoryImpl.
     * Uses a DataSource for database interaction (mocked here).
     * @param dataSource The data source for repository operations.
     */
    public DisciplinaryNoteRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        // Initialize with some dummy data
        Student student1 = new Student("S001", "Alice Smith");
        Student student2 = new Student("S002", "Bob Johnson");
        Teacher teacher1 = new Teacher("T001", "Mr. Davis");
        Teacher teacher2 = new Teacher("T002", "Ms. Garcia");

        students.put(student1.getStudentId(), student1);
        students.put(student2.getStudentId(), student2);
        teachers.put(teacher1.getTeacherId(), teacher1);
        teachers.put(teacher2.getTeacherId(), teacher2);

        notes.put("N001", new DisciplinaryNote("N001", "S001", "Late for class", "T001", LocalDate.of(2023, 10, 26)));
        notes.put("N002", new DisciplinaryNote("N002", "S002", "Misconduct in hallway", "T002", LocalDate.of(2023, 10, 27)));
    }

    /**
     * Sets whether to simulate a connection error.
     * @param simulateConnectionError True to simulate errors, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }

    /**
     * Finds a disciplinary note by its ID.
     * Modified to satisfy requirement R11 by potentially throwing RepositoryConnectionException.
     *
     * @param noteId The unique identifier of the note.
     * @return An Optional containing the DisciplinaryNote if found, otherwise empty.
     * @throws RepositoryConnectionException if there's an issue connecting to the repository.
     */
    @Override
    public Optional<DisciplinaryNote> findById(String noteId) throws RepositoryConnectionException {
        // Simulate a connection error
        if (simulateConnectionError || random.nextDouble() < 0.1) { // 10% chance of random error
            System.err.println("[Repository] Simulating connection error during findById for noteId: " + noteId);
            throw new RepositoryConnectionException("Failed to connect to disciplinary note database during find operation.");
        }

        System.out.println("[Repository] Finding note with ID: " + noteId + " using DataSource: " + dataSource.getConnection());
        Optional<DisciplinaryNote> foundNote = Optional.ofNullable(notes.get(noteId));
        dataSource.releaseConnection(dataSource.getConnection()); // Simulate releasing connection
        return foundNote;
    }

    /**
     * Saves a disciplinary note.
     * Modified to satisfy requirement R11 by potentially throwing RepositoryConnectionException.
     *
     * @param note The DisciplinaryNote to save.
     * @throws RepositoryConnectionException if there's an issue connecting to the repository.
     */
    @Override
    public void save(DisciplinaryNote note) throws RepositoryConnectionException {
        // Simulate a connection error
        if (simulateConnectionError || random.nextDouble() < 0.1) { // 10% chance of random error
            System.err.println("[Repository] Simulating connection error during save for noteId: " + note.getNoteId());
            throw new RepositoryConnectionException("Failed to connect to disciplinary note database during save operation.");
        }

        System.out.println("[Repository] Saving note with ID: " + note.getNoteId() + " using DataSource: " + dataSource.getConnection());
        notes.put(note.getNoteId(), note);
        dataSource.releaseConnection(dataSource.getConnection()); // Simulate releasing connection
        System.out.println("[Repository] Note " + note.getNoteId() + " saved/updated successfully.");
    }

    /**
     * Helper method to get DTO for display, for initial loading in Controller.
     * This method is not part of the `IDisciplinaryNoteRepository` interface
     * but is useful for the initial `showEditForm` interaction.
     *
     * @param noteId The ID of the note.
     * @return DisciplinaryNoteDetailsDto if found, otherwise null.
     * @throws RepositoryConnectionException if connection fails.
     */
    public DisciplinaryNoteDetailsDto getNoteDetailsDto(String noteId) throws RepositoryConnectionException {
        Optional<DisciplinaryNote> noteOpt = findById(noteId);
        if (noteOpt.isPresent()) {
            DisciplinaryNote note = noteOpt.get();
            String studentName = students.getOrDefault(note.getStudentId(), new Student(note.getStudentId(), "Unknown Student")).getName();
            String teacherName = teachers.getOrDefault(note.getTeacherId(), new Teacher(note.getTeacherId(), "Unknown Teacher")).getName();
            return new DisciplinaryNoteDetailsDto(
                    note.getNoteId(),
                    studentName,
                    note.getDescription(),
                    teacherName,
                    note.getDate()
            );
        }
        return null; // Or throw NoteNotFoundException
    }
}