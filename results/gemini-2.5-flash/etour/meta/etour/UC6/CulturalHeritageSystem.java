import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the core system that manages cultural heritage refreshment points.
 * It provides functionalities to search for points and delete them.
 * This class simulates potential connection interruptions.
 */
public class CulturalHeritageSystem {
    private List<RefreshmentPoint> refreshmentPoints;
    private boolean etourConnectionActive;

    /**
     * Constructs a new CulturalHeritageSystem and initializes it with some sample data.
     */
    public CulturalHeritageSystem() {
        this.refreshmentPoints = new ArrayList<>();
        // Initialize with some sample refreshment points
        refreshmentPoints.add(new RefreshmentPoint("RP001", "Cafe by the River", "City Center"));
        refreshmentPoints.add(new RefreshmentPoint("RP002", "Museum Bistro", "Historic District"));
        refreshmentPoints.add(new RefreshmentPoint("RP003", "Park Kiosk", "Central Park"));
        refreshmentPoints.add(new RefreshmentPoint("RP004", "Gallery Cafe", "Art Quarter"));
        this.etourConnectionActive = true; // Assume connection is active initially
    }

    /**
     * Simulates the "SearchCulturalHeritage" use case.
     * Returns a list of all available refreshment points.
     *
     * @return An unmodifiable list of RefreshmentPoint objects.
     * @throws ETOURConnectionException if the connection to the ETOUR server is interrupted.
     */
    public List<RefreshmentPoint> searchCulturalHeritage() throws ETOURConnectionException {
        // Simulate connection interruption
        if (!isETOURConnectionActive()) {
            throw new ETOURConnectionException("ETOUR connection interrupted during search.");
        }
        System.out.println("System: Displaying available refreshment points.");
        return Collections.unmodifiableList(new ArrayList<>(refreshmentPoints));
    }

    /**
     * Deletes a refreshment point from the system based on its ID.
     * This method simulates the actual deletion process.
     *
     * @param refreshmentPointId The ID of the refreshment point to delete.
     * @return true if the refreshment point was successfully deleted, false if not found.
     * @throws ETOURConnectionException if the connection to the ETOUR server is interrupted during deletion.
     */
    public boolean deleteRefreshmentPoint(String refreshmentPointId) throws ETOURConnectionException {
        // Simulate connection interruption
        if (!isETOURConnectionActive()) {
            throw new ETOURConnectionException("ETOUR connection interrupted during deletion.");
        }

        Optional<RefreshmentPoint> pointToDelete = refreshmentPoints.stream()
                                                                  .filter(rp -> rp.getId().equals(refreshmentPointId))
                                                                  .findFirst();

        if (pointToDelete.isPresent()) {
            refreshmentPoints.remove(pointToDelete.get());
            System.out.println("System: Refreshment point '" + refreshmentPointId + "' successfully deleted.");
            return true;
        } else {
            System.out.println("System: Refreshment point with ID '" + refreshmentPointId + "' not found.");
            return false;
        }
    }

    /**
     * Checks if the ETOUR connection is currently active.
     * This method can be used to simulate connection failures.
     *
     * @return true if the connection is active, false otherwise.
     */
    public boolean isETOURConnectionActive() {
        // Simulate a random chance of connection interruption for demonstration purposes
        // In a real system, this would be determined by actual network status
        if (ThreadLocalRandom.current().nextInt(100) < 5) { // 5% chance of interruption
            this.etourConnectionActive = false;
        }
        return etourConnectionActive;
    }

    /**
     * Simulates re-establishing the ETOUR connection.
     */
    public void reestablishETOURConnection() {
        System.out.println("System: Attempting to re-establish ETOUR connection...");
        this.etourConnectionActive = true; // For simplicity, assume it always succeeds
        System.out.println("System: ETOUR connection re-established.");
    }

    /**
     * Custom exception for ETOUR connection interruptions.
     */
    public static class ETOURConnectionException extends Exception {
        public ETOURConnectionException(String message) {
            super(message);
        }
    }
}