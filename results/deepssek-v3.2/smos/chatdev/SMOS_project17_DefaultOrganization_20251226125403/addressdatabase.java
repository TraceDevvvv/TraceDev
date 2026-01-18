/*
 Simulates a database/archive for storing and retrieving addresses
 In a real application, this would connect to an actual database
 */
import java.util.ArrayList;
import java.util.List;
public class AddressDatabase {
    private List<Address> addresses;
    public AddressDatabase() {
        addresses = new ArrayList<>();
        initializeSampleData();
    }
    private void initializeSampleData() {
        // Add sample addresses for demonstration
        addresses.add(new Address("John Doe", "123 Main St", "Springfield", "IL", "62704", "USA"));
        addresses.add(new Address("Jane Smith", "456 Oak Ave", "Metropolis", "NY", "10001", "USA"));
        addresses.add(new Address("Bob Johnson", "789 Pine Rd", "Gotham", "CA", "90210", "USA"));
        addresses.add(new Address("Alice Brown", "101 Maple Ln", "Star City", "TX", "73301", "USA"));
        addresses.add(new Address("Charlie Wilson", "202 Elm Blvd", "Central City", "FL", "33101", "USA"));
    }
    public List<Address> getAllAddresses() {
        return new ArrayList<>(addresses); // Return a copy to protect internal list
    }
    public int getAddressCount() {
        return addresses.size();
    }
}