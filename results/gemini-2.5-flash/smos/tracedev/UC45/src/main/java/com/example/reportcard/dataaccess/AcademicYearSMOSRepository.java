package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.AcademicYear;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of IAcademicYearRepository using SMOSDataAccessor.
 * Parses string data from SMOS into AcademicYear objects.
 */
public class AcademicYearSMOSRepository implements IAcademicYearRepository {
    private SMOSDataAccessor smosDataAccessor;

    public AcademicYearSMOSRepository(SMOSDataAccessor accessor) {
        this.smosDataAccessor = accessor;
    }

    @Override
    public List<AcademicYear> findAll() {
        // Prepare parameters for SMOS data accessor. For findAll, params are empty.
        Map<String, String> params = new HashMap<>();
        String academicYearsData = smosDataAccessor.fetchData("academicYears", params);
        
        return parseAcademicYearsData(academicYearsData);
    }

    /**
     * Parses a JSON-like string into a list of AcademicYear objects.
     * This is a simplified parser for demonstration, assuming a specific string format.
     * Example input: "[{\"id\":\"2023-2024\",\"year\":\"2023-2024\",\"startDate\":\"2023-09-01\",\"endDate\":\"2024-06-30\"}]"
     * @param data The string data from SMOS.
     * @return A list of AcademicYear objects.
     */
    private List<AcademicYear> parseAcademicYearsData(String data) {
        List<AcademicYear> academicYears = new ArrayList<>();
        if (data == null || data.isEmpty() || !data.startsWith("[") || !data.endsWith("]")) {
            return academicYears;
        }

        // Remove brackets and split by object
        String content = data.substring(1, data.length() - 1);
        String[] yearStrings = content.split("\\},\\{"); // Split by "},{", assuming valid JSON structure

        for (String yearStr : yearStrings) {
            // Re-add braces for easier parsing, if they were removed
            if (!yearStr.startsWith("{")) yearStr = "{" + yearStr;
            if (!yearStr.endsWith("}")) yearStr = yearStr + "}";

            // Simple key-value extraction (not robust JSON parsing)
            String id = extractValue(yearStr, "id");
            String year = extractValue(yearStr, "year");
            String startDate = extractValue(yearStr, "startDate");
            String endDate = extractValue(yearStr, "endDate");

            if (id != null && year != null && startDate != null && endDate != null) {
                academicYears.add(new AcademicYear(id, year, startDate, endDate));
            }
        }
        return academicYears;
    }

    /**
     * Helper to extract a value from a simplified JSON string.
     * @param jsonString The JSON-like string.
     * @param key The key to extract.
     * @return The extracted value, or null if not found.
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