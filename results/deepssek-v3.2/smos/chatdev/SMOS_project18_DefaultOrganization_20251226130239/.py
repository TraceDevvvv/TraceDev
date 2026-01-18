/**
 * Singleton class to manage the archive of addresses.
 * This simulates the backend logic for inserting and validating addresses.
 * In a real application, this would interact with a database or server.
 */
public class ArchiveManager {
    // Simulate an in-memory list of addresses for demonstration
    private static java.util.List<String> addressArchive = new java.util.ArrayList<>();
    /**
     * Inserts a new address into the archive after validating it.
     * This method mimics server interaction and may simulate errors.
     * 
     * @param addressName the address to insert
     * @return true if insertion was successful, false otherwise (e.g., server error)
     */
    public static boolean insertAddress(String addressName) {
        // Additional validation can be added here (e.g., regex for valid address format)
        if (addressName == null || addressName.trim().isEmpty()) {
            return false; // Should not happen if GUI validation passes, but safe check
        }
        // Simulate a server connection that might be interrupted (e.g., SMOS server)
        // For demonstration, let's assume a 20% chance of server error
        double random = Math.random();
        if (random < 0.2) {
            return false; // Simulate server interruption
        }
        // If no error, add to archive and return success
        addressArchive.add(addressName);
        System.out.println("Address added to archive: " + addressName);
        return true;
    }
    /**
     * Utility method to retrieve all addresses (for debugging or extension).
     * 
     * @return a list of all addresses in the archive
     */
    public static java.util.List<String> getAllAddresses() {
        return new java.util.ArrayList<>(addressArchive); // Return a copy for safety
    }
}