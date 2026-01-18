/**
 * Service class responsible for performing the search for cultural heritage sites.
 * It contains dummy data representing available sites and implements the logic
 * to filter sites based on user-provided criteria and the user's actual location.
 * It also simulates potential connection interruptions to the "ETOUR server".
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
public class SearchService {
    private List<CulturalSite> allCulturalSites;
    private Random random;
    /**
     * Constructor for SearchService.
     * Initializes a dummy database of cultural sites.
     */
    public SearchService() {
        this.allCulturalSites = new ArrayList<>();
        this.random = new Random();
        populateDummyData();
    }
    /**
     * Populates the service with a list of predefined cultural sites for demonstration.
     * This acts as our "database" or "ETOUR server" data.
     */
    private void populateDummyData() {
        allCulturalSites.add(new CulturalSite("Colosseum", "Ancient amphitheater in the center of Rome.", "Rome, Italy", "Archaeological Site"));
        allCulturalSites.add(new CulturalSite("Roman Forum", "A rectangular forum surrounded by the ruins of several important ancient government buildings at the center of the city of Rome.", "Rome, Italy", "Archaeological Site"));
        allCulturalSites.add(new CulturalSite("Vatican Museums", "Public museums of the Vatican City.", "Vatican City", "Museum"));
        allCulturalSites.add(new CulturalSite("Pantheon", "Former Roman temple, now a church, in Rome.", "Rome, Italy", "Historical Building"));
        allCulturalSites.add(new CulturalSite("Uffizi Gallery", "Prominent art museum in Florence.", "Florence, Italy", "Museum"));
        allCulturalSites.add(new CulturalSite("Accademia Gallery", "Housings Michelangelo's David in Florence.", "Florence, Italy", "Museum"));
        allCulturalSites.add(new CulturalSite("Eiffel Tower", "Wrought-iron lattice tower on the Champ de Mars in Paris.", "Paris, France", "Historical Building"));
        allCulturalSites.add(new CulturalSite("Louvre Museum", "World's largest art museum and a historic monument in Paris.", "Paris, France", "Museum"));
        allCulturalSites.add(new CulturalSite("British Museum", "A public institution dedicated to human history, art and culture in London.", "London, UK", "Museum"));
        allCulturalSites.add(new CulturalSite("Stonehenge", "A prehistoric monument in Wiltshire, England.", "Salisbury, UK", "Archaeological Site"));
        allCulturalSites.add(new CulturalSite("Pyramids of Giza", "Ancient pyramids in Egypt.", "Giza, Egypt", "Archaeological Site"));
    }
    /**
     * Performs a search for cultural sites based on the provided criteria and the user's actual location.
     * This method simulates the processing of research and interaction with a data source (ETOUR server).
     *
     * @param criteria The search criteria (keyword, location from form, category) provided by the user.
     * @param userActualLocation The actual geographical location of the user (e.g., from GPS).
     * @return A list of CulturalSite objects that match the search criteria.
     * @throws ETOURConnectionException If a simulated connection interruption occurs.
     */
    public List<CulturalSite> performSearch(SearchCriteria criteria, String userActualLocation) throws ETOURConnectionException {
        System.out.println("SearchService: Performing search with criteria: " +
                "Keyword='" + criteria.getKeyword() + "', Form Location='" + criteria.getLocation() +
                "', Category='" + criteria.getCategory() + "', User Actual Location='" + userActualLocation + "'");
        // Simulate server connection interruption (e.g., 20% chance of failure)
        if (random.nextInt(100) < 20) { // 20% chance
            throw new ETOURConnectionException("Failed to connect to ETOUR server. Service temporarily unavailable.");
        }
        // Determine the effective location to use for filtering.
        // If the user did not specify a location in the form, use their actual current location.
        // Otherwise, prioritize the location specified in the form.
        String effectiveSearchLocation = criteria.getLocation().isEmpty() ? userActualLocation : criteria.getLocation();
        // Filter sites based on criteria
        List<CulturalSite> results = allCulturalSites.stream()
            .filter(site -> {
                // Keyword match (case-insensitive, partial match)
                boolean keywordMatches = criteria.getKeyword().isEmpty() ||
                        site.getName().toLowerCase().contains(criteria.getKeyword().toLowerCase()) ||
                        site.getDescription().toLowerCase().contains(criteria.getKeyword().toLowerCase());
                // Location match using the determined effective search location
                boolean locationMatches = effectiveSearchLocation.isEmpty() ||
                                          site.getLocation().toLowerCase().contains(effectiveSearchLocation.toLowerCase());
                // Category match (case-insensitive, exact match, or "All")
                boolean categoryMatches = criteria.getCategory().equalsIgnoreCase("All") ||
                                          criteria.getCategory().isEmpty() || // Handles cases where category might be null/empty from GUI, though JComboBox defaults to "All"
                                          site.getCategory().equalsIgnoreCase(criteria.getCategory());
                return keywordMatches && locationMatches && categoryMatches;
            })
            .collect(Collectors.toList());
        // Simulate network delay
        try {
            Thread.sleep(1000 + random.nextInt(1000)); // 1 to 2 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("SearchService: Found " + results.size() + " results using effective location: " + effectiveSearchLocation);
        return results;
    }
}