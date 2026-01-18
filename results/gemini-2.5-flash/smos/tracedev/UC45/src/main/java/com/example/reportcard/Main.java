package com.example.reportcard;

import com.example.reportcard.application.ReportCardService;
import com.example.reportcard.dataaccess.AcademicYearSMOSRepository;
import com.example.reportcard.dataaccess.ClassSMOSRepository;
import com.example.reportcard.dataaccess.IAcademicYearRepository;
import com.example.reportcard.dataaccess.IClassRepository;
import com.example.reportcard.dataaccess.IReportCardRepository;
import com.example.reportcard.dataaccess.IStudentRepository;
import com.example.reportcard.dataaccess.ReportCardSMOSRepository;
import com.example.reportcard.dataaccess.SMOSDataAccessor;
import com.example.reportcard.dataaccess.StudentSMOSRepository;
import com.example.reportcard.presentation.ReportCardController;
import com.example.reportcard.presentation.ReportCardView;

import java.util.Arrays;

/**
 * Main class to demonstrate the Report Card application flow as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Initialize Data Access Layer (Repositories and Data Accessor)
        SMOSDataAccessor smosDataAccessor = new SMOSDataAccessor();
        IAcademicYearRepository academicYearRepo = new AcademicYearSMOSRepository(smosDataAccessor);
        IClassRepository classRepo = new ClassSMOSRepository(smosDataAccessor);
        IStudentRepository studentRepo = new StudentSMOSRepository(smosDataAccessor);
        IReportCardRepository reportCardRepo = new ReportCardSMOSRepository(smosDataAccessor);

        // 2. Initialize Application Layer (Service)
        ReportCardService reportCardService = new ReportCardService(
                academicYearRepo,
                classRepo,
                studentRepo,
                reportCardRepo
        );

        // 3. Initialize Presentation Layer (Controller and View)
        // Controller depends on Service
        ReportCardController reportCardController = new ReportCardController(reportCardService);
        // View depends on Controller, and sets itself back to controller
        ReportCardView reportCardView = new ReportCardView(reportCardController);

        System.out.println("--- Starting Report Card Application Demo ---");

        // Simulate the sequence diagram flow for a successful report generation
        System.out.println("\n--- Scenario 1: Successful Report Generation ---");
        reportCardView.clickOnlineReports(); // Administrator -> View: clickOnlineReports()
        reportCardView.selectAcademicYear("2023-2024"); // Administrator -> View: selectAcademicYear()
        reportCardView.selectClass("10A"); // Administrator -> View: selectClass()
        reportCardView.selectStudent("S001"); // Administrator -> View: selectStudent()
        reportCardView.selectMonths(Arrays.asList("Jan", "Feb", "Mar", "Apr")); // Administrator -> View: selectMonths()
        reportCardView.clickGenerateReport(); // Administrator -> View: clickGenerateReport()

        // Simulate the sequence diagram flow for an interrupted connection
        System.out.println("\n\n--- Scenario 2: SMOS Connection Interrupted ---");
        SMOSDataAccessor.SIMULATE_REPORT_ERROR = true; // Set flag to simulate error
        reportCardView.clickOnlineReports(); // Restart flow to ensure state is clean (optional, but good practice)
        reportCardView.selectAcademicYear("2023-2024");
        reportCardView.selectClass("10A");
        reportCardView.selectStudent("S001");
        reportCardView.selectMonths(Arrays.asList("Jan", "Feb", "Mar", "Apr"));
        reportCardView.clickGenerateReport(); // This call should now trigger the error path

        SMOSDataAccessor.SIMULATE_REPORT_ERROR = false; // Reset for any future calls

        System.out.println("\n--- End of Demo ---");
    }
}