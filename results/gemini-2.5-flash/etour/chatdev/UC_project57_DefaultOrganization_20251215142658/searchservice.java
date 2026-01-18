package service;
import model.SearchCriteria;
import model.Site;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
 * Simulates the backend service for performing advanced searches.
 * This class includes logic for simulating network delays, processing
 * location data, generating mock results, and handling potential failures.
 */
public class SearchService {
    private final Random random = new Random();
    /**
     * Performs an advanced search based on the provided criteria.
     * This method simulates network latency, processing time, and potential connection issues.
     *
     * @param criteria The SearchCriteria object containing all search parameters.
     * @return A list of Site objects matching the criteria.
     * @throws Exception If a simulated connection interruption or other error occurs.
     */
    public List<Site> performAdvancedSearch(SearchCriteria criteria) throws Exception {
        System.out.println("Processing advanced search with criteria: " + criteria);
        // Simulate network delay and server processing time
        // The use case states: "The system requirements into the transaction in more than 15 seconds."
        // We'll simulate a range, sometimes exceeding 15 seconds.
        long processingTimeMillis = 5000 + random.nextInt(15000); // Between 5 to 20 seconds
        System.out.println("Simulating search processing for " + (double)processingTimeMillis / 1000 + " seconds...");
        try {
            Thread.sleep(processingTimeMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new Exception("Search interrupted during processing.", e);
        }
        // Simulate "Interruption of the connection to the server ETOUR"
        // 1 in 5 chance of failure
        if (random.nextInt(5) == 0) {
            System.err.println("Simulated connection interruption!");
            throw new Exception("Connection to ETOUR server interrupted. Please try again.");
        }
        // Simulate location processing (for simplicity, just acknowledge it was received)
        System.out.println("Processing tourist location: " + criteria.getTouristLocation());
        // In a real application, this would involve geocoding, proximity search, etc.
        // Generate mock results based on criteria
        List<Site> results = generateMockSites(criteria);
        System.out.println("Search completed successfully. Found " + results.size() + " sites.");
        return results;
    }
    /**
     * Generates a predefined list of mock tourist sites.
     * In a real system, this would query a database. For this simulation,
     * it filters a static list based on simple keyword/category matching.
     *
     * @param criteria The search criteria to filter mock sites.
     * @return A list of mock Site objects that loosely match the criteria.
     */
    private List<Site> generateMockSites(SearchCriteria criteria) {
        List<Site> allSites = new ArrayList<>();
        allSites.add(new Site("Eiffel Tower", "Iconic iron lattice tower", "Paris, France"));
        allSites.add(new Site("Louvre Museum", "World's largest art museum", "Paris, France"));
        allSites.add(new Site("Statue of Liberty", "Neoclassical sculpture on Liberty Island", "New York, USA"));
        allSites.add(new Site("Central Park", "Urban park in Manhattan", "New York, USA"));
        allSites.add(new Site("Colosseum", "Ancient Roman amphitheatre", "Rome, Italy"));
        allSites.add(new Site("Vatican City", "Smallest independent state", "Rome, Italy"));
        allSites.add(new Site("Great Wall of China", "Series of fortifications", "Huairou, China"));
        allSites.add(new Site("Mount Fuji", "Highest mountain in Japan", "Honshu, Japan"));
        allSites.add(new Site("Tokyo Imperial Palace", "Primary residence of the Emperor of Japan", "Tokyo, Japan"));
        allSites.add(new Site("Sagrada Familia", "Large unfinished Roman Catholic minor basilica", "Barcelona, Spain"));
        allSites.add(new Site("Park Güell", "Public park system composed of gardens and architectural elements", "Barcelona, Spain"));
        List<Site> filteredResults = new ArrayList<>();
        String keyword = criteria.getKeyword().toLowerCase();
        String category = criteria.getCategory().toLowerCase();
        String touristsLocation = criteria.getTouristLocation().toLowerCase();
        for (Site site : allSites) {
            boolean matches = true;
            // Simple keyword matching
            if (!keyword.isEmpty() && !(site.getName().toLowerCase().contains(keyword) || site.getDescription().toLowerCase().contains(keyword))) {
                matches = false;
            }
            // Simple category matching (mock categories)
            if (!category.isEmpty()) {
                switch (category) {
                    case "museum":
                        if (!site.getName().toLowerCase().contains("museum") && !site.getName().toLowerCase().contains("louvre")) {
                            matches = false;
                        }
                        break;
                    case "park":
                        if (!site.getName().toLowerCase().contains("park") && !site.getName().toLowerCase().contains("güell")) {
                            matches = false;
                        }
                        break;
                    case "historical":
                        // Broad interpretation for historical sites
                        if (!(site.getName().toLowerCase().contains("colosseum") || site.getName().toLowerCase().contains("wall") || site.getName().toLowerCase().contains("palace") || site.getName().toLowerCase().contains("tower"))) {
                            matches = false;
                        }
                        break;
                    case "monument":
                        if (!(site.getName().toLowerCase().contains("tower") || site.getName().toLowerCase().contains("statue") || site.getName().toLowerCase().contains("colosseum"))) {
                            matches = false;
                        }
                        break;
                    default:
                        // If category is specified but doesn't match predefined, filter out
                        if (!category.equals("any")) { // 'any' category means no category filter
                            // For simplicity, if category is specific and not 'any',
                            // and doesn't match known types, then filter out.
                            // In a real system, categories would be distinct data points.
                            matches = false;
                        }
                        break;
                }
            }
            // For price, we can assume all mock sites are "medium" price (e.g., maxPrice 1 or 2)
            // If criteria.getMaxPrice() is 0 (cheap/free), then no match
            if (criteria.getMaxPrice() == 0) { // Simulating no "free" sites in mock data
                matches = false;
            }
            // Apply a strong filter based on tourist's current location if provided.
            // This ensures the system "Gets the position... and process the request."
            if (matches && !touristsLocation.isEmpty()) {
                // If a tourist location is provided, and the site's location does NOT contain it, filter it out.
                // This makes the location a significant filtering criterion.
                if (!site.getLocation().toLowerCase().contains(touristsLocation)) {
                    matches = false;
                }
            }
            if (matches) {
                filteredResults.add(site);
            }
        }
        return filteredResults;
    }
}