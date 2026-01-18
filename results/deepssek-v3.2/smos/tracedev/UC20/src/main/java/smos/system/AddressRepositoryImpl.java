package smos.system;

import java.util.List;
import java.util.ArrayList;

/**
 * Concrete implementation of IAddressRepository.
 * Assumption: Database class is simulated for simplicity.
 */
public class AddressRepositoryImpl implements IAddressRepository {
    private Database dataSource;

    public AddressRepositoryImpl(Database dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Address findById(String id) {
        // Simulate database query
        // In a real implementation, this would execute SQL SELECT
        System.out.println("SELECT * FROM addresses WHERE id = " + id);
        // For demo, return a dummy address if id equals "found", else null
        if ("found".equals(id)) {
            return new Address(id, "Main St", "City", "12345");
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        // Simulate delete
        System.out.println("DELETE FROM addresses WHERE id = " + id);
    }

    @Override
    public List<Address> findAll() {
        // Simulate retrieving all addresses
        System.out.println("SELECT * FROM addresses");
        List<Address> list = new ArrayList<>();
        list.add(new Address("1", "Street 1", "City1", "11111"));
        list.add(new Address("2", "Street 2", "City2", "22222"));
        return list;
    }

    @Override
    public boolean hasAssociatedClasses(String addressId) {
        // Simulate checking associated classes
        System.out.println("SELECT COUNT(*) FROM classes WHERE address_id = " + addressId);
        // For demo, assume addressId "hasClasses" has associated classes
        return "hasClasses".equals(addressId);
    }
}