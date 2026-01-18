package com.example.reportcard;

import com.example.reportcard.presentation.ProfessorController;
import com.example.reportcard.presentation.ReportCardView;
import com.example.reportcard.presentation.ReportCardViewModel;
import com.example.reportcard.repository.AcademicYearRepositoryImpl;
import com.example.reportcard.repository.ClassRepositoryImpl;
import com.example.reportcard.repository.IClassRepository;
import com.example.reportcard.repository.ReportCardRepositoryImpl;
import com.example.reportcard.repository.StudentRepositoryImpl;
import com.example.reportcard.service.NetworkConnectivityServiceImpl;
import com.example.reportcard.service.ReportCardService;
import com.example.reportcard.service.SessionManager;

/**
 * Main application class to set up and run the Report Card system.
 * This class handles dependency injection and simulates user interaction.
 */
public class Application {

    public static void main(String[] args) {
        // --- Infrastructure Layer Setup (Mock Repositories and Serv) ---
        // ClassRepository needs to be created first for AcademicYearRepositoryImpl's constructor
        ClassRepositoryImpl classRepository = new ClassRepositoryImpl();
        AcademicYearRepositoryImpl academicYearRepository = new AcademicYearRepositoryImpl(classRepository);
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        ReportCardRepositoryImpl reportCardRepository = new ReportCardRepositoryImpl();
        NetworkConnectivityServiceImpl networkService = new NetworkConnectivityServiceImpl();

        // Application Service Layer Setup
        ReportCardService reportCardService = new ReportCardService(
                academicYearRepository,
                classRepository,
                studentRepository,
                reportCardRepository,
                networkService
        );
        SessionManager sessionManager = new SessionManager();

        // Presentation Layer Setup
        ReportCardView reportCardView = new ReportCardView();
        ReportCardViewModel reportCardViewModel = new ReportCardViewModel();
        ProfessorController professorController = new ProfessorController(
                reportCardService,
                sessionManager,
                reportCardView,
                reportCardViewModel
        );

        // --- Simulate User Interaction ---
        System.out.println("--- Starting Report Card Application Simulation ---");

        // Step 1: Professor requests initial view / academic years (Flow 1)
        reportCardView.renderInitialView();
        professorController.requestAcademicYears();

        // Step 2: Professor selects an academic year (Flow 2, 3)
        // Assume professor clicks on "AY2023"
        professorController.selectAcademicYear("AY2023");

        // Step 3: Professor selects a class (Flow 4, 5)
        // Assume professor clicks on "C101" (Math Grade 10)
        professorController.selectClass("C101");

        // Step 4: Professor selects a student (Flow 6)
        // Assume professor clicks on "S101" (Alice Smith)
        professorController.selectStudent("S101");

        // Step 5: Professor selects a quarter (Flow 7)
        // Assume professor selects "Q1"
        professorController.selectQuadrimestre("Q1");

        // Step 6: Professor clicks "Display Report Card" (Flow 8, 9)
        professorController.displayReportCard();

        // --- Simulate Network Interruption Scenario ---
        System.out.println("\n--- Simulating Network Interruption ---");
        networkService.setOnline(false); // Go offline

        // Try to request academic years again
        professorController.requestAcademicYears();

        // Try to display report card again
        professorController.displayReportCard();

        System.out.println("\n--- Restoring Network and trying another report card ---");
        networkService.setOnline(true); // Go online
        sessionManager.setProfessorId("P001"); // Ensure P001 is active (default)

        // Select different year/class/student to see if it works with restored connection
        professorController.selectAcademicYear("AY2023");
        professorController.selectClass("C102"); // Physics Grade 10
        professorController.selectStudent("S103"); // Charlie Brown
        professorController.selectQuadrimestre("Q1"); // Charlie Brown's Q1 report card for C102 (Physics Grade 10, AY2023)
        professorController.displayReportCard(); // This report card should be found if mocked correctly.

        System.out.println("\n--- Simulating No Report Card Found ---");
        professorController.selectStudent("S999"); // Non-existent student
        professorController.selectAcademicYear("AY2023");
        professorController.selectQuadrimestre("Q1");
        professorController.displayReportCard(); // Should show "Report card not found"

        System.out.println("\n--- Report Card Application Simulation Finished ---");
    }
}