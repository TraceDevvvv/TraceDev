package com.example;

import java.util.concurrent.TimeUnit; // For simulating network delay

/**
 * Adapter class to simulate interaction with an external SMOS server.
 * This class provides mock data in JSON string format and can simulate connection errors.
 */
public class SMOSServerAdapter {

    private String connectionString;
    private boolean simulateConnectionError = false; // Flag to control error simulation

    /**
     * Constructor for SMOSServerAdapter.
     *
     * @param connectionString The connection string for the SMOS server (mocked).
     */
    public SMOSServerAdapter(String connectionString) {
        this.connectionString = connectionString;
        System.out.println("SMOSServerAdapter initialized with connection: " + connectionString);
    }

    /**
     * Sets the flag to simulate a connection error.
     *
     * @param simulateConnectionError True to simulate an error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }

    /**
     * Fetches academic years for a given professor from the simulated SMOS server.
     *
     * @param professorId The ID of the professor.
     * @return A JSON string representing a list of academic years, or null if an error occurs.
     */
    public String fetchAcademicYears(String professorId) {
        System.out.println("SMOSServerAdapter: Fetching academic years for professorId: " + professorId);
        try {
            TimeUnit.MILLISECONDS.sleep(200); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (simulateConnectionError) {
            System.err.println("SMOSServerAdapter: Simulating connection error for fetchAcademicYears.");
            return null; // Simulate connection error
        }

        // Mock data
        if ("prof123".equals(professorId)) {
            return "[" +
                    "{\"id\":\"AY2023\", \"year\":\"2023-2024\"}," +
                    "{\"id\":\"AY2022\", \"year\":\"2022-2023\"}," +
                    "{\"id\":\"AY2021\", \"year\":\"2021-2022\"}" +
                    "]";
        } else {
            return "[]"; // No academic years for unknown professor
        }
    }

    /**
     * Fetches classes for a given professor and academic year from the simulated SMOS server.
     *
     * @param professorId The ID of the professor.
     * @param academicYearId The ID of the academic year.
     * @return A JSON string representing a list of classes, or null if an error occurs.
     */
    public String fetchClasses(String professorId, String academicYearId) {
        System.out.println("SMOSServerAdapter: Fetching classes for professorId: " + professorId + ", academicYearId: " + academicYearId);
        try {
            TimeUnit.MILLISECONDS.sleep(200); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (simulateConnectionError) {
            System.err.println("SMOSServerAdapter: Simulating connection error for fetchClasses.");
            return null; // Simulate connection error
        }

        // Mock data
        if ("prof123".equals(professorId)) {
            if ("AY2023".equals(academicYearId)) {
                return "[" +
                        "{\"id\":\"C101\", \"name\":\"Advanced Java\", \"academicYearId\":\"AY2023\", \"professorId\":\"prof123\"}," +
                        "{\"id\":\"C102\", \"name\":\"Software Design\", \"academicYearId\":\"AY2023\", \"professorId\":\"prof123\"}" +
                        "]";
            } else if ("AY2022".equals(academicYearId)) {
                return "[" +
                        "{\"id\":\"C201\", \"name\":\"Data Structures\", \"academicYearId\":\"AY2022\", \"professorId\":\"prof123\"}" +
                        "]";
            }
        }
        return "[]"; // No classes for unknown professor/year combination
    }
}