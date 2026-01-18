/*
RegistrationService.java - Simulates a service that interacts with a backend "SMOS server"
to fetch registration requests. It includes logic to simulate network delays and connection interruptions.
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
public class RegistrationService {
    // Random number generator for simulating delays and errors
    private Random random = new Random();
    /**
     * Simulates fetching a list of pending registration requests from a server.
     * This method includes artificial delays and a chance to throw a
     * ConnectionInterruptedException to mimic real-world network issues.
     *
     * @return A list of {@link RegistrationRequest} objects that are pending activation.
     * @throws ConnectionInterruptedException If a simulated connection failure occurs.
     */
    public List<RegistrationRequest> fetchPendingRequests() throws ConnectionInterruptedException {
        // Simulate network delay to mimic real-world request latency
        try {
            // Random delay between 500ms and 1500ms
            Thread.sleep(random.nextInt(1001) + 500);
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            // Propagate as a connection interruption, associating the original interrupted exception as cause
            throw new ConnectionInterruptedException("Service operation interrupted.", e);
        }
        // Simulate a chance of connection interruption (e.g., 30% chance)
        if (random.nextInt(100) < 30) {
            throw new ConnectionInterruptedException("Connection to SMOS server interrupted.");
        }
        // Create a list of simulated pending registration requests
        List<RegistrationRequest> requests = new ArrayList<>();
        requests.add(new RegistrationRequest("S001", "Alice Smith", "2023-10-20"));
        requests.add(new RegistrationRequest("S002", "Bob Johnson", "2023-10-21"));
        requests.add(new RegistrationRequest("S003", "Charlie Brown", "2023-10-22"));
        requests.add(new RegistrationRequest("S004", "Diana Prince", "2023-10-22"));
        requests.add(new RegistrationRequest("S005", "Eve Adams", "2023-10-23"));
        // Simulate the case where there might be no requests available some times.
        // For example, if random.nextBoolean() is true, clear the list,
        // to show the "no requests found" scenario occasionally.
        if (random.nextBoolean()) {
             // For illustration, sometimes return an empty list
             requests.clear();
        }
        // Filter to ensure only unactivated requests are returned
        // (Though in this simulation, all hardcoded requests are pending initially)
        return requests.stream()
                .filter(req -> !req.isActivated()) // Assuming 'activated' property is default false
                .collect(Collectors.toList());
    }
}