package com.example.repository;

import com.example.model.AcademicYear;
import com.example.external.SMOSClient;
import java.util.List;
import java.util.ArrayList;

/**
 * Repository for AcademicYear data access.
 * Abstracts the external SMOS client.
 */
public class AcademicYearRepository {
    private SMOSClient smosClient;

    public AcademicYearRepository(SMOSClient smosClient) {
        this.smosClient = smosClient;
    }

    /**
     * Finds academic years for a given professor.
     * @param professorId the professor's ID
     * @return list of AcademicYear; empty list if connection fails.
     */
    public List<AcademicYear> findYearsByProfessorId(String professorId) {
        try {
            return smosClient.fetchAcademicYears(professorId);
        } catch (RuntimeException e) {
            // Log error and return empty list per sequence diagram (connection error)
            System.err.println("Error fetching academic years: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Method corresponding to sequence diagram message "fetch academic years data".
     * Delegates to findYearsByProfessorId.
     */
    public List<AcademicYear> fetchAcademicYearsData(String professorId) {
        return findYearsByProfessorId(professorId);
    }
}