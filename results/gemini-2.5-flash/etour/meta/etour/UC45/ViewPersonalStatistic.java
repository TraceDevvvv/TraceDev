import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit; // For simulating delays

/**
 * Represents a custom exception for server connection issues.
 * This helps in handling the "Interruption of the connection to the server" edge case.
 */
class ServerConnectionException extends Exception {
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerConnectionException(String message) {
        super(message);
    }
}

/**
 * Represents a user in the system, specifically a Point Of Restaurant Operator.
 * For simplicity, it only holds a username.
 */
class Operator {
    private final String username;
    private final String associatedRefreshmentPointId; // The ID of the refreshment point this operator manages

    public Operator(String username, String associatedRefreshmentPointId) {
        this.username = username;
        this.associatedRefreshmentPointId = associatedRefreshmentPointId;
    }

    public String getUsername() {
        return username;
    }

    public String getAssociatedRefreshmentPointId() {
        return associatedRefreshmentPointId;
    }

    @Override
    public String toString() {
        return "Operator{" +
               "username='" + username + '\'' +
               ", associatedRefreshmentPointId='" + associatedRefreshmentPointId + '\'' +
               '}';
    }
}

/**
 * Represents a Refreshment Point for which statistics are displayed.
 */
class RefreshmentPoint {
    private final String id;
    private final String name;
    private final String location;

    public RefreshmentPoint(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", location='" + location + '\'' +
               '}';
    }
}

/**
 * Represents a single statistic item, e.g., "Total Sales: 1500.75 USD".
 */
class Statistic {
    private final String name;
    private final double value;
    private final String unit;

    public Statistic(String name, double value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return String.format("%-25s: %.2f %s", name, value, unit);
    }
}

/**
 * Service responsible for authenticating users.
 * Simulates a backend authentication process.
 */
class AuthenticationService {

    // For demonstration, a simple hardcoded user
    private static final String VALID_USERNAME = "operator1";
    private static final String VALID_PASSWORD = "password123";
    private static final String ASSOCIATED_REFRESHMENT_POINT_ID = "RP001";

    /**
     * Simulates user authentication.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return An Operator object if authentication is successful, null otherwise.
     */
    public Operator authenticate(String username, String password) {
        System.out.println("Authenticating user: " + username + "...");
        try {
            // Simulate network delay or processing time
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Authentication process interrupted.");
            return null;
        }

        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            System.out.println("Authentication successful for " + username + ".");
            return new Operator(username, ASSOCIATED_REFRESHMENT_POINT_ID);
        } else {
            System.out.println("Authentication failed for " + username + ". Invalid credentials.");
            return null;
        }
    }
}

/**
 * Service responsible for fetching statistics for refreshment points.
 * Simulates data retrieval from a backend system.
 */
class StatisticService {

    // Simulate a database or external service that holds refreshment point data
    private final List<RefreshmentPoint> refreshmentPoints = Arrays.asList(
            new RefreshmentPoint("RP001", "Central Cafe", "Downtown"),
            new RefreshmentPoint("RP002", "Parkside Bistro", "City Park")
    );

