package com.example.repository;

import com.example.model.BeanCulturalHeritage;
import com.example.exception.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repository component for managing Cultural Heritage data in a simulated database.
 * This class uses an in-memory list to simulate database operations.
 */
public class DBCulturalHeritage {

    private List<BeanCulturalHeritage> culturalHeritageList;
    private AtomicInteger idCounter = new AtomicInteger(0); // Used to simulate auto-incrementing IDs

    public DBCulturalHeritage() {
        this.culturalHeritageList = new ArrayList<>();
        // Populate with some dummy data for demonstration
        initializeDummyData();
    }

    /**
     * Initializes the in-memory database with some dummy cultural heritage objects.
     */
    private void initializeDummyData() {
        // Note: Dates are simplified for demo. In a real app, use Calendar/LocalDate for time management.
        culturalHeritageList.add(new BeanCulturalHeritage(
                idCounter.incrementAndGet(), "Colosseum", "Rome", "+39067009009",
                "Ancient Roman amphitheatre", "Piazza del Colosseo, 1", "00184", "RM",
                new Date(120, 0, 1, 8, 30), new Date(120, 0, 1, 17, 0), "Christmas Day", "Ground Floor"
        ));
        culturalHeritageList.add(new BeanCulturalHeritage(
                idCounter.incrementAndGet(), "Uffizi Gallery", "Florence", "+39055294883",
                "Prominent Italian art museum", "Piazzale degli Uffizi, 6", "50122", "FI",
                new Date(120, 0, 1, 9, 0), new Date(120, 0, 1, 18, 45), "Monday", "East Wing"
        ));
        culturalHeritageList.add(new BeanCulturalHeritage(
                idCounter.incrementAndGet(), "Pompeii", "Naples", "+390818575111",
                "Ancient Roman city buried by Vesuvius", "Via Villa dei Misteri, 2", "80045", "NA",
                new Date(120, 0, 1, 9, 0), new Date(120, 0, 1, 17, 0), "New Year's Day", "Large Theater Area"
        ));
    }

    /**
     * Retrieves all cultural heritage objects from the "database".
     * @return A list of all BeanCulturalHeritage objects.
     * @throws SQLException if a database access error occurs.
     */
    public List<BeanCulturalHeritage> getAllCulturalHeritage() throws SQLException {
        // Simulate potential database latency or error
        // if (Math.random() < 0.1) throw new SQLException("Simulated database connection error.");
        return new ArrayList<>(culturalHeritageList); // Return a copy to prevent external modification
    }

    /**
     * Retrieves a specific cultural heritage object by its ID.
     * @param id The ID of the cultural heritage object to retrieve.
     * @return The BeanCulturalHeritage object, or null if not found.
     * @throws SQLException if a database access error occurs.
     */
    public BeanCulturalHeritage getCulturalHeritageById(int id) throws SQLException {
        // Simulate potential database latency or error
        // if (Math.random() < 0.05) throw new SQLException("Simulated database read error.");
        for (BeanCulturalHeritage bc : culturalHeritageList) {
            if (bc.getId() == id) {
                return new BeanCulturalHeritage(bc.getId(), bc.getName(), bc.getCity(), bc.getPhone(),
                        bc.getDescription(), bc.getStreet(), bc.getCap(), bc.getProvince(),
                        bc.getOpeningTime(), bc.getClosingTime(), bc.getClosingDay(), bc.getLocation());
            }
        }
        return null;
    }

    /**
     * Modifies an existing cultural heritage object in the "database".
     * @param pBC The BeanCulturalHeritage object with updated data.
     * @return true if the modification was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean modifyCulturalHeritage(BeanCulturalHeritage pBC) throws SQLException {
        if (pBC == null) {
            throw new SQLException("Cultural Heritage object cannot be null for modification.");
        }

        // Simulate potential database error for certain IDs or randomly
        // if (pBC.getId() == 999 || Math.random() < 0.05) {
        //     throw new SQLException("Simulated database write error during modification.");
        // }

        for (int i = 0; i < culturalHeritageList.size(); i++) {
            if (culturalHeritageList.get(i).getId() == pBC.getId()) {
                // Update all fields of the existing object with the new object's fields
                culturalHeritageList.set(i, pBC);
                System.out.println("DEBUG: Cultural Heritage " + pBC.getId() + " modified in DB.");
                return true;
            }
        }
        return false; // Cultural heritage with given ID not found
    }
}