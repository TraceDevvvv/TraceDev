import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a tourist site with basic information.
 */
class Site {
    private String id;
    private String name;
    private String location;

    /**
     * Constructs a new Site.
     *
     * @param id       The unique identifier of the site.
     * @param name     The name of the site.
     * @param location The geographical location of the site.
     */
    public Site(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    /**
     * Returns the ID of the site.
     *
     * @return The site ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the site.
     *
     * @return The site name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location of the site.
     *
     * @return The site location.
     */
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Location: " + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(id, site.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

/**
 * Represents a Tourist who can manage their bookmarked sites.
 */
class Tourist {
    private String touristId;
    private String name;
    private List<Site> bookmarkedSites;

    /**
     * Constructs a new Tourist.
     *
     * @param touristId The unique identifier for the tourist.
     * @param name      The name of the tourist.
     */
    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
        this.bookmarkedSites = new ArrayList<>();
    }

    /**
     * Returns the tourist's ID.
     *
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Returns the tourist's name.
     *
     * @return The tourist name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of sites bookmarked by the tourist.
     *
     * @return A list of bookmarked Site objects.
     */
    public List<Site> getBookmarkedSites() {
        return new ArrayList<>(bookmarkedSites); // Return a copy to prevent external modification
    }

    /**
     * Adds a site to the tourist's bookmarks.
     *
     * @param site The site to add.
     * @return true if the site was added, false if it was already bookmarked.
     */
    public boolean addBookmark(Site site) {
        if (!bookmarkedSites.contains(site)) {
            bookmarkedSites.add(site);
            return true;
        }
        return false;
    }

    /**
     * Removes a site from the tourist's bookmarks.
     *
     * @param siteId The ID of the site to remove.
     * @return true if the site was removed, false if it was not found in bookmarks.
     */
    public boolean removeBookmark(String siteId) {
        return bookmarkedSites.removeIf(site -> site.getId().equals(siteId));
    }
}

/**
 * Simulates the ETOUR server connection for managing site data.
 * In a real application, this would interact with a database or external service.
 */
class ETOURServer {
    private List<Site> availableSites;

    public ETOURServer() {
        this.availableSites = new ArrayList<>();
        // Populate with some dummy data
        availableSites.add(new Site("S001", "Eiffel Tower", "Paris, France"));
        availableSites.add(new Site("S002", "Louvre Museum", "Paris, France"));
        availableSites.add(new Site("S003", "Colosseum", "Rome, Italy"));
        availableSites.add(new Site("S004", "Great Wall of China", "Beijing, China"));
    }

    /**
     * Simulates checking server connection status.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        // Simulate connection status, can be made dynamic for testing interruption
        return true;
    }

    /**
     * Retrieves a site by its ID.
     *
     * @param siteId The ID of the site to retrieve.
     * @return An Optional containing the Site if found, or an empty Optional.
     */
    public Optional<Site> getSiteById(String siteId) {
        return availableSites.stream()
                .filter(site -> site.getId().equals(siteId))
                .findFirst();
    }
}

/**
 * Manages the interaction for deleting a site from a tourist's preferences (bookmarks).
 * This class orchestrates the flow of events as described in the use case.
 */
public class DeleteSiteFromPreferences {

    private Tourist currentTourist;
    private ETOURServer etourServer;
    private Scanner scanner;

