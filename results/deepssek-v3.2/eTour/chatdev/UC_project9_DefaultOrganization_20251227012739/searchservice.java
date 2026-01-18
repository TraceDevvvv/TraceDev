'''
Service class that simulates the server-side search functionality.
In a real application, this would connect to a remote server (ETOUR).
For demonstration, it uses a mock database of refreshment points.
Includes simulation of server connection interruption.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class SearchService {
    // A mock list of refreshment points to simulate a database
    private List<RefreshmentPoint> mockDatabase;
    public SearchService() {
        initializeMockDatabase();
    }
    /**
     * Initializes the mock database with some sample refreshment points.
     */
    private void initializeMockDatabase() {
        mockDatabase = new ArrayList<>();
        mockDatabase.add(new RefreshmentPoint("Cafe Central", 4.5, 2.3));
        mockDatabase.add(new RefreshmentPoint("Rest Stop Alpha", 3.8, 5.7));
        mockDatabase.add(new RefreshmentPoint("Mountain View Diner", 4.9, 12.4));
        mockDatabase.add(new RefreshmentPoint("Quick Bite", 2.5, 1.1));
        mockDatabase.add(new RefreshmentPoint("Lakeside Lounge", 4.2, 8.9));
        mockDatabase.add(new RefreshmentPoint("Highway Oasis", 3.5, 15.0));
        mockDatabase.add(new RefreshmentPoint("Green Valley Rest Area", 4.7, 25.3));
        mockDatabase.add(new RefreshmentPoint("City Plaza Cafe", 4.0, 0.5));
    }
    /**
     * Searches refreshment points based on the given criteria.
     * Simulates server processing time (max 15 seconds as per quality requirement).
     * Also simulates a random chance of server connection interruption.
     *
     * @param name        (optional) part of the refreshment point name (case-insensitive partial match)
     * @param minRating   minimum rating (inclusive)
     * @param maxDistance maximum distance in km (inclusive)
     * @return list of refreshment points matching the criteria
     * @throws ServerConnectionException if the connection to the server ETOUR is interrupted
     */
    public List<RefreshmentPoint> searchRefreshmentPoints(String name, double minRating, double maxDistance)
            throws ServerConnectionException {
        // Validate input parameters
        if (minRating < 0 || minRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
        if (maxDistance < 0) {
            throw new IllegalArgumentException("Distance must be non-negative.");
        }
        // Simulate processing time: random delay up to 3 seconds (well under 15 seconds)
        // This is to demonstrate the GUI remains responsive thanks to SwingWorker.
        try {
            Random rand = new Random();
            int delay = rand.nextInt(3000); // up to 3 seconds
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServerConnectionException("Search interrupted.");
        }
        // Simulate random server connection interruption (10% chance) for demonstration
        Random rand = new Random();
        if (rand.nextInt(100) < 10) { // 10% probability
            throw new ServerConnectionException("Connection to server ETOUR was interrupted.");
        }
        // Perform the actual search on the mock database
        List<RefreshmentPoint> results = new ArrayList<>();
        for (RefreshmentPoint rp : mockDatabase) {
            boolean nameMatch = name.isEmpty() || rp.getName().toLowerCase().contains(name.toLowerCase());
            boolean ratingMatch = rp.getRating() >= minRating;
            boolean distanceMatch = rp.getDistance() <= maxDistance;
            if (nameMatch && ratingMatch && distanceMatch) {
                results.add(rp);
            }
        }
        return results;
    }
}