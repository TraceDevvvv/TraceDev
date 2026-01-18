/**
 * This class simulates the business logic and data access layer for RefreshmentPoint objects.
 * It manages a collection of refreshment points and provides methods for retrieving and deleting them.
 * It also simulates potential service interruptions.
 */
package com.chatdev.service;
import com.chatdev.model.RefreshmentPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 * This class simulates the business logic and data access layer for RefreshmentPoint objects.
 * It manages a collection of refreshment points and provides methods for retrieving and deleting them.
 * It also simulates potential service interruptions.
 */
public class RefreshmentService {
    // Simulated in-memory database for refreshment points
    private List<RefreshmentPoint> refreshmentPoints;
    /**
     * Initializes the service with some sample data.
     */
    public RefreshmentService() {
        refreshmentPoints = new ArrayList<>();
        refreshmentPoints.add(new RefreshmentPoint(101, "Cafe Central", "Historic District"));
        refreshmentPoints.add(new RefreshmentPoint(102, "Park Kiosk", "Central Park West"));
        refreshmentPoints.add(new RefreshmentPoint(103, "Museum Eatery", "Art Gallery Area"));
        refreshmentPoints.add(new RefreshmentPoint(104, "Riverside Cafe", "Riverside Promenade"));
        refreshmentPoints.add(new RefreshmentPoint(105, "Old Town Brewery", "Old Town Square"));
    }
    /**
     * Retrieves all refreshment points currently in the system.
     * This simulates the result of a 'RicercaBeneCulturale' use case presenting the list.
     * @return A list of all RefreshmentPoint objects.
     * @throws ServiceException if a simulated connection interruption occurs.
     */
    public List<RefreshmentPoint> getAllRefreshmentPoints() throws ServiceException {
        // Simulate a connection interruption with a 10% chance
        if (ThreadLocalRandom.current().nextInt(10) == 0) {
            throw new ServiceException("ETOUR connection to the server interrupted while fetching data.");
        }
        // Return a new ArrayList to prevent external modifications to the internal list
        return new ArrayList<>(refreshmentPoints);
    }
    /**
     * Deletes a refreshment point from the system based on its ID.
     * @param id The ID of the refreshment point to delete.
     * @return true if the refreshment point was successfully deleted, false otherwise (e.g., not found).
     * @throws ServiceException if a simulated connection interruption or deletion error occurs.
     */
    public boolean deleteRefreshmentPoint(int id) throws ServiceException {
        // Simulate a connection interruption with a 15% chance during deletion
        if (ThreadLocalRandom.current().nextInt(100) < 15) {
            throw new ServiceException("ETOUR connection to the server interrupted during deletion.");
        }
        Iterator<RefreshmentPoint> iterator = refreshmentPoints.iterator();
        while (iterator.hasNext()) {
            RefreshmentPoint rp = iterator.next();
            if (rp.getId() == id) {
                iterator.remove();
                return true; // Successfully deleted
            }
        }
        return false; // Refreshment point not found
    }
}