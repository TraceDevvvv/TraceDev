package com.smos.enrollment;

/**
 * SMOSServer class representing the SMOS (Student Management and Operations System) server.
 * Handles connection status management and simulates server interruptions.
 * This server is responsible for synchronizing student enrollment data across systems.
 */
public class SMOSServer {
    private String serverUrl;
    private int serverPort;
    private boolean isConnected;
    private int connectionAttempts;
    private static final int MAX_CONNECTION_ATTEMPTS = 3;
    private static final long DEFAULT_TIMEOUT_MS = 5000; // 5 seconds
    
    /**
     * Default constructor with default server settings
     */
    public SMOSServer() {
        this.serverUrl = "smos.university.edu";
        this.serverPort = 8080;
        this.isConnected = true; // Start as connected
        this.connectionAttempts = 0;
    }
    
    /**
     * Parameterized constructor for custom server configuration
     * @param serverUrl SMOS server URL
     * @param serverPort SMOS server port
     */
    public SMOSServer(String serverUrl, int serverPort) {
        this.serverUrl = serverUrl != null ? serverUrl : "smos.university.edu";
        this.serverPort = serverPort > 0 ? serverPort : 8080;
        this.isConnected = true; // Start as connected
        this.connectionAttempts = 0;
    }
    
    // Getters and Setters
    
    public String getServerUrl() {
        return serverUrl;
    }
    
    public void setServerUrl(String serverUrl) {
        if (serverUrl != null && !serverUrl.trim().isEmpty()) {
            this.serverUrl = serverUrl;
        }
    }
    
    public int getServerPort() {
        return serverPort;
    }
    
    public void setServerPort(int serverPort) {
        if (serverPort > 0) {
            this.serverPort = serverPort;
        }
    }
    
    public boolean isConnected() {
        return isConnected;
    }
    
    public int getConnectionAttempts() {
        return connectionAttempts;
    }
    
    public static int getMaxConnectionAttempts() {
        return MAX_CONNECTION_ATTEMPTS;
    }
    
    public static long getDefaultTimeoutMs() {
        return DEFAULT_TIMEOUT_MS;
    }
    
    /**
     * Establishes connection to the SMOS server
     * Simulates connection attempt with timeout
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        if (isConnected) {
            System.out.println("Already connected to SMOS server at " + serverUrl + ":" + serverPort);
            return true;
        }
        
        System.out.println("Attempting to connect to SMOS server at " + serverUrl + ":" + serverPort + "...");
        connectionAttempts++;
        
        // Simulate connection attempt with possible failure
        if (connectionAttempts > MAX_CONNECTION_ATTEMPTS) {
            System.out.println("❌ Failed to connect after " + MAX_CONNECTION_ATTEMPTS + " attempts.");
            return false;
        }
        
        // Simulate connection delay
        try {
            Thread.sleep(1000); // Simulate 1 second connection time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 90% chance of successful connection
        boolean success = Math.random() > 0.1;
        
        if (success) {
            isConnected = true;
            System.out.println("✓ Successfully connected to SMOS server.");
            connectionAttempts = 0; // Reset attempts on success
        } else {
            System.out.println("⚠ Connection attempt " + connectionAttempts + " failed. Will retry...");
        }
        
        return success;
    }
    
    /**
     * Disconnects from the SMOS server
     * Handles the postcondition about connection interruption
     */
    public void disconnect() {
        if (!isConnected) {
            System.out.println("Already disconnected from SMOS server.");
            return;
        }
        
        System.out.println("Disconnecting from SMOS server...");
        isConnected = false;
        System.out.println("✓ Disconnected from SMOS server.");
    }
    
    /**
     * Interrupts the SMOS server connection (simulates unexpected disconnection)
     * This method handles the postcondition about connection interruption
     */
    public void interruptConnection() {
        if (!isConnected) {
            System.out.println("SMOS server connection is already interrupted.");
            return;
        }
        
        System.out.println("\n⚠⚠⚠ SMOS SERVER CONNECTION INTERRUPTED ⚠⚠⚠");
        System.out.println("The connection to " + serverUrl + ":" + serverPort + " has been unexpectedly terminated.");
        System.out.println("This may affect enrollment synchronization.");
        
        isConnected = false;
        connectionAttempts = 0;
    }
    
    /**
     * Restores SMOS server connection after interruption
     * @return true if connection restored successfully, false otherwise
     */
    public boolean restoreConnection() {
        System.out.println("Attempting to restore SMOS server connection...");
        
        // Reset attempts before trying to restore
        connectionAttempts = 0;
        
        // Try to connect
        if (connect()) {
            System.out.println("✓ SMOS server connection restored successfully.");
            return true;
        } else {
            System.out.println("❌ Failed to restore SMOS server connection.");
            return false;
        }
    }
    
    /**
     * Synchronizes a student's enrollment status with the SMOS server
     * @param student Student object to synchronize
     * @return true if synchronization successful, false otherwise
     * @throws IllegalStateException if server is not connected
     */
    public boolean synchronizeStudentEnrollment(Student student) {
        if (!isConnected) {
            throw new IllegalStateException("Cannot synchronize - SMOS server is not connected");
        }
        
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null for synchronization");
        }
        
