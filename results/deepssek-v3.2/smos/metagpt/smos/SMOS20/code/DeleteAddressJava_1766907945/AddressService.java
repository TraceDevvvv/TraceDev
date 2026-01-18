import java.util.ArrayList;
import java.util.List;

/**
 * AddressService class containing business logic for address operations.
 * This class handles the deletion of addresses, checking for associated classes,
 * and managing the address archive.
 */
public class AddressService {
    private List<Address> addressArchive;
    private boolean smosServerConnected;
    
    /**
     * Constructor to initialize the address service.
     * Initializes an empty address archive and sets up SMOS server connection.
     */
    public AddressService() {
        this.addressArchive = new ArrayList<>();
        this.smosServerConnected = true; // Assume SMOS server is initially connected
        initializeSampleData(); // Add sample data for demonstration
    }
    
    /**
     * Initialize some sample addresses for demonstration purposes.
     */
    private void initializeSampleData() {
        // Create sample addresses
        Address address1 = new Address(1, "123 Main St", "New York", "10001", "USA");
        address1.addAssociatedClass("ClassA"); // This address has associated classes
        
        Address address2 = new Address(2, "456 Oak Ave", "Los Angeles", "90001", "USA");
        // No associated classes for this address
        
        Address address3 = new Address(3, "789 Pine Rd", "Chicago", "60601", "USA");
        address3.addAssociatedClass("ClassB");
        address3.addAssociatedClass("ClassC");
        
        Address address4 = new Address(4, "101 Elm St", "Houston", "77001", "USA");
        // No associated classes for this address
        
        // Add addresses to archive
        addressArchive.add(address1);
        addressArchive.add(address2);
        addressArchive.add(address3);
        addressArchive.add(address4);
    }
    
    /**
     * Check if the user is logged in as an administrator.
     * For demonstration purposes, this always returns true.
     * In a real system, this would check user credentials and role.
     * 
     * @return true if user is administrator, false otherwise
     */
    public boolean isUserAdministrator() {
        // In a real implementation, this would check user session/credentials
        return true; // For demonstration, assume user is administrator
    }
    
    /**
     * Check if the user has performed the "viewdettaglizzazione" use case.
     * For demonstration purposes, this always returns true.
     * 
     * @return true if the use case was performed, false otherwise
     */
    public boolean hasViewedAddressDetails() {
        // In a real implementation, this would track user actions
        return true; // For demonstration, assume user has viewed details
    }
    
    /**
     * Check if SMOS server is connected.
     * 
     * @return true if SMOS server is connected, false otherwise
     */
    public boolean isSmosServerConnected() {
        return smosServerConnected;
    }
    
    /**
     * Simulate interrupting SMOS server connection.
     * This meets the postcondition requirement.
     */
    public void interruptSmosServerConnection() {
        smosServerConnected = false;
        System.out.println("INFO: Connection to SMOS server has been interrupted.");
    }
    
    /**
     * Display detailed information of an address (simulating the "viewdettaglizzazione" use case).
     * 
     * @param addressId The ID of the address to display
     */
    public void displayAddressDetails(int addressId) {
        Address address = findAddressById(addressId);
        if (address != null) {
            System.out.println("=== Address Details ===");
            System.out.println("ID: " + address.getId());
            System.out.println("Full Address: " + address.getFullAddress());
            System.out.println("Street: " + address.getStreet());
            System.out.println("City: " + address.getCity());
            System.out.println("Zip Code: " + address.getZipCode());
            System.out.println("Country: " + address.getCountry());
            
            List<String> associatedClasses = address.getAssociatedClasses();
            if (!associatedClasses.isEmpty()) {
                System.out.println("Associated Classes: " + String.join(", ", associatedClasses));
            } else {
                System.out.println("Associated Classes: None");
            }
            System.out.println("=====================\n");
        } else {
            System.out.println("ERROR: Address with ID " + addressId + " not found.");
        }
    }
    
