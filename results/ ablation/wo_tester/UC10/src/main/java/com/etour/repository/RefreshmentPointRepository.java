package com.etour.repository;

import com.etour.service.RefreshmentPointService;
import com.etour.service.ETOURServerService;
import com.etour.model.RefreshmentPoint;
import com.etour.model.RefreshmentPointDetails;
import com.etour.exception.ConnectionException;
import com.etour.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles refreshment point retrieval and error conditions (Flow of Events 4)
 */
public class RefreshmentPointRepository implements RefreshmentPointService {
    private ETOURServerService etourServerService;
    private List<RefreshmentPoint> pointsCache;

    public RefreshmentPointRepository() {
        this.etourServerService = new ETOURServerService();
        initializeCache();
    }

    private void initializeCache() {
        pointsCache = new ArrayList<>();
        pointsCache.add(new RefreshmentPoint("RP001", "Rest Area North", "Highway 1 North", "Rest Area"));
        pointsCache.add(new RefreshmentPoint("RP002", "Mountain View Cafe", "Scenic Route 66", "Cafe"));
        pointsCache.add(new RefreshmentPoint("RP003", "City Center Diner", "Downtown Plaza", "Diner"));
    }

    public RefreshmentPoint getRefreshmentPointById(String pointId) {
        return pointsCache.stream()
                .filter(p -> p.getPointId().equals(pointId))
                .findFirst()
                .orElse(null);
    }

    public List<RefreshmentPoint> getAllRefreshmentPoints() {
        // Flow of Events 1: Returns list of all refreshment points for display to Agency Operator
        return new ArrayList<>(pointsCache);
    }

    public RefreshmentPointDetails fetchPointDetails(String pointId) {
        // Fetch details from ETOUR server
        try {
            RefreshmentPointDetails details = etourServerService.fetchRefreshmentPointData(pointId);
            // Populate point details (as per sequence diagram)
            return details;
        } catch (ConnectionException e) {
            handleConnectionError();
            throw e;
        }
    }

    public RefreshmentPoint createRefreshmentPoint(String pointId) {
        // Create a new refreshment point and add to cache
        RefreshmentPoint newPoint = new RefreshmentPoint(pointId, "New Point", "Unknown Address", "Unknown");
        pointsCache.add(newPoint);
        return newPoint;
    }

    public RefreshmentPoint retrieveOrInitializeRefreshmentPoint(String pointId) {
        RefreshmentPoint point = getRefreshmentPointById(pointId);
        if (point == null) {
            handleNotFoundError(pointId);
            throw new NotFoundException("Refreshment point not found", pointId);
        }
        return point;
    }

    public void handleConnectionError() {
        // Log connection error, maybe attempt reconnection
        System.err.println("Connection error handled by repository");
    }

    private void handleNotFoundError(String pointId) {
        // Log not found error
        System.err.println("Refreshment point not found: " + pointId);
    }

    // Interface method implementations
    @Override
    public RefreshmentPointDetails getPointDetails(String pointId) {
        return fetchPointDetails(pointId);
    }
}