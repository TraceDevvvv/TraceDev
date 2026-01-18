package com.example.justification.infrastructure.repository;

import com.example.justification.application.port.out.IJustificationRepository;
import com.example.justification.domain.model.Justification;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Concrete implementation of {@link IJustificationRepository}.
 * This class provides efficient data retrieval and persistence (R10)
 * by simulating a database interaction using an in-memory map.
 */
public class JustificationRepositoryImpl implements IJustificationRepository {

    // Simulates a database table for Justifications
    private final Map<String, Justification> database = new HashMap<>();

    /**
     * Constructs a new JustificationRepositoryImpl.
     * Initializes with some dummy data for demonstration purposes.
     */
    public JustificationRepositoryImpl() {
        // Dummy data for testing
        database.put("J123", new Justification("J123", "Medical appointment", "Pending", "A001"));
        database.put("J124", new Justification("J124", "Family emergency", "Approved", "A002"));
    }

    /**
     * Finds a justification by its unique identifier.
     *
     * @param id The unique identifier of the justification.
     * @return The {@link Justification} entity if found, otherwise null.
     */
    @Override
    public Justification findById(String id) {
        System.out.println("[RepoImpl] -> DB: SELECT * FROM Justifications WHERE id = " + id); // Simulate DB interaction (m7)
        Justification justification = database.get(id);
        if (justification != null) {
            System.out.println("[DB] --> [RepoImpl]: justificationData (row data for " + id + ")");
            // Simulate the return of a new Justification object (m9)
            System.out.println("[RepoImpl] --> [RepoInterface]: new Justification(justificationData)");
        } else {
            System.out.println("[DB] --> [RepoImpl]: No data found for " + id);
        }
        // Returns a new instance to simulate a fresh fetch from DB
        return Optional.ofNullable(justification)
                .map(j -> new Justification(j.getId(), j.getDetails(), j.getStatus(), j.getAbsenceId()))
                .orElse(null);
    }

    /**
     * Saves or updates a justification in the repository.
     *
     * @param justification The {@link Justification} entity to save.
     */
    @Override
    public void save(Justification justification) {
        System.out.println("[RepoImpl] -> DB: INSERT/UPDATE Justification id=" + justification.getId());
        database.put(justification.getId(), justification); // Simulate saving to DB
        System.out.println("[DB] --> [RepoImpl]: Save successful for " + justification.getId());
    }

    /**
     * Deletes a justification by its unique identifier.
     *
     * @param id The unique identifier of the justification to delete.
     */
    @Override
    public void delete(String id) {
        System.out.println("[RepoImpl] -> DB: DELETE FROM Justifications WHERE id = " + id);
        Justification removed = database.remove(id); // Simulate deleting from DB
        if (removed != null) {
            System.out.println("[DB] --> [RepoImpl]: Delete successful for " + id);
        } else {
            System.out.println("[DB] --> [RepoImpl]: No justification found with id " + id + " to delete.");
        }
    }
}