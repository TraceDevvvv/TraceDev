package com.example.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Concrete mock implementation of IClassRepository.
 * Simulates data storage and retrieval for Class entities.
 * Can simulate SMOSConnectionException to demonstrate error handling (ExC2).
 */
public class ClassRepository implements IClassRepository {

    // In-memory store for mock data
    private final Map<String, Class> classStore = new ConcurrentHashMap<>();
    private static final double EXCEPTION_PROBABILITY = 0.1; // 10% chance to throw an exception

    /**
     * Initializes the repository with some sample data.
     */
    public ClassRepository() {
        // Sample Classes
        classStore.put("C101", new Class("C101", "Mathematics I", "MATH101", "P001"));
        classStore.put("C102", new Class("C102", "Physics Fundamentals", "PHY101", "P001"));
        classStore.put("C103", new Class("C103", "Chemistry Basics", "CHEM101", "P002"));
        classStore.put("C104", new Class("C104", "Advanced Calculus", "MATH201", "P001"));
        classStore.put("C105", new Class("C105", "Computer Science Intro", "CS101", "P003"));
    }

    /**
     * Simulates a potential connection error to the SMOS server.
     * Added to satisfy requirement ExC2.
     * @throws SMOSConnectionException if a random check fails.
     */
    private void simulateConnectionError() throws SMOSConnectionException {
        if (Math.random() < EXCEPTION_PROBABILITY) {
            throw new SMOSConnectionException("Simulated SMOS server connection interrupted for ClassRepository.");
        }
    }

    @Override
    public Class findById(String id) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[ClassRepository] findById(" + id + ")");
        return classStore.get(id);
    }

    @Override
    public List<Class> findAll() throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[ClassRepository] findAll()");
        return new ArrayList<>(classStore.values());
    }

    @Override
    public Class save(Class entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[ClassRepository] save(" + entity.getClassId() + ")");
        classStore.put(entity.getClassId(), entity);
        return entity;
    }

    @Override
    public void delete(Class entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[ClassRepository] delete(" + entity.getClassId() + ")");
        classStore.remove(entity.getClassId());
    }

    @Override
    public List<Class> findByProfessorId(String professorId) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[ClassRepository] findByProfessorId(" + professorId + ")");
        // QR1: This method always retrieves the most current data.
        return classStore.values().stream()
                .filter(c -> c.getProfessorId().equals(professorId))
                .collect(Collectors.toList());
    }
}