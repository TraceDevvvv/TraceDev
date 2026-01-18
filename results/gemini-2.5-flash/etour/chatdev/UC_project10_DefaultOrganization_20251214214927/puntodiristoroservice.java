'''
DOCSTRING:
This service class simulates the data access layer for PuntoDiRistoro objects.
In a real-world application, this would interact with a database,
a remote ETOUR server, or another backend system.
It provides methods to retrieve a list of all points of rest and
to fetch details for a specific point by its ID.
It also simulates potential connection errors.
'''
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class PuntoDiRistoroService {
    // Simulates an in-memory database of points of rest
    private List<PuntoDiRistoro> puntiDiRistoroDB;
    // A flag to simulate server connection interruption
    private boolean simulateConnectionError = false;
    public PuntoDiRistoroService() {
        // Initialize with dummy data
        puntiDiRistoroDB = new ArrayList<>(Arrays.asList(
            new PuntoDiRistoro("PR001", "Ristorante Bella Italia", "Via Roma 10, Milano",
                               "Traditional Italian cuisine in a cozy atmosphere with a wide selection of wines. We offer both indoor and outdoor seating.", "Restaurant", 50,
                               "021234567", "www.bellaitalia.it"),
            new PuntoDiRistoro("PR002", "Caffè Centrale", "Piazza Duomo 5, Firenze",
                               "Historic cafe offering pastries, coffee, and light meals. A perfect spot for a quick break while enjoying the city view.", "Cafe", 30,
                               "055987654", "www.caffecentrale.it"),
            new PuntoDiRistoro("PR003", "Trattoria del Sole", "Lungarno Pacinotti 2, Pisa",
                               "Authentic Tuscan dishes with a view of the river. Enjoy our seasonal menu featuring fresh, locally sourced ingredients. Reservations recommended.", "Restaurant", 70,
                               "050112233", "www.trattoriadelsole.it"),
            new PuntoDiRistoro("PR004", "Gelateria Artigianale", "Corso Umberto I 15, Napoli",
                               "Handmade gelato with fresh, local ingredients. Over 30 unique flavors, including vegan and sugar-free options. Open late during summer.", "Gelateria", 20,
                               "081445566", "www.gelateriaartigianale.it")
        ));
    }
    /**
     * Retrieves all available PuntoDiRistoro objects.
     * Simulates potential connection interruption.
     * @return A list of PuntoDiRistoro objects.
     * @throws ServerConnectionException if a simulated connection error occurs.
     */
    public List<PuntoDiRistoro> getAllPuntiDiRistoro() throws ServerConnectionException {
        if (simulateConnectionError) {
            throw new ServerConnectionException("Interruzione della connessione al server ETOUR durante il recupero dell'elenco.");
        }
        return new ArrayList<>(puntiDiRistoroDB); // Return a copy to prevent external modification
    }
    /**
     * Retrieves the details of a specific PuntoDiRistoro by its ID.
     * Simulates potential connection interruption.
     * @param id The unique identifier of the PuntoDiRistoro.
     * @return An Optional containing the PuntoDiRistoro if found, or empty if not.
     * @throws ServerConnectionException if a simulated connection error occurs.
     */
    public Optional<PuntoDiRistoro> getPuntoDiRistoroById(String id) throws ServerConnectionException {
        if (simulateConnectionError) {
            throw new ServerConnectionException("Interruzione della connessione al server ETOUR durante il recupero dei dettagli.");
        }
        return puntiDiRistoroDB.stream()
                               .filter(p -> p.getId().equals(id))
                               .findFirst();
    }
    /**
     * Sets the flag to simulate a server connection error.
     * @param simulateConnectionError True to simulate an error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }
    /**
     * Custom exception for simulating server connection issues,
     * as described in the quality requirements ("Interruption of the connection to the server ETOUR").
     */
    public static class ServerConnectionException extends Exception {
        public ServerConnectionException(String message) {
            super(message);
        }
    }
}