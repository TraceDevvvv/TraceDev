
package com.example.school;

import com.example.school.domain.*;
import com.example.school.infrastructure.*;
import com.example.school.application.ReportCardApplicationService;
import com.example.school.presentation.AdministratorController;
import com.example.school.presentation.ReportCardView;
import com.example.school.presentation.ReportCardViewModel;

import java.util.ArrayList; // Added import for ArrayList
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main application class to demonstrate the interaction flow.
 * This acts as the entry point and sets up the dependency injection.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("Starting School Report Card Application Simulation...");

        // 1. Infrastructure Layer Initialization
        DatabaseContext dbContext = new DatabaseContext();
        dbContext.connect(); // Simulate database connection

        IClassRepository classRepository = new ClassRepository(dbContext);
        IStudentRepository studentRepository = new StudentRepository(dbContext);
        IReportCardRepository reportCardRepository = new ReportCardRepository(dbContext);

        // 2. Application Layer Initialization
        ReportCardApplicationService reportCardAppService = new ReportCardApplicationService(
                classRepository, studentRepository, reportCardRepository);

        // 3. Presentation Layer Initialization
        ReportCardView reportCardView = new ReportCardView();
        ReportCardViewModel reportCardViewModel = new ReportCardViewModel();
        AdministratorController administratorController = new AdministratorController(
                reportCardAppService, reportCardView, reportCardViewModel);

        // --- Simulate Administrator Interactions (following the sequence diagram) ---

        // Entry Conditions: Administrator IS logged in.
        // Entry Conditions: Administrator HAS clicked "Online reports" button.
        System.out.println("\n--- SIMULATION STEP 1: Admin clicks 'Online Reports' ---");
        administratorController.displayClasses(); // Triggers display of classes

        // Administrator chooses a pupil class.
        System.out.println("\n--- SIMULATION STEP 2: Admin selects Class C001 ---");
        String selectedClassId = "C001";
        administratorController.selectClass(selectedClassId); // Triggers display of students in C001

        // Administrator selects a pupil.
        System.out.println("\n--- SIMULATION STEP 3: Admin selects Student S001 ---");
        String selectedStudentId = "S001";
        administratorController.selectStudent(selectedStudentId);

        // Administrator clicks 'Insert Report Card' button.
        System.out.println("\n--- SIMULATION STEP 4: Admin clicks 'Insert Report Card' ---");
        administratorController.displayReportCardEntryForm(selectedStudentId); // Displays the form

        // Administrator inserts grades into the form.
        Map<String, Object> newReportCardData = new HashMap<>();
        newReportCardData.put("studentId", selectedStudentId);
        newReportCardData.put("academicYearId", reportCardViewModel.getCurrentAcademicYearId());
        newReportCardData.put("term", "Term 1");

        // Simulate grades entry
        List<Map<String, Object>> grades = new ArrayList<>();
        Map<String, Object> mathGrade = new HashMap<>();
        mathGrade.put("subjectId", "SUB001"); // Mathematics
        mathGrade.put("score", 85.5);
        grades.add(mathGrade);

        Map<String, Object> scienceGrade = new HashMap<>();
        scienceGrade.put("subjectId", "SUB002"); // Science
        scienceGrade.put("score", 92.0);
        grades.add(scienceGrade);

        newReportCardData.put("grades", grades);

        System.out.println("\n--- SIMULATION STEP 5: Admin enters grades and clicks 'Save' ---");
        // Administrator clicks 'Save' button.
        administratorController.saveReportCard(newReportCardData);

        // --- Simulate another scenario: invalid data ---
        System.out.println("\n--- SIMULATION STEP 6: Admin tries to save with invalid data (missing studentId) ---");
        Map<String, Object> invalidReportCardData = new HashMap<>();
        // invalidReportCardData.put("studentId", "S001"); // Missing studentId
        invalidReportCardData.put("academicYearId", reportCardViewModel.getCurrentAcademicYearId());
        invalidReportCardData.put("term", "Term 2");
        invalidReportCardData.put("grades", grades); // Re-use grades

        administratorController.saveReportCard(invalidReportCardData);

        // Check if report card was actually saved in the "database"
        System.out.println("\n--- Current state of 'database' ---");
        System.out.println("Report Cards in DB: " + dbContext.reportCardsTable.size());
        System.out.println("Grades in DB: " + dbContext.gradesTable.size());

        dbContext.disconnect(); // Simulate database disconnection
        System.out.println("\nSchool Report Card Application Simulation Ended.");
    }
}
