'''
Provides business logic for managing tourist accounts,
specifically for searching tourists based on parameters.
It simulates a data source.
'''
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
class TouristService {
    // A simulated list of tourist accounts. In a real application, this would come from a database.
    private List<Tourist> allTourists;
    /**
     * Constructor for TouristService. Initializes with dummy data.
     */
    public TouristService() {
        // Initialize with some sample data as a mutable ArrayList
        // The `allTourists` list in `TouristService` is initialized using `Arrays.asList()`, which creates a fixed-size list.
        // This means operations like `add()` or `remove()` would throw an `UnsupportedOperationException` if called.
        // While the current `searchTourists` method only reads from the list, it's a good practice to use a resizable list type like `ArrayList` if the intention is to represent a dynamic collection, even if currently static.
        // This improves future flexibility and clarity of intent, preventing potential issues if the service functionality expands in the future.
        this.allTourists = new ArrayList<>(Arrays.asList(
            new Tourist("T001", "Alice", "Smith", "alice.smith@example.com", "USA"),
            new Tourist("T002", "Bob", "Johnson", "bob.j@example.com", "Canada"),
            new Tourist("T003", "Charlie", "Brown", "charlie.b@example.com", "UK"),
            new Tourist("T004", "Diana", "Miller", "diana.m@example.com", "Germany"),
            new Tourist("T005", "Eve", "Davis", "eve.d@example.com", "France"),
            new Tourist("T006", "Frank", "Wilson", "frank.w@example.com", "USA"),
            new Tourist("T007", "Grace", "Moore", "grace.m@example.com", "Australia"),
            new Tourist("T008", "Heidi", "Taylor", "heidi.t@example.com", "Germany"),
            new Tourist("T009", "Ivan", "Anderson", "ivan.a@example.com", "USA"),
            new Tourist("T010", "Judy", "Thomas", "judy.t@example.com", "Canada")
        ));
    }
    /**
     * Searches for tourist accounts based on provided criteria.
     * This method simulates a backend search operation, including potential delays
     * and connection interruptions.
     *
     * @param firstName Optional first name to search by (case-insensitive, partial match).
     * @param lastName Optional last name to search by (case-insensitive, partial match).
     * @param country Optional country to search by (case-insensitive, partial match).
     * @param email Optional email to search by (case-insensitive, partial match).
     * @return A list of Tourist objects that match the criteria.
     * @throws ConnectionInterruptionException If a simulated connection interruption occurs.
     */
    public List<Tourist> searchTourists(String firstName, String lastName, String country, String email)
            throws ConnectionInterruptionException {
        // Simulate network delay
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(200, 1000)); // 200ms to 1s delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new ConnectionInterruptionException("Search interrupted due to thread interruption.", e);
        }
        // Simulate an interruption of the connection to the server ETOUR
        // This simulates a rare but possible backend issue
        if (ThreadLocalRandom.current().nextInt(100) < 5) { // 5% chance of connection error
            throw new ConnectionInterruptionException("Interruption of the connection to the server ETOUR.");
        }
        // Filter the list based on non-empty parameters
        return allTourists.stream()
            .filter(t -> firstName == null || firstName.trim().isEmpty() ||
                         t.getFirstName().toLowerCase().contains(firstName.trim().toLowerCase()))
            .filter(t -> lastName == null || lastName.trim().isEmpty() ||
                         t.getLastName().toLowerCase().contains(lastName.trim().toLowerCase()))
            .filter(t -> country == null || country.trim().isEmpty() ||
                         t.getCountry().toLowerCase().contains(country.trim().toLowerCase()))
            .filter(t -> email == null || email.trim().isEmpty() ||
                         t.getEmail().toLowerCase().contains(email.trim().toLowerCase()))
            .collect(Collectors.toList());
    }
    /**
     * Custom exception to simulate connection issues with the backend server.
     */
    public static class ConnectionInterruptionException extends Exception {
        public ConnectionInterruptionException(String message) {
            super(message);
        }
        public ConnectionInterruptionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}