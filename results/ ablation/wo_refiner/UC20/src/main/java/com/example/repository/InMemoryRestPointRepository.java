package com.example.repository;

import com.example.model.RestPoint;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of IRestPointRepository for demonstration.
 */
public class InMemoryRestPointRepository implements IRestPointRepository {
    private Map<String, RestPoint> restPoints = new HashMap<>();

    public InMemoryRestPointRepository() {
        // Pre-populate with a sample rest point.
        restPoints.put("RP001", new RestPoint("RP001", "Rest Point Alpha", "A nice rest point", 5));
    }

    @Override
    public RestPoint findById(String id) {
        return restPoints.get(id);
    }

    @Override
    public void save(RestPoint restPoint) {
        restPoints.put(restPoint.getId(), restPoint);
    }
}