package com.example.insertnote;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service class for note operations.
 * Handles saving notes to a simulated database, triggering email notifications,
 * and managing note data with proper validation and error handling.
 */
public class NoteService {
    private static final Logger LOGGER = Logger.getLogger(NoteService.class.getName());
    
    // Simulated database using thread-safe list
    private final List<Note> notesDatabase;
    private final EmailService emailService;
    
    /**
     * Default constructor that creates its own EmailService instance.
     * Useful for simple applications without dependency injection.
     */
    public NoteService() {
        this.notesDatabase = new CopyOnWriteArrayList<>();
        this.emailService = new EmailService();
        LOGGER.info("NoteService initialized with default EmailService");
    }
    
    /**
     * Constructor with dependency injection for EmailService.
     * Useful for testing and better separation of concerns.
     * 
     * @param emailService The EmailService instance to use for notifications
     */
    public NoteService(EmailService emailService) {
        this.notesDatabase = new CopyOnWriteArrayList<>();
        this.emailService = emailService;
        LOGGER.info("NoteService initialized with provided EmailService");
    }
    
    /**
     * Saves a disciplinary note to the system.
     * Performs validation, saves to simulated database, and triggers email notification.
     * 
     * @param note The note to save
     * @return true if note was saved successfully, false otherwise
     * @throws IllegalArgumentException if note is null
     */
    public boolean saveNote(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        
        LOGGER.info("Attempting to save note for student: " + note.getStudent());
        
        try {
            // Step 1: Validate note
            note.validate();
            LOGGER.fine("Note validation passed");
            
            // Step 2: Check for duplicates (optional business rule)
            if (isDuplicateNote(note)) {
                LOGGER.warning("Possible duplicate note detected for student: " + note.getStudent());
                // In some systems, duplicates might be allowed
                // For this system, we allow duplicates but log a warning
            }
            
            // Step 3: Save to simulated database
            boolean saved = saveToDatabase(note);
            if (!saved) {
                LOGGER.severe("Failed to save note to database for student: " + note.getStudent());
                return false;
            }
            
            LOGGER.info("Note saved to database for student: " + note.getStudent());
            
            // Step 4: Send email notification to parent
            LOGGER.info("Triggering email notification for student: " + note.getStudent());
            boolean emailSent = emailService.sendNotificationToParent(note);
            
            if (emailSent) {
                LOGGER.info("Email notification sent successfully for student: " + note.getStudent());
            } else {
                LOGGER.warning("Email notification failed for student: " + note.getStudent() + 
                              ", but note was saved to database");
                // Note: The note is still saved even if email fails
                // This is a business decision - some systems might require successful email
                // For this use case, we consider the note saved successfully
            }
            
            // Step 5: Log the operation for auditing
            logNoteOperation(note, "INSERT");
            
            return true;
            
        } catch (IllegalStateException e) {
            LOGGER.log(Level.SEVERE, "Note validation failed: " + e.getMessage(), e);
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error while saving note", e);
            return false;
        }
    }
    
