import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * Simulates interaction with the ETOUR server to retrieve personal favorites.
 * This class includes mock data, simulated network delays, and a chance of connection failure.
 */
class ETOURService {
    private static final Random random = new Random(); // Random generator for simulating connection failures
    /**
     * Simulates fetching a list of favorite sites from the ETOUR server.
     * This method introduces a simulated network delay and might throw a ConnectionException.
     *
     * @return A list of Favorite objects if the connection is successful.
     * @throws ConnectionException if there's a simulated interruption of the connection to the ETOUR server.
     */
    public List<Favorite> getFavorites() throws ConnectionException {
        // Simulate network delay
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(3) + 1); // Simulate 1-3 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new ConnectionException("Operation interrupted while connecting to ETOUR.");
        }
        // Simulate connection interruption (e.g., 20% chance of failure)
        if (random.nextDouble() < 0.2) {
            throw new ConnectionException("Interruption of the connection to the server ETOUR.");
        }
        // Return mock data for demonstration purposes
        return Arrays.asList(
                new Favorite("Eiffel Tower", "https://www.toureiffel.paris"),
                new Favorite("Colosseum", "https://www.coopculture.it/en/sites/colosseum.cfm"),
                new Favorite("Great Wall of China", "https://www.travelchinaguide.com/china_great_wall/"),
                new Favorite("Machu Picchu", "https://www.machupicchu.gob.pe/"),
                new Favorite("Pyramids of Giza", "https://egyptianmuseum.org/pyramids-giza"),
                new Favorite("Taj Mahal", "https://www.tajmahal.gov.in/")
        );
    }
}