    /**
     * Constructs a new DeleteSiteFromPreferences manager.
     *
     * @param tourist     The currently logged-in tourist.
     * @param etourServer The ETOUR server instance for data access.
     */
    public DeleteSiteFromPreferences(Tourist tourist, ETOURServer etourServer) {
        this.currentTourist = tourist;
        this.etourServer = etourServer;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initiates the process of deleting a site from the tourist's bookmarks.
     * This method implements the "Flow of events" from the use case.
     */
    public void executeDeleteSiteFlow() {
        System.out.println("\n--- Delete Site from Bookmarks ---");

        // Entry condition: The tourist card is in a particular site.
        // For this simulation, we assume the tourist is "viewing" their bookmarks.
        if (currentTourist.getBookmarkedSites().isEmpty()) {
            System.out.println("You have no bookmarked sites to remove.");
            return;
        }

        System.out.println("Your current bookmarked sites:");
        currentTourist.getBookmarkedSites().forEach(System.out::println);

        // 1. Choose to remove your site from the list of bookmarks by specific features.
        System.out.print("Enter the ID of the site you wish to remove (or 'cancel' to abort): ");
        String siteIdToRemove = scanner.nextLine().trim();

        if (siteIdToRemove.equalsIgnoreCase("cancel")) {
            System.out.println("Operation cancelled by Tourist.");
            return; // Exit condition: The Tourist cancel the operation
        }

        // Check if the site exists in the tourist's bookmarks
        Optional<Site> siteToDeleteOptional = currentTourist.getBookmarkedSites().stream()
                .filter(site -> site.getId().equalsIgnoreCase(siteIdToRemove))
                .findFirst();

        if (siteToDeleteOptional.isEmpty()) {
            System.out.println("Error: Site with ID '" + siteIdToRemove + "' not found in your bookmarks.");
            return;
        }

        Site siteToDelete = siteToDeleteOptional.get();

        // 2. Prompt removal
        System.out.println("\nYou have selected to remove: " + siteToDelete.getName() + " (ID: " + siteToDelete.getId() + ")");
        System.out.print("Are you sure you want to remove this site from your bookmarks? (yes/no): ");
        String confirmation = scanner.nextLine().trim();

        // 3. Confirm the removal.
        if (confirmation.equalsIgnoreCase("yes")) {
            // Check for server connection before attempting removal
            if (!etourServer.isConnected()) {
                System.out.println("Error: Interruption of the connection to the server ETOUR. Please try again later.");
                return; // Exit condition: Interruption of the connection to the server ETOUR.
            }

            // 4. Removes the selected site from the list of bookmarks.
            boolean removed = currentTourist.removeBookmark(siteToDelete.getId());

            if (removed) {
                System.out.println("Success: '" + siteToDelete.getName() + "' has been removed from your bookmarks.");
                // Exit condition: The notification system has been removed from the site bookmarks.
            } else {
                // This case should ideally not be reached if siteToDeleteOptional was not empty,
                // but included for robustness.
                System.out.println("Error: Could not remove '" + siteToDelete.getName() + "'. It might have been removed already or an internal error occurred.");
            }
        } else if (confirmation.equalsIgnoreCase("no")) {
            System.out.println("Operation cancelled by Tourist.");
            // Exit condition: The Tourist cancel the operation
        } else {
            System.out.println("Invalid input. Operation cancelled.");
            // Exit condition: The Tourist cancel the operation (due to invalid input)
        }
    }

    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Main method to demonstrate the DeleteSiteFromPreferences use case.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Setup initial state
        ETOURServer server = new ETOURServer();
        Tourist tourist = new Tourist("T001", "Alice Smith");

        // Add some initial bookmarks for demonstration
        server.getSiteById("S001").ifPresent(tourist::addBookmark);
        server.getSiteById("S002").ifPresent(tourist::addBookmark);
        server.getSiteById("S003").ifPresent(tourist::addBookmark);

        // Create and execute the use case
        DeleteSiteFromPreferences deleteFlow = new DeleteSiteFromPreferences(tourist, server);
        try {
            deleteFlow.executeDeleteSiteFlow();

            // Demonstrate another attempt or check state
            System.out.println("\n--- After first attempt ---");
            System.out.println("Current bookmarks for " + tourist.getName() + ":");
            if (tourist.getBookmarkedSites().isEmpty()) {
                System.out.println("No bookmarks.");
            } else {
                tourist.getBookmarkedSites().forEach(System.out::println);
            }

            // Simulate trying to remove a non-existent site
            System.out.println("\n--- Attempting to remove a non-existent site ---");
            deleteFlow.executeDeleteSiteFlow();

            // Simulate cancelling the operation
            System.out.println("\n--- Attempting to cancel removal ---");
            deleteFlow.executeDeleteSiteFlow();

        } finally {
            deleteFlow.closeScanner();
        }
    }
}