package com.example.repository;

import com.example.domain.RefreshmentPoint;
import com.example.domain.RefreshmentPointType;
import com.example.domain.SearchCriteria;
import com.example.exception.DataAccessException;
import com.example.external.ETourServer;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the refreshment point repository.
 * Depends on ETourServer external system.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {
    // Assuming DataSource is injected (e.g., via Spring), but for simplicity we initialize with a mock.
    private DataSource dataSource;
    private ETourServer eTourServer;

    // Constructors
    public RefreshmentPointRepositoryImpl() {
        // For demonstration, we create a mock ETourServer
        this.eTourServer = new ETourServer();
    }

    public RefreshmentPointRepositoryImpl(DataSource dataSource, ETourServer eTourServer) {
        this.dataSource = dataSource;
        this.eTourServer = eTourServer;
    }

    @Override
    public List<RefreshmentPoint> findAll() {
        // For simplicity, return empty list or could delegate to findByCriteria with empty criteria
        return new ArrayList<>();
    }

    @Override
    public List<RefreshmentPoint> findByCriteria(SearchCriteria criteria) throws DataAccessException {
        try {
            // Call external server with a timeout constraint (max 15 sec as per REQ-PERF-001)
            // In a real implementation, we would set a timeout on the query execution.
            ResultSet resultSet = eTourServer.executeQuery(criteria);

            // Map result set to domain objects
            return mapToDomainObjects(resultSet);
        } catch (Exception e) {
            // Wrap any exception in DataAccessException as per sequence diagram alternative flow
            throw new DataAccessException("Connection to server interrupted", e);
        }
    }

    /**
     * Maps a ResultSet from the external server to a list of RefreshmentPoint domain objects.
     * This is a simplified mapping for demonstration.
     */
    private List<RefreshmentPoint> mapToDomainObjects(ResultSet resultSet) {
        List<RefreshmentPoint> points = new ArrayList<>();
        try {
            // In a real implementation, we would iterate through the ResultSet.
            // For this example, we create some mock data.
            // Simulating mapping of two records.
            points.add(new RefreshmentPoint("1", "Cafe Central", "Main Street", RefreshmentPointType.CAFE,
                    Arrays.asList("WiFi", "Coffee")));
            points.add(new RefreshmentPoint("2", "Water Fountain", "Park Avenue", RefreshmentPointType.WATER_FOUNTAIN,
                    Arrays.asList("Cold water")));
        } catch (Exception e) {
            // Log error and return empty list or partial list
            System.err.println("Error mapping result set: " + e.getMessage());
        }
        return points;
    }
}