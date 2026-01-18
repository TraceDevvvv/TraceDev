import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a cultural heritage site.
 */
class CulturalHeritageSite {
    private String name;
    private String location;
    private String description;

    public CulturalHeritageSite(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Site Name: " + name + ", Location: " + location + ", Description: " + description;
    }
}

/**
 * Represents the search form filled by the Guest User.
 */
class SearchForm {
    private String keyword;
    private String location;

    public SearchForm(String keyword, String location) {
        this.keyword = keyword;
        this.location = location;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getLocation() {
        return location;
    }
}

/**
 * Manages the search functionality and data.
 */
class SearchService {
    private List<CulturalHeritageSite> sites;

    public SearchService() {
        // Initialize with some dummy data for demonstration
        sites = new ArrayList<>();
        sites.add(new CulturalHeritageSite("Ancient Temple", "Kyoto, Japan", "A historic temple with beautiful gardens."));
        sites.add(new CulturalHeritageSite("Roman Colosseum", "Rome, Italy", "An iconic ancient amphitheater."));
        sites.add(new CulturalHeritageSite("Eiffel Tower", "Paris, France", "A famous landmark and iron lattice tower."));
        sites.add(new CulturalHeritageSite("Great Wall", "Beijing, China", "A series of fortifications made of stone, brick, tamped earth, wood, and other materials."));
        sites.add(new CulturalHeritageSite("Machu Picchu", "Cusco, Peru", "An ancient Inca citadel set high in the Andes Mountains."));
    }

    /**
     * Performs a search based on the provided form.
     * @param form The search form containing keyword and location.
     * @return A list of sites that match the criteria.
     */
    public List<CulturalHeritageSite> search(SearchForm form) {
        List<CulturalHeritageSite> results = new ArrayList<>();
        for (CulturalHeritageSite site : sites) {
            boolean keywordMatch = form.getKeyword() == null || form.getKeyword().isEmpty() ||
                                   site.getName().toLowerCase().contains(form.getKeyword().toLowerCase()) ||
                                   site.getDescription().toLowerCase().contains(form.getKeyword().toLowerCase());
            boolean locationMatch = form.getLocation() == null || form.getLocation().isEmpty() ||
                                    site.getLocation().toLowerCase().contains(form.getLocation().toLowerCase());

            if (keywordMatch && locationMatch) {
                results.add(site);
            }
        }
        return results;
    }
}

/**
 * Main application class for the SEARCH use case.
 */
public class SearchApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SearchService searchService = new SearchService();

        System.out.println("Welcome, Guest User! Please log on to activate search functionality.");
        // Simulate guest user logging on
        System.out.println("Guest User logged on successfully.");

        // Step 1: Activate the search functionality.
        System.out.println("\nActivating search functionality...");

        // Step 2: Displays the corresponding form.
        System.out.println("\n--- Search Form ---");
        System.out.print("Enter keyword (e.g., 'temple', 'tower', leave blank for any): ");
        String keyword = scanner.nextLine();

        System.out.print("Enter location (e.g., 'Japan', 'Italy', leave blank for any): ");
        String location = scanner.nextLine();

        // Step 3: Fill out the form and submit.
        SearchForm form = new SearchForm(keyword, location);
        System.out.println("\nSubmitting search form...");

        // Step 4: Gets the position of the Guest citing the case of use location and process research.
        // This step is simulated by directly processing the search form.
        List<CulturalHeritageSite> searchResults = searchService.search(form);

        // Exit conditions: The system displays a list of sites that meet the search criteria.
        System.out.println("\n--- Search Results ---");
        if (searchResults.isEmpty()) {
            System.out.println("No sites found matching your criteria.");
        } else {
            for (CulturalHeritageSite site : searchResults) {
                System.out.println(site);
            }
        }

        // Simulate interruption of the connection to the server ETOUR (edge case)
        System.out.println("\nSimulating potential interruption: Connection to ETOUR server lost.");
        // In a real application, this would involve error handling and retry mechanisms.

        scanner.close();
    }
}
