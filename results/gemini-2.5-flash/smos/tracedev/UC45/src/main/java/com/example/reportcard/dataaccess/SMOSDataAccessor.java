package com.example.reportcard.dataaccess;

import java.util.Map;

/**
 * Simulates an external data access system (SMOS).
 * It returns hardcoded JSON-like strings based on the endpoint and parameters.
 */
public class SMOSDataAccessor {

    // Flag to simulate a connection interruption for ReportCard fetching
    public static boolean SIMULATE_REPORT_ERROR = false;

    /**
     * Simulates fetching data from the SMOS system.
     * In a real application, this would involve network calls, database queries, etc.
     *
     * @param endpoint The data endpoint (e.g., "academicYears", "classes", "students", "reportCard").
     * @param params A map of parameters for the request.
     * @return A JSON-like string representation of the fetched data.
     * @throws RuntimeException if a simulated connection error occurs, specifically for "reportCard" endpoint.
     */
    public String fetchData(String endpoint, Map<String, String> params) {
        System.out.println(String.format("SMOSDataAccessor: Fetching data from endpoint '%s' with params: %s", endpoint, params));

        switch (endpoint) {
            case "academicYears":
                // Mock data for academic years
                return "[{\"id\":\"2023-2024\",\"year\":\"2023-2024\",\"startDate\":\"2023-09-01\",\"endDate\":\"2024-06-30\"}, " +
                       "{\"id\":\"2022-2023\",\"year\":\"2022-2023\",\"startDate\":\"2022-09-01\",\"endDate\":\"2023-06-30\"}]";
            case "classes":
                // Mock data for classes based on academicYearId
                String yearId = params.get("yearId");
                if ("2023-2024".equals(yearId)) {
                    return "[{\"id\":\"10A\",\"name\":\"Grade 10A\",\"academicYearId\":\"2023-2024\"}, " +
                           "{\"id\":\"10B\",\"name\":\"Grade 10B\",\"academicYearId\":\"2023-2024\"}]";
                } else if ("2022-2023".equals(yearId)) {
                    return "[{\"id\":\"09A\",\"name\":\"Grade 09A\",\"academicYearId\":\"2022-2023\"}]";
                }
                return "[]";
            case "students":
                // Mock data for students based on classId
                String classId = params.get("classId");
                if ("10A".equals(classId)) {
                    return "[{\"id\":\"S001\",\"name\":\"Alice Smith\",\"classId\":\"10A\"}, " +
                           "{\"id\":\"S002\",\"name\":\"Bob Johnson\",\"classId\":\"10A\"}]";
                } else if ("10B".equals(classId)) {
                    return "[{\"id\":\"S003\",\"name\":\"Charlie Brown\",\"classId\":\"10B\"}]";
                } else if ("09A".equals(classId)) {
                    return "[{\"id\":\"S004\",\"name\":\"Diana Prince\",\"classId\":\"09A\"}]";
                }
                return "[]";
            case "reportCard":
                // Simulate error condition as per sequence diagram
                if (SIMULATE_REPORT_ERROR) {
                    throw new RuntimeException("SMOS connection interrupted for report card request.");
                }

                // Mock data for a report card
                String studentId = params.get("studentId");
                String academicYearId = params.get("yearId");
                String months = params.get("months"); // e.g., "[Jan,Feb,Mar,Apr]"

                if ("S001".equals(studentId) && "2023-2024".equals(academicYearId) && "[Jan,Feb,Mar,Apr]".equals(months)) {
                    return "{\"id\":\"RC001\",\"studentId\":\"S001\",\"academicYearId\":\"2023-2024\",\"periodMonths\":[\"Jan\",\"Feb\",\"Mar\",\"Apr\"]," +
                           "\"grades\":{\"Math\":\"A\",\"Science\":\"B\",\"History\":\"C+\",\"Art\":\"A-\"}}";
                }
                return null; // No report card found for given criteria
            default:
                return "{}"; // Empty JSON object for unknown endpoints
        }
    }
}