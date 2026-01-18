package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.Class;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of IClassRepository using SMOSDataAccessor.
 * Parses string data from SMOS into Class objects.
 */
public class ClassSMOSRepository implements IClassRepository {
    private SMOSDataAccessor smosDataAccessor;

    public ClassSMOSRepository(SMOSDataAccessor accessor) {
        this.smosDataAccessor = accessor;
    }

    @Override
    public List<Class> findByAcademicYear(String yearId) {
        // Prepare parameters for SMOS data accessor.
        Map<String, String> params = new HashMap<>();
        params.put("yearId", yearId);
        String classesData = smosDataAccessor.fetchData("classes", params);

        return parseClassesData(classesData, yearId);
    }

    /**
     * Parses a JSON-like string into a list of Class objects.
     * This is a simplified parser for demonstration, assuming a specific string format.
     * Example input: "[{\"id\":\"10A\",\"name\":\"Grade 10A\",\"academicYearId\":\"2023-2024\"}]"
     * @param data The string data from SMOS.
     * @param academicYearId The academic year ID to associate with parsed classes (from input).
     * @return A list of Class objects.
     */
    private List<Class> parseClassesData(String data, String academicYearId) {
        List<Class> classes = new ArrayList<>();
        if (data == null || data.isEmpty() || !data.startsWith("[") || !data.endsWith("]")) {
            return classes;
        }

        String content = data.substring(1, data.length() - 1);
        String[] classStrings = content.split("\\},\\{");

        for (String classStr : classStrings) {
            if (!classStr.startsWith("{")) classStr = "{" + classStr;
            if (!classStr.endsWith("}")) classStr = classStr + "}";

            String id = extractValue(classStr, "id");
            String name = extractValue(classStr, "name");
            // academicYearId is already known from the query
            
            if (id != null && name != null) {
                classes.add(new Class(id, name, academicYearId));
            }
        }
        return classes;
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