package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of IStudentRepository using SMOSDataAccessor.
 * Parses string data from SMOS into Student objects.
 */
public class StudentSMOSRepository implements IStudentRepository {
    private SMOSDataAccessor smosDataAccessor;

    public StudentSMOSRepository(SMOSDataAccessor accessor) {
        this.smosDataAccessor = accessor;
    }

    @Override
    public List<Student> findByClass(String classId) {
        // Prepare parameters for SMOS data accessor.
        Map<String, String> params = new HashMap<>();
        params.put("classId", classId);
        String studentsData = smosDataAccessor.fetchData("students", params);

        return parseStudentsData(studentsData, classId);
    }

    /**
     * Parses a JSON-like string into a list of Student objects.
     * This is a simplified parser for demonstration, assuming a specific string format.
     * Example input: "[{\"id\":\"S001\",\"name\":\"Alice Smith\",\"classId\":\"10A\"}]"
     * @param data The string data from SMOS.
     * @param classId The class ID to associate with parsed students (from input).
     * @return A list of Student objects.
     */
    private List<Student> parseStudentsData(String data, String classId) {
        List<Student> students = new ArrayList<>();
        if (data == null || data.isEmpty() || !data.startsWith("[") || !data.endsWith("]")) {
            return students;
        }

        String content = data.substring(1, data.length() - 1);
        String[] studentStrings = content.split("\\},\\{");

        for (String studentStr : studentStrings) {
            if (!studentStr.startsWith("{")) studentStr = "{" + studentStr;
            if (!studentStr.endsWith("}")) studentStr = studentStr + "}";

            String id = extractValue(studentStr, "id");
            String name = extractValue(studentStr, "name");
            // classId is already known from the query
            
            if (id != null && name != null) {
                students.add(new Student(id, name, classId));
            }
        }
        return students;
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