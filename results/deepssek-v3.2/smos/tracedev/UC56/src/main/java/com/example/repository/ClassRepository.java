package com.example.repository;

import com.example.model.Class;
import com.example.external.SMOSClient;
import java.util.List;
import java.util.ArrayList;

/**
 * Repository for Class data access.
 * Abstracts the external SMOS client.
 */
public class ClassRepository {
    private SMOSClient smosClient;

    public ClassRepository(SMOSClient smosClient) {
        this.smosClient = smosClient;
    }

    /**
     * Finds classes for a professor and academic year.
     * @param professorId the professor's ID
     * @param academicYearId the academic year ID
     * @return list of Class; empty list if connection fails.
     */
    public List<Class> findClassesByProfessorAndYear(String professorId, String academicYearId) {
        try {
            return smosClient.fetchClasses(professorId, academicYearId);
        } catch (RuntimeException e) {
            // Log error and return empty list per sequence diagram (connection error)
            System.err.println("Error fetching classes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Method corresponding to sequence diagram message "fetch class data for year".
     * Delegates to findClassesByProfessorAndYear.
     */
    public List<Class> fetchClassDataForYear(String professorId, String academicYearId) {
        return findClassesByProfessorAndYear(professorId, academicYearId);
    }
}