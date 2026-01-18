
// This service handles the search functionality for sites.
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.io.IOException;

public class SiteSearchService {
    // Simulated database of sites. In a real application, this could be a database or file system scan.
    private List<Site> siteDatabase;

    public SiteSearchService() {
        initializeDatabase();
    }

    // Initialize with some sample sites for demonstration.
    private void initializeDatabase() {
        siteDatabase = Arrays.asList(
            new Site("ProjectAlpha", "/home/user/projects/alpha"),
            new Site("ProjectBeta", "/home/user/projects/beta"),
            new Site("AlphaSite", "/var/www/alpha"),
            new Site("BetaSite", "/var/www/beta"),
            new Site("TestSite", "/tmp/test"),
            new Site("Demo", "/opt/demo")
        );
    }

    /**
     * Searches for sites matching the query.
     * @param query The search keyword.
     * @param timeoutSeconds The maximum time allowed for the search.
     * @return A list of matching sites.
     * @throws TimeoutException If the search exceeds the time limit.
     * @throws InterruptedException If the search is interrupted.
     * @throws ExecutionException If an error occurs during execution.
     */
    public List<Site> searchSites(String query, int timeoutSeconds) 
            throws TimeoutException, InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Site>> future = executor.submit(() -> performSearch(query));

        try {
            // Apply timeout as per quality requirement
            return future.get(timeoutSeconds, TimeUnit.SECONDS);
        } finally {
            executor.shutdown();
        }
    }

    // Simulate the actual search process (could be IO/network intensive).
    private List<Site> performSearch(String query) throws Exception {
        // Simulate processing delay (e.g., network or file system access)
        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 1000));

        // Simulate server interruption (ETOUR) with a random chance
        if (ThreadLocalRandom.current().nextDouble() < 0.05) {
            throw new ExecutionException(new IOException("Connection to server ETOUR interrupted"));
        }

        // Case-insensitive search in site names
        return siteDatabase.stream()
                .filter(site -> site.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