        System.out.println("Synchronizing student enrollment with SMOS server...");
        System.out.println("Student: " + student.getFullName() + " (ID: " + student.getStudentId() + ")");
        System.out.println("Status: " + student.getEnrollmentStatus());
        
        // Simulate server communication delay
        try {
            Thread.sleep(800); // Simulate 0.8 second server processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        // Simulate 95% chance of successful synchronization
        boolean success = Math.random() > 0.05;
        
        if (success) {
            System.out.println("✓ Student enrollment synchronized successfully with SMOS server.");
        } else {
            System.out.println("⚠ Synchronization failed. Will retry on next attempt.");
        }
        
        return success;
    }
    
    /**
     * Synchronizes a registration request with the SMOS server
     * @param request RegistrationRequest to synchronize
     * @return true if synchronization successful, false otherwise
     * @throws IllegalStateException if server is not connected
     */
    public boolean synchronizeRegistrationRequest(RegistrationRequest request) {
        if (!isConnected) {
            throw new IllegalStateException("Cannot synchronize - SMOS server is not connected");
        }
        
        if (request == null) {
            throw new IllegalArgumentException("Registration request cannot be null for synchronization");
        }
        
        System.out.println("Synchronizing registration request with SMOS server...");
        System.out.println("Request ID: " + request.getRequestId());
        System.out.println("Student: " + (request.getStudent() != null ? 
                           request.getStudent().getFullName() : "Unknown"));
        System.out.println("Status: " + request.getStatus());
        
        // Simulate server communication delay
        try {
            Thread.sleep(600); // Simulate 0.6 second server processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        // Simulate 97% chance of successful synchronization
        boolean success = Math.random() > 0.03;
        
        if (success) {
            System.out.println("✓ Registration request synchronized successfully with SMOS server.");
            
            // If request is approved and has a student, sync the student too
            if (request.isApproved() && request.getStudent() != null) {
                return synchronizeStudentEnrollment(request.getStudent());
            }
        } else {
            System.out.println("⚠ Synchronization failed. Will retry on next attempt.");
        }
        
        return success;
    }
    
    /**
     * Validates server connectivity by sending a ping request
     * @return true if server responds, false otherwise
     */
    public boolean pingServer() {
        if (!isConnected) {
            return false;
        }
        
        System.out.println("Pinging SMOS server at " + serverUrl + ":" + serverPort + "...");
        
        // Simulate ping delay
        try {
            Thread.sleep(300); // Simulate 0.3 second ping time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        // 98% chance of successful ping
        boolean success = Math.random() > 0.02;
        
        if (success) {
            System.out.println("✓ SMOS server is responsive.");
        } else {
            System.out.println("❌ SMOS server is not responding.");
            isConnected = false; // Mark as disconnected if ping fails
        }
        
        return success;
    }
    
    /**
     * Gets server status information as a formatted string
     * @return Server status information
     */
    public String getServerStatus() {
        return String.format(
            "SMOS Server Status:\n" +
            "  URL: %s\n" +
            "  Port: %d\n" +
            "  Connection: %s\n" +
            "  Connection Attempts: %d\n" +
            "  Max Attempts: %d\n" +
            "  Timeout: %d ms",
            serverUrl, serverPort,
            isConnected ? "Connected ✓" : "Disconnected ✗",
            connectionAttempts,
            MAX_CONNECTION_ATTEMPTS,
            DEFAULT_TIMEOUT_MS
        );
    }
    
    /**
     * Simulates network issues causing intermittent connectivity
     * Randomly interrupts and restores connection to simulate real-world scenarios
     */
    public void simulateNetworkIssues() {
        System.out.println("\n⚠ Simulating network issues...");
        
        // Randomly decide to interrupt or restore
        if (Math.random() > 0.5) {
            if (isConnected) {
                interruptConnection();
            }
        } else {
            if (!isConnected) {
                restoreConnection();
            }
        }
    }
    
    /**
     * Resets the server connection state to default
     */
    public void resetConnection() {
        System.out.println("Resetting SMOS server connection...");
        isConnected = true;
        connectionAttempts = 0;
        System.out.println("✓ Connection reset to default state.");
    }
    
    @Override
    public String toString() {
        return String.format("SMOSServer[url=%s, port=%d, connected=%s, attempts=%d]", 
                           serverUrl, serverPort, isConnected, connectionAttempts);
    }
    
    /**
     * Main demonstration method for SMOSServer class
     * This is for testing purposes only
     */
    public static void main(String[] args) {
        System.out.println("=== SMOS Server Demonstration ===");
        SMOSServer server = new SMOSServer();
        
        System.out.println(server.getServerStatus());
        
        // Test connection
        server.pingServer();
        
        // Test interruption
        server.interruptConnection();
        
        // Test restoration
        server.restoreConnection();
        
        System.out.println("\nDemonstration complete.");
    }
}