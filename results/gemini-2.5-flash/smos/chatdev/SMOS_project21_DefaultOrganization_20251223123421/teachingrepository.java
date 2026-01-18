'''
/**
 * Acts as a simulated repository for managing Teaching and Address data.
 * In a real-world application, this class would typically interface with a database
 * or a backend service (e.g., an SMOS server). For the purpose of this example,
 * all data is stored in-memory using static maps.
 * It provides methods to retrieve and update teachings and addresses, including
 * simulated network interruption scenarios.
 */
'''
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
public class TeachingRepository {
    private static final Map<String, Teaching> allTeachings = new HashMap<>();
    private static final Map<String, Address> allAddresses = new HashMap<>();
    // Static initializer block to populate some dummy data
    static {
        // Create some sample teachings
        Teaching t1 = new Teaching("T001", "Mathematics 101");
        Teaching t2 = new Teaching("T002", "Physics Fundamentals");
        Teaching t3 = new Teaching("T003", "Chemistry Basics");
        Teaching t4 = new Teaching("T004", "Computer Science Intro");
        Teaching t5 = new Teaching("T005", "English Literature");
        Teaching t6 = new Teaching("T006", "History of Europe");
        allTeachings.put(t1.getId(), t1);
        allTeachings.put(t2.getId(), t2);
        allTeachings.put(t3.getId(), t3);
        allTeachings.put(t4.getId(), t4);
        allTeachings.put(t5.getId(), t5);
        allTeachings.put(t6.getId(), t6);
        // Create some sample addresses
        Address address1 = new Address("A001", "Main Campus Building A - Room 101");
        address1.addTeaching(t1); // Assign Math 101 to Address 1
        address1.addTeaching(t4); // Assign CS Intro to Address 1
        Address address2 = new Address("A002", "Downtown Branch Office - Conference Room");
        address2.addTeaching(t2); // Assign Physics to Address 2
        Address address3 = new Address("A003", "Online Virtual Classroom");
        allAddresses.put(address1.getId(), address1);
        allAddresses.put(address2.getId(), address2);
        allAddresses.put(address3.getId(), address3);
    }
    /**
     * Retrieves all available teachings from the repository.
     * @return A collection of all Teaching objects.
     */
    public Collection<Teaching> getAllTeachings() {
        return Collections.unmodifiableCollection(allTeachings.values());
    }
    /**
     * Retrieves all available addresses from the repository.
     * This method ensures encapsulation by providing access to address data.
     * @return A collection of all Address objects.
     */
    public Collection<Address> getAllAddresses() {
        return Collections.unmodifiableCollection(allAddresses.values());
    }
    /**
     * Retrieves an Address by its unique ID.
     * Simulates fetching data from a backend, including potential connection issues.
     * @param addressId The ID of the address to retrieve.
     * @return The Address object if found, null otherwise.
     * @throws RuntimeException if a simulated connection error occurs.
     */
    public Address getAddressById(String addressId) {
        // Simulate a connection error randomly for robustness
        if (Math.random() < 0.1) { // 10% chance of connection interruption
            throw new RuntimeException("Connection to SMOS server interrupted while fetching address: " + addressId);
        }
        return allAddresses.get(addressId);
    }
    /**
     * Updates the assigned teachings for a specific address.
     * Simulates saving data to a backend, including potential connection issues.
     * @param addressId The ID of the address to update.
     * @param newTeachings The new set of teachings to assign to the address.
     * @return true if the update was successful, false if the address was not found.
     * @throws RuntimeException if a simulated connection error occurs.
     */
    public boolean updateAddressTeachings(String addressId, Set<Teaching> newTeachings) {
        // Simulate a connection error randomly
        if (Math.random() < 0.15) { // 15% chance of connection interruption
            throw new RuntimeException("Connection to SMOS server interrupted while updating teachings for address: " + addressId);
        }
        Address address = allAddresses.get(addressId);
        if (address != null) {
            address.setAssignedTeachings(newTeachings);
            System.out.println("Teachings for address " + addressId + " updated successfully.");
            return true;
        }
        return false;
    }
}