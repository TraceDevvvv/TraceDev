package com.example.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Concrete mock implementation of IAbsenceRepository.
 * Simulates data storage and retrieval for Absence entities.
 * Can simulate SMOSConnectionException to demonstrate error handling (ExC2).
 */
public class AbsenceRepository implements IAbsenceRepository {

    // In-memory store for mock data
    private final Map<String, Absence> absenceStore = new ConcurrentHashMap<>();
    private static final double EXCEPTION_PROBABILITY = 0.1; // 10% chance to throw an exception

    /**
     * Initializes the repository with some sample data.
     */
    public AbsenceRepository() {
        // Sample Absences for C101 (Mathematics I)
        absenceStore.put("A001", new Absence("A001", "S1001", "C101", new Date(System.currentTimeMillis() - 86400000 * 5), "Unexcused", "Sick, no note"));
        absenceStore.put("A002", new Absence("A002", "S1002", "C101", new Date(System.currentTimeMillis() - 86400000 * 3), "Excused", "Family emergency"));
        absenceStore.put("A003", new Absence("A003", "S1001", "C101", new Date(System.currentTimeMillis() - 86400000 * 1), "Unexcused", "Slept in"));

        // Sample Absences for C102 (Physics Fundamentals)
        absenceStore.put("A004", new Absence("A004", "S1003", "C102", new Date(System.currentTimeMillis() - 86400000 * 2), "Excused", "Doctor's appointment"));
    }

    /**
     * Simulates a potential connection error to the SMOS server.
     * Added to satisfy requirement ExC2.
     * @throws SMOSConnectionException if a random check fails.
     */
    private void simulateConnectionError() throws SMOSConnectionException {
        if (Math.random() < EXCEPTION_PROBABILITY) {
            throw new SMOSConnectionException("Simulated SMOS server connection interrupted for AbsenceRepository.");
        }
    }

    @Override
    public Absence findById(String id) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[AbsenceRepository] findById(" + id + ")");
        return absenceStore.get(id);
    }

    @Override
    public List<Absence> findAll() throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[AbsenceRepository] findAll()");
        return new ArrayList<>(absenceStore.values());
    }

    @Override
    public Absence save(Absence entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[AbsenceRepository] save(" + entity.getAbsenceId() + ")");
        absenceStore.put(entity.getAbsenceId(), entity);
        return entity;
    }

    @Override
    public void delete(Absence entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[AbsenceRepository] delete(" + entity.getAbsenceId() + ")");
        absenceStore.remove(entity.getAbsenceId());
    }

    @Override
    public List<Absence> findByClassId(String classId) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[AbsenceRepository] findByClassId(" + classId + ")");
        // QR1: This method always retrieves the most current data.
        return absenceStore.values().stream()
                .filter(a -> a.getClassId().equals(classId))
                .collect(Collectors.toList());
    }
}