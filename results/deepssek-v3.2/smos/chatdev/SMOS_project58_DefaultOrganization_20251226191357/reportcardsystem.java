'''
Core system class that manages all report card data, business logic,
and SMOS server connection simulation.
'''
package com.chatdev.reportcardsystem.model;
import com.chatdev.reportcardsystem.data.ReportCard;
import com.chatdev.reportcardsystem.data.SubjectGrade;
import java.util.*;
public class ReportCardSystem {
    private Map<String, List<String>> academicYearClasses;
    private Map<String, Map<String, List<String>>> classStudents;
    private Map<String, ReportCard> reportCards;
    private SMOSServerConnection serverConnection;
    public ReportCardSystem() {
        this.serverConnection = new SMOSServerConnection();
        initializeSampleData();
    }
    /**
     * Initializes sample data for demonstration purposes.
     * In a real application, this would connect to a database.
     */
    private void initializeSampleData() {
        academicYearClasses = new HashMap<>();
        academicYearClasses.put("2023-2024", Arrays.asList("10A", "10B", "11A"));
        academicYearClasses.put("2022-2023", Arrays.asList("9A", "9B", "10A"));
        academicYearClasses.put("2021-2022", Arrays.asList("8A", "8B"));
        classStudents = new HashMap<>();
        Map<String, List<String>> students2024 = new HashMap<>();
        students2024.put("10A", Arrays.asList("S001 - John Smith", "S002 - Emma Johnson", "S003 - Michael Brown"));
        students2024.put("10B", Arrays.asList("S004 - Sarah Davis", "S005 - David Wilson"));
        students2024.put("11A", Arrays.asList("S006 - Lisa Miller", "S007 - James Taylor"));
        Map<String, List<String>> students2023 = new HashMap<>();
        students2023.put("9A", Arrays.asList("S008 - Maria Garcia", "S009 - Robert Martinez"));
        students2023.put("9B", Arrays.asList("S010 - Jennifer Anderson"));
        students2023.put("10A", Arrays.asList("S001 - John Smith", "S002 - Emma Johnson"));
        classStudents.put("2023-2024", students2024);
        classStudents.put("2022-2023", students2023);
        reportCards = new HashMap<>();
        ReportCard johnReport = new ReportCard("S001", "John Smith", "10A", "2023-2024", "Q1");
        johnReport.addGrade(new SubjectGrade("Mathematics", 85, "A"));
        johnReport.addGrade(new SubjectGrade("English", 90, "A"));
        johnReport.addGrade(new SubjectGrade("Science", 78, "B"));
        johnReport.addGrade(new SubjectGrade("History", 92, "A"));
        johnReport.addGrade(new SubjectGrade("Physical Education", 88, "A"));
        johnReport.setComments("Excellent performance. Shows great improvement in Science.");
        reportCards.put("S001-Q1-2023-2024", johnReport);
        ReportCard emmaReport = new ReportCard("S002", "Emma Johnson", "10A", "2023-2024", "Q1");
        emmaReport.addGrade(new SubjectGrade("Mathematics", 95, "A+"));
        emmaReport.addGrade(new SubjectGrade("English", 88, "A"));
        emmaReport.addGrade(new SubjectGrade("Science", 92, "A"));
        emmaReport.addGrade(new SubjectGrade("History", 85, "A"));
        emmaReport.addGrade(new SubjectGrade("Physical Education", 90, "A"));
        emmaReport.setComments("Outstanding performance across all subjects.");
        reportCards.put("S002-Q1-2023-2024", emmaReport);
    }
    /**
     * Gets all available academic years.
     * @return List of academic year strings
     */
    public List<String> getAvailableAcademicYears() {
        return new ArrayList<>(academicYearClasses.keySet());
    }
    /**
     * Gets classes for a specific academic year.
     * @param academicYear The academic year
     * @return List of class names
     */
    public List<String> getClassesForAcademicYear(String academicYear) {
        return academicYearClasses.getOrDefault(academicYear, new ArrayList<>());
    }
    /**
     * Gets students for a specific class in an academic year.
     * @param academicYear The academic year
     * @param className The class name
     * @return List of student identifiers with names
     */
    public List<String> getStudentsForClass(String academicYear, String className) {
        Map<String, List<String>> yearData = classStudents.get(academicYear);
        if (yearData != null) {
            return yearData.getOrDefault(className, new ArrayList<>());
        }
        return new ArrayList<>();
    }
    /**
     * Gets a report card for a specific student and quarter.
     * @param studentId The student ID
     * @param quarter The quarter (Q1, Q2, Q3, Q4)
     * @param academicYear The academic year
     * @return The report card, or null if not found
     */
    public ReportCard getReportCard(String studentId, String quarter, String academicYear) {
        String key = studentId + "-" + quarter + "-" + academicYear;
        ReportCard report = reportCards.get(key);
        if (report == null) {
            String studentName = getStudentName(studentId);
            if (studentName != null) {
                report = generateSampleReport(studentId, studentName, "10A", academicYear, quarter);
            }
        }
        return report;
    }
    /**
     * Gets student name from ID.
     * @param studentId The student ID
     * @return Student name or null if not found
     */
    private String getStudentName(String studentId) {
        for (Map.Entry<String, Map<String, List<String>>> yearEntry : classStudents.entrySet()) {
            for (Map.Entry<String, List<String>> classEntry : yearEntry.getValue().entrySet()) {
                for (String student : classEntry.getValue()) {
                    if (student.startsWith(studentId + " - ")) {
                        return student.substring(studentId.length() + 3);
                    }
                }
            }
        }
        return null;
    }
    /**
     * Generates a sample report card for demonstration.
     */
    private ReportCard generateSampleReport(String studentId, String studentName,
                                           String className, String academicYear, String quarter) {
        ReportCard report = new ReportCard(studentId, studentName, className, academicYear, quarter);
        String[] subjects = {"Mathematics", "English", "Science", "History", "Physical Education", "Art", "Music"};
        Random random = new Random(studentId.hashCode() + quarter.hashCode());
        for (String subject : subjects) {
            int score = 70 + random.nextInt(31);
            String grade;
            if (score >= 90) grade = "A";
            else if (score >= 80) grade = "B";
            else if (score >= 70) grade = "C";
            else if (score >= 60) grade = "D";
            else grade = "F";
            report.addGrade(new SubjectGrade(subject, score, grade));
        }
        report.setComments("Good performance overall. " +
                          (random.nextBoolean() ? "Shows consistent effort." : "Room for improvement in some areas."));
        return report;
    }
    /**
     * Simulates disconnecting from the SMOS server as specified in postconditions.
     */
    public void disconnectFromServer() {
        if (serverConnection != null) {
            serverConnection.disconnect();
        }
    }
    /**
     * Gets the SMOS server connection instance.
     * @return SMOSServerConnection
     */
    public SMOSServerConnection getServerConnection() {
        return serverConnection;
    }
}