package com.example.refreshmentpoint.repository;

import com.example.refreshmentpoint.model.RefreshmentPoint;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class RefreshmentPointRepository {

    private final Map<String, RefreshmentPoint> refreshmentPoints = new ConcurrentHashMap<>();

    public RefreshmentPointRepository() {
        // Initialize with some dummy data
        refreshmentPoints.put("1", new RefreshmentPoint("1", "Cafe Central", 48.2082, 16.3738, "cafe", List.of("wifi", "restroom")));
        refreshmentPoints.put("2", new RefreshmentPoint("2", "Park Schönbrunn", 48.1846, 16.3119, "park", List.of("restroom", "parking")));
        refreshmentPoints.put("3", new RefreshmentPoint("3", "Restaurant Figlmüller", 48.2091, 16.3727, "restaurant", List.of("wifi")));
        refreshmentPoints.put("4", new RefreshmentPoint("4", "Museumsquartier Cafe", 48.2027, 16.3597, "cafe", List.of("wifi", "outdoor_seating")));
    }

    public List<RefreshmentPoint> findAll() {
        return new ArrayList<>(refreshmentPoints.values());
    }

    public List<RefreshmentPoint> findByCriteria(String type, List<String> amenities) {
        return refreshmentPoints.values().stream()
                .filter(rp -> type == null || rp.getType().equalsIgnoreCase(type))
                .filter(rp -> amenities == null || amenities.isEmpty() || rp.getAmenities().containsAll(amenities))
                .collect(Collectors.toList());
    }

    public RefreshmentPoint findById(String id) {
        return refreshmentPoints.get(id);
    }
}
