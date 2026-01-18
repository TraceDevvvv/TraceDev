'''
Agency class representing agency operators in the Cultural Heritage Management System.
This class handles agency authentication and maintains agency information.
'''
import java.util.HashMap;
import java.util.Map;
public class Agency {
    private String id;
    private String name;
    private String username;
    private String password;
    // Simulated agency database - in a real application, this would come from a database
    private static final Map<String, Agency> AGENCIES = new HashMap<>();
    static {
        // Initialize with demo agencies
        AGENCIES.put("agency1", new Agency("AG001", "Cultural Heritage Agency", "agency1", "pass123"));
        AGENCIES.put("admin", new Agency("ADMIN", "System Administrator", "admin", "admin123"));
        AGENCIES.put("museum", new Agency("MUS001", "National Museum", "museum", "museum456"));
        AGENCIES.put("archive", new Agency("ARC001", "Historical Archive", "archive", "archive789"));
    }
    /**
     * Constructor for creating a new Agency instance.
     * @param id Unique identifier for the agency
     * @param name Display name of the agency
     * @param username Login username
     * @param password Login password
     */
    public Agency(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    /**
     * Authenticates an agency based on username and password.
     * @param username The username to authenticate
     * @param password The password to verify
     * @return Authenticated Agency object or null if authentication fails
     */
    public static Agency authenticate(String username, String password) {
        Agency agency = AGENCIES.get(username);
        if (agency != null && agency.password.equals(password)) {
            return agency;
        }
        return null;
    }
    /**
     * Gets all available agencies (for demonstration purposes).
     * @return Map of all agencies
     */
    public static Map<String, Agency> getAllAgencies() {
        return new HashMap<>(AGENCIES);
    }
    /**
     * Checks if an agency with the given username exists.
     * @param username The username to check
     * @return true if agency exists, false otherwise
     */
    public static boolean agencyExists(String username) {
        return AGENCIES.containsKey(username);
    }
    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    // Note: Password getter is not provided for security reasons
    /**
     * Returns a string representation of the agency.
     * @return String containing agency details
     */
    @Override
    public String toString() {
        return "Agency{id='" + id + "', name='" + name + "', username='" + username + "'}";
    }
}