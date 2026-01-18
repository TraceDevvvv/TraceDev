package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.AcademicYear;
import com.example.reportcard.domain.Month;
import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of IReportCardRepository using SMOSDataAccessor.
 * Parses string data from SMOS into ReportCard objects.
 */
public class ReportCardSMOSRepository implements IReportCardRepository {
    private SMOSDataAccessor smosDataAccessor;

    public ReportCardSMOSRepository(SMOSDataAccessor accessor) {
        this.smosDataAccessor = accessor;
    }

    @Override
    public ReportCard findByStudentAndPeriod(String studentId, String academicYearId, List<Month> months) {
        // Prepare parameters for SMOS data accessor.
        Map<String, String> params = new HashMap<>();
        params.put("studentId", studentId);
        params.put("yearId", academicYearId);
        params.put("months", monthsToString(months)); // Convert List<Month> to a specific string format

        String reportCardData = smosDataAccessor.fetchData("reportCard", params);
        
        return parseReportCardData(reportCardData, studentId, academicYearId, months);
    }

    /**
     * Converts a list of Month objects to a string representation for SMOS parameters.
     * Example: [new Month("Jan",1), new Month("Feb",2)] -> "[Jan,Feb]"
     * @param months The list of months.
     * @return A string representation of months.
     */
    private String monthsToString(List<Month> months) {
        if (months == null || months.isEmpty()) {
            return "[]";
        }
        return "[" + months.stream().map(Month::getName).collect(Collectors.joining(",")) + "]";
    }

    /**
     * Parses a JSON-like string into a ReportCard object.
     * This is a simplified parser for demonstration, assuming a specific string format.
     * Example input: "{\"id\":\"RC001\",\"studentId\":\"S001\",\"academicYearId\":\"2023-2024\",\"periodMonths\":[\"Jan\",\"Feb\",\"Mar\",\"Apr\"],\"grades\":{\"Math\":\"A\",\"Science\":\"B\"}}"
     * @param data The string data from SMOS.
     * @param studentId The student ID (used to create a mock Student object).
     * @param academicYearId The academic year ID (used to create a mock AcademicYear object).
     * @param months The list of months (passed directly from input).
     * @return A ReportCard object, or null if parsing fails or data is null.
     */
    private ReportCard parseReportCardData(String data, String studentId, String academicYearId, List<Month> months) {
        if (data == null || data.isEmpty() || !data.startsWith("{") || !data.endsWith("}")) {
            return null; // No report card data or invalid format
        }

        // Mock objects for ReportCard which depend on other domain objects.
        // In a real scenario, these would be retrieved from their respective repositories.
        // For simplicity, we just create them with minimal info required for ReportCard.getReportDetails().
        Student mockStudent = new Student(studentId, "Student " + studentId, "MockClass");
        AcademicYear mockAcademicYear = new AcademicYear(academicYearId, academicYearId, "2000-01-01", "2000-12-31");


        String id = extractValue(data, "id");
        String periodMonthsStr = extractArrayValue(data, "periodMonths");
        String gradesStr = extractMapValue(data, "grades");

        List<Month> parsedMonths = new ArrayList<>();
        if (periodMonthsStr != null) {
            String[] monthNames = periodMonthsStr.split(",");
            for (String monthName : monthNames) {
                // Assuming month names are like "Jan", "Feb", etc. and we can map them to numbers
                // For simplicity, we'll just create a Month object with name and dummy number
                parsedMonths.add(new Month(monthName, 0)); // Dummy number
            }
        }

        Map<String, String> parsedGrades = new HashMap<>();
        if (gradesStr != null && !gradesStr.isEmpty()) {
            String[] gradeEntries = gradesStr.split(",");
            for (String entry : gradeEntries) {
                String[] parts = entry.split(":");
                if (parts.length == 2) {
                    parsedGrades.put(parts[0], parts[1]);
                }
            }
        }

        if (id != null) {
            // Note: The 'months' parameter is used directly here, as parsing month names to full objects is complex for a simple parser.
            // If the sequence diagram implies the service passes the original month objects, then this should be used.
            // The SMOS data accessor returns only names, so relying on the passed 'months' list is more robust here.
            return new ReportCard(id, mockStudent, mockAcademicYear, months, parsedGrades);
        }
        return null;
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

    /**
     * Helper to extract an array value from a simplified JSON string (e.g., ["Jan","Feb"]).
     * @param jsonString The JSON-like string.
     * @param key The key to extract.
     * @return A comma-separated string of array elements, or null if not found.
     */
    private String extractArrayValue(String jsonString, String key) {
        String searchKey = "\"" + key + "\":[";
        int startIndex = jsonString.indexOf(searchKey);
        if (startIndex != -1) {
            startIndex += searchKey.length();
            int endIndex = jsonString.indexOf("]", startIndex);
            if (endIndex != -1) {
                return jsonString.substring(startIndex, endIndex).replace("\"", ""); // Remove quotes from elements
            }
        }
        return null;
    }

    /**
     * Helper to extract a map value from a simplified JSON string (e.g., {"Math":"A","Science":"B"}).
     * @param jsonString The JSON-like string.
     * @param key The key to extract.
     * @return A comma-separated string of key:value pairs, or null if not found.
     */
    private String extractMapValue(String jsonString, String key) {
        String searchKey = "\"" + key + "\":{";
        int startIndex = jsonString.indexOf(searchKey);
        if (startIndex != -1) {
            startIndex += searchKey.length();
            int endIndex = jsonString.indexOf("}", startIndex);
            if (endIndex != -1) {
                return jsonString.substring(startIndex, endIndex).replace("\"", ""); // Remove quotes from keys/values
            }
        }
        return null;
    }
}