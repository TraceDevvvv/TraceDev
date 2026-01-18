package com.example.service;

import com.example.model.Tourist;
import com.example.repository.TouristRepository;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Service layer for Tourist operations.
 */
public class TouristService {
    private TouristRepository touristRepository;

    public TouristService(TouristRepository repository) {
        this.touristRepository = repository;
    }

    public List<Tourist> findAllTourists() {
        return touristRepository.findAll();
    }

    public List<Tourist> findTouristsByCriteria(String criteria) {
        return touristRepository.findByCriteria(criteria);
    }

    public Tourist findTouristById(int touristId) {
        return touristRepository.findById(touristId).orElse(null);
    }

    /**
     * Uploads tourist data to external server (ETOUR).
     * Simulates the upload as per sequence diagram.
     * @param touristId the tourist id
     * @return true if upload successful, false otherwise
     */
    public boolean uploadTouristData(int touristId) {
        // In a real scenario, we would prepare the data from the tourist entity.
        // For simulation, we create a dummy map.
        Map<String, Object> data = new HashMap<>();
        data.put("touristId", touristId);
        data.put("action", "upload_account_data");
        // Call repository method that interacts with external server.
        return touristRepository.updateTouristData(touristId, data);
    }
}