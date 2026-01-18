
package etour.framework;

import etour.domain.SearchPreferences;
import java.util.Arrays;

/**
 * External service data source simulating the ETOUR server.
 * Added connection check method.
 */
public class ServerETOURDataSource implements PreferencesDataSource {
    // Simulate a simple in‑memory store for demonstration.
    private SearchPreferences simulatedStorage;

    public ServerETOURDataSource() {
        // Initialize with dummy data for tourist "T001"
        simulatedStorage = new SearchPreferences("T001", "English",
                Arrays.asList("Museum", "Park"), 10);
    }

    @Override
    public SearchPreferences fetchPreferences(String touristId) {
        if (!isConnectionActive()) {
            throw new RuntimeException("Connection error: server unreachable");
        }
        // Return preferences only if touristId matches our dummy.
        if ("T001".equals(touristId)) {
            return simulatedStorage;
        }
        return null;
    }

    @Override
    public boolean persistPreferences(SearchPreferences preferences) {
        if (!isConnectionActive()) {
            throw new RuntimeException("Connection error: server unreachable");
        }
        simulatedStorage = preferences;
        System.out.println("ETOUR: persisted " + preferences);
        return true;
    }

    /**
     * Checks whether the connection to the server is active.
     * @return true if active, false otherwise
     */
    public boolean isConnectionActive() {
        // Simulate that connection is always active for this demo.
        // Could be extended to randomly fail for testing.
        return true;
    }
}
