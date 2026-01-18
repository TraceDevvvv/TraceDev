package infrastructure;

import domain.LateEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of ILateEntryRepository.
 * This implementation uses an in-memory list to simulate a data store for demonstration purposes.
 * It belongs to the Infrastructure Layer.
 */
public class LateEntryRepositoryImpl implements ILateEntryRepository {

    // Simulates a database table for LateEntry objects
    private final List<LateEntry> dataStore = new ArrayList<>();

    // Constructor to pre-populate some data for testing
    public LateEntryRepositoryImpl() {
        // Add some dummy data
        dataStore.add(new LateEntry("LE001", "S001", new Date(System.currentTimeMillis() - 86400000L * 2), "Assignment 1 submitted late"));
        dataStore.add(new LateEntry("LE002", "S002", new Date(System.currentTimeMillis() - 86400000L), "Project proposal submitted late"));
        dataStore.add(new LateEntry("LE003", "S001", new Date(), "Attendance entry late"));
        dataStore.add(new LateEntry("LE004", "S003", new Date(System.currentTimeMillis() - 86400000L), "Lab report late"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LateEntry> findByDate(Date date) {
        // In a real application, this would query a database.
        // For simulation, we filter the in-memory list by date.
        // Note: Date comparison can be tricky. For simplicity, we'll compare year, month, day.
        // This is a common simplification for demonstration but might need a more robust solution
        // for production (e.g., using java.time.LocalDate for precise date comparisons).
        List<LateEntry> result = dataStore.stream()
                .filter(entry -> {
                    // Normalize dates to compare only day, month, year
                    Date entryDate = entry.getEntryDate();
                    return entryDate.getYear() == date.getYear() &&
                           entryDate.getMonth() == date.getMonth() &&
                           entryDate.getDate() == date.getDate();
                })
                .collect(Collectors.toList());
        System.out.println("[Repository] Found " + result.size() + " late entries for date: " + date);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String lateEntryId) {
        // In a real application, this would delete from a database.
        // For simulation, we remove from the in-memory list.
        boolean removed = dataStore.removeIf(entry -> entry.getId().equals(lateEntryId));
        if (removed) {
            System.out.println("[Repository] Deleted late entry with ID: " + lateEntryId);
            // 4. System deletes the late entry from the archive.
            System.out.println("[Repository] 4. System deletes the late entry from the archive.");
        } else {
            System.out.println("[Repository] Late entry with ID: " + lateEntryId + " not found for deletion.");
        }
    }
}