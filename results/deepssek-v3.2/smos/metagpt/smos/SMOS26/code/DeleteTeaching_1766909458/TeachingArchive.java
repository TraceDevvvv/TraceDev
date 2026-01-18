import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Manages a collection of teachings in the archive.
 * Provides functionality to add, retrieve, and delete teachings.
 * Implements thread-safe operations for concurrent access.
 */
public class TeachingArchive {
    private Map<Integer, Teaching> teachings;
    private ReentrantReadWriteLock lock;
    
    /**
     * Constructs a new empty TeachingArchive.
     */
    public TeachingArchive() {
        this.teachings = new HashMap<>();
        this.lock = new ReentrantReadWriteLock(true); // Fair lock for thread safety
    }
    
    /**
     * Adds a teaching to the archive.
     * Validates that the teaching is not null and that no teaching with the same ID exists.
     * 
     * @param teaching The teaching to add
     * @return true if the teaching was added successfully, false if a teaching with the same ID already exists
     * @throws IllegalArgumentException if teaching is null
     */
    public boolean addTeaching(Teaching teaching) {
        if (teaching == null) {
            throw new IllegalArgumentException("Teaching cannot be null.");
        }
        
        lock.writeLock().lock();
        try {
            int teachingId = teaching.getId();
            
            // Check if teaching with the same ID already exists
            if (teachings.containsKey(teachingId)) {
                System.out.println("Warning: Teaching with ID " + teachingId + " already exists in archive.");
                return false;
            }
            
            teachings.put(teachingId, teaching);
            System.out.println("Teaching added to archive: " + teaching.getCourseName() + " (ID: " + teachingId + ")");
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Deletes a teaching from the archive.
     * Only administrators can delete teachings.
     * Follows the sequence described in use case: eliminate teaching from archive.
     * 
     * @param teachingId The ID of the teaching to delete
     * @param admin The administrator attempting the deletion
     * @return true if the teaching was deleted successfully, false otherwise
     * @throws IllegalArgumentException if teachingId is invalid or admin is null
     * @throws SecurityException if the administrator doesn't have delete permission
     */
    public boolean deleteTeaching(int teachingId, Administrator admin) {
        if (teachingId <= 0) {
            throw new IllegalArgumentException("Teaching ID must be a positive integer.");
        }
        
        if (admin == null) {
            throw new IllegalArgumentException("Administrator cannot be null.");
        }
        
        // Check if admin has delete permission
        if (!admin.hasDeletePermission()) {
            throw new SecurityException("Administrator does not have permission to delete teachings.");
        }
        
        lock.writeLock().lock();
        try {
            if (!teachings.containsKey(teachingId)) {
                System.out.println("Teaching with ID " + teachingId + " not found in archive.");
                return false;
            }
            
            Teaching teachingToDelete = teachings.get(teachingId);
            
            // Additional validation: Check if teaching can be deleted
            // (e.g., check if it's currently active)
            if (teachingToDelete.isCurrentlyActive()) {
                System.out.println("Warning: Teaching '" + teachingToDelete.getCourseName() + 
                                 "' is currently active. Proceeding with deletion anyway.");
            }
            
            // Remove teaching from archive
            teachings.remove(teachingId);
            System.out.println("Teaching deleted from archive: " + 
                             teachingToDelete.getCourseName() + " (ID: " + teachingId + ")");
            
            // Log the deletion for audit purposes
            admin.logDeletion(teachingId, teachingToDelete.getCourseName());
            
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Retrieves all teachings from the archive.
     * Returns an unmodifiable list to prevent external modification.
     * 
     * @return List of all teachings in the archive
     */
    public List<Teaching> getAllTeachings() {
        lock.readLock().lock();
        try {
            // Return a defensive copy to prevent modification of internal data
            return Collections.unmodifiableList(new ArrayList<>(teachings.values()));
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Retrieves a teaching by its ID.
     * 
     * @param teachingId The ID of the teaching to retrieve
     * @return The teaching with the specified ID, or null if not found
     * @throws IllegalArgumentException if teachingId is invalid
     */
    public Teaching getTeachingById(int teachingId) {
        if (teachingId <= 0) {
            throw new IllegalArgumentException("Teaching ID must be a positive integer.");
        }
        
        lock.readLock().lock();
        try {
            return teachings.get(teachingId);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Checks if a teaching with the specified ID exists in the archive.
     * 
     * @param teachingId The ID to check
     * @return true if a teaching with the ID exists, false otherwise
     * @throws IllegalArgumentException if teachingId is invalid
     */
    public boolean containsTeaching(int teachingId) {
        if (teachingId <= 0) {
            throw new IllegalArgumentException("Teaching ID must be a positive integer.");
        }
        
        lock.readLock().lock();
        try {
            return teachings.containsKey(teachingId);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets the number of teachings in the archive.
     * 
     * @return The total count of teachings
     */
    public int getTeachingCount() {
        lock.readLock().lock();
        try {
            return teachings.size();
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Gets teachings taught by a specific instructor.
     * 
     * @param instructorName The name of the instructor
     * @return List of teachings by the specified instructor
     * @throws IllegalArgumentException if instructorName is null or empty
     */
    public List<Teaching> getTeachingsByInstructor(String instructorName) {
        if (instructorName == null || instructorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be null or empty.");
        }
        
        lock.readLock().lock();
        try {
            List<Teaching> result = new ArrayList<>();
            String normalizedName = instructorName.trim();
            
            for (Teaching teaching : teachings.values()) {
                if (teaching.getInstructor().equalsIgnoreCase(normalizedName)) {
                    result.add(teaching);
                }
            }
            
            return Collections.unmodifiableList(result);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    /**
     * Clears all teachings from the archive (for testing/reset purposes).
     * Admin permission is required for this operation.
     * 
     * @param admin The administrator attempting the operation
     * @return true if the archive was cleared successfully
     * @throws SecurityException if the administrator doesn't have permission
     */
    public boolean clearArchive(Administrator admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Administrator cannot be null.");
        }
        
        if (!admin.hasClearArchivePermission()) {
            throw new SecurityException("Administrator does not have permission to clear the archive.");
        }
        
        lock.writeLock().lock();
        try {
            int count = teachings.size();
            teachings.clear();
            System.out.println("Archive cleared. Removed " + count + " teachings.");
            
            // Log the clear operation for audit purposes
            admin.logArchiveClear(count);
            
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    /**
     * Returns a string representation of the archive status.
     * 
     * @return String containing archive statistics
     */
    @Override
    public String toString() {
        lock.readLock().lock();
        try {
            return "TeachingArchive{teachingsCount=" + teachings.size() + "}";
        } finally {
            lock.readLock().unlock();
        }
    }
}