    /**
     * Delete an address from the archive.
     * This method implements the main use case logic:
     * 1. Checks preconditions (administrator, viewed details)
     * 2. Validates address exists
     * 3. Checks if address has associated classes
     * 4. Deletes address if no associated classes
     * 5. Displays updated list
     * 6. Interrupts SMOS server connection
     * 
     * @param addressId The ID of the address to delete
     * @return true if address was deleted successfully, false otherwise
     */
    public boolean deleteAddress(int addressId) {
        // Check preconditions
        if (!isUserAdministrator()) {
            System.out.println("ERROR: You must be logged in as an administrator to delete addresses.");
            return false;
        }
        
        if (!hasViewedAddressDetails()) {
            System.out.println("ERROR: You must view address details before deleting.");
            return false;
        }
        
        // Find the address
        Address addressToDelete = findAddressById(addressId);
        if (addressToDelete == null) {
            System.out.println("ERROR: Address with ID " + addressId + " not found in archive.");
            return false;
        }
        
        // Display current details (simulating user has viewed details)
        displayAddressDetails(addressId);
        
        // Check if address has associated classes
        if (addressToDelete.hasAssociatedClasses()) {
            System.out.println("ERROR: Unable to delete the address, delete the associated classes and try again.");
            System.out.println("Address has the following associated classes: " + 
                             String.join(", ", addressToDelete.getAssociatedClasses()));
            return false;
        }
        
        // Delete the address
        boolean removed = addressArchive.remove(addressToDelete);
        if (removed) {
            System.out.println("SUCCESS: Address with ID " + addressId + " has been deleted from archive.");
            
            // Display updated list of addresses
            System.out.println("\n=== Updated List of Addresses ===");
            displayAllAddresses();
            
            // Interrupt SMOS server connection (postcondition)
            interruptSmosServerConnection();
            
            return true;
        } else {
            System.out.println("ERROR: Failed to delete address with ID " + addressId);
            return false;
        }
    }
    
    /**
     * Find an address by its ID.
     * 
     * @param addressId The ID to search for
     * @return The Address object if found, null otherwise
     */
    private Address findAddressById(int addressId) {
        for (Address address : addressArchive) {
            if (address.getId() == addressId) {
                return address;
            }
        }
        return null;
    }
    
    /**
     * Display all addresses in the archive.
     * This simulates step 2 of the use case: "Displays the list of updated addresses".
     */
    public void displayAllAddresses() {
        if (addressArchive.isEmpty()) {
            System.out.println("No addresses in archive.");
            return;
        }
        
        for (Address address : addressArchive) {
            System.out.println(address.toString());
        }
        System.out.println(); // Add blank line for readability
    }
    
    /**
     * Get the list of all addresses in the archive.
     * 
     * @return List of all addresses
     */
    public List<Address> getAllAddresses() {
        return new ArrayList<>(addressArchive); // Return a copy to maintain encapsulation
    }
    
    /**
     * Get a specific address by ID.
     * 
     * @param addressId The ID of the address to retrieve
     * @return The Address object if found, null otherwise
     */
    public Address getAddressById(int addressId) {
        return findAddressById(addressId);
    }
    
    /**
     * Remove all associated classes from an address (for testing purposes).
     * 
     * @param addressId The ID of the address
     * @return true if all classes were removed, false otherwise
     */
    public boolean removeAllAssociatedClasses(int addressId) {
        Address address = findAddressById(addressId);
        if (address == null) {
            System.out.println("ERROR: Address with ID " + addressId + " not found.");
            return false;
        }
        
        List<String> classes = new ArrayList<>(address.getAssociatedClasses());
        boolean allRemoved = true;
        for (String className : classes) {
            if (!address.removeAssociatedClass(className)) {
                allRemoved = false;
            }
        }
        
        if (allRemoved && !classes.isEmpty()) {
            System.out.println("INFO: All associated classes removed from address ID " + addressId);
        }
        
        return allRemoved;
    }
}