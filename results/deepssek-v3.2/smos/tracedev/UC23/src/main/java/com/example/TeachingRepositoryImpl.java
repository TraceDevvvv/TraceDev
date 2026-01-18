package com.example;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Implementation of TeachingRepository.
 * Assumption: Uses a DataSource for database connectivity.
 */
public class TeachingRepositoryImpl implements TeachingRepository {
    private DataSource dataSource;
    private List<Teaching> teachings = new ArrayList<>(); // In-memory storage for simplicity

    /**
     * Constructor for TeachingRepositoryImpl.
     * @param dataSource the data source for database operations
     */
    public TeachingRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Saves a teaching entity.
     * Simulates database save operation.
     * @param teaching the teaching to save
     * @return the saved teaching
     */
    @Override
    public Teaching save(Teaching teaching) {
        // Simulate connection interruption with 10% probability
        if (Math.random() < 0.1) {
            throw new RuntimeException("Connection to SMOS server interrupted");
        }
        teachings.add(teaching);
        System.out.println("Teaching saved: " + teaching.getName());
        return teaching;
    }

    /**
     * Retrieves all teaching entities.
     * @return a list of all teachings
     */
    @Override
    public List<Teaching> findAll() {
        return new ArrayList<>(teachings);
    }
}