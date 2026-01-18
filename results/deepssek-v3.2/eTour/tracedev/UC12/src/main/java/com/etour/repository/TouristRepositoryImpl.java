package com.etour.repository;

import com.etour.model.Tourist;
import com.etour.connection.ETOURServerConnection;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of TouristRepository that uses an ETOURServerConnection
 * to fetch data from the remote server.
 */
public class TouristRepositoryImpl implements TouristRepository {

    private ETOURServerConnection dataSource;

    /**
     * Constructor for dependency injection.
     * @param dataSource the server connection to be used.
     */
    public TouristRepositoryImpl(ETOURServerConnection dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retrieves all tourists from the server.
     * @return list of Tourist entities.
     */
    @Override
    public List<Tourist> findAll() {
        // Check connection as per sequence diagram
        if (!dataSource.isConnected()) {
            dataSource.connect();
        }
        List<Map<String, String>> rawData = dataSource.fetchAllTouristData();
        List<Tourist> tourists = new ArrayList<>();
        for (Map<String, String> data : rawData) {
            tourists.add(createTouristFromMap(data));
        }
        // Simulate uploading data to tourist account (as per requirement)
        uploadDataToTouristAccount();
        return tourists;
    }

    /**
     * Retrieves a tourist by ID from the server.
     * @param id the tourist ID.
     * @return Tourist entity or null if not found.
     */
    @Override
    public Tourist findById(String id) {
        // Check connection as per sequence diagram
        if (!dataSource.isConnected()) {
            dataSource.connect();
        }
        Map<String, String> rawData = dataSource.fetchTouristData(id);
        if (rawData != null && !rawData.isEmpty()) {
            Tourist tourist = createTouristFromMap(rawData);
            uploadDataToTouristAccount();
            return tourist;
        } else {
            return null;
        }
    }

    /**
     * Creates a Tourist entity from a map of raw data.
     * @param data map containing tourist attributes.
     * @return Tourist entity.
     */
    protected Tourist createTouristFromMap(Map<String, String> data) {
        // Assumption: the map contains keys "id", "name", "email", and possibly other details.
        Tourist tourist = new Tourist();
        tourist.setId(data.getOrDefault("id", ""));
        tourist.setName(data.getOrDefault("name", ""));
        tourist.setEmail(data.getOrDefault("email", ""));
        // All other entries become part of otherDetails
        Map<String, String> otherDetails = new HashMap<>(data);
        otherDetails.remove("id");
        otherDetails.remove("name");
        otherDetails.remove("email");
        tourist.setOtherDetails(otherDetails);
        return tourist;
    }

    /**
     * Simulates uploading data to the tourist account.
     * In a real system, this might update local storage or cache.
     */
    protected void uploadDataToTouristAccount() {
        // Placeholder: in a real implementation, this would sync data to a local database.
        System.out.println("Data uploaded to tourist account (simulated).");
    }
}