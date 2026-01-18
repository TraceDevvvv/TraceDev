/**
 * Simulates database operations for logging and SMOS server communication.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
public class DatabaseSimulator {
    private List<DatabaseLog> operationsLog;
    private AtomicBoolean serverConnected;
    /**
     * Constructor initializes the operation log and server state.
     */
    public DatabaseSimulator() {
        this.operationsLog = new ArrayList<>();
        this.serverConnected = new AtomicBoolean(true);
    }
    /**
     * Logs an operation to simulate database record keeping.
     * 
     * @param operationType Type of operation (ASSIGN/REMOVE)
     * @param addressId     The address ID involved
     * @param teachingId    The teaching ID involved
     */
    public void logOperation(String operationType, String addressId, String teachingId) {
        if (!serverConnected.get()) {
            throw new RuntimeException("SMOS server connection interrupted");
        }
        DatabaseLog log = new DatabaseLog(new Date(), operationType, addressId, teachingId);
        operationsLog.add(log);
        // Simulate SMOS server communication
        simulateSMOSCommunication(log);
    }
    /**
     * Simulates communication with SMOS server.
     * 
     * @param log The database log entry
     */
    private void simulateSMOSCommunication(DatabaseLog log) {
        // Simulate potential server interruption (10% chance for simulation)
        if (Math.random() < 0.1) {
            serverConnected.set(false);
            throw new RuntimeException("SMOS server connection interrupted during communication");
        }
        // Simulate server communication delay
        try {
            Thread.sleep(100); // Short delay to simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SMOS communication interrupted", e);
        }
        System.out.println("SMOS Update: " + log);
    }
    /**
     * Gets all logged operations.
     * 
     * @return List of database logs
     */
    public List<DatabaseLog> getOperationsLog() {
        return new ArrayList<>(operationsLog);
    }
    /**
     * Checks if server is connected.
     * 
     * @return true if server is connected
     */
    public boolean isServerConnected() {
        return serverConnected.get();
    }
    /**
     * Resets server connection (for simulation purposes).
     */
    public void resetServerConnection() {
        serverConnected.set(true);
    }
    /**
     * Inner class to represent a database log entry.
     */
    private static class DatabaseLog {
        private Date timestamp;
        private String operationType;
        private String addressId;
        private String teachingId;
        public DatabaseLog(Date timestamp, String operationType,
                String addressId, String teachingId) {
            this.timestamp = timestamp;
            this.operationType = operationType;
            this.addressId = addressId;
            this.teachingId = teachingId;
        }
        @Override
        public String toString() {
            return "[" + timestamp + "] " + operationType +
                    " - Address: " + addressId + ", Teaching: " + teachingId;
        }
    }
}