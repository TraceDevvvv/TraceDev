I'll create the DataHandler class for server communication. This class should handle sending delay data to a server, with proper error handling, simulation mode for testing, and connection management as mentioned in the postconditions (SMOS server).

```java
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * DataHandler class responsible for server communication.
 * Handles sending delay data to the server with error handling and retry logic.
 * Supports simulation mode for testing without actual server connection.
 */
public class DataHandler {
    // Configuration constants
    private static final String DEFAULT_SERVER_URL = "http://localhost:8080/api/delays";
    private static final int CONNECTION_TIMEOUT = 10000; // 10 seconds
    private static final int READ_TIMEOUT = 15000; // 15 seconds
    private static final int MAX_RETRY_ATTEMPTS = 3;
    
    // File constants for data persistence
    private static final String DATA_DIR = "ata_delay_data";
    private static final String BACKUP_FILE_FORMAT = "yyyyMMdd_HHmmss";
    
    private String serverUrl;
    private boolean simulationMode;
    private String currentStaffName;
    private List<DelayEntry> pendingEntries;
    
    // Logging and status
    private StringBuilder operationLog;
    
    /**
     * Constructor with default server URL
     * @param staffName Name of the ATA staff using the system
     */
    public DataHandler(String staffName) {
        this(DEFAULT_SERVER_URL, staffName);
    }
    
    /**
     * Constructor with custom server URL
     * @param serverUrl The URL of the server endpoint
     * @param staffName Name of the ATA staff using the system
     */
    public DataHandler(String serverUrl, String staffName) {
        this.serverUrl = serverUrl;
        this.currentStaffName = staffName;
        this.simulationMode = false;
        this.pendingEntries = new ArrayList<>();
        this.operationLog = new StringBuilder();
        
        log("DataHandler initialized for staff: " + staffName);
        log("Server URL: " + serverUrl);
    }
    
    /**
     * Enable or disable simulation mode
     * @param simulationMode True for simulation mode, false for real server communication
     */
    public void setSimulationMode(boolean simulationMode) {
        this.simulationMode = simulationMode;
        log("Simulation mode: " + (simulationMode ? "ENABLED" : "DISABLED"));
    }
    
    /**
     * Send delay data to the server
     * @param delayEntries List of delay entries to send
     * @return True if successful, false otherwise
     */
    public boolean sendDelayData(List<DelayEntry> delayEntries) {
        if (delayEntries == null || delayEntries.isEmpty()) {
            log("No delay entries to send");
            return false;
        }
        
        log("Preparing to send " + delayEntries.size() + " delay entries");
        
        // Validate all entries before sending
        String validationError = validateDelayEntries(delayEntries);
        if (validationError != null) {
            log("Validation failed: " + validationError);
            return false;
        }
        
        // Store pending entries
        pendingEntries = new ArrayList<>(delayEntries);
        
        // Check for simulation mode
        if (simulationMode) {
            return simulateServerSubmission(delayEntries);
        }
        
        // Real server communication
        return sendToServer(delayEntries);
    }
    
    /**
     * Send data to the actual server with retry logic
     * @param delayEntries List of delay entries
     * @return True if successful, false otherwise
     */
    private boolean sendToServer(List<DelayEntry> delayEntries) {
        log("Attempting to connect to server: " + serverUrl);
        
        // Try multiple times in case of connection issues
        for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
            try {
                log("Connection attempt " + attempt + " of " + MAX_RETRY_ATTEMPTS);
                
                // Create HTTP connection
                URL url = new URL(serverUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                
                // Configure connection
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("User-Agent", "ATA-Delay-System/1.0");
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setDoOutput(true);
                
                // Create JSON payload
                String jsonPayload = createJsonPayload(delayEntries);
                log("Payload size: " + jsonPayload.length() + " bytes");
                
                // Send data
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonPayload.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                
                // Get response
                int responseCode = connection.getResponseCode();
                log("Server response code: " + responseCode);
                
                // Read response
                StringBuilder response = new StringBuilder();
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                }
                
                // Handle response
                if (responseCode == HttpURLConnection.HTTP_OK || 
                    responseCode == HttpURLConnection.HTTP_CREATED) {
                    log("Server accepted data successfully");
                    log("Response: " + response.toString());
                    
                    // Clear pending entries on success
                    pendingEntries.clear();
                    createBackupFile(delayEntries, true);
                    return true;
                } else {
                    log("Server rejected data with code: " + responseCode);
                    log("Error response: " + response.toString());
                    
                    // Save to backup file for retry
                    createBackupFile(delayEntries, false);
                    
                    // Wait before retry (exponential backoff)
                    if (attempt < MAX_RETRY_ATTEMPTS) {
                        int waitTime = 1000 * (int) Math.pow(2, attempt - 1); // 1s, 2s, 4s
                        log("Waiting " + waitTime + "ms before retry...");
                        Thread.sleep(waitTime);
                    }
                }
                
                connection.disconnect();
                
            } catch (SocketTimeoutException e) {
                log("Connection timeout on attempt " + attempt + ": " + e.getMessage());
                createBackupFile(delayEntries, false);
                
                if (attempt == MAX_RETRY_ATTEMPTS) {
                    log("Max retry attempts reached. Data saved to backup.");
                    showConnectionError("Connection timeout. Data saved locally for later submission.");
                }
                
            } catch (ConnectException e) {
                log("Connection refused on attempt " + attempt + ": " + e.getMessage());
                createBackupFile(delayEntries, false);
                
                if (attempt == MAX_RETRY_ATTEMPTS) {
                    log("Max retry attempts reached. SMOS server connection interrupted.");
                    showConnectionError("SMOS server connection interrupted. Data saved locally.");
                    
                    // SMOS server interruption as per postconditions
                    handleSMOSServerInterruption();
                }
                
            } catch (IOException e) {
                log("IO error on attempt " + attempt + ": " + e.getMessage());
                createBackupFile(delayEntries, false);
                
                if (attempt == MAX_RETRY_ATTEMPTS) {
                    log("Max retry attempts reached due to IO error.");
                    showConnectionError("Network error. Please check your connection.");
                }
                
            } catch (Exception e) {
                log("Unexpected error on attempt " + attempt + ": " + e.getMessage());
                e.printStackTrace();
                createBackupFile(delayEntries, false);
                
                if (attempt == MAX_RETRY_ATTEMPTS) {
                    log("Max retry attempts reached due to unexpected error.");
                    showConnectionError("Unexpected error occurred: " + e.getMessage());
                }
            }
        }
        
        return false;
    }
    
    /**
     * Simulate server submission (for testing without actual server)
     * @param delayEntries List of delay entries
     * @return Always returns true for simulation
     */
    private boolean simulateServerSubmission(List<DelayEntry> delayEntries) {
        log("SIMULATION MODE: Simulating server submission");
        
        try {
            // Simulate network delay
            Thread.sleep(1500);
            
            // Validate in simulation
            String validationError = validateDelayEntries(delayEntries);
            if (validationError != null) {
                log("SIMULATION: Validation failed - " + validationError);
                return false;
            }
            
            // Create backup file even in simulation
            createBackupFile(delayEntries, true);
            
            // Log simulated success
            log("SIMULATION: Successfully submitted " + delayEntries.size() + " entries");
            for (DelayEntry entry : delayEntries) {
                log("SIMULATION: " + entry.getSummary());
            }
            
            // Clear pending entries
            pendingEntries.clear();
            
            return true;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("SIMULATION: Interrupted during simulation");
            return false;
        } catch (Exception e) {
            log("SIMULATION: Error during simulation - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Validate a list of delay entries
     * @param delayEntries List of delay entries to validate
     * @return Error message if validation fails, null if valid
     */
    private String validateDelayEntries(List<DelayEntry> delayEntries) {
        if (delayEntries == null) {
            return "Delay entries list is null";
        }
        
        if (delayEntries.isEmpty()) {
            return "No delay entries to process";
        }
        
        StringBuilder errors = new StringBuilder();
        int errorCount = 0;
        
        for (int i = 0; i < delayEntries.size(); i++) {
            DelayEntry entry = delayEntries.get(i);
            
            if (entry == null) {
                errors.append("Entry ").append(i).append(" is null\n");
                errorCount++;
                continue;
            }
            
            if (!entry.isValid()) {
                errors.append("Entry ").append(i).append(" is invalid: ")
                      .append(entry.getStudentId()).append("\n");
                errorCount++;
                continue;
            }
            
            // Additional validation rules
            if (entry.getDelayMinutes() <= 0) {
                errors.append("Entry ").append(i).append(" has invalid delay minutes: ")
                      .append(entry.getDelayMinutes()).append("\n");
                errorCount++;
            }
            
            if (entry.getStudentId() == null || entry.getStudentId().trim().isEmpty()) {
                errors.append("Entry ").append(i).append(" has empty student ID\n");
                errorCount++;
            }
            
            if (entry.getEntryBy() == null || entry.getEntryBy().trim().isEmpty()) {
                errors.append("Entry ").append(i).append(" has empty staff identifier\n");
                errorCount++;
            }
        }
        
        if (errorCount > 0) {
            return "Found " + errorCount + " validation error(s):\n" + errors.toString();
        }
        
        return null;
    }
    
    /**
     * Create JSON payload from delay entries
     * @param delayEntries List of delay entries
     * @return JSON string
     */
    private String createJsonPayload(List<DelayEntry> delayEntries) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"staff\": \"").append(currentStaffName).append("\",\n");
        json.append("  \"timestamp\": \"").append(LocalDateTime.now()).append("\",\n");
        json.append("  \"entries\": [\n");
        
        for (int i = 0; i < delayEntries.size(); i++) {
            DelayEntry entry = delayEntries.get(i);
            json.append("    {\n");
            json.append("      \"studentId\": \"").append(entry.getStudentId()).append("\",\n");
            json.append("      \"studentName\": \"").append(entry.getStudentName()).append("\",\n");
            json.append("      \"className\": \"").append(entry.getClassName()).append("\",\n");
            json.append("      \"delayMinutes\": ").append(entry.getDelayMinutes()).append(",\n");
            json.append("      \"entryTime\": \"").append(entry.getFormattedDateTime()).append("\"\n");
            
            if (i < delayEntries.size() - 1) {
                json.append("    },\n");
            } else {
                json.append("    }\n");
            }
        }
        
        json.append("  ]\n");
        json.append("}");
        
        return json.toString();
    }
    
    /**
     * Create a backup file for data persistence
     * @param delayEntries List of delay entries
     * @param success Whether the operation was successful
     */
    private void createBackupFile(List<DelayEntry> delayEntries, boolean success) {
        try {
            // Create data directory if it doesn't exist
            File dataDir = new File(DATA_DIR);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            
            // Create filename with timestamp
            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern(BACKUP_FILE_FORMAT));
            String status = success ? "success" : "pending";
            String filename = DATA_DIR + File.separator + 
                             "delay_data_" + timestamp + "_" + status + ".csv";
            
            // Write CSV file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                // Write header
                writer.println("studentId,studentName,delayMinutes,className,entryBy,entryDateTime");
                
                // Write data
                for (DelayEntry entry : delayEntries) {
                    writer.println(entry.toCSVString());
                }
                
                log("Backup file created: " + filename + " (" + delayEntries.size() + " entries)");
                
            } catch (IOException e) {
                log("Error creating backup file: " + e.getMessage());
            }
            
        } catch (Exception e) {
            log("Unexpected error creating backup: " + e.getMessage());
        }
    }
    
    /**
     * Handle SMOS server interruption as per postconditions
     */
    private void handleSMOSServerInterruption() {
        log("SMOS SERVER INTERRUPTION HANDLED");
        
        // In a real implementation, this would:
        // 1. Switch to backup server if available
        // 2. Notify administrators
        // 3. Enable offline mode
        // 4. Schedule retry for pending data
        
        // For now, just log and notify user
        showConnectionError("SMOS server connection interrupted. Working in offline mode.");
        
        // Switch to simulation mode temporarily
        boolean wasSimulation = simulationMode;
        simulationMode = true;
        log("Temporarily switched to simulation mode due to SMOS interruption");
        
        // Try to send pending entries in simulation mode
        if (!pendingEntries.isEmpty()) {
            log("Attempting to process " + pendingEntries.size() + " pending entries in simulation");
            simulateServerSubmission(new ArrayList<>(pendingEntries));
        }
        
        // Restore original mode
        simulationMode = wasSimulation;
    }
    
    /**
     * Show connection error message to user
     * @param message Error message
     */
    private void showConnectionError(String message) {
        // In a real application, this would update the UI status
        // For now, we'll just log it
        log("USER NOTIFICATION: " + message);
        
        // Could show a dialog if running in GUI context
        // JOptionPane.showMessageDialog(null, message, "Connection Error", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Log operation message
     * @param message Message to log
     */
    private void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String logMessage = "[" + timestamp + "] " + message;
        operationLog.append(logMessage).append("\n");
        System.out.println(logMessage); // Also print to console for debugging
    }
    
    /**
     * Get the operation log
     * @return String containing all logged operations
     */
    public String getOperationLog() {
        return operationLog.toString();
    }
    
    /**
     * Clear the operation log
     */
    public void clearOperationLog() {
        operationLog = new StringBuilder();
        log("Operation log cleared");
    }
    
    /**
     * Get pending entries count
     * @return Number of pending entries
     */
    public int getPendingEntriesCount() {
        return pendingEntries.size();
    }
    
    /**
     * Get server URL
     * @return Current server URL
     */
    public String getServerUrl() {
        return serverUrl;
    }
    
    /**
     * Set server URL
     * @param serverUrl New server URL
     */
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        log("Server URL updated to: " + serverUrl);
    }
    
    /**
     * Test server connection
     * @return True if connection test is successful, false otherwise
     */
    public boolean testConnection() {
        if (simulationMode) {
            log("Simulation mode: Connection test skipped");
            return true;
        }
        
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            
            boolean success = (responseCode == HttpURLConnection.HTTP_OK);
            log("Connection test " + (success ? "SUCCESS" : "FAILED") + " (code: " + responseCode + ")");
            return success;
            
        } catch (Exception e) {
            log("Connection test failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Save operation log to file
     * @return True if successful, false otherwise
     */
    public boolean saveLogToFile() {
        try {
            File dataDir = new File(DATA_DIR);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            
            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd"));
            String filename = DATA_DIR + File.separator