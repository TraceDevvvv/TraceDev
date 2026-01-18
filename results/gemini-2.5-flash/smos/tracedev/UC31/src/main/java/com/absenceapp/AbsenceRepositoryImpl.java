package com.absenceapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IAbsenceRepository.
 * Simulates a database interaction and uses an ISMOServerClient for external synchronization.
 */
public class AbsenceRepositoryImpl implements IAbsenceRepository {
    private Object databaseConnection; // Simulated database connection
    private ISMOServerClient smoServerClient; // Dependency for SMO server communication
    private Map<String, Absence> inMemoryStore = new ConcurrentHashMap<>(); // In-memory store for simulation

    /**
     * Constructor for AbsenceRepositoryImpl.
     *
     * @param smoServerClient The client for SMO Server integration.
     */
    public AbsenceRepositoryImpl(ISMOServerClient smoServerClient) {
        this.smoServerClient = smoServerClient;
        this.databaseConnection = new Object(); // Simulate connection
        // Populate with some initial data
        Absence a1 = new Absence("abs1", "s101", new Date(), AbsenceType.ABSENT, "Fever", AbsenceStatus.PENDING);
        Absence a2 = new Absence("abs2", "s102", new Date(), AbsenceType.TARDY, "Traffic", AbsenceStatus.APPROVED);
        Absence a3 = new Absence("abs3", "s101", new Date(System.currentTimeMillis() + 86400000), AbsenceType.EXCUSED, "Family trip", AbsenceStatus.PENDING);
        inMemoryStore.put(a1.getId(), a1);
        inMemoryStore.put(a2.getId(), a2);
        inMemoryStore.put(a3.getId(), a3);
    }

    /**
     * Finds an absence record by its ID.
     *
     * @param id The ID of the absence.
     * @return An Optional containing the Absence if found, empty otherwise.
     */
    @Override
    public Optional<Absence> findById(String id) {
        System.out.println("Repo: Finding absence by ID: " + id);
        // Simulate database lookup delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Optional.ofNullable(inMemoryStore.get(id));
    }

    /**
     * Finds all absence records for a specific date.
     *
     * @param date The date to search for.
     * @return A list of Absence records.
     */
    @Override
    public List<Absence> findByDate(Date date) {
        System.out.println("Repo: Finding absences by date: " + date);
        // Simulate database lookup delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Simple date comparison - assumes dates are normalized (e.g., no time component)
        // For real app, use Calendar/LocalDate for proper date comparison
        return inMemoryStore.values().stream()
                .filter(a -> a.getDate().toString().equals(date.toString())) // Basic date comparison for simulation
                .collect(Collectors.toList());
    }

    /**
     * Saves an absence record. If the absence ID is null, it's considered a new record.
     * If the ID exists, it updates the existing record.
     *
     * @param absence The absence object to save.
     * @return The saved Absence object (with generated ID if new).
     * @throws PersistenceException if there's a problem saving the data.
     */
    @Override
    public Absence save(Absence absence) throws PersistenceException {
        System.out.println("Repo: Saving absence: " + absence.getId() + " - " + absence.getStudentId());
        try {
            // Simulate database interaction (e.g., insert or update)
            Thread.sleep(70);

            // If it's a new absence (no ID yet), assign one.
            if (absence.getId() == null || absence.getId().isEmpty()) {
                absence.setId(java.util.UUID.randomUUID().toString());
            }

            inMemoryStore.put(absence.getId(), absence); // Save/Update in memory

            // REQ-005: Send modified data to SMO Server
            smoServerClient.sendModifiedData(absence); // This can throw ConnectionException

            return absence;
        } catch (ConnectionException e) {
            // REQ-002: Wrap ConnectionException in PersistenceException
            System.err.println("Repo: Connection error during save: " + e.getMessage());
            throw new PersistenceException("Failed to save absence due to SMO Server connection error for absence ID: " + absence.getId(), e);
        } catch (Exception e) {
            // Catch other potential persistence errors
            System.err.println("Repo: Generic persistence error during save: " + e.getMessage());
            throw new PersistenceException("Failed to save absence for ID: " + absence.getId(), e);
        }
    }

    /**
     * Deletes an absence record by its ID.
     *
     * @param id The ID of the absence to delete.
     * @throws PersistenceException if there's a problem deleting the data.
     */
    @Override
    public void delete(String id) throws PersistenceException {
        System.out.println("Repo: Deleting absence by ID: " + id);
        try {
            // Simulate database interaction (e.g., delete)
            Thread.sleep(60);

            if (inMemoryStore.remove(id) == null) {
                // If the item wasn't found, it might still be considered a successful 'delete' from the DB perspective
                // or an error if strict existence check is needed. For simplicity, we proceed.
                System.out.println("Repo: Absence with ID " + id + " not found for deletion.");
            }

            // REQ-005: Send modified data (deletion) to SMO Server
            // Assuming sendModifiedData can handle just an ID for deletion notification
            smoServerClient.sendModifiedData(id); // This can throw ConnectionException

        } catch (ConnectionException e) {
            // REQ-002: Wrap ConnectionException in PersistenceException
            System.err.println("Repo: Connection error during delete: " + e.getMessage());
            throw new PersistenceException("Failed to delete absence due to SMO Server connection error for absence ID: " + id, e);
        } catch (Exception e) {
            // Catch other potential persistence errors
            System.err.println("Repo: Generic persistence error during delete: " + e.getMessage());
            throw new PersistenceException("Failed to delete absence for ID: " + id, e);
        }
    }
}