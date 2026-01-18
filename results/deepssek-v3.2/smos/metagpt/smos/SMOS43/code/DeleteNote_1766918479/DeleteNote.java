import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Java program implementing the DeleteNote use case.
 * This program models the deletion of disciplinary notes by an administrator,
 * including sending notifications to parents and handling interruptions.
 */
public class DeleteNote {
    // Logger for system messages
    private static final Logger LOGGER = Logger.getLogger(DeleteNote.class.getName());
    
    public static void main(String[] args) {
        try {
            LOGGER.info("Starting DeleteNote use case simulation.");
            
            // Simulate administrator logged in and viewing note details
            Administrator admin = new Administrator("admin123");
            System.out.println("Administrator logged in as: " + admin.getUsername());
            
            // Note ID that is being viewed (simulated from SHOWDDETTICLETA use case)
            int noteId = 1001;
            Note note = new Note(noteId, "Disciplinary note for late submission", 3001);
            
            System.out.println("\n--- Note Details (from SHOWDDETTICLETA) ---");
            System.out.println("Note ID: " + note.getId());
            System.out.println("Description: " + note.getDescription());
            System.out.println("Student ID: " + note.getStudentId());
            
            // Simulate user clicking "Delete" button
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nDo you want to delete this note? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (!confirm.equals("yes")) {
                System.out.println("Administrator interrupted the operation. Deletion cancelled.");
                LOGGER.info("Administrator interrupted deletion operation.");
                return;
            }
            
            // Execute deletion process
            boolean success = executeDeletionProcess(admin, note);
            
            if (success) {
                System.out.println("\nNote deletion successfully completed.");
                System.out.println("System returning to registry screen...");
            } else {
                System.out.println("\nNote deletion failed. Please try again.");
            }
            
            scanner.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in DeleteNote process: " + e.getMessage(), e);
            System.err.println("System error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Executes the complete deletion process:
     * 1. Send notification to student's parents
     * 2. Eliminate note data from archive
     * @param admin The administrator performing the deletion
     * @param note The note to be deleted
     * @return true if successful, false otherwise
     */
    private static boolean executeDeletionProcess(Administrator admin, Note note) {
        LOGGER.info("Starting deletion process for note ID: " + note.getId());
        
        // Initialize serv
        NotificationService notificationService = new NotificationService();
        Archive archive = new Archive();
        
        try {
            // Step 1: Send notification to student's parents
            System.out.println("\n[Step 1] Sending notification to student's parents...");
            boolean notificationSent = notificationService.sendNotificationToParents(note);
            
            if (!notificationSent) {
                throw new NotificationException("Failed to send notification to parents.");
            }
            System.out.println("Notification sent successfully.");
            
            // Step 2: Eliminate note data from archive
            System.out.println("\n[Step 2] Eliminating note data from archive...");
            boolean noteDeleted = archive.deleteNote(note.getId());
            
            if (!noteDeleted) {
                throw new ArchiveException("Failed to delete note from archive.");
            }
            System.out.println("Note data deleted from archive successfully.");
            
            LOGGER.info("Deletion process completed successfully for note ID: " + note.getId());
            return true;
            
        } catch (NotificationException e) {
            LOGGER.severe("Notification error: " + e.getMessage());
            System.err.println("Error: " + e.getMessage() + " - Note deletion cancelled.");
        } catch (ArchiveException e) {
            LOGGER.severe("Archive error: " + e.getMessage());
            System.err.println("Error: " + e.getMessage() + " - Note deletion cancelled.");
        } catch (InterruptedException e) {
            LOGGER.info("Deletion process interrupted by administrator or system.");
            System.out.println("Deletion process interrupted. Operation cancelled.");
            Thread.currentThread().interrupt(); // Restore interrupt status
        } catch (SMOSConnectionException e) {
            LOGGER.severe("SMOS server connection error: " + e.getMessage());
            System.err.println("Error: Connection to SMOS server interrupted. Note deletion cancelled.");
        }
        
        return false;
    }
}

/**
 * Represents an Administrator user.
 */
class Administrator {
    private String username;
    private boolean isLoggedIn;
    
    public Administrator(String username) {
        this.username = username;
        this.isLoggedIn = true; // Simulating already logged in as per preconditions
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Simulates administrator interruption of operation.
     * In a real system, this could be triggered by a cancel button or user action.
     */
    public void interruptOperation() {
        System.out.println("Administrator intentionally interrupting operation...");
        Thread.currentThread().interrupt();
    }
}

/**
 * Represents a disciplinary note.
 */
class Note {
    private int id;
    private String description;
    private int studentId; // Associated student ID for parent notification
    
    public Note(int id, String description, int studentId) {
        this.id = id;
        this.description = description;
        this.studentId = studentId;
    }
    
    public int getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    /**
     * Gets parent contact info for the associated student.
     * In a real system, this would query a database.
     */
    public String getParentContactInfo() {
        // Simulated parent contact information
        return "parents_of_student_" + studentId + "@school.edu";
    }
}

/**
 * Service responsible for sending notifications to parents.
 */
class NotificationService {
    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());
    private AtomicBoolean connectionStatus = new AtomicBoolean(true);
    
    /**
     * Sends notification to the student's parents about the disciplinary note.
     * @param note The note being deleted
     * @return true if notification sent successfully, false otherwise
     * @throws NotificationException if notification fails
     * @throws SMOSConnectionException if SMOS server connection is interrupted
     */
    public boolean sendNotificationToParents(Note note) throws NotificationException, SMOSConnectionException {
        LOGGER.info("Attempting to send notification for note ID: " + note.getId());
        
        // Check for SMOS server connection interruption (edge case)
        if (!isSMOSServerConnected()) {
            throw new SMOSConnectionException("Cannot connect to SMOS server for notification.");
        }
        
        // Check for thread interruption (simulating admin interruption)
        if (Thread.currentThread().isInterrupted()) {
            LOGGER.info("Notification process interrupted before sending.");
            throw new InterruptedException("Notification interrupted");
        }
        
        // Simulate sending notification (e.g., email, SMS)
        String parentContact = note.getParentContactInfo();
        
        try {
            // Simulate network delay
            Thread.sleep(1000);
            
            // Check for interruption during delay
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException("Notification interrupted during sending");
            }
            
            // Simulate sending notification
            LOGGER.info("Notification sent to: " + parentContact);
            System.out.println("Notification content: Discipline note #" + note.getId() + 
                             " has been deleted. Student ID: " + note.getStudentId());
            
            // Simulate possible notification failure (10% chance for demonstration)
            if (Math.random() < 0.1) {
                throw new NotificationException("Notification service unavailable");
            }
            
            return true;
        } catch (InterruptedException e) {
            LOGGER.info("Notification sending interrupted.");
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw e;
        } catch (Exception e) {
            throw new NotificationException("Failed to send notification: " + e.getMessage());
        }
    }
    
    /**
     * Simulates checking SMOS server connection.
     * In a real system, this would check network connectivity.
     */
    private boolean isSMOSServerConnected() {
        // Simulate occasional connection issues (20% chance for demonstration)
        if (Math.random() < 0.2) {
            connectionStatus.set(false);
            return false;
        }
        connectionStatus.set(true);
        return true;
    }
}

/**
 * Archive system for storing and deleting notes.
 */
class Archive {
    private static final Logger LOGGER = Logger.getLogger(Archive.class.getName());
    
    /**
     * Deletes a note from the archive.
     * @param noteId The ID of the note to delete
     * @return true if deletion successful, false otherwise
     * @throws ArchiveException if deletion fails
     */
    public boolean deleteNote(int noteId) throws ArchiveException {
        LOGGER.info("Attempting to delete note from archive, ID: " + noteId);
        
        // Check for thread interruption (simulating admin interruption)
        if (Thread.currentThread().isInterrupted()) {
            LOGGER.info("Archive deletion process interrupted before execution.");
            throw new InterruptedException("Archive deletion interrupted");
        }
        
        try {
            // Simulate database/archive access delay
            Thread.sleep(800);
            
            // Check for interruption during delay
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException("Archive deletion interrupted during execution");
            }
            
            // Simulate deletion logic
            // In a real system, this would execute SQL DELETE or similar operation
            LOGGER.info("Note ID " + noteId + " deleted from archive.");
            
            // Simulate possible deletion failure (5% chance for demonstration)
            if (Math.random() < 0.05) {
                throw new ArchiveException("Database constraint violation or missing record");
            }
            
            return true;
        } catch (InterruptedException e) {
            LOGGER.info("Archive deletion interrupted.");
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw e;
        } catch (Exception e) {
            throw new ArchiveException("Failed to delete note from archive: " + e.getMessage());
        }
    }
}

/**
 * Custom exception for notification failures.
 */
class NotificationException extends Exception {
    public NotificationException(String message) {
        super(message);
    }
}

/**
 * Custom exception for archive operation failures.
 */
class ArchiveException extends Exception {
    public ArchiveException(String message) {
        super(message);
    }
}

/**
 * Custom exception for SMOS server connection interruptions.
 */
class SMOSConnectionException extends Exception {
    public SMOSConnectionException(String message) {
        super(message);
    }
}