package com.example.infrastructure;

import com.example.domain.Banner;
import com.example.domain.Image;
import com.example.domain.TurningPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of ITurningPointRepository, simulating a database.
 * Part of the Infrastructure Layer.
 */
public class DbTurningPointRepository implements ITurningPointRepository {

    // In-memory "database" for demonstration purposes
    private final Map<String, TurningPoint> turningPoints = new HashMap<>();

    public DbTurningPointRepository() {
        // Populate with some mock data
        initializeMockData();
    }

    private void initializeMockData() {
        TurningPoint tp1 = new TurningPoint("TP001", "Central Plaza");
        TurningPoint tp2 = new TurningPoint("TP002", "Riverside Park");

        Image img1 = new Image("IMG001", "http://example.com/banner1.jpg", "jpeg", 1000, 200, "mock_data_img1".getBytes());
        Image img2 = new Image("IMG002", "http://example.com/banner2.png", "png", 800, 150, "mock_data_img2".getBytes());
        Image img3 = new Image("IMG003", "http://example.com/banner3.gif", "gif", 600, 100, "mock_data_img3".getBytes());

        Banner b1 = new Banner("BN001", "Summer Sale Banner", img1);
        Banner b2 = new Banner("BN002", "New Arrivals Banner", img2);
        Banner b3 = new Banner("BN003", "Event Promotion Banner", img3);
        Banner b4 = new Banner("BN004", "Empty Banner", null); // Banner with no image initially

        tp1.addBanner(b1);
        tp1.addBanner(b2);
        tp2.addBanner(b3);
        tp2.addBanner(b4);

        turningPoints.put(tp1.getId(), tp1);
        turningPoints.put(tp2.getId(), tp2);

        System.out.println("DbTurningPointRepository: Initialized with mock data.");
        System.out.println("  Turning Points: " + turningPoints.size());
        turningPoints.values().forEach(tp -> System.out.println("    " + tp.getName() + " (" + tp.getBanners().size() + " banners)"));
    }

    /**
     * Finds a TurningPoint by its unique identifier.
     * @param id The ID of the turning point.
     * @return The TurningPoint if found, null otherwise.
     */
    @Override
    public TurningPoint findById(String id) {
        System.out.println("TPR: Searching for TurningPoint with ID: " + id);
        return turningPoints.get(id);
    }

    /**
     * Retrieves all available TurningPoints.
     * @return A list of all TurningPoint objects.
     */
    @Override
    public List<TurningPoint> findAll() {
        System.out.println("TPR: Retrieving all TurningPoints.");
        return new ArrayList<>(turningPoints.values());
    }
}