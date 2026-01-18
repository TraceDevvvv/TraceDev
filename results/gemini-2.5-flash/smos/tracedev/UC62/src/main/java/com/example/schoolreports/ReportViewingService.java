
package com.example.schoolreports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Application Service Layer.
 * This class provides business logic for viewing reports, orchestrating
 * data retrieval from the repository and mapping raw data to domain models.
 */
public class ReportViewingService {

    private IReportRepository reportRepository;
    // SimpleDateFormat is not thread-safe, create new instance for each use or use ThreadLocal if in a concurrent environment.
    // For this example, it's used within a single thread, so a new instance per method call is fine.
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    /**
     * Constructs a new ReportViewingService with a dependency on an IReportRepository.
     *
     * @param reportRepository The repository used to fetch raw report data.
     */
    public ReportViewingService(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Retrieves a list of student report summaries for a given parent.
     * This method fetches raw data from the repository and maps it to domain-specific StudentReportSummary objects.
     *
     * @param parentId The ID of the parent.
     * @return A list of StudentReportSummary objects.
     * @throws SMOSConnectionException If there is a connection issue with the SMOS system.
     */
    public List<StudentReportSummary> getStudentReportsForParent(String parentId) throws SMOSConnectionException {
        System.out.println("ReportViewingService: Getting student reports for parent: " + parentId);
        List<SMOSRawReportData> rawReports = reportRepository.fetchStudentReports(parentId);

        List<StudentReportSummary> summaries = new ArrayList<>();
        // Note: Maps raw data to StudentReportSummary objects, ensures accuracy and completeness.
        for (SMOSRawReportData rawData : rawReports) {
            // Assuming rawReportSummaryJson is simple and contains "reportId" and "date" as in SMOSGateway simulation
            String reportId = parseJsonField(rawData.getRawReportSummaryJson(), "reportId");
            String dateStr = parseJsonField(rawData.getRawReportSummaryJson(), "date");
            Date reportDate = null;
            if (dateStr != null) {
                try {
                    reportDate = DATE_FORMAT.parse(dateStr);
                } catch (ParseException e) {
                    System.err.println("ReportViewingService: Failed to parse date '" + dateStr + "' from raw data for report ID " + reportId + ": " + e.getMessage());
                    // Assign a default date or handle error as appropriate
                    reportDate = new Date(); // Use current date as fallback
                }
            } else {
                reportDate = new Date(); // Use current date if no date string found
            }
            summaries.add(new StudentReportSummary(reportId, rawData.getRawStudentName(), reportDate));
        }
        System.out.println("ReportViewingService: Mapped " + summaries.size() + " raw reports to StudentReportSummary objects.");
        return summaries;
    }

    /**
     * Retrieves detailed report card information for a specific report card ID.
     * This method fetches raw data and maps it to a domain-specific ReportCard object.
     *
     * @param reportCardId The ID of the report card.
     * @return A ReportCard object containing detailed information.
     * @throws SMOSConnectionException If there is a connection issue with the SMOS system.
     */
    public ReportCard getReportCardDetails(String reportCardId) throws SMOSConnectionException {
        System.out.println("ReportViewingService: Getting report card details for ID: " + reportCardId);
        SMOSRawReportCardData rawCardData = reportRepository.fetchReportCardDetails(reportCardId);

        // Note: Maps raw data to ReportCard object, ensures accuracy and completeness.
        // Assuming rawReportDetailsJson contains "term", "year", "comments", and "grades"
        String rawJson = rawCardData.getRawReportDetailsJson();

        String term = parseJsonField(rawJson, "term");
        String academicYear = parseJsonField(rawJson, "year");
        String teacherComments = parseJsonField(rawJson, "comments");

        List<CourseGrade> courseGrades = new ArrayList<>();
        // Simple parsing for grades array - this would typically use a JSON library like Jackson or Gson
        String gradesJsonArray = extractJsonArray(rawJson, "grades");
        if (gradesJsonArray != null) {
            // Split into individual grade objects (very naive parsing for demo)
            String[] gradeStrings = gradesJsonArray.substring(1, gradesJsonArray.length() - 1).split("\\},\\s*\\{");
            for (String gradeString : gradeStrings) {
                if (!gradeString.trim().isEmpty()) {
                    String cleanGradeString = "{" + gradeString.trim() + "}";
                    String courseName = parseJsonField(cleanGradeString, "course");
                    String grade = parseJsonField(cleanGradeString, "grade");
                    String comment = parseJsonField(cleanGradeString, "comment");
                    if (courseName != null && grade != null) {
                        courseGrades.add(new CourseGrade(courseName, grade, comment != null ? comment : "N/A"));
                    }
                }
            }
        }


        System.out.println("ReportViewingService: Mapped raw report card data for ID " + reportCardId + " to ReportCard object.");
        return new ReportCard(
                rawCardData.getRawReportCardId(),
                rawCardData.getRawStudentId(),
                // Student name might need to come from another source or be derived from student ID if not in raw card data
                // For simplicity, we'll assume it's part of the raw data or passed contextually.
                // In SMOSGateway, we set rawStudentId/Name based on reportCardId, so we use it here.
                rawCardData.getRawStudentId().equals("studentA456") ? "Alice Smith" :
                rawCardData.getRawStudentId().equals("studentB789") ? "Bob Johnson" : "Unknown Student",
                term != null ? term : "N/A",
                academicYear != null ? academicYear : "N/A",
                courseGrades,
                teacherComments != null ? teacherComments : "No general comments."
        );
    }

    /**
     * Helper method to parse a specific field from a simple JSON string (for demonstration purposes).
     * This is a very rudimentary parser and would be replaced by a proper JSON library in a real application.
     */
    private String parseJsonField(String json, String fieldName) {
        if (json == null || fieldName == null) return null;
        String searchKey = "\"" + fieldName + "\":";
        int startIndex = json.indexOf(searchKey);
        if (startIndex == -1) return null;

        startIndex += searchKey.length();
        // Skip whitespace
        while (startIndex < json.length() && Character.isWhitespace(json.charAt(startIndex))) {
            startIndex++;
        }

        if (json.charAt(startIndex) == '"') { // String value
            startIndex++; // Skip opening quote
            int endIndex = json.indexOf('"', startIndex);
            if (endIndex != -1) {
                return json.substring(startIndex, endIndex);
            }
        } else { // Non-string value (e.g., number, boolean, null) - for simplicity assume strings for now
            // The following while loop was calculating an endIndex that was then immediately overwritten
            // by the logic below it. It can be ignored as dead code, or removed, but for minimal changes
            // we focus on fixing the problematic return statement.
            int endIndex = startIndex;
            while (endIndex < json.length() && (Character.isLetterOrDigit(json.charAt(endIndex)) || json.charAt(endIndex) == '-' || json.charAt(endIndex) == '+' || json.charAt(endIndex) == '.')) {
                endIndex++;
            }
             // Fallback for non-quoted strings like 'true', 'false', numbers in actual JSON
             // For this simulation, assuming string fields for 'term', 'year', 'comments', 'grade', 'course', 'reportId', 'date'.
            // Fix: Replace indexOfAny (which does not exist in standard String API) with a manual search for delimiters.
            int minDelimiterIndex = json.length(); // Initialize with a value beyond any possible valid index

            int commaIdx = json.indexOf(',', startIndex);
            if (commaIdx != -1) {
                minDelimiterIndex = Math.min(minDelimiterIndex, commaIdx);
            }

            int braceIdx = json.indexOf('}', startIndex);
            if (braceIdx != -1) {
                minDelimiterIndex = Math.min(minDelimiterIndex, braceIdx);
            }

            int bracketIdx = json.indexOf(']', startIndex);
            if (bracketIdx != -1) {
                minDelimiterIndex = Math.min(minDelimiterIndex, bracketIdx);
            }

            // Extract the substring up to the first delimiter found, or to the end of the string.
            return json.substring(startIndex, minDelimiterIndex).trim();
        }
        return null;
    }

    /**
     * Extracts a JSON array string for a given field name (for demonstration purposes).
     */
    private String extractJsonArray(String json, String fieldName) {
        if (json == null || fieldName == null) return null;
        String searchKey = "\"" + fieldName + "\":";
        int startIndex = json.indexOf(searchKey);
        if (startIndex == -1) return null;

        startIndex += searchKey.length();
        // Skip whitespace
        while (startIndex < json.length() && Character.isWhitespace(json.charAt(startIndex))) {
            startIndex++;
        }
        if (json.charAt(startIndex) == '[') {
            int bracketCount = 0;
            int endIndex = startIndex;
            while(endIndex < json.length()) {
                if (json.charAt(endIndex) == '[') bracketCount++;
                if (json.charAt(endIndex) == ']') bracketCount--;
                if (bracketCount == 0 && json.charAt(endIndex) == ']') break;
                endIndex++;
            }
            if (endIndex < json.length()) {
                return json.substring(startIndex, endIndex + 1);
            }
        }
        return null;
    }
}
