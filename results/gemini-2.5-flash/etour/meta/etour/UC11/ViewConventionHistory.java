import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a single convention or booking.
 */
class Convention {
    private String conventionId;
    private String pointOfRestId;
    private String description;
    private LocalDate conventionDate;

    public Convention(String conventionId, String pointOfRestId, String description, LocalDate conventionDate) {
        this.conventionId = conventionId;
        this.pointOfRestId = pointOfRestId;
        this.description = description;
        this.conventionDate = conventionDate;
    }

    public String getConventionId() {
        return conventionId;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getConventionDate() {
        return conventionDate;
    }

    @Override
    public String toString() {
        return "Convention{" +
               "id='" + conventionId + '\'' +
               ", pointOfRestId='" + pointOfRestId + '\'' +
               ", description='" + description + '\'' +
               ", date=" + conventionDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convention that = (Convention) o;
        return Objects.equals(conventionId, that.conventionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conventionId);
    }
}

/**
 * Represents a "point of rest" such as a restaurant or hotel.
 */
class PointOfRest {
    private String id;
    private String name;

    public PointOfRest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PointOfRest{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfRest that = (PointOfRest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

/**
 * Exception thrown when there's an interruption in connection to the ETOUR server.
 */
class ETOURConnectionException extends RuntimeException {
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ETOURConnectionException(String message) {
        super(message);
    }
}

/**
 * Interface for serv that retrieve convention history.
 */
interface ConventionHistoryService {
    /**
     * Retrieves the historical conventions for a given point of rest ID.
     *
     * @param pointOfRestId The ID of the point of rest.
     * @return A list of conventions associated with the point of rest.
     * @throws ETOURConnectionException if there's a problem connecting to the ETOUR server.
     */
    List<Convention> getConventionHistory(String pointOfRestId) throws ETOURConnectionException;

    /**
     * Retrieves a list of all available points of rest.
     * @return A list of PointOfRest objects.
     * @throws ETOURConnectionException if there's a problem connecting to the ETOUR server.
     */
    List<PointOfRest> getAllPointsOfRest() throws ETOURConnectionException;
}

/**
 * An implementation of ConventionHistoryService that simulates interaction with an ETOUR server.
 * It includes a random chance of connection interruption.
 */
class ETOURConventionHistoryService implements ConventionHistoryService {
    private final Map<String, List<Convention>> conventionData;
    private final List<PointOfRest> pointsOfRest;
    private final Random random;
    private static final double CONNECTION_FAILURE_RATE = 0.2; // 20% chance of failure

    public ETOURConventionHistoryService() {
        this.conventionData = new HashMap<>();
        this.pointsOfRest = new ArrayList<>();
        this.random = new Random();
        initializeMockData();
    }

    /**
     * Initializes mock data for points of rest and conventions.
     */
    private void initializeMockData() {
        // Create mock points of rest
        PointOfRest por1 = new PointOfRest("POR001", "Grand Hotel");
        PointOfRest por2 = new PointOfRest("POR002", "Riverside Restaurant");
        PointOfRest por3 = new PointOfRest("POR003", "Mountain Lodge");

        pointsOfRest.add(por1);
        pointsOfRest.add(por2);
        pointsOfRest.add(por3);

        // Create mock conventions for POR001
        List<Convention> conventionsPor1 = new ArrayList<>();
        conventionsPor1.add(new Convention("CON001", "POR001", "Annual Sales Meeting", LocalDate.of(2023, 1, 15)));
        conventionsPor1.add(new Convention("CON002", "POR001", "Company Retreat", LocalDate.of(2023, 3, 10)));
        conventionsPor1.add(new Convention("CON003", "POR001", "Product Launch Event", LocalDate.of(2023, 5, 22)));
        conventionData.put(por1.getId(), conventionsPor1);

        // Create mock conventions for POR002
        List<Convention> conventionsPor2 = new ArrayList<>();
        conventionsPor2.add(new Convention("CON004", "POR002", "Birthday Party", LocalDate.of(2023, 2, 1)));
        conventionsPor2.add(new Convention("CON005", "POR002", "Wedding Reception", LocalDate.of(2023, 4, 5)));
        conventionData.put(por2.getId(), conventionsPor2);

        // Create mock conventions for POR003
        List<Convention> conventionsPor3 = new ArrayList<>();
        conventionsPor3.add(new Convention("CON006", "POR003", "Ski Trip Booking", LocalDate.of(2022, 12, 1)));
        conventionData.put(por3.getId(), conventionsPor3);
    }

    /**
     * Simulates a connection to the ETOUR server.
     * Throws ETOURConnectionException based on CONNECTION_FAILURE_RATE.
     */
    private void simulateETOURConnection() {
        if (random.nextDouble() < CONNECTION_FAILURE_RATE) {
            throw new ETOURConnectionException("Simulated ETOUR server connection interruption.");
        }
        // Simulate network latency
        try {
            Thread.sleep(200); // Simulate a small delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ETOURConnectionException("ETOUR server connection interrupted during latency simulation.", e);
        }
    }

    @Override
    public List<Convention> getConventionHistory(String pointOfRestId) throws ETOURConnectionException {
        simulateETOURConnection(); // Check for connection interruption
        // Return an immutable copy to prevent external modification
        return Optional.ofNullable(conventionData.get(pointOfRestId))
                       .map(ArrayList::new) // Create a new ArrayList from the existing list
                       .orElse(new ArrayList<>()); // Return empty list if no data found
    }

    @Override
    public List<PointOfRest> getAllPointsOfRest() throws ETOURConnectionException {
        simulateETOURConnection(); // Check for connection interruption
        return new ArrayList<>(pointsOfRest); // Return a copy to prevent external modification
    }
}

/**
 * Simulates the user interface for an Agency Operator to view convention history.
 */
class AgencyOperatorUI {
    private final ConventionHistoryService conventionHistoryService;
    private final Scanner scanner;

    public AgencyOperatorUI(ConventionHistoryService conventionHistoryService) {
        this.conventionHistoryService = conventionHistoryService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu and handles user input.
     */
    public void start() {
        System.out.println("Welcome, Agency Operator!");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Historical Conventions Display ---");
            System.out.println("1. View Convention History for a Point of Rest");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewConventionHistory();
                    break;
                case "2":
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Allows the operator to select a point of rest and view its convention history.
     */
    private void viewConventionHistory() {
        System.out.println("\n--- Select a Point of Rest ---");
        try {
            List<PointOfRest> availablePoints = conventionHistoryService.getAllPointsOfRest();

            if (availablePoints.isEmpty()) {
                System.out.println("No points of rest available.");
                return;
            }

            System.out.println("Available Points of Rest:");
            for (int i = 0; i < availablePoints.size(); i++) {
                System.out.println((i + 1) + ". " + availablePoints.get(i).getName() + " (ID: " + availablePoints.get(i).getId() + ")");
            }
            System.out.print("Enter the number of the point of rest to view history: ");

            String input = scanner.nextLine();
            int selectedIndex;
            try {
                selectedIndex = Integer.parseInt(input) - 1;
                if (selectedIndex < 0 || selectedIndex >= availablePoints.size()) {
                    System.out.println("Invalid selection. Please enter a valid number.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                return;
            }

            PointOfRest selectedPointOfRest = availablePoints.get(selectedIndex);
            System.out.println("\nFetching convention history for: " + selectedPointOfRest.getName() + " (ID: " + selectedPointOfRest.getId() + ")");

            // Upload data on conventions from the restaurant selected.
            List<Convention> history = conventionHistoryService.getConventionHistory(selectedPointOfRest.getId());

            // The system displays the history of conventions concerning the point of eating selected.
            if (history.isEmpty()) {
                System.out.println("No historical conventions found for " + selectedPointOfRest.getName() + ".");
            } else {
                System.out.println("--- Convention History for " + selectedPointOfRest.getName() + " ---");
                for (Convention convention : history) {
                    System.out.println("- " + convention);
                }
            }
        } catch (ETOURConnectionException e) {
            // Interruption of the connection to the server ETOUR.
            System.err.println("Error: " + e.getMessage());
            System.err.println("Please check your connection to the ETOUR server and try again.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/**
 * Main class to run the ViewConventionHistory application.
 * This class orchestrates the components to fulfill the use case.
 */
public class ViewConventionHistory {
    public static void main(String[] args) {
        // Initialize the service layer
        ConventionHistoryService service = new ETOURConventionHistoryService();

        // Initialize the UI layer with the service
        AgencyOperatorUI ui = new AgencyOperatorUI(service);

        // Start the application flow
        ui.start();
    }
}