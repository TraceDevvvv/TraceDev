'''
A mock service class that simulates interaction with an external
ETOUR server to retrieve cultural good data. It provides methods to
fetch a list of cultural goods and details for a specific one.
Includes functionality to simulate connection errors.
'''
package com.chatdev.culturalviewer.service;
import com.chatdev.culturalviewer.model.BeneCulturale;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * '''
 * A mock service class that simulates interaction with an external
 * ETOUR server to retrieve cultural good data. It provides methods to
 * fetch a list of cultural goods and details for a specific one.
 * Includes functionality to simulate connection errors.
 * '''
 */
public class ETOURService {
    private Map<String, BeneCulturale> culturalGoodsDB;
    private boolean simulateConnectionFailure = false;
    private Random random = new Random();
    /**
     * '''
     * Constructs an ETOURService instance and initializes it with mock data.
     * '''
     */
    public ETOURService() {
        culturalGoodsDB = new HashMap<>();
        // Populate with mock data for simulation
        culturalGoodsDB.put("CG001", new BeneCulturale(
                "CG001",
                "Colosseum",
                "An oval amphitheatre in the centre of the city of Rome, Italy. " +
                        "Built of travertine limestone, tuff, and brick-faced concrete, " +
                        "it was the largest amphitheatre ever built.",
                "Rome, Italy",
                "Historic Site"));
        culturalGoodsDB.put("CG002", new BeneCulturale(
                "CG002",
                "Mona Lisa",
                "A half-length portrait painting by Italian artist Leonardo da Vinci. " +
                        "Considered an archetypal masterpiece of the Italian Renaissance, " +
                        "it has been described as 'the best known, the most visited, " +
                        "the most written about, the most sung about, the most parodied work of art in the world'.",
                "Louvre Museum, Paris, France",
                "Painting"));
        culturalGoodsDB.put("CG003", new BeneCulturale(
                "CG003",
                "Great Pyramid of Giza",
                "The oldest and largest of the three pyramids in the Giza pyramid complex, " +
                        "bordering what is now Giza in Egypt. It is the oldest of the Seven Wonders of the Ancient World, " +
                        "and the only one to remain largely intact.",
                "Giza, Egypt",
                "Ancient Monument"));
        culturalGoodsDB.put("CG004", new BeneCulturale(
                "CG004",
                "The Thinker",
                "A bronze sculpture by Auguste Rodin, usually placed on a stone pedestal. " +
                        "The work shows a nude male figure of heroic size, sitting and contemplating, " +
                        "his right elbow resting on his left knee, with one hand supporting his chin.",
                "Musée Rodin, Paris, France",
                "Sculpture"));
        culturalGoodsDB.put("CG005", new BeneCulturale(
                "CG005",
                "Taj Mahal",
                "An ivory-white marble mausoleum on the right bank of the river Yamuna " +
                        "in the Indian city of Agra. It was commissioned in 1632 by " +
                        "the Mughal emperor Shah Jahan to house the tomb of his favourite wife, Mumtaz Mahal.",
                "Agra, India",
                "Mausoleum"));
    }
    /**
     * '''
     * Retrieves a list of all cultural goods available in the system.
     * This method simulates network latency and potential connection errors.
     *
     * @return A {@link List} of {@link BeneCulturale} objects.
     * @throws ETOURConnectionException if a simulated connection failure occurs.
     * '''
     */
    public List<BeneCulturale> getAllCulturalGoods() throws ETOURConnectionException {
        simulateNetworkLatency();
        if (simulateConnectionFailure) {
            throw new ETOURConnectionException("Connection to ETOUR server interrupted during list retrieval.");
        }
        return new ArrayList<>(culturalGoodsDB.values());
    }
    /**
     * '''
     * Retrieves the detailed information for a specific cultural good by its ID.
     * This method simulates network latency and potential connection errors.
     *
     * @param culturalGoodId The unique identifier of the cultural good to retrieve.
     * @return The {@link BeneCulturale} object corresponding to the given ID.
     * @throws ETOURConnectionException if a simulated connection failure occurs.
     *                                  or if the cultural good with the given ID is not found.
     * '''
     */
    public BeneCulturale getCulturalGoodDetails(String culturalGoodId) throws ETOURConnectionException {
        simulateNetworkLatency();
        // Simulate a connection failure only if simulateConnectionFailure is true
        if (simulateConnectionFailure) {
            throw new ETOURConnectionException("Connection to ETOUR server interrupted while fetching details for " + culturalGoodId);
        }
        BeneCulturale culturalGood = culturalGoodsDB.get(culturalGoodId);
        if (culturalGood == null) {
            throw new ETOURConnectionException("Cultural good with ID '" + culturalGoodId + "' not found on ETOUR server.");
        }
        return culturalGood;
    }
    /**
     * '''
     * Toggles the simulation of connection failures. When set to true,
     * subsequent calls to data retrieval methods may throw ETOURConnectionException.
     *
     * @param fail True to simulate connection failures, false otherwise.
     * '''
     */
    public void setSimulateConnectionFailure(boolean fail) {
        this.simulateConnectionFailure = fail;
    }
    /**
     * '''
     * Checks if connection failure simulation is currently active.
     *
     * @return True if connection failures are being simulated, false otherwise.
     * '''
     */
    public boolean isSimulatingConnectionFailure() {
        return simulateConnectionFailure;
    }
    /**
     * '''
     * Simulates network latency by pausing the current thread for a short duration.
     * '''
     */
    private void simulateNetworkLatency() {
        try {
            // Simulate network delay between 500ms and 1500ms
            Thread.sleep(500 + random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("ETOURService simulation interrupted: " + e.getMessage());
        }
    }
}