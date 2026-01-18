import java.util.List;
import java.util.Scanner;
import java java.util.concurrent.*;

/**
 * Main application class for the SearchSite program.
 * This class orchestrates the user interaction, search functionality, and result display.
 */
public class SearchSiteApp {

    private static final long SEARCH_TIMEOUT_SECONDS = 10; // Quality requirement: return results within a time limit

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SiteSearcher siteSearcher = new SiteSearcher(); // Initialize the site searcher
        ExecutorService executor = Executors.newSingleThreadExecutor(); // For handling search timeout

        System.out.println("Welcome to the Site Search Application!");

        // 1. Activate the search functionality of a site.
        // This is implicitly done by starting the application.

        while (true) {
            // 2. Show the form for research
            SearchForm searchForm = new SearchForm();
            System.out.println("\n--- Search Form ---");
            System.out.print("Enter search query (e.g., 'example.com', 'local files'): ");
            searchForm.setQuery(scanner.nextLine());

            System.out.print("Enter search path (optional, e.g., 'C:/', '/home/user/documents'): ");
            String pathInput = scanner.nextLine();
            if (!pathInput.isEmpty()) {
                searchForm.setSearchPath(pathInput);
            }

            // 3. Fill in the form of research and submit
            System.out.println("Submitting search request...");

            // 4. Processing the request
            // Use a Future to manage the search task and apply a timeout.
            Future<List<SearchResult>> futureResults = executor.submit(() -> {
                try {
                    return siteSearcher.search(searchForm);
                } catch (Exception e) {
                    // Catch any exceptions during search and rethrow as a RuntimeException
                    // to be handled by the main thread.
                    throw new RuntimeException("Error during search: " + e.getMessage(), e);
                }
            });

            try {
                List<SearchResult> results = futureResults.get(SEARCH_TIMEOUT_SECONDS, TimeUnit.SECONDS);

                // Exit conditions: The system returns a list of sites found
                if (results.isEmpty()) {
                    System.out.println("No sites found matching your query.");
                } else {
                    System.out.println("\n--- Search Results ---");
                    for (int i = 0; i < results.size(); i++) {
                        System.out.println((i + 1) + ". " + results.get(i));
                    }
                }
            } catch (TimeoutException e) {
                System.err.println("Search timed out after " + SEARCH_TIMEOUT_SECONDS + " seconds. Please try a more specific query or a smaller search path.");
                futureResults.cancel(true); // Interrupt the search task
            } catch (InterruptedException e) {
                System.err.println("Search was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Restore the interrupted status
            } catch (ExecutionException e) {
                // Handle exceptions thrown by the searcher.
                Throwable cause = e.getCause();
                if (cause instanceof RuntimeException && cause.getMessage().contains("ETOUR")) {
                    // Interruption of the connection to the server ETOUR.
                    System.err.println("Connection to server interrupted (ETOUR error): " + cause.getMessage());
                } else {
                    System.err.println("An error occurred during search: " + cause.getMessage());
                }
            } finally {
                // Prompt for another search or exit
                System.out.print("\nPerform another search? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("yes")) {
                    break;
                }
            }
        }

        System.out.println("Thank you for using the Site Search Application!");
        executor.shutdownNow(); // Shut down the executor service
        scanner.close(); // Close the scanner
    }
}