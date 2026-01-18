'''
Manages the data for PuntoDiRistoro objects, simulating a backend service or database.
Handles loading, validation, and saving of point of rest data.
'''
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
public class PuntoDiRistoroService {
    // Using CopyOnWriteArrayList for thread-safe iteration if needed,
    // though for simple in-memory list and single-thread GUI, ArrayList is fine.
    // It is chosen here for potential future extensibility in a multi-threaded environment.
    private final List<PuntoDiRistoro> puntiDiiRistoro;
    /**
     * Constructs a new PuntoDiRistoroService and initializes it with dummy data.
     */
    public PuntoDiRistoroService() {
        this.puntiDiiRistoro = new CopyOnWriteArrayList<>();
        // Initialize with some dummy data for demonstration purposes
        puntiDiiRistoro.add(new PuntoDiRistoro("PDR001", "Ristorante Sole", "Via Roma 1, Milano", "Restaurant", true, true));
        puntiDiiRistoro.add(new PuntoDiRistoro("PDR002", "Caffè Centrale", "Piazza Duomo 5, Firenze", "Cafe", true, false)); // Not functional
        puntiDiiRistoro.add(new PuntoDiRistoro("PDR003", "Hotel Bellevue Bar", "Lungomare 10, Napoli", "Bar", false, true)); // Not active
        puntiDiiRistoro.add(new PuntoDiRistoro("PDR004", "Trattoria da Luigi", "Corso Italia 20, Roma", "Restaurant", true, true));
        puntiDiiRistoro.add(new PuntoDiRistoro("PDR005", "Gelateria Arcobaleno", "Via Garibaldi 3, Torino", "Gelateria", true, true));
    }
    /**
     * Retrieves all registered points of rest.
     * Simulates the 'RicercaPuntoDiRistoro' use case result by providing a full list.
     *
     * @return An unmodifiable list of all points of rest.
     * @throws ServiceException if data fetching fails due to a simulated connection interruption.
     */
    public List<PuntoDiRistoro> getAllPuntiDiRistoro() throws ServiceException {
        // Simulate potential connection interruption during data retrieval as per use case "ETOUR server".
        // For demonstration purposes, there's a 10% chance of this operation failing.
        if (Math.random() < 0.1) {
            throw new ServiceException("Failed to retrieve points of rest: Interrupted connection to ETOUR server.");
        }
        // Return a new ArrayList wrapped in unmodifiableList to ensure external modifications
        // do not affect the internal state and to prevent changes from outside this service.
        return Collections.unmodifiableList(new ArrayList<>(puntiDiiRistoro));
    }
    /**
     * Retrieves a specific point of rest by its ID.
     *
     * @param id The ID of the point of rest to retrieve.
     * @return An Optional containing the PuntoDiRistoro if found, or an empty Optional if not.
     */
    public Optional<PuntoDiRistoro> getPuntoDiRistoroById(String id) {
        return puntiDiiRistoro.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
    /**
     * Validates the data of a PuntoDiRistoro object.
     * This method implements basic validation rules for the point of rest's properties.
     *
     * @param punto The PuntoDiRistoro object to validate.
     * @return An Optional containing an error message string if validation fails, or an empty Optional if valid.
     */
    public Optional<String> validatePuntoDiRistoro(PuntoDiRistoro punto) {
        if (punto == null) {
            return Optional.of("Punto di ristoro cannot be null.");
        }
        if (punto.getName() == null || punto.getName().trim().isEmpty()) {
            return Optional.of("Name cannot be empty.");
        }
        if (punto.getName().trim().length() < 3) {
            return Optional.of("Name must be at least 3 characters long.");
        }
        if (punto.getAddress() == null || punto.getAddress().trim().isEmpty()) {
            return Optional.of("Address cannot be empty.");
        }
        if (punto.getAddress().trim().length() < 5) {
            return Optional.of("Address must be at least 5 characters long.");
        }
        // "Select Type" is the default placeholder in the UI; consider it an invalid selection.
        if (punto.getType() == null || punto.getType().trim().isEmpty() || "Select Type".equals(punto.getType())) {
            return Optional.of("Type must be selected.");
        }
        // Additional business rules could be added here, e.g., unique name check, valid type values, etc.
        return Optional.empty(); // If all checks pass, the punto is considered valid.
    }
    /**
     * Stores the modified data of a point of rest.
     * This method simulates a persistent storage operation.
     *
     * @param updatedPunto The PuntoDiRistoro object with the updated data.
     * @throws IllegalArgumentException if the updatedPunto is null.
     * @throws ServiceException if the punto cannot be found (by ID) or a simulated connection error occurs during saving.
     */
    public void savePuntoDiRistoro(PuntoDiRistoro updatedPunto) throws ServiceException {
        if (updatedPunto == null) {
            throw new IllegalArgumentException("Updated PuntoDiRistoro cannot be null.");
        }
        // Simulate a real-world scenario where a connection might drop during a save operation
        // ("Interruption of the connection to the server ETOUR").
        // For demonstration, there's a 15% chance of this operation failing.
        if (Math.random() < 0.15) {
            throw new ServiceException("Failed to save data: Interruption of the connection to the server ETOUR.");
        }
        // Find the original item by ID in the list and replace it with the updated object.
        boolean foundAndUpdated = false;
        for (int i = 0; i < puntiDiiRistoro.size(); i++) {
            if (puntiDiiRistoro.get(i).getId().equals(updatedPunto.getId())) {
                puntiDiiRistoro.set(i, updatedPunto); // Replace the old object with the updated one
                foundAndUpdated = true;
                break;
            }
        }
        // If the point of rest with the given ID was not found, throw an exception.
        if (!foundAndUpdated) {
            throw new ServiceException("Punto di Ristoro with ID " + updatedPunto.getId() + " not found for update.");
        }
    }
}