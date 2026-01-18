/**
 * Simulates a database for late entries.
 * In a real application, this would connect to an actual database (e.g., SMOS server).
 * Handles server connection interruption as per use case postcondition.
 */
import java.util.*;
public class DatabaseSimulator {
    private List<LateEntry> lateEntries;
    private int nextId = 1;
    // Simulate server connection state for SMOS server
    private boolean serverConnected = true;
    public DatabaseSimulator() {
        lateEntries = new ArrayList<>();
    }
    /**
     * Add sample late entries for demonstration.
     */
    public void populateSampleData() {
        addLateEntry("John Doe", "Mathematics", "2023-10-01", "Traffic jam");
        addLateEntry("Jane Smith", "Physics", "2023-10-01", "Overslept");
        addLateEntry("Bob Johnson", "Chemistry", "2023-10-02", "Family emergency");
        addLateEntry("Alice Brown", "Mathematics", "2023-10-02", "Public transport delay");
        addLateEntry("Charlie Wilson", "History", "2023-10-03", "Car breakdown");
        addLateEntry("David Lee", "Computer Science", "2023-10-03", "Software bug");
        addLateEntry("Emma Watson", "Literature", "2023-10-04", "Book reading overtime");
    }
    /**
     * Add a new late entry to the simulated database.
     * @param studentName name of the student
     * @param course course name
     * @param date date string (YYYY-MM-DD)
     * @param reason reason for delay
     */
    public void addLateEntry(String studentName, String course, String date, String reason) {
        LateEntry entry = new LateEntry(nextId++, studentName, course, date, reason);
        lateEntries.add(entry);
    }
    /**
     * Retrieve all late entries for a specific date.
     * @param date the date string to filter by
     * @return list of late entries matching the date
     */
    public List<LateEntry> getLateEntriesByDate(String date) {
        // Check server connection first
        if (!serverConnected) {
            throw new RuntimeException("SMOS server connection interrupted");
        }
        List<LateEntry> result = new ArrayList<>();
        for (LateEntry entry : lateEntries) {
            if (entry.getDate().equals(date)) {
                result.add(entry);
            }
        }
        return result;
    }
    /**
     * Delete a late entry by its ID (simulates removal from archive).
     * Handles server connection interruptions as per postconditions.
     * @param id the unique ID of the late entry
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteLateEntry(int id) {
        // Check server connection before operation (postcondition: Connection to SMOS server interrupted)
        if (!serverConnected) {
            return false;
        }
        // Simulate potential server connection interruption during operation (20% probability)
        if (Math.random() < 0.2) {
            serverConnected = false;
            return false;
        }
        // Use Iterator to avoid ConcurrentModificationException
        Iterator<LateEntry> iterator = lateEntries.iterator();
        while (iterator.hasNext()) {
            LateEntry entry = iterator.next();
            if (entry.getId() == id) {
                iterator.remove();
                // Simulate successful archive deletion
                return true;
            }
        }
        return false;
    }
    /**
     * Get available dates from existing late entries.
     * @return a set of dates sorted in reverse chronological order (most recent first)
     */
    public Set<String> getAvailableDates() {
        // Check server connection
        if (!serverConnected) {
            return new TreeSet<>(Collections.reverseOrder());
        }
        Set<String> dates = new TreeSet<>(Collections.reverseOrder()); // Most recent first
        for (LateEntry entry : lateEntries) {
            dates.add(entry.getDate());
        }
        return dates;
    }
    /**
     * Simulate reconnection to SMOS server (for recovery scenarios)
     * Implements recovery from connection interruption postcondition.
     * @return true if reconnection successful
     */
    public boolean reconnectServer() {
        // Simulate reconnection attempt with 90% success rate
        if (Math.random() < 0.9) {
            serverConnected = true;
            return true;
        }
        return false;
    }
    /**
     * Check SMOS server connection status
     * @return true if server is connected
     */
    public boolean isServerConnected() {
        return serverConnected;
    }
    /**
     * Get all late entries (for debugging or other uses).
     * @return all late entries
     */
    public List<LateEntry> getAllLateEntries() {
        if (!serverConnected) {
            throw new RuntimeException("SMOS server connection interrupted");
        }
        return new ArrayList<>(lateEntries);
    }
    /**
     * Simulate manual disconnection of SMOS server (for testing)
     */
    public void disconnectServer() {
        serverConnected = false;
    }
}