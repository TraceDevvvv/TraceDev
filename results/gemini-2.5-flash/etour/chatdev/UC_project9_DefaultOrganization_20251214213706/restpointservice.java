'''
Service class responsible for simulating the search for points of rest.
This class abstracts the data source and business logic. It includes
simulated network delays and potential connection failures to mimic a real-world scenario.
'''
package com.chatdev.ricercapuntidiristoro.service;
import com.chatdev.ricercapuntidiristoro.ETOURConnectionException;
import com.chatdev.ricercapuntidiristoro.model.RestPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
/**
 * Service class responsible for simulating the search for points of rest.
 * This class abstracts the data source and business logic. It includes
 * simulated network delays and potential connection failures to mimic a real-world scenario.
 */
public class RestPointService {
    private final List<RestPoint> allRestPoints;
    private final Random random = new Random();
    // Adjusted MAX_SIMULATED_DELAY_MS to aim for compliance with the 15 seconds max operation time.
    private static final int MAX_SIMULATED_DELAY_MS = 14000; // Max simulated delay (e.g., 14 seconds), aiming to stay within 15s
    private static final int MIN_SIMULATED_DELAY_MS = 1000; // Minimum simulated delay
    private static final int CONNECTION_FAILURE_CHANCE = 15; // 15% chance of connection failure
    /**
     * Constructor initializes a dummy list of rest points to act as a simulated database.
     * This data will be used for filtering during search operations.
     */
    public RestPointService() {
        allRestPoints = new ArrayList<>();
        allRestPoints.add(new RestPoint("RP001", "La Trattoria del Borgo", "Via Roma 10, Milano", "Restaurant"));
        allRestPoints.add(new RestPoint("RP002", "Caffè Centrale", "Piazza Duomo 5, Milano", "Cafe"));
        allRestPoints.add(new RestPoint("RP003", "Hotel Splendid", "Corso Italia 20, Roma", "Hotel"));
        allRestPoints.add(new RestPoint("RP004", "Paninoteca Veloce", "Via Garibaldi 1, Firenze", "Fast Food"));
        allRestPoints.add(new RestPoint("RP005", "Gelateria Artigianale", "Lungarno Mediceo, Pisa", "Gelateria"));
        allRestPoints.add(new RestPoint("RP006", "Ristorante Bella Italia", "Via Veneto 11, Roma", "Restaurant"));
        allRestPoints.add(new RestPoint("RP007", "Bar Tabacchi Mario", "Viale Dante 3, Napoli", "Cafe"));
        allRestPoints.add(new RestPoint("RP008", "Parco Relax", "Via dei Giardini, Torino", "Picnic Area"));
        allRestPoints.add(new RestPoint("RP009", "Pizzeria da Gennaro", "Via Toledo 50, Napoli", "Restaurant"));
        allRestPoints.add(new RestPoint("RP010", "Stazione di Servizio AGIP", "Autostrada A1 km 150, Bologna", "Service Station"));
        allRestPoints.add(new RestPoint("RP011", "Trattoria del Mare", "Lungomare Cristoforo Colombo, Bari", "Restaurant"));
        allRestPoints.add(new RestPoint("RP012", "Caffetteria Express", "Stazione Termini, Roma", "Cafe"));
        allRestPoints.add(new RestPoint("RP013", "Ristorante Il Ponte", "Ponte Vecchio, Firenze", "Restaurant"));
        allRestPoints.add(new RestPoint("RP014", "Agriturismo Verde", "Località Campagna, Perugia", "Farm Stay"));
        allRestPoints.add(new RestPoint("RP015", "Tavola Calda Speedy", "Via del Corso 1, Roma", "Fast Food"));
        allRestPoints.add(new RestPoint("RP016", "Panetteria Del Fornaio", "Corso Buenos Aires, Milano", "Cafe"));
        allRestPoints.add(new RestPoint("RP017", "Ristorante Pizzeria", "Via San Gregorio, Napoli", "Restaurant"));
        allRestPoints.add(new RestPoint("RP018", "Area Sosta Camper", "Litoranea Jonica, Lecce", "Campsite"));
    }
    /**
     * Simulates searching for rest points based on provided criteria.
     * This method deliberately includes simulated network delay and
     * a chance of throwing a connection failure exception to mimic real network conditions.
     *
     * @param name     Partial or full name of the rest point. Can be null or empty for no filtering by name.
     * @param location Partial or full location of the rest point. Can be null or empty for no filtering by location.
     * @param type     Type of the rest point. Can be null or empty for no filtering by type.
     * @return A list of {@link RestPoint} objects that match the specified criteria.
     * @throws ETOURConnectionException if a simulated interruption of the connection to the ETOUR server occurs.
     * @throws InterruptedException if the thread performing the simulated delay is interrupted.
     */
    public List<RestPoint> searchRestPoints(String name, String location, String type)
            throws ETOURConnectionException, InterruptedException {
        // Simulate network delay to mimic real-world request latency.
        // The delay is randomized to provide varied response times, aiming to keep total operation time within the 15-second limit.
        long simulatedDelay = MIN_SIMULATED_DELAY_MS + random.nextInt(MAX_SIMULATED_DELAY_MS - MIN_SIMULATED_DELAY_MS);
        TimeUnit.MILLISECONDS.sleep(simulatedDelay);
        // Simulate a connection interruption with a certain probability.
        // This represents a server error or network outage.
        if (random.nextInt(100) < CONNECTION_FAILURE_CHANCE) {
            throw new ETOURConnectionException("Interruzione improvvisa della connessione al server ETOUR.");
        }
        // Prepare search parameters for case-insensitive matching.
        // Convert to lowercase and handle null/empty strings to simplify filtering logic.
        final String searchName = (name != null && !name.isEmpty()) ? name.toLowerCase() : null;
        final String searchLocation = (location != null && !location.isEmpty()) ? location.toLowerCase() : null;
        final String searchType = (type != null && !type.isEmpty()) ? type.toLowerCase() : null;
        // Filter the list of all rest points based on the provided criteria.
        // The filtering uses Java Streams for a concise and efficient approach.
        return allRestPoints.stream()
                .filter(rp -> searchName == null || rp.getName().toLowerCase().contains(searchName))
                .filter(rp -> searchLocation == null || rp.getLocation().toLowerCase().contains(searchLocation))
                .filter(rp -> searchType == null || rp.getType().toLowerCase().contains(searchType))
                .collect(Collectors.toList());
    }
}