package com.example.smos.infrastructure;

import com.example.smos.domain.AcademicYear;
import com.example.smos.exception.SMOSConnectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation of AcademicYearRepository for archived academic years.
 * This implementation uses in-memory mock data and can simulate connection failures.
 */
public class ArchiveAcademicYearRepository implements AcademicYearRepository {

    private boolean simulateConnectionFailure = false;

    // Mock data for academic years
    private final List<AcademicYear> academicYears = Arrays.asList(
            new AcademicYear("AY001", "2021-2022"),
            new AcademicYear("AY002", "2022-2023"),
            new AcademicYear("AY003", "2023-2024")
    );

    /**
     * Sets a flag to simulate a connection failure during data retrieval.
     * @param simulateConnectionFailure True to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    @Override
    public List<AcademicYear> findAll() {
        System.out.println("[ArchiveAcademicYearRepository] Attempting to find all academic years...");
        if (simulateConnectionFailure) {
            System.err.println("[ArchiveAcademicYearRepository] Simulating SMOS connection failure for findAll().");
            throw new SMOSConnectionException("Connection to SMOS server interrupted for academic years.");
        }
        // Simulate a delay for repository call
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ArrayList<>(academicYears); // Return a copy to prevent external modification
    }
}