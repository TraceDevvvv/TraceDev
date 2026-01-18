package com.example.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Concrete mock implementation of IDisciplinaryNoteRepository.
 * Simulates data storage and retrieval for DisciplinaryNote entities.
 * Can simulate SMOSConnectionException to demonstrate error handling (ExC2).
 */
public class DisciplinaryNoteRepository implements IDisciplinaryNoteRepository {

    // In-memory store for mock data
    private final Map<String, DisciplinaryNote> noteStore = new ConcurrentHashMap<>();
    private static final double EXCEPTION_PROBABILITY = 0.1; // 10% chance to throw an exception

    /**
     * Initializes the repository with some sample data.
     */
    public DisciplinaryNoteRepository() {
        // Sample DisciplinaryNotes for C101 (Mathematics I)
        noteStore.put("N001", new DisciplinaryNote("N001", "S1001", "C101", new Date(System.currentTimeMillis() - 86400000 * 10), "Disruptive behavior during lecture."));
        noteStore.put("N002", new DisciplinaryNote("N002", "S1002", "C101", new Date(System.currentTimeMillis() - 86400000 * 7), "Using phone during exam."));

        // Sample DisciplinaryNotes for C102 (Physics Fundamentals)
        noteStore.put("N003", new DisciplinaryNote("N003", "S1003", "C102", new Date(System.currentTimeMillis() - 86400000 * 4), "Late submission of major project."));
    }

    /**
     * Simulates a potential connection error to the SMOS server.
     * Added to satisfy requirement ExC2.
     * @throws SMOSConnectionException if a random check fails.
     */
    private void simulateConnectionError() throws SMOSConnectionException {
        if (Math.random() < EXCEPTION_PROBABILITY) {
            throw new SMOSConnectionException("Simulated SMOS server connection interrupted for DisciplinaryNoteRepository.");
        }
    }

    @Override
    public DisciplinaryNote findById(String id) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DisciplinaryNoteRepository] findById(" + id + ")");
        return noteStore.get(id);
    }

    @Override
    public List<DisciplinaryNote> findAll() throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DisciplinaryNoteRepository] findAll()");
        return new ArrayList<>(noteStore.values());
    }

    @Override
    public DisciplinaryNote save(DisciplinaryNote entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DisciplinaryNoteRepository] save(" + entity.getNoteId() + ")");
        noteStore.put(entity.getNoteId(), entity);
        return entity;
    }

    @Override
    public void delete(DisciplinaryNote entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DisciplinaryNoteRepository] delete(" + entity.getNoteId() + ")");
        noteStore.remove(entity.getNoteId());
    }

    @Override
    public List<DisciplinaryNote> findByClassId(String classId) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DisciplinaryNoteRepository] findByClassId(" + classId + ")");
        // QR1: This method always retrieves the most current data.
        return noteStore.values().stream()
                .filter(n -> n.getClassId().equals(classId))
                .collect(Collectors.toList());
    }
}