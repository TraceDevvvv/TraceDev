package infrastructure;

import domain.LateEntry;
import java.util.Date;
import java.util.List;

/**
 * Interface for managing (finding and deleting) LateEntry objects.
 * This is part of the Infrastructure Layer, abstracting data access.
 */
public interface ILateEntryRepository {

    /**
     * Finds all late entries recorded on a specific date.
     * @param date The date to search for late entries.
     * @return A list of LateEntry objects matching the given date.
     */
    List<LateEntry> findByDate(Date date);

    /**
     * Deletes a late entry identified by its unique ID.
     * @param lateEntryId The ID of the late entry to delete.
     */
    void delete(String lateEntryId);
}