    /**
     * Saves the note to the simulated database.
     * In a real application, this would use JDBC, JPA, or another persistence mechanism.
     * 
     * @param note The note to save
     * @return true if save was successful, false otherwise
     */
    private boolean saveToDatabase(Note note) {
        try {
            // Simulate database operations
            // In a real system, this would involve:
            // 1. Opening database connection
            // 2. Executing INSERT statement
            // 3. Handling transactions
            // 4. Closing resources
            
            // Simulate occasional database failures (5% chance)
            if (Math.random() < 0.05) {
                LOGGER.severe("Simulated database connection failure");
                return false;
            }
            
            // Add note to the list (simulating database insert)
            notesDatabase.add(note.copy()); // Store a copy to prevent external modification
            
            // Simulate database write delay
            Thread.sleep(100);
            
            LOGGER.fine("Note saved to simulated database: " + note.getStudent());
            return true;
            
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "Database operation interrupted", e);
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database error while saving note", e);
            return false;
        }
    }
    
    /**
     * Checks if a similar note already exists in the database.
     * This is a simple duplicate detection - in a real system, this would be more sophisticated.
     * 
     * @param note The note to check
     * @return true if a similar note exists, false otherwise
     */
    private boolean isDuplicateNote(Note note) {
        // Simple duplicate detection: same student, same date, similar description
        return notesDatabase.stream()
                .anyMatch(existingNote -> 
                    existingNote.getStudent().equalsIgnoreCase(note.getStudent()) &&
                    existingNote.getDate().equals(note.getDate()) &&
                    existingNote.getDescription().toLowerCase()
                        .contains(note.getDescription().toLowerCase().substring(0, 
                            Math.min(10, note.getDescription().length()))));
    }
    
    /**
     * Retrieves all notes from the simulated database.
     * 
     * @return List of all notes (copies to prevent modification of internal state)
     */
    public List<Note> getAllNotes() {
        LOGGER.fine("Retrieving all notes from database");
        // Return copies to protect internal data
        List<Note> copies = new ArrayList<>();
        for (Note note : notesDatabase) {
            copies.add(note.copy());
        }
        return copies;
    }
    
    /**
     * Finds notes by student name.
     * 
     * @param studentName The student name to search for
     * @return List of notes for the specified student
     */
    public List<Note> findNotesByStudent(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        
        LOGGER.fine("Searching notes for student: " + studentName);
        List<Note> result = new ArrayList<>();
        
        for (Note note : notesDatabase) {
            if (note.getStudent().equalsIgnoreCase(studentName.trim())) {
                result.add(note.copy());
            }
        }
        
        LOGGER.fine("Found " + result.size() + " notes for student: " + studentName);
        return result;
    }
    
    /**
     * Gets the total number of notes in the system.
     * 
     * @return Count of notes
     */
    public int getNoteCount() {
        return notesDatabase.size();
    }
    
    /**
     * Clears all notes from the simulated database.
     * WARNING: This is for testing/cleanup purposes only.
     */
    public void clearAllNotes() {
        LOGGER.warning("Clearing all notes from database - total notes: " + notesDatabase.size());
        notesDatabase.clear();
    }
    
    /**
     * Logs note operations for auditing purposes.
     * 
     * @param note The note being operated on
     * @param operation The operation performed (INSERT, UPDATE, DELETE, etc.)
     */
    private void logNoteOperation(Note note, String operation) {
        LOGGER.info("Note operation audit:");
        LOGGER.info("  Operation: " + operation);
        LOGGER.info("  Student: " + note.getStudent());
        LOGGER.info("  Date: " + note.getDate());
        LOGGER.info("  Teacher: " + note.getTeacher());
        LOGGER.info("  Timestamp: " + java.time.LocalDateTime.now());
        LOGGER.info("  Database size: " + notesDatabase.size());
    }
    
    /**
     * Gets the EmailService instance being used.
     * 
     * @return The EmailService instance
     */
    public EmailService getEmailService() {
        return emailService;
    }
    
    /**
     * Validates and prepares a note for saving.
     * This method can be used by external callers to validate notes before saving.
     * 
     * @param note The note to validate
     * @throws IllegalArgumentException if note is null or invalid
     */
    public void validateNote(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        
        note.validate();
        
        // Additional business rules can be added here
        // For example: check if teacher exists in system, if student is enrolled, etc.
        LOGGER.fine("Note passed all validation checks");
    }
    
    /**
     * Simulates database backup operation.
     * In a real system, this would backup the notes database.
     * 
     * @return true if backup successful, false otherwise
     */
    public boolean backupDatabase() {
        LOGGER.info("Starting simulated database backup...");
        try {
            // Simulate backup process
            Thread.sleep(200);
            LOGGER.info("Database backup completed successfully");
            return true;
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "Database backup interrupted", e);
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database backup failed", e);
            return false;
        }
    }
}