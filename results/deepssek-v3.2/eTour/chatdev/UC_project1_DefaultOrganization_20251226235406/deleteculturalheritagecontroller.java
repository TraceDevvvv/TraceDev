'''
DeleteCulturalHeritageController handles the business logic for deleting cultural heritage.
Manages user interactions, validates operations, and coordinates with database and server.
'''
import java.util.List;
public class DeleteCulturalHeritageController {
    private CulturalHeritageDatabase database;
    private ServerConnection serverConnection;
    private boolean operationInProgress;
    public DeleteCulturalHeritageController() {
        database = new CulturalHeritageDatabase();
        serverConnection = new ServerConnection();
        operationInProgress = false;
    }
    /**
     * Searches for cultural heritage items.
     * Simulates the SearchCulturalHeritage use case result.
     */
    public List<CulturalHeritage> searchCulturalHeritage(String query) {
        return database.searchCulturalHeritage(query);
    }
    /**
     * Initiates the deletion process for a cultural heritage item.
     * Block input controls during operation to prevent multiple submissions.
     */
    public synchronized boolean initiateDeletion(int culturalHeritageId) {
        if (operationInProgress) {
            System.out.println("Operation already in progress. Please wait.");
            return false;
        }
        CulturalHeritage item = database.getCulturalHeritageById(culturalHeritageId);
        if (item == null) {
            System.out.println("Cultural heritage item not found.");
            return false;
        }
        operationInProgress = true;
        return true;
    }
    /**
     * Confirms and executes the deletion operation.
     * Handles server connection interruptions.
     */
    public synchronized String confirmDeletion(int culturalHeritageId) {
        if (!operationInProgress) {
            return "No deletion operation in progress.";
        }
        try {
            // Check server connection before proceeding
            if (!serverConnection.isConnected()) {
                operationInProgress = false;
                return "ERROR: Connection to ETOUR server interrupted. Operation cancelled.";
            }
            // Perform the actual deletion
            boolean success = database.deleteCulturalHeritage(culturalHeritageId);
            operationInProgress = false;
            if (success) {
                return "SUCCESS: Cultural heritage item has been successfully deleted.";
            } else {
                return "ERROR: Failed to delete cultural heritage item.";
            }
        } catch (Exception e) {
            operationInProgress = false;
            return "ERROR: An unexpected error occurred: " + e.getMessage();
        }
    }
    /**
     * Cancels the ongoing deletion operation.
     */
    public synchronized void cancelDeletion() {
        operationInProgress = false;
        System.out.println("Deletion operation cancelled by agency operator.");
    }
    /**
     * Gets all cultural heritage items for display.
     */
    public List<CulturalHeritage> getAllCulturalHeritage() {
        return database.getAllCulturalHeritage();
    }
    /**
     * Checks if an operation is in progress.
     */
    public boolean isOperationInProgress() {
        return operationInProgress;
    }
    /**
     * Simulates logging in the agency operator.
     * Entry condition: The agency has logged.
     */
    public boolean loginAgencyOperator(String username, String password) {
        // Simple authentication simulation
        return "agency".equals(username) && "password123".equals(password);
    }
}