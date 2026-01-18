import java.util.Scanner;

/**
 * Represents a Banner with a unique ID.
 */
class Banner {
    private String bannerId;

    /**
     * Constructs a new Banner.
     * @param bannerId The unique identifier for the banner.
     */
    public Banner(String bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * Gets the banner ID.
     * @return The banner ID.
     */
    public String getBannerId() {
        return bannerId;
    }

    @Override
    public String toString() {
        return "Banner{" +
               "bannerId='" + bannerId + '\'' +
               '}';
    }
}

/**
 * Represents a Refreshment Point, which can hold a certain number of banners.
 */
class RefreshmentPoint {
    private String pointId;
    private String name;
    private int currentBannerCount;
    private int maxAllowedBanners;

    /**
     * Constructs a new RefreshmentPoint.
     * @param pointId The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     * @param currentBannerCount The current number of banners associated with this point.
     * @param maxAllowedBanners The maximum number of banners allowed for this point.
     */
    public RefreshmentPoint(String pointId, String name, int currentBannerCount, int maxAllowedBanners) {
        this.pointId = pointId;
        this.name = name;
        this.currentBannerCount = currentBannerCount;
        this.maxAllowedBanners = maxAllowedBanners;
    }

    /**
     * Gets the refreshment point ID.
     * @return The point ID.
     */
    public String getPointId() {
        return pointId;
    }

    /**
     * Gets the name of the refreshment point.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current number of banners.
     * @return The current banner count.
     */
    public int getCurrentBannerCount() {
        return currentBannerCount;
    }

    /**
     * Gets the maximum number of banners allowed.
     * @return The maximum allowed banners.
     */
    public int getMaxAllowedBanners() {
        return maxAllowedBanners;
    }

    /**
     * Checks if a new banner can be added to this refreshment point.
     * @return true if a new banner can be added, false otherwise.
     */
    public boolean canAddBanner() {
        return currentBannerCount < maxAllowedBanners;
    }

    /**
     * Increments the current banner count. This method should only be called
     * after verifying that a banner can be added using canAddBanner().
     */
    public void addBanner() {
        if (canAddBanner()) {
            currentBannerCount++;
        } else {
            System.err.println("Error: Cannot add banner. Maximum limit reached for " + name);
        }
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
               "pointId='" + pointId + '\'' +
               ", name='" + name + '\'' +
               ", currentBannerCount=" + currentBannerCount +
               ", maxAllowedBanners=" + maxAllowedBanners +
               '}';
    }
}

/**
 * Simulates a data access layer for Refreshment Points.
 * In a real application, this would interact with a database or external service.
 */
class RefreshmentPointRepository {

    /**
     * Simulates loading data for a refreshment point.
     * @param pointId The ID of the refreshment point to load.
     * @return A RefreshmentPoint object if found, or null if not found.
     */
    public RefreshmentPoint loadRefreshmentPoint(String pointId) {
        // Simulate data loading from a database or external source
        // For demonstration, we'll return a hardcoded point.
        if ("RP001".equals(pointId)) {
            // This point has 2 banners and a max of 3, so it can accept more.
            return new RefreshmentPoint("RP001", "Central Cafe", 2, 3);
        } else if ("RP002".equals(pointId)) {
            // This point has 5 banners and a max of 5, so it cannot accept more.
            return new RefreshmentPoint("RP002", "Beach Bar", 5, 5);
        } else if ("RP003".equals(pointId)) {
            // This point has 0 banners and a max of 1, so it can accept more.
            return new RefreshmentPoint("RP003", "Mountain View Restaurant", 0, 1);
        }
        return null; // Point not found
    }

    /**
     * Simulates saving the state of a refreshment point.
     * In a real application, this would persist changes to a database.
     * For this use case, we just print the updated state.
     * @param point The RefreshmentPoint object to save.
     */
    public void saveRefreshmentPoint(RefreshmentPoint point) {
        System.out.println("Saving refreshment point state: " + point);
        // In a real application, this would update the database.
    }
}

/**
 * Main class for the CheckBannerNumber application.
 * This class orchestrates the flow of events as described in the use case.
 */
public class CheckBannerNumber {

    private RefreshmentPointRepository repository;
    private Scanner scanner;

