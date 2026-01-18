package com.example.adapters;

import com.example.domain.Tourist;
import com.example.domain.TouristRepository;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Implementation of TouristRepository using a DataSource.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private DataSource dataSource;
    // In-memory simulation for simplicity
    private List<Tourist> touristStore;

    public TouristRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.touristStore = new ArrayList<>();
        // Pre-populate with sample data for demonstration
        touristStore.add(new Tourist("1", "John Doe", "john@example.com", "1234567890"));
        touristStore.add(new Tourist("2", "Jane Smith", "jane@example.com", "0987654321"));
    }

    @Override
    public Tourist findById(String id) {
        // Simulate database query via DataSource
        System.out.println("TouristRepositoryImpl: query by id " + id);
        dataSource.connect(); // Establish connection
        for (Tourist t : touristStore) {
            if (t.getId().equals(id)) {
                dataSource.disconnect();
                return t;
            }
        }
        dataSource.disconnect();
        return null;
    }

    @Override
    public Tourist update(Tourist tourist) {
        // Simulate update operation
        System.out.println("TouristRepositoryImpl: update record");
        dataSource.connect();
        for (int i = 0; i < touristStore.size(); i++) {
            if (touristStore.get(i).getId().equals(tourist.getId())) {
                touristStore.set(i, tourist);
                dataSource.disconnect();

                // Simulate sending to ETO