/**
 * SiteService.java - Simulates a service that fetches visited sites with feedback from a server.
 * In a real application, this would connect to a database or remote API (e.g., ETOUR server).
 * This mock implementation returns a hardcoded list for demonstration, and can simulate server interruption.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class SiteService {
    private Random random = new Random();
    /**
     * Fetches the list of sites for which the authenticated tourist has submitted feedback.
     * Simulates potential server connection interruption by random exception.
     * @return List of Site objects.
     * @throws RuntimeException if server connection is interrupted (simulated).
     */
    public List<Site> fetchVisitedSitesForTourist() {
        // Simulate occasional server interruption (20% chance)
        if (random.nextInt(10) < 2) { // 20% probability
            throw new RuntimeException("ETOUR server connection interrupted");
        }
        // In a real scenario, this method would call a REST API or database.
        // Here we return mock data.
        List<Site> sites = new ArrayList<>();
        sites.add(new Site("Eiffel Tower", "Paris, France", "2023-05-15", 5));
        sites.add(new Site("Colosseum", "Rome, Italy", "2023-06-22", 4));
        sites.add(new Site("Grand Canyon", "Arizona, USA", "2023-04-10", 5));
        sites.add(new Site("Great Wall of China", "Beijing, China", "2023-08-30", 4));
        sites.add(new Site("Machu Picchu", "Cuzco Region, Peru", "2023-07-18", 5));
        return sites;
    }
}