/**
 * Simulates a database for tourist sites. It provides methods to retrieve site details
 * and simulate database connection states.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class SiteDatabase {
    private Map<String, Site> sites;
    private boolean isConnected;
    /**
     * Constructs a new SiteDatabase and initializes it with some dummy data.
     * The connection status is initially set to true.
     */
    public SiteDatabase() {
        sites = new HashMap<>();
        // Populate with some dummy data
        sites.put("S001", new Site("S001", "Eiffel Tower", "An iconic wrought-iron lattice tower on the Champ de Mars in Paris, France.", "Paris, France", 4.8));
        sites.put("S002", new Site("S002", "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy. Capable of seating 50,000 spectators.", "Rome, Italy", 4.7));
        sites.put("S003", new Site("S003", "Great Wall of China", "A series of fortifications that were built across the historical northern borders of ancient Chinese states.", "Northern China", 4.9));
        sites.put("S004", new Site("S004", "Taj Mahal", "An ivory-white marble mausoleum on the right bank of the river Yamuna in the Indian city of Agra.", "Agra, India", 4.6));
        sites.put("S005", new Site("S005", "Machu Picchu", "An ancient Inca citadel located in the Eastern Cordillera of southern Peru, in the Cusco Region, Urubamba Province.", "Cusco Region, Peru", 4.9));
        isConnected = true; // Initially connected
    }
    /**
     * Retrieves the details of a specific site from the simulated database.
     *
     * @param siteId The unique identifier of the site to retrieve.
     * @return The Site object if found.
     * @throws DatabaseConnectionException If the simulated connection to the database is interrupted.
     * @throws SiteNotFoundException If no site with the given ID is found.
     */
    public Site getSiteDetails(String siteId) throws DatabaseConnectionException, SiteNotFoundException {
        // Simulate checking connection status
        if (!isConnected) {
            throw new DatabaseConnectionException("Connection to server ETOUR interrupted.");
        }
        // Simulate database lookup
        Site site = sites.get(siteId);
        if (site == null) {
            throw new SiteNotFoundException("Site with ID '" + siteId + "' not found.");
        }
        return site;
    }
    /**
     * Sets the simulated connection status to the database.
     * This is used to test interruption scenarios.
     *
     * @param status True to simulate an active connection, false to simulate an interruption.
     */
    public void setConnectionStatus(boolean status) {
        this.isConnected = status;
    }
    /**
     * Gets the current simulated connection status.
     * @return True if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }
    /**
     * Gets a set of all available site IDs in the database.
     * @return A Set of String representing site IDs.
     */
    public Set<String> getAvailableSiteIds() {
        return sites.keySet();
    }
}