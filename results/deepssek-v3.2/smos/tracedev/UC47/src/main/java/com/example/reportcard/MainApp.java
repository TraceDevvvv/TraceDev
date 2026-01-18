package com.example.reportcard;

import com.example.reportcard.application.EditReportCardUseCase;
import com.example.reportcard.application.EditReportCardUseCaseImpl;
import com.example.reportcard.authentication.AuthenticationService;
import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.ReportCardDTO;
import com.example.reportcard.domain.ReportCardService;
import com.example.reportcard.domain.ReportCardServiceImpl;
import com.example.reportcard.infrastructure.ReportCardRepository;
import com.example.reportcard.infrastructure.ReportCardRepositoryImpl;
import com.example.reportcard.presentation.EditReportCardController;
import com.example.reportcard.presentation.EditReportCardControllerImpl;
import com.example.reportcard.presentation.ReportCardEditView;
import com.example.reportcard.presentation.StudentListView;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class demonstrating the sequence diagram flow.
 * This simulates the interactions and is fully runnable.
 */
public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== Report Card Modification System Demo ===\n");

        // Infrastructure
        ReportCardRepository repository = new ReportCardRepositoryImpl();
        // Pre‑populate a report card for student "S001"
        Map<String, Integer> initialGrades = new HashMap<>();
        initialGrades.put("Math", 85);
        initialGrades.put("Science", 90);
        ReportCard initialCard = new ReportCard("S001", initialGrades);
        repository.save(initialCard);

        // Domain
        ReportCardService reportCardService = new ReportCardServiceImpl();

        // Application
        EditReportCardUseCase useCase = new EditReportCardUseCaseImpl(repository, reportCardService);

        // Authentication
        AuthenticationService authService = new AuthenticationService();

        // Presentation
        ReportCardEditView view = new ReportCardEditView();
        EditReportCardController controller = new EditReportCardControllerImpl(useCase, authService, view);
        view.setController(controller);

        // Simulate previous use case
        StudentListView studentListView = new StudentListView();
        studentListView.displayStudentList();
        studentListView.onStudentSelected("S001");

        System.out.println("\n--- Flow: Administrator clicks edit button ---");
        // Administrator clicks edit button for student S001
        view.onEditButtonClicked("S001");

        System.out.println("\n--- Flow: Administrator modifies grades ---");
        // Simulate Administrator entering new data
        Map<String, Integer> newGrades = new HashMap<>();
        newGrades.put("Math", 95);  // Changed from 85 to 95
        newGrades.put("Science", 90);
        newGrades.put("History", 88); // New subject

        // Administrator clicks confirmation key
        view.submitChanges(newGrades);

        System.out.println("\n--- End of Demo ---");
    }
}