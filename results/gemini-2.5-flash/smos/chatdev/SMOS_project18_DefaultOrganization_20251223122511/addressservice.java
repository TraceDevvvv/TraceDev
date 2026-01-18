/**
 * Simulates a backend service for managing addresses.
 * Handles data validation and "persistence" (in-memory list).
 * Can simulate server connection errors.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AddressService {
    // In-memory list to simulate the address archive
    private final List<Address> addressArchive;
    // Flag to simulate a server connection error
    private boolean simulateServerError = false;
    /**
     * Constructs an AddressService instance.
     * Initializes the in-memory archive.
     */
    public AddressService() {
        this.addressArchive = new ArrayList<>();
        // Add some initial addresses to simulate existing data
        addSimulatedAddresses();
    }
    /**
     * Pre-populates the archive with some sample addresses.
     */
    private void addSimulatedAddresses() {
        addressArchive.add(new Address("123 Main St, Anytown"));
        addressArchive.add(new Address("456 Oak Ave, Otherville"));
        addressArchive.add(new Address("789 Pine Ln, Somewhere"));
    }
    /**
     * Validates the given address name.
     * For this example, an address name is considered valid if it's not null,
     * not empty, and contains at least 5 characters.
     *
     * @param addressName The address name to validate.
     * @return true if the address name is valid, false otherwise.
     */
    public boolean validateAddressName(String addressName) {
        // Basic validation: must not be null or empty, and at least 5 characters long
        return addressName != null && !addressName.trim().isEmpty() && addressName.trim().length() >= 5;
    }
    /**
     * Saves a new address to the archive after validation.
     * Simulates potential server connection errors.
     *
     * @param address The Address object to save.
     * @throws AddressServiceException if validation fails or a simulated server error occurs.
     */
    public void saveAddress(Address address) throws AddressServiceException {
        // Simulate a server connection error if the flag is set
        if (simulateServerError) {
            throw new AddressServiceException("Connection data error to the SMOS server interrupted.");
        }
        // Validate the address name before saving
        if (!validateAddressName(address.getAddressName())) {
            // Activate the "Errodati" (data error) use case
            throw new AddressServiceException("Errodati: Invalid address name. It cannot be empty and must be at least 5 characters long.");
        }
        // Simulate saving the address to the archive
        addressArchive.add(address);
        System.out.println("Address '" + address.getAddressName() + "' saved successfully.");
    }
    /**
     * Retrieves all addresses from the archive.
     * @return An unmodifiable list of addresses.
     */
    public List<Address> getAllAddresses() {
        return Collections.unmodifiableList(addressArchive);
    }
    /**
     * Checks if a server error simulation is active.
     * @return true if server error is simulated, false otherwise.
     */
    public boolean isSimulateServerError() {
        return simulateServerError;
    }
    /**
     * Sets the flag to simulate a server error.
     * @param simulateServerError true to simulate an error, false otherwise.
     */
    public void setSimulateServerError(boolean simulateServerError) {
        this.simulateServerError = simulateServerError;
    }
}