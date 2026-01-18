package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of IAcademicRepository that interacts with SMOSServerAdapter.
 * It handles the conversion of raw data from the adapter into domain objects.
 */
public class AcademicRepositoryImpl implements IAcademicRepository {

    private SMOSServerAdapter smosServerAdapter;

    public AcademicRepositoryImpl(SMOSServerAdapter smosServerAdapter) {
        this.smosServerAdapter = smosServerAdapter;
    }

    /**
     * Finds academic years by professor ID.
     * Delegates to SMOSServerAdapter and parses the JSON response.
     *
     * @param professorId The ID of the professor.
     * @return A list of AcademicYear objects.
     * @throws DataAccessException if there's a connection error or parsing issue.
     */
    @Override
    public List<AcademicYear> findAcademicYearsByProfessor(String professorId) throws DataAccessException {
        System.out.println("AcademicRepositoryImpl: findAcademicYearsByProfessor(" + professorId + ")");
        String academicYearJsonData = smosServerAdapter.fetchAcademicYears(professorId);

        if (academicYearJsonData == null) {
            throw new DataAccessException("Failed to connect to SMOS Server to fetch academic years.");
        }

        // Basic mock JSON parsing (assuming simple JSON array of objects)
        List<AcademicYear> academicYears = new ArrayList<>();
        try {
            // Remove brackets and split by object delimiter
            String cleanedJson = academicYearJsonData.substring(1, academicYearJsonData.length() - 1);
            String[] yearStrings = cleanedJson.split("\\},\\{");

            if (yearStrings.length == 1 && yearStrings[0].trim().isEmpty()) {
                return Collections.emptyList(); // Handle empty array case
            }

            for (String yearString : yearStrings) {
                // Remove curly braces if present from splitting
                if (!yearString.startsWith("{")) yearString = "{" + yearString;
                if (!yearString.endsWith("}")) yearString = yearString + "}";

                String id = extractValue(yearString, "id");
                String year = extractValue(yearString, "year");
                if (id != null && year != null) {
                    academicYears.add(new AcademicYear(id, year));
                }
            }
        } catch (Exception e) {
            throw new DataAccessException("Error parsing academic years JSON data: " + academicYearJsonData, e);
        }
        return academicYears;
    }

    /**
     * Finds classes by academic year and professor ID.
     * Delegates to SMOSServerAdapter and parses the JSON response.
     *
     * @param professorId The ID of the professor.
     * @param academicYearId The ID of the academic year.
     * @return A list of Class objects.
     * @throws DataAccessException if there's a connection error or parsing issue.
     */
    @Override
    public List<Class> findClassesByAcademicYearAndProfessor(String professorId, String academicYearId) throws DataAccessException {
        System.out.println("AcademicRepositoryImpl: findClassesByAcademicYearAndProfessor(" + professorId + ", " + academicYearId + ")");
        String classJsonData = smosServerAdapter.fetchClasses(professorId, academicYearId);

        if (classJsonData == null) {
            throw new DataAccessException("Failed to connect to SMOS Server to fetch classes.");
        }

        // Basic mock JSON parsing (assuming simple JSON array of objects)
        List<Class> classes = new ArrayList<>();
        try {
            String cleanedJson = classJsonData.substring(1, classJsonData.length() - 1);
            String[] classStrings = cleanedJson.split("\\},\\{");

            if (classStrings.length == 1 && classStrings[0].trim().isEmpty()) {
                return Collections.emptyList(); // Handle empty array case
            }

            for (String classString : classStrings) {
                if (!classString.startsWith("{")) classString = "{" + classString;
                if (!classString.endsWith("}")) classString = classString + "}";

                String id = extractValue(classString, "id");
                String name = extractValue(classString, "name");
                String ayId = extractValue(classString, "academicYearId");
                String pId = extractValue(classString, "professorId");
                if (id != null && name != null && ayId != null && pId != null) {
                    classes.add(new Class(id, name, ayId, pId));
                }
            }
        } catch (Exception e) {
            throw new DataAccessException("Error parsing classes JSON data: " + classJsonData, e);
        }
        return classes;
    }

    /**
     * Helper method for basic manual JSON value extraction.
     * This is a simplification to avoid external JSON libraries.
     *
     * @param jsonString The JSON object string (e.g., "{"id":"AY2023", "year":"2023-2024"}").
     * @param key The key whose value is to be extracted.
     * @return The extracted value string, or null if not found.
     */
    private String extractValue(String jsonString, String key) {
        String searchKey = "\"" + key + "\":\"";
        int startIndex = jsonString.indexOf(searchKey);
        if (startIndex != -1) {
            startIndex += searchKey.length();
            int endIndex = jsonString.indexOf("\"", startIndex);
            if (endIndex != -1) {
                return jsonString.substring(startIndex, endIndex);
            }
        }
        return null;
    }
}