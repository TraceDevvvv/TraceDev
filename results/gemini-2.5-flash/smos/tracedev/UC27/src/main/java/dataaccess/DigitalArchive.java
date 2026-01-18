package dataaccess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulates a data source (e.g., a database or file system) for academic records.
 * It provides raw data as Lists of Maps, mimicking query results.
 */
public class DigitalArchive {

    // Simulated data for academic years
    private final List<Map<String, String>> academicYearsData = Arrays.asList(
            new HashMap<String, String>() {{ put("id", "AY2022-2023"); put("yearNumber", "2022"); }},
            new HashMap<String, String>() {{ put("id", "AY2023-2024"); put("yearNumber", "2023"); }},
            new HashMap<String, String>() {{ put("id", "AY2024-2025"); put("yearNumber", "2024"); }}
    );

    // Simulated data for digital registers
    private final Map<String, List<Map<String, String>>> registersByYear = new HashMap<>();

    public DigitalArchive() {
        // Initialize sample registers for different years
        registersByYear.put("AY2022-2023", Arrays.asList(
                new HashMap<String, String>() {{
                    put("registerId", "DR001");
                    put("title", "Math 101 Lecture Notes");
                    put("content", "Details of Math 101, Week 1-5.");
                    put("academicYearId", "AY2022-2023");
                    put("academicClassId", "C001");
                }},
                new HashMap<String, String>() {{
                    put("registerId", "DR002");
                    put("title", "Physics 201 Lab Report");
                    put("content", "Results from Physics 201 lab experiments.");
                    put("academicYearId", "AY2022-2023");
                    put("academicClassId", "C002");
                }}
        ));

        registersByYear.put("AY2023-2024", Arrays.asList(
                new HashMap<String, String>() {{
                    put("registerId", "DR003");
                    put("title", "History 301 Essay Guidelines");
                    put("content", "Guidelines for the final essay in History 301.");
                    put("academicYearId", "AY2023-2024");
                    put("academicClassId", "C003");
                }},
                new HashMap<String, String>() {{
                    put("registerId", "DR004");
                    put("title", "Chemistry 100 Midterm Review");
                    put("content", "Key topics for Chemistry 100 midterm exam.");
                    put("academicYearId", "AY2023-2024");
                    put("academicClassId", "C004");
                }}
        ));
    }

    /**
     * Simulates querying for digital records based on an academic year ID.
     *
     * @param yearId The ID of the academic year.
     * @return A list of maps, where each map represents a digital register record.
     */
    public List<Map<String, String>> queryRecords(String yearId) {
        System.out.println("[DigitalArchive] Querying records for academic year: " + yearId);
        // Simulate database lookup delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return registersByYear.getOrDefault(yearId, Arrays.asList());
    }

    /**
     * Simulates querying for all available academic years.
     *
     * @return A list of maps, where each map represents an academic year.
     */
    public List<Map<String, String>> queryAcademicYears() {
        System.out.println("[DigitalArchive] Querying all available academic years.");
        // Simulate database lookup delay
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return academicYearsData;
    }
}