package com.example.external;

import com.example.model.AcademicYear;
import com.example.model.Class;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for interacting with external SMOS server.
 * Simulates connection status and data fetching.
 */
public class SMOSClient {
    private boolean connected = true; // default to connected for simulation

    /**
     * Fetches academic years data for a given professor from SMOS.
     * @param professorId the professor's ID
     * @return list of AcademicYearData (simplified as AcademicYear entities)
     */
    public List<AcademicYear> fetchAcademicYears(String professorId) {
        // Simulate external call; if not connected, throw an exception.
        if (!isConnected()) {
            throw new RuntimeException("SMOS server connection interrupted");
        }
        // Dummy data for demonstration.
        List<AcademicYear> years = new ArrayList<>();
        years.add(new AcademicYear("year1", "2023-2024", new ArrayList<>()));
        years.add(new AcademicYear("year2", "2024-2025", new ArrayList<>()));
        return years;
    }

    /**
     * Fetches classes data for a professor and academic year from SMOS.
     * @param professorId the professor's ID
     * @param academicYearId the academic year ID
     * @return list of ClassData (simplified as Class entities)
     */
    public List<Class> fetchClasses(String professorId, String academicYearId) {
        // Simulate external call; if not connected, throw an exception.
        if (!isConnected()) {
            throw new RuntimeException("SMOS server connection interrupted");
        }
        // Dummy data for demonstration.
        List<Class> classes = new ArrayList<>();
        classes.add(new Class("class1", "Mathematics", professorId, academicYearId));
        classes.add(new Class("class2", "Physics", professorId, academicYearId));
        return classes;
    }

    /**
     * Returns the connection status to SMOS server.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Sets the connection status (for simulation).
     * @param connected the connection status
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Method representing the SMOS server participant.
     * No additional code needed; the class itself represents SMOS.
     */
}