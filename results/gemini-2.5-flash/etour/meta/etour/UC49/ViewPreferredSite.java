import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a Bookmark, which is a preferred site saved by a Tourist.
 */
class Bookmark {
    private String name;
    private String url;
    private String description;

    /**
     * Constructs a new Bookmark.
     *
     * @param name The name of the bookmarked site.
     * @param url The URL of the bookmarked site.
     * @param description A brief description of the site.
     */
    public Bookmark(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", URL: " + url + ", Description: " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(name, bookmark.name) &&
               Objects.equals(url, bookmark.url) &&
               Objects.equals(description, bookmark.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, description);
    }
}

/**
 * Represents a Tourist actor in the system.
 * This class would typically hold user-specific data like authentication status and bookmarks.
 */
class Tourist {
    private String username;
    private boolean isAuthenticated;
    private List<Bookmark> preferredSites;

    /**
     * Constructs a new Tourist.
     *
     * @param username The username of the tourist.
     */
    public Tourist(String username) {
        this.username = username;
        this.isAuthenticated = false; // Initially not authenticated
        this.preferredSites = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Simulates the authentication process.
     * In a real system, this would involve checking credentials.
     *
     * @param authenticated True if the tourist is successfully authenticated, false otherwise.
     */
    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /**
     * Adds a bookmark to the tourist's list of preferred sites.
     *
     * @param bookmark The bookmark to add.
     */
    public void addPreferredSite(Bookmark bookmark) {
        if (bookmark != null && !preferredSites.contains(bookmark)) {
            preferredSites.add(bookmark);
        }
    }

    /**
     * Returns the list of preferred sites for this tourist.
     *
     * @return A list of Bookmark objects.
     */
    public List<Bookmark> getPreferredSites() {
        return new ArrayList<>(preferredSites); // Return a copy to prevent external modification
    }

    /**
     * Simulates loading bookmarks from a data source.
     * In a real application, this would involve database calls or API requests.
     *
     * @param bookmarks The list of bookmarks to load.
     */
    public void loadBookmarks(List<Bookmark> bookmarks) {
        if (bookmarks != null) {
            this.preferredSites.clear(); // Clear existing bookmarks before loading new ones
            this.preferredSites.addAll(bookmarks);
        }
    }
}

/**
 * Represents the ETOUR server connection.
 * This class simulates potential connection interruptions.
 */
class ETOURServer {
    private boolean isConnected;

    public ETOURServer() {
        this.isConnected = true; // Assume connected initially
    }

    /**
     * Checks if the server is currently connected.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Simulates an interruption or restoration of the server connection.
     *
     * @param connected True to simulate connection, false to simulate interruption.
     */
    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }
}

/**
 * Main class for the ViewPreferredSite use case.
 * This class orchestrates the interaction between the Tourist and the system
 * to display personal favorites.
 */
public class ViewPreferredSite {

    private Tourist currentTourist;
    private ETOURServer etourServer;
    private Scanner scanner;

