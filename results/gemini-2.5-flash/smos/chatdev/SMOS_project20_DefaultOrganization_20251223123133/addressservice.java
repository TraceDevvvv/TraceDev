/**
 * Manages the data and business logic for addresses, simulating a backend service.
 * It provides methods to retrieve, check, and delete address data.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class AddressService {
    // A mock database to store addresses. In a real application, this would interact with a persistent store.
    private List<Address> addresses;
    /**
     * Constructs an AddressService and initializes with some sample data.
     */
    public AddressService() {
        addresses = new ArrayList<>();
        initializeAddresses();
    }
    /**
     * Populates the mock database with predefined address data.
     */
    private void initializeAddresses() {
        // Sample addresses, some with associated classes to test the deletion logic
        addresses.add(new Address("ADDR001", "123 Main St", "Anytown", false));
        addresses.add(new Address("ADDR002", "456 Oak Ave", "Othertown", true)); // Has associated classes
        addresses.add(new Address("ADDR003", "789 Pine Ln", "Somewhere", false));
        addresses.add(new Address("ADDR004", "101 Maple Rd", "Nowhere", true)); // Has associated classes
        addresses.add(new Address("ADDR005", "222 Elm Blvd", "Anytown", false));
    }
    /**
     * Retrieves a list of all addresses currently in the system.
     * @return A list of Address objects.
     */
    public List<Address> getAllAddresses() {
        return new ArrayList<>(addresses); // Return a copy to prevent external modification
    }
    /**
     * Checks if a specific address has associated classes.
     * This simulates checking dependencies before deletion.
     * @param addressId The ID of the address to check.
     * @return True if the address has associated classes, false otherwise. Returns false if address not found.
     */
    public boolean hasAssociatedClasses(String addressId) {
        for (Address address : addresses) {
            if (address.getId().equals(addressId)) {
                return address.hasAssociatedClasses();
            }
        }
        // If address not found, it implies no associated classes specific to that address.
        // Or, depending on the business logic, one might throw an AddressNotFoundException.
        return false;
    }
    /**
     * Deletes an address from the system if it does not have associated classes.
     * @param addressId The ID of the address to delete.
     * @return True if the address was successfully deleted, false if it could not be deleted
     *         (e.g., due to associated classes or not found).
     */
    public boolean deleteAddress(String addressId) {
        Iterator<Address> iterator = addresses.iterator();
        while (iterator.hasNext()) {
            Address address = iterator.next();
            if (address.getId().equals(addressId)) {
                // Pre-condition check: if it has associated classes, deletion is not allowed
                if (address.hasAssociatedClasses()) {
                    return false; // Cannot delete due to associated classes
                }
                iterator.remove(); // Remove the address if no associated classes
                return true; // Deletion successful
            }
        }
        return false; // Address not found
    }
}