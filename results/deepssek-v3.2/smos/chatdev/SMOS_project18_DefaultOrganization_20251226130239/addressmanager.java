/**
 * Singleton class to manage the archive of addresses.
 * This simulates the backend logic for inserting and validating addresses.
 * In a real application, this would interact with a database or server.
 * Implements the core functionality for the EnterNewAddress use case.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ArchiveManager {
    // Simulate an in-memory list of addresses for demonstration
    private static List<String> addressArchive = new ArrayList<>();
    private static ArchiveManager instance;
    // Regular expression for validating address format
    private static final Pattern ADDRESS_PATTERN = 
        Pattern.compile("^[A-Za-z0-9\\s\\-,.#]+$", Pattern.CASE_INSENSITIVE);
    // Private constructor for singleton pattern
    private ArchiveManager() {}
    /**
     * Returns the singleton instance of ArchiveManager.
     * 
     * @return the singleton ArchiveManager instance
     */
    public static synchronized ArchiveManager getInstance() {
        if (instance == null) {
            instance = new ArchiveManager();
        }
        return instance;
    }
    /**
     * Validates the address name according to business rules.
     * 
     * @param addressName the address to validate
     * @return true if valid, false otherwise
     */
    private boolean validateAddress(String addressName) {
        if (addressName == null || addressName.trim().isEmpty()) {
            System.out.println("Validation Error: Address name cannot be empty.");
            return false;
        }
        String trimmedAddress = addressName.trim();
        // Check length constraints
        if (trimmedAddress.length() < 2) {
            System.out.println("Validation Error: Address name must be at least 2 characters long.");
            return false;
        }
        if (trimmedAddress.length() > 100) {
            System.out.println("Validation Error: Address name cannot exceed 100 characters.");
            return false;
        }
        // Validate format using regex
        Matcher matcher = ADDRESS_PATTERN.matcher(trimmedAddress);
        if (!matcher.matches()) {
            System.out.println("Validation Error: Address name contains invalid characters.");
            return false;
        }
        return true;
    }
    /**
     * Inserts a new address into the archive after validating it.
     * This method mimics server interaction and may simulate errors.
     * 
     * @param addressName the address to insert
     * @return true if insertion was successful, false otherwise (e.g., server error)
     */
    public synchronized boolean insertAddress(String addressName) {
        // Validate address name
        if (!validateAddress(addressName)) {
            return false;
        }
        String trimmedAddress = addressName.trim();
        // Check if address already exists (case-insensitive for better user experience)
        for (String existingAddress : addressArchive) {
            if (existingAddress.equalsIgnoreCase(trimmedAddress)) {
                System.out.println("Error: Address '" + trimmedAddress + "' already exists in archive.");
                return false;
            }
        }
        // Simulate a server connection that might be interrupted (e.g., SMOS server)
        // For demonstration, assume a 20% chance of server error as per use case
        double random = Math.random();
        if (random < 0.2) {
            System.out.println("Error: Connection to SMOS server interrupted.");
            return false; // Simulate server interruption
        }
        // If no error, add to archive and return success
        addressArchive.add(trimmedAddress);
        System.out.println("Success: Address '" + trimmedAddress + "' added to archive.");
        return true;
    }
    /**
     * Utility method to retrieve all addresses (for debugging or extension).
     * 
     * @return a list of all addresses in the archive
     */
    public List<String> getAllAddresses() {
        return new ArrayList<>(addressArchive); // Return a copy for safety
    }
    /**
     * Clears the archive (for testing purposes)
     */
    public void clearArchive() {
        addressArchive.clear();
    }
    /**
     * Searches for addresses containing the given search term.
     * 
     * @param searchTerm the term to search for
     * @return list of matching addresses
     */
    public List<String> searchAddresses(String searchTerm) {
        List<String> results = new ArrayList<>();
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return results;
        }
        String lowerSearchTerm = searchTerm.toLowerCase().trim();
        for (String address : addressArchive) {
            if (address.toLowerCase().contains(lowerSearchTerm)) {
                results.add(address);
            }
        }
        return results;
    }
    /**
     * Gets the count of addresses in the archive.
     * 
     * @return number of addresses
     */
    public int getAddressCount() {
        return addressArchive.size();
    }
}