    /**
     * Constructs a new ViewPreferredSite instance.
     *
     * @param tourist The authenticated tourist.
     * @param server The ETOUR server instance.
     */
    public ViewPreferredSite(Tourist tourist, ETOURServer server) {
        // Entry condition: The Tourist has successfully authenticated to the system.
        if (tourist == null || !tourist.isAuthenticated()) {
            throw new IllegalArgumentException("Tourist must be authenticated to view preferred sites.");
        }
        this.currentTourist = tourist;
        this.etourServer = server;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu for the tourist.
     */
    private void displayMenu() {
        System.out.println("\n--- Preferred Sites Menu ---");
        System.out.println("1. View My Bookmarks");
        System.out.println("2. Simulate Server Interruption (for testing)");
        System.out.println("3. Simulate Server Restoration (for testing)");
        System.out.println("4. Exit");
        System.out.print("Please choose an option: ");
    }

    /**
     * Simulates the "Upload the list of bookmarks" step.
     * In a real system, this would involve fetching data from a backend.
     * For this simulation, we'll use a predefined list or allow user input.
     *
     * @return A list of bookmarks, or an empty list if an error occurs.
     */
    private List<Bookmark> uploadBookmarks() {
        System.out.println("Simulating upload of bookmarks...");
        // Simulate fetching bookmarks from a server or database
        // For demonstration, we'll return some dummy data.
        // In a real scenario, this would involve network calls and error handling.
        if (!etourServer.isConnected()) {
            System.err.println("Error: Cannot upload bookmarks. Connection to ETOUR server interrupted.");
            return new ArrayList<>();
        }

        // Example dummy bookmarks
        List<Bookmark> fetchedBookmarks = Arrays.asList(
            new Bookmark("Eiffel Tower", "https://www.toureiffel.paris/", "Iconic landmark in Paris."),
            new Bookmark("Louvre Museum", "https://www.louvre.fr/en", "World's largest art museum."),
            new Bookmark("Colosseum", "https://www.coopculture.it/en/colosseo-e-foro-romano/", "Ancient Roman amphitheatre.")
        );

        System.out.println("Bookmarks uploaded successfully (simulated).");
        return fetchedBookmarks;
    }

    /**
     * Displays the list of bookmarks to the tourist.
     * This is the core functionality of the use case.
     */
    public void displayBookmarks() {
        // Check for server connection before attempting to display
        if (!etourServer.isConnected()) {
            System.err.println("Cannot display bookmarks: Connection to ETOUR server interrupted.");
            System.err.println("Please check your connection and try again.");
            return;
        }

        System.out.println("\n--- Your Personal Favorites (Bookmarks) ---");
        List<Bookmark> bookmarks = currentTourist.getPreferredSites();

        if (bookmarks.isEmpty()) {
            System.out.println("You currently have no bookmarks saved.");
            System.out.println("Would you like to upload some bookmarks now? (yes/no)");
            String choice = scanner.nextLine().trim().toLowerCase();
            if ("yes".equals(choice)) {
                List<Bookmark> uploaded = uploadBookmarks();
                if (!uploaded.isEmpty()) {
                    currentTourist.loadBookmarks(uploaded);
                    bookmarks = currentTourist.getPreferredSites(); // Refresh the list
                    if (bookmarks.isEmpty()) {
                        System.out.println("No bookmarks were uploaded or saved.");
                        return;
                    }
                } else {
                    System.out.println("Bookmark upload failed or no bookmarks were available.");
                    return;
                }
            } else {
                return; // User chose not to upload
            }
        }

        // Exit condition: The system displays the list of bookmarks.
        for (int i = 0; i < bookmarks.size(); i++) {
            System.out.println((i + 1) + ". " + bookmarks.get(i));
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Runs the main application loop for the ViewPreferredSite use case.
     */
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Flow of events User System: 1. Select the feature to display the list of personal favorites.
                    // This is implicitly handled by the user selecting option 1.
                    // Flow of events User System: 2. Upload the list of bookmarks.
                    // This is handled within displayBookmarks if the list is empty.
                    displayBookmarks();
                    break;
                case "2":
                    System.out.println("Simulating ETOUR server interruption...");
                    etourServer.setConnected(false);
                    System.out.println("ETOUR server connection status: " + (etourServer.isConnected() ? "Connected" : "Interrupted"));
                    break;
                case "3":
                    System.out.println("Simulating ETOUR server restoration...");
                    etourServer.setConnected(true);
                    System.out.println("ETOUR server connection status: " + (etourServer.isConnected() ? "Connected" : "Interrupted"));
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting View Preferred Sites. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Main method to run the application.
     * This simulates the entry point where a tourist has already authenticated.
     */
    public static void main(String[] args) {
        // Simulate a tourist who has successfully authenticated
        Tourist tourist = new Tourist("john_doe");
        tourist.setAuthenticated(true); // Entry condition met

        // Simulate the ETOUR server
        ETOURServer etourServer = new ETOURServer();

        try {
            // Initialize the use case handler
            ViewPreferredSite app = new ViewPreferredSite(tourist, etourServer);
            app.run();
        } catch (IllegalArgumentException e) {
            System.err.println("Application startup failed: " + e.getMessage());
        }
    }
}