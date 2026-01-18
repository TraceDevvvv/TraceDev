package application;

import domain.LateEntry;
import infrastructure.ILateEntryRepository;
import java.util.Date;
import java.util.List;

/**
 * Application Service for managing the elimination of delays.
 * This class orchestrates domain objects and infrastructure components
 * to perform application-specific business logic.
 * It belongs to the Application Layer.
 */
public class DelayEliminationService {

    private final ILateEntryRepository lateEntryRepository;

    /**
     * Constructs a DelayEliminationService with a dependency on ILateEntryRepository.
     * @param lateEntryRepository The repository for accessing LateEntry data.
     */
    public DelayEliminationService(ILateEntryRepository lateEntryRepository) {
        // Dependency injection of the repository interface
        this.lateEntryRepository = lateEntryRepository;
    }

    /**
     * Retrieves a list of late entries for a given date.
     * This method delegates to the infrastructure layer to fetch the data.
     * @param date The specific date for which to retrieve late entries.
     * @return A list of LateEntry objects.
     */
    public List<LateEntry> getLateEntriesForDate(Date date) {
        System.out.println("[Service] Requesting late entries for date: " + date);
        // Delegates the data retrieval to the repository
        return lateEntryRepository.findByDate(date);
    }

    /**
     * Eliminates a specific late entry by its ID.
     * This method encapsulates the business logic for removing a delay,
     * delegating the actual data deletion to the infrastructure layer.
     * @param lateEntryId The unique identifier of the late entry to eliminate.
     */
    public void eliminateDelay(String lateEntryId) {
        System.out.println("[Service] Eliminating delay for late entry ID: " + lateEntryId);
        // Delegates the deletion to the repository
        lateEntryRepository.delete(lateEntryId);
        // Additional business logic could be added here, e.g., logging, notifying other serv.
    }
}