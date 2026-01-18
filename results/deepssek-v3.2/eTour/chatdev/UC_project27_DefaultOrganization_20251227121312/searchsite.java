/**
 * SearchSite - A program to search for specific sites on the computer.
 * This implementation uses mock data and includes a proper timeout mechanism.
 * Implements the SearchSite use case with all required functionality.
 */
import java.util.*;
import java.util.concurrent.*;
public class SearchSite {
    // Simulated database of sites (in real scenario, this could be file system paths or network resources)
    private static final List<String> SITES = Arrays.asList(
        "C:/WebSites/index.html",
        "D:/Projects/Site1/home.html",
        "C:/Users/Admin/Documents/site2/login.php",
        "E:/Backup/site3/about.html",
        "C:/WebSites/contact.html"
    );
    // Random generator for simulating server interruptions
    private static final Random RANDOM = new Random();
    /**
     * Simulates a search request with proper timeout and interruption handling.
     * @param query The search keyword to look for in site paths
     * @param timeoutMillis Maximum allowed time for search in milliseconds
     * @return List of matching site paths
     * @throws ConnectionInterruptedException If connection to server is interrupted
     * @throws TimeoutException If search exceeds the time limit
     */
    private static List<String> searchSites(String query, long timeoutMillis) 
            throws ConnectionInterruptedException, TimeoutException {
        // Create executor service for timeout handling
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<String>> future = executor.submit(() -> {
            // Simulate server connection interruption (10% chance)
            if (RANDOM.nextInt(10) == 0) {
                Thread.currentThread().interrupt();
                throw new InterruptedException("Connection to server ETOUR interrupted.");
            }
            // Simulate processing delay
            try {
                Thread.sleep(200); // Simulate network/processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw e;
            }
            // Perform search (case-insensitive partial match)
            List<String> results = new ArrayList<>();
            for (String site : SITES) {
                if (site.toLowerCase().contains(query.toLowerCase())) {
                    results.add(site);
                }
            }
            return results;
        });
        try {
            // Get results with timeout
            return future.get(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true); // Cancel the task if it times out
            throw new TimeoutException("Search exceeded time limit of " + timeoutMillis + " ms.");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof InterruptedException) {
                throw new ConnectionInterruptedException("Connection to server ETOUR interrupted.");
            }
            throw new RuntimeException("Search failed: " + cause.getMessage(), cause);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionInterruptedException("Connection to server ETOUR interrupted.");
        } finally {
            executor.shutdown();
        }
    }
    /**
     * Helper method to validate timeout input
     */
    private static long getTimeoutFromUser(Scanner scanner) {
        while (true) {
            System.out.print("Enter timeout in milliseconds (default 5000, min 100): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return 5000; // Default timeout
            }
            try {
                long timeout = Long.parseLong(input);
                if (timeout < 100) {
                    System.out.println("Timeout must be at least 100 ms. Please try again.");
                    continue;
                }
                return timeout;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    /**
     * Main method: Entry point of the program, implements the use case flow.
     * 1. Activates search functionality
     * 2. Shows search form
     * 3. Accepts user input and submits
     * 4. Processes the request with timeout
     * 5. Displays results or handles exceptions
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SearchSite Program ===");
        System.out.println("Activate search functionality (press Enter to continue)...");
        scanner.nextLine(); // Step 1: Activate search
        // Step 2: Show form for research
        System.out.println("\n--- Search Form ---");
        System.out.print("Enter site name or keyword to search: ");
        String query = scanner.nextLine(); // Step 3: Fill form and submit
        System.out.println("\n--- Timeout Configuration ---");
        long timeout = getTimeoutFromUser(scanner);
        System.out.println("\nProcessing request with timeout of " + timeout + " ms..."); // Step 4: Processing
        try {
            // Perform search with proper timeout handling
            long startTime = System.currentTimeMillis();
            List<String> results = searchSites(query, timeout);
            long endTime = System.currentTimeMillis();
            // Exit condition: return list of sites found
            if (results.isEmpty()) {
                System.out.println("\nNo sites found matching: " + query);
            } else {
                System.out.println("\nSites found (" + results.size() + ") in " + (endTime - startTime) + " ms:");
                for (String site : results) {
                    System.out.println("  - " + site);
                }
            }
        } catch (ConnectionInterruptedException e) {
            System.err.println("\nError: " + e.getMessage());
            System.err.println("Please check your server connection and try again.");
        } catch (TimeoutException e) {
            System.err.println("\nError: " + e.getMessage());
            System.err.println("Try increasing the timeout or refining your search query.");
        } catch (Exception e) {
            System.err.println("\nUnexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            System.out.println("\nSearch completed.");
        }
    }
}