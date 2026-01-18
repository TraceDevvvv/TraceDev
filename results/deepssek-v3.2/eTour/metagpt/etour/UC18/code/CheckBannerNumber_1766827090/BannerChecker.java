import java.util.Scanner;

/**
 * BannerChecker class implements the core logic for checking banner numbers.
 * This class handles the main use case flow:
 * 1. Load refreshment point data and verify banner count
 * 2. Display notification if check fails
 * 3. Confirm notification reading
 * 4. Recover previous state
 * 5. Handle server connection exceptions
 */
public class BannerChecker {
    
    private RefreshmentPoint refreshmentPoint;
    private Scanner scanner;
    private boolean serverConnected;
    
    /**
     * Constructor for BannerChecker.
     * 
     * @param refreshmentPoint The refreshment point to check
     * @throws IllegalArgumentException if refreshmentPoint is null
     */
    public BannerChecker(RefreshmentPoint refreshmentPoint) {
        if (refreshmentPoint == null) {
            throw new IllegalArgumentException("Refreshment point cannot be null");
        }
        this.refreshmentPoint = refreshmentPoint;
        this.scanner = new Scanner(System.in);
        this.serverConnected = true; // Assume connected by default
    }
    
    /**
     * Main method to perform the banner check operation.
     * This method follows the use case flow.
     * 
     * @return true if banner can be added, false otherwise
     * @throws ServerConnectionException if server connection is interrupted
     */
    public boolean checkBannerNumber() throws ServerConnectionException {
        System.out.println("=== Banner Number Check Operation ===");
        System.out.println("Starting banner check for: " + refreshmentPoint.getName());
        
        // Step 1: Check server connection
        checkServerConnection();
        
        // Step 2: Load data and verify banner count
        boolean canAddBanner = verifyBannerCount();
        
        if (!canAddBanner) {
            // Step 2a: If check fails, display notification
            displayNotification("Cannot add banner: Maximum limit reached. Current banners: " 
                + refreshmentPoint.getCurrentBanners() + "/" + refreshmentPoint.getMaxAllowedBanners());
            
            // Step 3: Confirm notification reading
            confirmNotificationReading();
            
            // Step 4: Recover previous state
            recoverPreviousState();
            
            return false;
        } else {
            System.out.println("✓ Banner can be added. Available slots: " + refreshmentPoint.getAvailableSlots());
            
            // Simulate banner addition
            if (refreshmentPoint.addBanner()) {
                System.out.println("✓ Banner added successfully. New count: " 
                    + refreshmentPoint.getCurrentBanners() + "/" + refreshmentPoint.getMaxAllowedBanners());
            }
            
            return true;
        }
    }
    
    /**
     * Step 1: Check server connection.
     * Simulates checking connection to ETOUR server.
     * 
     * @throws ServerConnectionException if server connection is interrupted
     */
    private void checkServerConnection() throws ServerConnectionException {
        System.out.println("Checking connection to ETOUR server...");
        
        // Simulate connection check with 90% success rate
        if (!serverConnected) {
            throw new ServerConnectionException("Connection to ETOUR server interrupted");
        }
        
        // Simulate random connection failure (10% chance)
        if (Math.random() < 0.1) {
            serverConnected = false;
            throw new ServerConnectionException("Lost connection to ETOUR server during operation");
        }
        
        System.out.println("✓ Server connection established.");
    }
    
    /**
     * Step 2: Load data and verify banner count.
     * Checks if current banners are less than maximum allowed.
     * 
     * @return true if banner can be added, false otherwise
     */
    private boolean verifyBannerCount() {
        System.out.println("Loading refreshment point data...");
        System.out.println("Refreshment Point: " + refreshmentPoint);
        
        boolean canAdd = refreshmentPoint.canAddBanner();
        
        if (!canAdd) {
            System.out.println("✗ Verification failed: Maximum banner limit reached.");
        }
        
        return canAdd;
    }
    
    /**
     * Step 2a: Display notification to user.
     * 
     * @param message The notification message to display
     */
    private void displayNotification(String message) {
        System.out.println("\n=== NOTIFICATION ===");
        System.out.println(message);
        System.out.println("===================\n");
    }
    
    /**
     * Step 3: Confirm notification reading.
     * Simulates user confirming they have read the notification.
     */
    private void confirmNotificationReading() {
        System.out.print("Please confirm you have read the notification (type 'OK' to continue): ");
        
        String input = scanner.nextLine();
        
        while (!input.equalsIgnoreCase("OK")) {
            System.out.println("Notification must be acknowledged. Please type 'OK' to continue.");
            System.out.print("Please confirm you have read the notification (type 'OK' to continue): ");
            input = scanner.nextLine();
        }
        
        System.out.println("✓ Notification acknowledged by operator.");
    }
    
    /**
     * Step 4: Recover previous state.
     * In a real system, this might involve rolling back transactions,
     * restoring previous data, etc.
     */
    private void recoverPreviousState() {
        System.out.println("Recovering previous state...");
        
        // In this simple implementation, we just display a message
        // In a real system, you might:
        // - Rollback database transactions
        // - Restore cached data
        // - Reset session state
        // - Log the recovery operation
        
        System.out.println("✓ Previous state recovered successfully.");
    }
    
    /**
     * Simulates a server connection failure for testing.
     */
    public void simulateServerDisconnection() {
        this.serverConnected = false;
        System.out.println("Server connection simulation: DISCONNECTED");
    }
    
    /**
     * Simulates reconnecting to the server.
     */
    public void simulateServerReconnection() {
        this.serverConnected = true;
        System.out.println("Server connection simulation: RECONNECTED");
    }
    
    /**
     * Closes resources used by the BannerChecker.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
        System.out.println("BannerChecker resources closed.");
    }
    
    /**
     * Getter for the refreshment point.
     */
    public RefreshmentPoint getRefreshmentPoint() {
        return refreshmentPoint;
    }
    
    /**
     * Setter for the refreshment point.
     */
    public void setRefreshmentPoint(RefreshmentPoint refreshmentPoint) {
        if (refreshmentPoint == null) {
            throw new IllegalArgumentException("Refreshment point cannot be null");
        }
        this.refreshmentPoint = refreshmentPoint;
    }
}