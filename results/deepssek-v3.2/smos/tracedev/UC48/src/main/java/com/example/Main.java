package com.example;

import com.example.application.DeleteReportCardUseCase;
import com.example.infrastructure.SMOSService;
import com.example.infrastructure.ReportCardRepositoryImpl;
import com.example.presentation.ConsoleDeleteReportCardView;
import com.example.presentation.ReportCardController;
import java.util.Scanner;

/**
 * Main class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize infrastructure
        SMOSService smosService = new SMOSService();
        ReportCardRepositoryImpl repository = new ReportCardRepositoryImpl(smosService);
        
        // Initialize use case
        DeleteReportCardUseCase useCase = new DeleteReportCardUseCase(repository, smosService);
        
        // Initialize view and controller
        ConsoleDeleteReportCardView view = new ConsoleDeleteReportCardView();
        ReportCardController controller = new ReportCardController(view, useCase);
        
        // Simulate administrator interaction
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Report Card Management System ===");
        
        // Initial loading of report cards
        controller.loadReportCards();
        
        // Simulate delete action
        System.out.print("Enter report card ID to delete: ");
        String reportCardId = scanner.nextLine();
        
        // Trigger deletion flow
        controller.onDeleteButtonClicked(reportCardId);
        
        scanner.close();
    }
}