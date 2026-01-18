// This file contains the main class for the Site Search application.
import java.util.*;
import java.util.concurrent.*;

public class SearchSite {
    private static final int TIMEOUT_SECONDS = 10; // Time limit for search operation

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SiteSearchService searchService = new SiteSearchService();

        System.out.println("Welcome to Site Search System");
        System.out.println("Enter the site name or keyword to search (or type 'exit' to quit):");

        while (true) {
            System.out.print("Search query: ");
            String query = scanner.nextLine().trim();

            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }

            if (query.isEmpty()) {
                System.out.println("Query cannot be empty. Please try again.");
                continue;
            }

            // Step 5: System processes the request with timeout
            try {
                List<Site> results = searchService.searchSites(query, TIMEOUT_SECONDS);
                if (results.isEmpty()) {
                    System.out.println("No sites found for query: " + query);
                } else {
                    System.out.println("Found " + results.size() + " site(s):");
                    for (Site site : results) {
                        System.out.println(" - " + site.getName() + " at " + site.getPath());
                    }
                }
            } catch (TimeoutException e) {
                System.out.println("Error: Search timed out. Please try again.");
            } catch (InterruptedException e) {
                System.out.println("Error: Search was interrupted.");
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                System.out.println("Error: Failed to process search request.");
            }
        }
        scanner.close();
    }
}