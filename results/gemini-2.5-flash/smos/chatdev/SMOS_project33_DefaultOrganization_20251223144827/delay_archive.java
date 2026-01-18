/*
DelayArchive.java
 DOCSTRING
    This class acts as a simulated data archive for student delay entries.
    It provides methods to store, retrieve, and delete Delay objects.
    For this simulation, data is stored in memory using a HashMap,
    mapping LocalDate to a list of Delay objects for that date.
    New delays are assigned a unique ID.
*/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
public class DelayArchive {
    // Stores delays, mapping a date to a list of delays on that date
    private Map<LocalDate, List<Delay>> archive;
    // Used to generate unique IDs for new delays
    private AtomicInteger nextId;
    // Add this field
    private boolean smosServerConnected = true; // Default to connected
    public DelayArchive() {
        this.archive = new HashMap<>();
        this.nextId = new AtomicInteger(100); // Start IDs from 100 for example
        // --- Populate with some sample data ---
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate lastWeek = today.minusWeeks(1);
        addDelay("Alice Smith", today, "Traffic jam");
        addDelay("Bob Johnson", today, "Overslept");
        addDelay("Charlie Brown", today, "Dentist appointment");
        addDelay("Diana Miller", yesterday, "Bus delay");
        addDelay("Eve Davis", lastWeek, "Illness");
    }
    /**
     * Adds a new delay to the archive. Assigns a unique ID automatically.
     * @param studentName The name of the student.
     * @param date The date of the delay.
     * @param reason The reason for the delay.
     * @return The newly created Delay object.
     */
    public Delay addDelay(String studentName, LocalDate date, String reason) {
        int id = nextId.getAndIncrement(); // Get next unique ID
        Delay newDelay = new Delay(id, studentName, date, reason);
        // If the date is not already in the archive, add a new list for it
        archive.computeIfAbsent(date, k -> new ArrayList<>()).add(newDelay);
        return newDelay;
    }
    /**
     * Retrieves a list of all delays recorded for a specific date.
     * @param date The date for which to retrieve delays.
     * @return A list of Delay objects for the given date. Returns an empty list if no delays are found.
     */
    public List<Delay> getDelaysByDate(LocalDate date) {
        // Return a new ArrayList to prevent external modifications to the internal list
        return new ArrayList<>(archive.getOrDefault(date, new ArrayList<>()));
    }
    /**
     * Deletes a specific delay from the archive based on its ID and date.
     * This method simulates deletion from a persistent store.
     * @param date The date of the delay to be deleted.
     * @param delayId The ID of the delay to be deleted.
     * @return true if the delay was found and successfully deleted, false otherwise.
     */
    public boolean deleteDelay(LocalDate date, int delayId) {
        List<Delay> delaysOnDate = archive.get(date);
        if (delaysOnDate != null) {
            // Remove the delay with the matching ID
            boolean removed = delaysOnDate.removeIf(delay -> delay.getId() == delayId);
            // If the list becomes empty after removal, remove the date entry from the map
            if (delaysOnDate.isEmpty()) {
                archive.remove(date);
            }
            return removed;
        }
        return false; // No delays found for the given date
    }
    /**
     * Returns a list of all dates for which delays are recorded in the archive.
     * @return A list of LocalDate objects.
     */
    public List<LocalDate> getAllDatesWithDelays() {
        List<LocalDate> dates = new ArrayList<>(archive.keySet());
        dates.sort(LocalDate::compareTo); // Sort dates for consistent display
        return dates;
    }
    /**
     * Checks if there's any active connection to a simulated external server.
     * This status can be toggled by the administrator for testing.
     * @return true if connection is active, false otherwise.
     */
    public boolean isSMOSServerConnected() {
        return smosServerConnected;
    }
    /**
     * Sets the simulated connection status to the SMOS server.
     * @param smosServerConnected true if connected, false if interrupted.
     */
    public void setSMOSServerConnected(boolean smosServerConnected) {
        this.smosServerConnected = smosServerConnected;
    }
    // Method for testing/debugging
    public void printArchiveContents() {
        System.out.println("--- Current Delay Archive Contents ---");
        archive.forEach((date, delays) -> {
            System.out.println("Date: " + date);
            delays.forEach(delay -> System.out.println("  " + delay));
        });
        System.out.println("------------------------------------");
    }
}