    /**
     * Retrieves personal statistics for a given refreshment point ID.
     * Simulates fetching data from a server, including potential connection issues.
     *
     * @param refreshmentPointId The ID of the refreshment point.
     * @return A list of Statistic objects.
     * @throws ServerConnectionException If there's an issue connecting to the server.
     */
    public List<Statistic> getPersonalStatistics(String refreshmentPointId) throws ServerConnectionException {
        System.out.println("Fetching statistics for refreshment point ID: " + refreshmentPointId + "...");
        try {
            // Simulate network delay or server processing time
            TimeUnit.SECONDS.sleep(2);

            // Simulate a server connection interruption randomly
            if (Math.random() < 0.2) { // 20% chance of connection interruption
                throw new ServerConnectionException("Simulated server connection interruption.");
            }

            // Simulate data retrieval based on refreshmentPointId
            if ("RP001".equals(refreshmentPointId)) {
                return Arrays.asList(
                        new Statistic("Total Sales (Today)", 1500.75, "USD"),
                        new Statistic("Number of Orders (Today)", 120, "Orders"),
                        new Statistic("Average Order Value", 12.50, "USD"),
                        new Statistic("Most Popular Item", 50, "Units (Coffee)")
                );
            } else if ("RP002".equals(refreshmentPointId)) {
                return Arrays.asList(
                        new Statistic("Total Sales (Today)", 2100.50, "USD"),
                        new Statistic("Number of Orders (Today)", 95, "Orders"),
                        new Statistic("Average Order Value", 22.11, "USD"),
                        new Statistic("Most Popular Item", 30, "Units (Sandwich)")
                );
            } else {
                System.out.println("No statistics found for refreshment point ID: " + refreshmentPointId);
                return new ArrayList<>(); // Return empty list if no data
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServerConnectionException("Statistic retrieval interrupted.", e);
        }
    }

    /**
     * Retrieves a RefreshmentPoint object by its ID.
     *
     * @param id The ID of the refreshment point.
     * @return The RefreshmentPoint object, or null if not found.
     */
    public RefreshmentPoint getRefreshmentPointById(String id) {
        return refreshmentPoints.stream()
                .filter(rp -> rp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

/**
 * Main application class for the ViewPersonalStatistic use case.
 * Orchestrates the authentication, feature selection, and display of statistics.
 */
public class ViewPersonalStatistic {

    private final AuthenticationService authService;
    private final StatisticService statisticService;
    private final Scanner scanner;

    public ViewPersonalStatistic() {
        this.authService = new AuthenticationService();
        this.statisticService = new StatisticService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the authentication process for the Point Of Restaurant Operator.
     *
     * @return The authenticated Operator object, or null if authentication fails.
     */
    private Operator performAuthentication() {
        Operator authenticatedOperator = null;
        int loginAttempts = 0;
        final int MAX_LOGIN_ATTEMPTS = 3;

        while (authenticatedOperator == null && loginAttempts < MAX_LOGIN_ATTEMPTS) {
            System.out.println("\n--- Operator Login ---");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            authenticatedOperator = authService.authenticate(username, password);

            if (authenticatedOperator == null) {
                loginAttempts++;
                System.out.println("Login failed. Please try again. (" + (MAX_LOGIN_ATTEMPTS - loginAttempts) + " attempts remaining)");
            }
        }

        if (authenticatedOperator == null) {
            System.out.println("Maximum login attempts reached. Exiting application.");
        }
        return authenticatedOperator;
    }

    /**
     * Displays the main menu and handles feature selection.
     *
     * @param operator The authenticated operator.
     */
    private void displayMainMenu(Operator operator) {
        System.out.println("\n--- Welcome, " + operator.getUsername() + "! ---");
        System.out.println("1. View Personal Statistics");
        System.out.println("2. Exit");
        System.out.print("Please select a feature: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                displayPersonalStatistics(operator);
                break;
            case "2":
                System.out.println("Exiting application. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMainMenu(operator); // Re-display menu for invalid input
                break;
        }
    }

    /**
     * Displays the statistics for the refreshment point associated with the operator.
     * Handles server connection interruptions as an edge case.
     *
     * @param operator The authenticated operator.
     */
    private void displayPersonalStatistics(Operator operator) {
        System.out.println("\n--- Displaying Personal Statistics ---");

        String refreshmentPointId = operator.getAssociatedRefreshmentPointId();
        RefreshmentPoint rp = statisticService.getRefreshmentPointById(refreshmentPointId);

        if (rp == null) {
            System.err.println("Error: Associated refreshment point not found for ID: " + refreshmentPointId);
            return;
        }

        System.out.println("Statistics for: " + rp.getName() + " (" + rp.getLocation() + ")");
        System.out.println("--------------------------------------------------");

        try {
            List<Statistic> statistics = statisticService.getPersonalStatistics(refreshmentPointId);

            if (statistics.isEmpty()) {
                System.out.println("No statistics available for this refreshment point at the moment.");
            } else {
                for (Statistic stat : statistics) {
                    System.out.println(stat);
                }
            }
        } catch (ServerConnectionException e) {
            // Handle the edge case: Interruption of the connection to the server
            System.err.println("Error: Could not retrieve statistics due to server connection issue.");
            System.err.println("Details: " + e.getMessage());
            System.err.println("Please check your network connection and try again later.");
        } finally {
            System.out.println("--------------------------------------------------");
            System.out.println("Press Enter to return to the main menu...");
            scanner.nextLine(); // Consume the newline and wait for user input
            displayMainMenu(operator); // Return to main menu after viewing stats
        }
    }

    /**
     * Starts the application flow.
     */
    public void start() {
        System.out.println("Starting View Personal Statistic Application...");

        // Entry condition: The Point Of Restaurant Operator has successfully authenticated to the system.
        Operator authenticatedOperator = performAuthentication();

        if (authenticatedOperator != null) {
            // Flow of events:
            // 1. Select the feature to display personal statistics.
            // 2. Displays a form that shows data for the statistics associated refreshment point.
            displayMainMenu(authenticatedOperator);
        } else {
            System.out.println("Application terminated due to authentication failure.");
        }

        scanner.close(); // Close the scanner when the application finishes
        System.out.println("Application finished.");
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ViewPersonalStatistic app = new ViewPersonalStatistic();
        app.start();
    }
}