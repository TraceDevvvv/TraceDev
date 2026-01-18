/*
Simulates a service that searches for websites based on a query.
It includes simulated network delays and a chance of connection failure
to mimic real-world asynchronous operations and error conditions.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
public class SiteSearchService {
    // A predefined list of sites for simulation purposes
    private static final List<Site> SITE_DATABASE = Arrays.asList(
            new Site("Google", "https://www.google.com", "A popular search engine."),
            new Site("Bing", "https://www.bing.com", "Microsoft's search engine."),
            new Site("DuckDuckGo", "https://duckduckgo.com", "Privacy-focused search engine."),
            new Site("Wikipedia", "https://www.wikipedia.org", "The Free Encyclopedia."),
            new Site("YouTube", "https://www.youtube.com", "A video sharing platform."),
            new Site("GitHub", "https://github.com", "Platform for software development."),
            new Site("Stack Overflow", "https://stackoverflow.com", "Q&A site for programmers."),
            new Site("Java Documentation", "https://docs.oracle.com/en/java/", "Official Java documentation.")
    );
    // Minimum simulated delay for processing the search request (in milliseconds)
    private static final int SIMULATED_DELAY_MIN_MS = 1000;
    // Maximum simulated delay for processing the search request (in milliseconds)
    private static final int SIMULATED_DELAY_MAX_MS = 3000;
    // Probability (0.0 to 1.0) of a simulated connection failure
    private static final double CONNECTION_FAILURE_CHANCE = 0.2;
    private final Random random;
    /**
     * Constructs a SiteSearchService instance.
     */
    public SiteSearchService() {
        random = new Random();
    }
    /**
     * Searches for sites matching the given query string.
     * Simulates network latency and potential connection failures.
     *
     * @param query The search query string. Case-insensitive.
     * @return A list of Site objects that match the query.
     * @throws ConnectionInterruptionException If a simulated connection failure occurs.
     */
    public List<Site> searchSites(String query) throws ConnectionInterruptionException {
        // Simulate network delay to meet "time limit set" quality requirement
        try {
            Thread.sleep(random.nextInt(SIMULATED_DELAY_MAX_MS - SIMULATED_DELAY_MIN_MS) + SIMULATED_DELAY_MIN_MS);
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            throw new ConnectionInterruptionException("Search interrupted during delay.", e);
        }
        // Simulate connection interruption to server ETOUR
        if (random.nextDouble() < CONNECTION_FAILURE_CHANCE) {
            throw new ConnectionInterruptionException("Interruption of the connection to the server ETOUR.");
        }
        final String lowerCaseQuery = query.toLowerCase();
        // Filter the database for sites matching the query
        return SITE_DATABASE.stream()
                .filter(site -> site.getName().toLowerCase().contains(lowerCaseQuery) ||
                                site.getDescription().toLowerCase().contains(lowerCaseQuery) ||
                                site.getUrl().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
    /**
     * Custom exception for simulating connection interruptions.
     */
    public static class ConnectionInterruptionException extends Exception {
        public ConnectionInterruptionException(String message) {
            super(message);
        }
        public ConnectionInterruptionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}