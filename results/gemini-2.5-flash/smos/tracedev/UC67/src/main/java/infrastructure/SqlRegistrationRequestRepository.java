package infrastructure;

import domain.IRegistrationRequestRepository;
import domain.RegistrationRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Infrastructure Layer Implementation: Concrete implementation of IRegistrationRequestRepository.
 * Simulates interaction with a SQL database (in this case, an in-memory map).
 * Corrected UML construct error and recommendation 1.
 */
public class SqlRegistrationRequestRepository implements IRegistrationRequestRepository {

    // Simulating a database table with an in-memory HashMap
    private final Map<String, RegistrationRequest> storage = new HashMap<>();

    /**
     * Saves a RegistrationRequest object.
     * In a real application, this would interact with a database via JDBC, JPA, etc.
     * For this simulation, it stores the request in a HashMap.
     *
     * @param request The RegistrationRequest to be saved.
     */
    @Override
    public void save(RegistrationRequest request) {
        System.out.println("SqlRegistrationRequestRepository: Saving request for username: " + request.getUsername());
        // Simulate database insertion
        storage.put(request.getUsername(), request);
        System.out.println("RegistrationDB: Inserted request for " + request.getUsername());
        System.out.println("SqlRegistrationRequestRepository: Request saved.");
    }

    /**
     * Finds a RegistrationRequest by its username.
     * In a real application, this would query a database.
     * For this simulation, it retrieves the request from the HashMap.
     *
     * @param username The username to search for.
     * @return The found RegistrationRequest, or null if not found.
     */
    @Override
    public RegistrationRequest findByUsername(String username) {
        System.out.println("SqlRegistrationRequestRepository: Finding request by username: " + username);
        // Simulate database query
        RegistrationRequest foundRequest = storage.get(username);
        if (foundRequest != null) {
            System.out.println("SqlRegistrationRequestRepository: Found request for " + username);
        } else {
            System.out.println("SqlRegistrationRequestRepository: No request found for " + username);
        }
        return foundRequest;
    }
}