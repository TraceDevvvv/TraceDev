package ModifyTouristData_1702899395;

public class TouristService {
    private TouristDataStore dataStore;

    public TouristService(TouristDataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Loads tourist data for a given authenticated tourist.
     * In a real system, authentication would provide the tourist ID.
     * @param touristId The ID of the authenticated tourist.
     * @return The Tourist object if found, null otherwise.
     */
    public Tourist loadTouristData(String touristId) {
        // Simulate successful authentication by assuming touristId is valid
        System.out.println("Loading data for tourist ID: " + touristId);
        return dataStore.getTouristById(touristId);
    }

    /**
     * Modifies the data of a tourist after validation.
     * @param touristId The ID of the tourist whose data is being modified.
     * @param newName The new name for the tourist.
     * @param newEmail The new email for the tourist.
     * @param newPassword The new password for the tourist.
     * @return true if modification is successful, false otherwise.
     * @throws IllegalArgumentException if the new data is invalid or insufficient.
     */
    public boolean modifyTouristData(String touristId, String newName, String newEmail, String newPassword) throws IllegalArgumentException {
        Tourist tourist = dataStore.getTouristById(touristId);
        if (tourist == null) {
            // This case should ideally not happen if authentication is successful
            // and loadTouristData is called first.
            throw new IllegalArgumentException("Tourist not found.");
        }

        // Create a temporary tourist object to validate new data
        Tourist tempTourist = new Tourist(touristId, newName, newEmail, newPassword);
        if (!tempTourist.isValid()) {
            // Activates the use case 'Errored' as per requirement 4
            throw new IllegalArgumentException("Invalid or insufficient data provided. Name and Email cannot be empty.");
        }

        // Simulate confirmation step (Requirement 4 & 5)
        System.out.println("Confirming changes for tourist ID: " + touristId);
        System.out.println("New Name: " + newName);
        System.out.println("New Email: " + newEmail);
        // In a real scenario, user would confirm via UI.
        // For this simulation, we assume confirmation is given.

        // Update the tourist object with new data (Requirement 6)
        tourist.setName(newName);
        tourist.setEmail(newEmail);
        tourist.setPassword(newPassword);
        dataStore.saveTourist(tourist);

        System.out.println("Data successfully modified for tourist ID: " + touristId);
        return true;
    }

    /**
     * Simulates a scenario where the tourist cancels the operation.
     * @param touristId The ID of the tourist.
     */
    public void cancelModification(String touristId) {
        System.out.println("Tourist " + touristId + " cancelled the data modification operation.");
    }

    /**
     * Simulates an interruption of connection to the server (ETOUR).
     */
    public void simulateConnectionInterruption() {
        System.err.println("ETOUR: Connection to the server interrupted.");
        // In a real application, this would involve throwing a specific exception
        // or handling network errors.
    }
}
