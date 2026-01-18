package ModifyTouristData_1702899395;

import java.util.HashMap;
import java.util.Map;

public class TouristDataStore {
    // Simulates a database or persistent storage for Tourist objects
    private Map<String, Tourist> tourists;

    public TouristDataStore() {
        this.tourists = new HashMap<>();
        // Add some dummy data for testing
        tourists.put("tourist123", new Tourist("tourist123", "John Doe", "john.doe@example.com", "password123"));
    }

    /**
     * Retrieves a tourist by their ID.
     * @param id The ID of the tourist to retrieve.
     * @return The Tourist object if found, null otherwise.
     */
    public Tourist getTouristById(String id) {
        return tourists.get(id);
    }

    /**
     * Saves or updates a tourist's data.
     * @param tourist The Tourist object to save or update.
     */
    public void saveTourist(Tourist tourist) {
        tourists.put(tourist.getId(), tourist);
        System.out.println("Tourist data saved for ID: " + tourist.getId());
    }
}
