package com.example.smos.infrastructure;

import com.example.smos.domain.Class;
import com.example.smos.exception.SMOSConnectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of ClassRepository for archived classes.
 * This implementation uses in-memory mock data and can simulate connection failures.
 */
public class ArchiveClassRepository implements ClassRepository {

    private boolean simulateConnectionFailure = false;

    // Mock data for classes
    private final List<Class> classes = Arrays.asList(
            new Class("C001", "Introduction to Programming", "AY001", "Fundamentals of Java"),
            new Class("C002", "Data Structures", "AY001", "Arrays, Lists, Trees"),
            new Class("C003", "Algorithms", "AY002", "Sorting and searching algorithms"),
            new Class("C004", "Database Systems", "AY002", "SQL and relational databases"),
            new Class("C005", "Web Development", "AY003", "HTML, CSS, JavaScript basics"),
            new Class("C006", "Software Engineering", "AY003", "SDLC and design patterns")
    );

    /**
     * Sets a flag to simulate a connection failure during data retrieval.
     * @param simulateConnectionFailure True to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    @Override
    public List<Class> findByAcademicYearId(String academicYearId) {
        System.out.println("[ArchiveClassRepository] Attempting to find classes for academic year: " + academicYearId);
        if (simulateConnectionFailure) {
            System.err.println("[ArchiveClassRepository] Simulating SMOS connection failure for findByAcademicYearId().");
            throw new SMOSConnectionException("Connection to SMOS server interrupted for classes.");
        }
        // Simulate a delay for repository call
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return classes.stream()
                .filter(c -> c.getAcademicYearId().equals(academicYearId))
                .collect(Collectors.toList());
    }
}