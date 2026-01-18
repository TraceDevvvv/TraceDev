/**
 * Manages the relationship between addresses and teachings.
 * Simulates database operations for the system.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AddressTeachingManager {
    private Map<String, Address> addresses;
    private Map<String, Teaching> teachings;
    private DatabaseSimulator dbSimulator;
    /**
     * Constructor initializes the manager with sample data.
     */
    public AddressTeachingManager() {
        this.addresses = new HashMap<>();
        this.teachings = new HashMap<>();
        this.dbSimulator = new DatabaseSimulator();
        // Initialize with sample data
        initializeSampleData();
    }
    /**
     * Initialize the system with sample addresses and teachings.
     */
    private void initializeSampleData() {
        // Create sample addresses
        Address address1 = new Address("ADDR001", "123 Main St", "New York", "10001");
        Address address2 = new Address("ADDR002", "456 Oak Ave", "Chicago", "60601");
        Address address3 = new Address("ADDR003", "789 Pine Rd", "Los Angeles", "90001");
        addresses.put(address1.getId(), address1);
        addresses.put(address2.getId(), address2);
        addresses.put(address3.getId(), address3);
        // Create sample teachings
        Teaching teaching1 = new Teaching("TEACH001", "Mathematics 101",
                "Basic mathematics course", "Dr. Smith", 40);
        Teaching teaching2 = new Teaching("TEACH002", "Computer Science",
                "Introduction to programming", "Prof. Johnson", 60);
        Teaching teaching3 = new Teaching("TEACH003", "Physics",
                "Classical mechanics", "Dr. Brown", 50);
        Teaching teaching4 = new Teaching("TEACH004", "Chemistry",
                "Organic chemistry basics", "Prof. Davis", 45);
        Teaching teaching5 = new Teaching("TEACH005", "Biology",
                "Cell biology and genetics", "Dr. Wilson", 55);
        teachings.put(teaching1.getId(), teaching1);
        teachings.put(teaching2.getId(), teaching2);
        teachings.put(teaching3.getId(), teaching3);
        teachings.put(teaching4.getId(), teaching4);
        teachings.put(teaching5.getId(), teaching5);
        // Assign some initial teachings
        address1.addTeaching(teaching1);
        address1.addTeaching(teaching2);
        address2.addTeaching(teaching3);
        address3.addTeaching(teaching4);
        address3.addTeaching(teaching5);
    }
    /**
     * Get all addresses in the system.
     * 
     * @return List of all addresses
     */
    public List<Address> getAllAddresses() {
        return new ArrayList<>(addresses.values());
    }
    /**
     * Get all teachings in the system.
     * 
     * @return List of all teachings
     */
    public List<Teaching> getAllTeachings() {
        return new ArrayList<>(teachings.values());
    }
    /**
     * Get teachings for a specific address.
     * 
     * @param addressId The ID of the address
     * @return List of teachings for the address, or empty list if address not found
     */
    public List<Teaching> getTeachingsForAddress(String addressId) {
        Address address = addresses.get(addressId);
        if (address != null) {
            return address.getTeachings(); // Already returns a copy
        }
        return new ArrayList<>();
    }
    /**
     * Get address by ID.
     * 
     * @param addressId The address ID
     * @return Address object, or null if not found
     */
    public Address getAddressById(String addressId) {
        return addresses.get(addressId);
    }
    /**
     * Assign teachings to an address (simulates database update).
     * 
     * @param addressId   The address ID
     * @param teachingIds List of teaching IDs to assign
     * @return true if operation was successful
     */
    public boolean assignTeachings(String addressId, List<String> teachingIds) {
        Address address = addresses.get(addressId);
        if (address == null) {
            return false;
        }
        boolean success = true;
        for (String teachingId : teachingIds) {
            Teaching teaching = teachings.get(teachingId);
            if (teaching != null && !address.getTeachings().contains(teaching)) {
                address.addTeaching(teaching);
                // Simulate database update
                dbSimulator.logOperation("ASSIGN", addressId, teachingId);
            }
        }
        return success;
    }
    /**
     * Remove teachings from an address (simulates database update).
     * 
     * @param addressId   The address ID
     * @param teachingIds List of teaching IDs to remove
     * @return true if operation was successful
     */
    public boolean removeTeachings(String addressId, List<String> teachingIds) {
        Address address = addresses.get(addressId);
        if (address == null) {
            return false;
        }
        boolean success = true;
        for (String teachingId : teachingIds) {
            Teaching teaching = teachings.get(teachingId);
            if (teaching != null && address.getTeachings().contains(teaching)) {
                address.removeTeaching(teaching);
                // Simulate database update
                dbSimulator.logOperation("REMOVE", addressId, teachingId);
            }
        }
        return success;
    }
    /**
     * Get teachings not currently assigned to an address.
     * 
     * @param addressId The address ID
     * @return List of available teachings
     */
    public List<Teaching> getAvailableTeachings(String addressId) {
        Address address = addresses.get(addressId);
        if (address == null) {
            return new ArrayList<>(teachings.values());
        }
        List<Teaching> available = new ArrayList<>();
        for (Teaching teaching : teachings.values()) {
            if (!address.getTeachings().contains(teaching)) {
                available.add(teaching);
            }
        }
        return available;
    }
}