package com.example.school.application;

import com.example.school.domain.Class;
import com.example.school.domain.Student;
import com.example.school.domain.ReportCard;
import com.example.school.domain.Grade;
import com.example.school.infrastructure.IClassRepository;
import com.example.school.infrastructure.IStudentRepository;
import com.example.school.infrastructure.IReportCardRepository;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.UUID; // For generating unique IDs

/**
 * Application service for managing report card related operations.
 * Acts as a coordinator between the Presentation Layer and the Domain/Infrastructure Layers.
 */
public class ReportCardApplicationService {
    private IClassRepository classRepository;
    private IStudentRepository studentRepository;
    private IReportCardRepository reportCardRepository;

    /**
     * Constructs a ReportCardApplicationService with necessary repositories.
     * @param classRepo Repository for class data.
     * @param studentRepo Repository for student data.
     * @param reportCardRepo Repository for report card data.
     */
    public ReportCardApplicationService(
            IClassRepository classRepo,
            IStudentRepository studentRepo,
            IReportCardRepository reportCardRepo) {
        this.classRepository = classRepo;
        this.studentRepository = studentRepo;
        this.reportCardRepository = reportCardRepo;
    }

    /**
     * Retrieves a list of classes for the specified academic year.
     * @param academicYearId The ID of the academic year.
     * @return A list of Class objects.
     */
    public List<Class> getClassesForCurrentAcademicYear(String academicYearId) {
        System.out.println("AppService: Getting classes for academic year: " + academicYearId);
        return classRepository.findClassesByAcademicYear(academicYearId);
    }

    /**
     * Retrieves a list of students enrolled in the specified class.
     * @param classId The ID of the class.
     * @return A list of Student objects.
     */
    public List<Student> getStudentsInClass(String classId) {
        System.out.println("AppService: Getting students in class: " + classId);
        return studentRepository.findStudentsByClass(classId);
    }

    /**
     * Retrieves a student by their unique ID.
     * @param studentId The ID of the student.
     * @return The Student object, or null if not found.
     */
    public Student getStudentById(String studentId) {
        System.out.println("AppService: Getting student by ID: " + studentId);
        return studentRepository.findStudentById(studentId);
    }

    /**
     * Saves a new report card based on provided data.
     * Constructs ReportCard and Grade domain objects internally from the map.
     * @param reportCardData A map containing report card details, including studentId, academicYearId, term, and grades.
     *                       Grades should be a List<Map<String, Object>>, where each map has "subjectId" and "score".
     * @return true if the report card was successfully saved, false otherwise.
     */
    public boolean saveReportCard(Map<String, Object> reportCardData) {
        System.out.println("AppService: Attempting to save report card data.");

        // 1. Validate data integrity before archiving.
        if (!validateReportCardData(reportCardData)) {
            System.err.println("AppService: Report card data validation failed.");
            return false;
        }

        // Extract data from the map and construct domain objects
        String reportCardId = UUID.randomUUID().toString(); // Generate unique ID for new report card
        String studentId = (String) reportCardData.get("studentId");
        String academicYearId = (String) reportCardData.get("academicYearId");
        String term = (String) reportCardData.get("term");

        ReportCard newReportCard = new ReportCard(reportCardId, studentId, academicYearId, term);

        // Process grades
        List<Map<String, Object>> gradesData = (List<Map<String, Object>>) reportCardData.get("grades");
        if (gradesData != null) {
            for (Map<String, Object> gradeData : gradesData) {
                String gradeId = UUID.randomUUID().toString(); // Generate unique ID for each grade
                String subjectId = (String) gradeData.get("subjectId");
                // Ensure score is handled correctly, as it might come as Integer or Double from map
                Number scoreNumber = (Number) gradeData.get("score");
                double score = scoreNumber != null ? scoreNumber.doubleValue() : 0.0;

                Grade grade = new Grade(gradeId, subjectId, score, reportCardId);
                newReportCard.addGrade(grade);
            }
        }

        // 2. Validate the constructed domain object
        if (!newReportCard.isValid()) {
            System.err.println("AppService: Constructed ReportCard object is invalid.");
            return false;
        }

        // 3. Save to repository
        boolean success = reportCardRepository.save(newReportCard);

        if (success) {
            System.out.println("AppService: Report card saved successfully for student " + studentId);
        } else {
            System.err.println("AppService: Failed to save report card for student " + studentId);
        }
        return success;
    }

    /**
     * Validates the raw report card data provided as a map.
     * This is a private helper method for internal application service use.
     * @param reportCardData The map containing report card details.
     * @return true if the data is valid for processing, false otherwise.
     */
    private boolean validateReportCardData(Map<String, Object> reportCardData) {
        if (reportCardData == null || reportCardData.isEmpty()) {
            System.err.println("Validation Error: ReportCard data is null or empty.");
            return false;
        }
        if (!reportCardData.containsKey("studentId") || ((String)reportCardData.get("studentId")).isEmpty()) {
            System.err.println("Validation Error: Missing studentId.");
            return false;
        }
        if (!reportCardData.containsKey("academicYearId") || ((String)reportCardData.get("academicYearId")).isEmpty()) {
            System.err.println("Validation Error: Missing academicYearId.");
            return false;
        }
        if (!reportCardData.containsKey("term") || ((String)reportCardData.get("term")).isEmpty()) {
            System.err.println("Validation Error: Missing term.");
            return false;
        }

        // Validate grades data structure
        if (reportCardData.containsKey("grades")) {
            Object gradesObj = reportCardData.get("grades");
            if (!(gradesObj instanceof List)) {
                System.err.println("Validation Error: 'grades' is not a List.");
                return false;
            }
            List<Map<String, Object>> gradesList = (List<Map<String, Object>>) gradesObj;
            for (Map<String, Object> gradeEntry : gradesList) {
                if (!gradeEntry.containsKey("subjectId") || ((String)gradeEntry.get("subjectId")).isEmpty()) {
                    System.err.println("Validation Error: Grade entry missing subjectId.");
                    return false;
                }
                if (!gradeEntry.containsKey("score") || !(gradeEntry.get("score") instanceof Number)) {
                    System.err.println("Validation Error: Grade entry missing or invalid score.");
                    return false;
                }
                // Further validation like score range can be done in Grade.isValid()
            }
        } else {
            System.err.println("Validation Warning: No grades provided in report card data.");
        }

        return true;
    }
}