    /**
     * Constructs a new CheckBannerNumber instance.
     */
    public CheckBannerNumber() {
        this.repository = new RefreshmentPointRepository();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates an interruption of the connection to the server ETOUR.
     * In a real scenario, this would involve handling network exceptions.
     */
    private void simulateEtourConnectionInterruption() {
        System.out.println("\n--- Simulating ETOUR server connection interruption ---");
        // In a real application, this might throw an exception or set a flag.
        // For this simulation, we just print a message.
    }

    /**
     * Executes the CheckBannerNumber use case.
     * @param pointId The ID of the refreshment point to check.
     */
    public void executeUseCase(String pointId) {
        RefreshmentPoint previousState = null; // To recover the previous state if needed

        try {
            // 1. Load the data of the Convention of refreshment point and verify that the number of banners
            //    is less than the specified number of the current banner.
            System.out.println("Attempting to load data for Refreshment Point ID: " + pointId);
            RefreshmentPoint refreshmentPoint = repository.loadRefreshmentPoint(pointId);

            if (refreshmentPoint == null) {
                System.out.println("Notification: Refreshment Point with ID '" + pointId + "' not found.");
                // 2. Confirmation of the reading of the notification.
                promptForConfirmation();
                // 3. Recovers the previous state (in this case, there was no state to recover for a non-existent point).
                System.out.println("Operation ended. No previous state to recover for non-existent point.");
                return; // End the operation input
            }

            // Store the initial state for potential recovery
            previousState = new RefreshmentPoint(
                refreshmentPoint.getPointId(),
                refreshmentPoint.getName(),
                refreshmentPoint.getCurrentBannerCount(),
                refreshmentPoint.getMaxAllowedBanners()
            );

            System.out.println("Loaded Refreshment Point: " + refreshmentPoint);

            if (!refreshmentPoint.canAddBanner()) {
                System.out.println("Notification: Cannot add a new banner to '" + refreshmentPoint.getName() + "'.");
                System.out.println("Current banners: " + refreshmentPoint.getCurrentBannerCount() +
                                   ", Maximum allowed: " + refreshmentPoint.getMaxAllowedBanners());
                // If not checked, will end the operation input and displays a notification.
                // 2. Confirmation of the reading of the notification.
                promptForConfirmation();
                // 3. Recovers the previous state.
                System.out.println("Recovering previous state (no changes were made): " + previousState);
                System.out.println("Operation ended.");
                return; // End the operation input
            }

            System.out.println("Verification successful: A new banner can be added to '" + refreshmentPoint.getName() + "'.");
            System.out.println("Current banners: " + refreshmentPoint.getCurrentBannerCount() +
                               ", Maximum allowed: " + refreshmentPoint.getMaxAllowedBanners());

            // Simulate adding a new banner (this is where the actual change would happen)
            // For this use case, we just demonstrate the check, not the actual addition and saving.
            // If we were to add it, it would look like this:
            // refreshmentPoint.addBanner();
            // repository.saveRefreshmentPoint(refreshmentPoint);
            // System.out.println("Simulated addition of a new banner. New count: " + refreshmentPoint.getCurrentBannerCount());

            // Exit conditions: The system returns control to the user interaction.
            System.out.println("\nOperation completed successfully. System returns control to user interaction.");

        } catch (Exception e) {
            // This catch block can handle general unexpected errors, including potential ETOUR connection issues
            System.err.println("An unexpected error occurred during the operation: " + e.getMessage());
            // 3. Recovers the previous state if an error occurred after loading
            if (previousState != null) {
                System.out.println("Attempting to recover to previous state: " + previousState);
                // In a real system, you might reload the state from the repository or revert changes.
                // For this simulation, we just print the previous state.
            } else {
                System.out.println("No previous state to recover or error occurred before state was captured.");
            }
            // Simulate ETOUR connection interruption as an example of an exit condition
            simulateEtourConnectionInterruption();
        } finally {
            // Ensure scanner is closed
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Prompts the user to confirm reading a notification.
     */
    private void promptForConfirmation() {
        System.out.println("Please type 'OK' to confirm you have read the notification.");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("OK")) {
            System.out.println("Invalid input. Please type 'OK' to confirm.");
            input = scanner.nextLine();
        }
        System.out.println("Notification confirmed.");
    }

    /**
     * Main method to run the application.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        CheckBannerNumber app = new CheckBannerNumber();

        System.out.println("--- Use Case: Check Banner Number ---");
        System.out.println("Scenario 1: Refreshment Point can accept a new banner (RP001)");
        app.executeUseCase("RP001");
        System.out.println("\n-------------------------------------\n");

        // Re-initialize app for a new scenario to ensure fresh scanner and state
        app = new CheckBannerNumber();
        System.out.println("Scenario 2: Refreshment Point cannot accept a new banner (RP002 - at max limit)");
        app.executeUseCase("RP002");
        System.out.println("\n-------------------------------------\n");

        app = new CheckBannerNumber();
        System.out.println("Scenario 3: Refreshment Point not found (RP999)");
        app.executeUseCase("RP999");
        System.out.println("\n-------------------------------------\n");

        app = new CheckBannerNumber();
        System.out.println("Scenario 4: Refreshment Point can accept a new banner (RP003 - empty)");
        app.executeUseCase("RP003");
        System.out.println("\n-------------------------------------\n");
    }
}