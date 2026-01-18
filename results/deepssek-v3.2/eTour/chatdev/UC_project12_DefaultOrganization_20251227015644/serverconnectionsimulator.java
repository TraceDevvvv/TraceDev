/**
 * Simulates server connection and data fetching for the ETOUR system.
 * This class includes simulated server interruption scenarios for testing.
 */
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class ServerConnectionSimulator {
    private static Random random = new Random();
    /**
     * Simulates fetching tourist data from the ETOUR server.
     * Randomly throws ConnectionInterruptedException to simulate network issues.
     * @return List of Tourist objects
     * @throws ConnectionInterruptedException if server connection is interrupted
     */
    public static List<Tourist> fetchTouristData() throws ConnectionInterruptedException {
        // Simulate 30% chance of connection interruption
        if (random.nextInt(10) < 3) {
            throw new ConnectionInterruptedException("Connection to ETOUR server was interrupted");
        }
        // Return simulated tourist data
        return Arrays.asList(
            new Tourist("T001", "John Smith", "USA", "john@email.com", "123-456-7890", "P123456", "1985-03-15"),
            new Tourist("T002", "Maria Garcia", "Spain", "maria@email.com", "987-654-3210", "P654321", "1990-07-22"),
            new Tourist("T003", "Kenji Tanaka", "Japan", "kenji@email.com", "555-123-4567", "P789012", "1988-11-05"),
            new Tourist("T004", "Sophie Dubois", "France", "sophie@email.com", "333-444-5555", "P345678", "1992-04-18"),
            new Tourist("T005", "Ahmed Hassan", "Egypt", "ahmed@email.com", "111-222-3333", "P901234", "1983-09-30")
        );
    }
    /**
     * Simulates fetching specific tourist details by ID
     * @param touristId The ID of the tourist to fetch
     * @return Tourist object or null if not found
     * @throws ConnectionInterruptedException if server connection is interrupted
     */
    public static Tourist fetchTouristById(String touristId) throws ConnectionInterruptedException {
        // Simulate 20% chance of connection interruption
        if (random.nextInt(10) < 2) {
            throw new ConnectionInterruptedException("Connection to ETOUR server was interrupted");
        }
        // Simulate fetching from server (in reality this would be a database query)
        List<Tourist> allTourists = fetchTouristData();
        return allTourists.stream()
                .filter(t -> t.getId().equals(touristId))
                .findFirst()
                .orElse(null);
    }
}