package com.schoolsystem;

import com.schoolsystem.authentication.AuthenticationService;
import com.schoolsystem.cache.Cache;
import com.schoolsystem.controller.ReportCardController;
import com.schoolsystem.domain.ReportCard;
import com.schoolsystem.infrastructure.SMOSServerClient;
import com.schoolsystem.repository.ReportCardRepositoryImpl;
import com.schoolsystem.service.ReportCardService;
import com.schoolsystem.view.ConsoleReportCardView;
import javax.sql.DataSource;
import java.util.List;

/**
 * Main class to demonstrate the system.
 * Simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        SMOSServerClient smos = new SMOSServerClient("http://smos.example.com");
        Cache cache = new Cache();
        
        // Simulate a DataSource (using null since we don't have actual DB setup)
        DataSource dataSource = null;
        
        ReportCardRepositoryImpl repository = new ReportCardRepositoryImpl(smos, cache, dataSource);
        ReportCardService service = new ReportCardService(repository);
        AuthenticationService auth = new AuthenticationService();
        ConsoleReportCardView view = new ConsoleReportCardView();
        ReportCardController controller = new ReportCardController(service, auth, view);

        // Simulate parent interaction
        System.out.println("=== Parent Views Student Report Card ===");
        String studentId = "S123";
        String sessionId = "session_xyz";

        // Step 1: Parent clicks child image -> show list
        System.out.println("\n1. Parent clicks child image (studentId=" + studentId + ")");
        view.setController(controller); // Allow view to call controller
        view.simulateParentClicksChildImage(studentId, sessionId);

        // Step 2: User selects a report card (simulated by view)
        System.out.println("\n2. Parent selects report card R001");
        view.simulateParentSelectsReportCard("R001", sessionId);
    }
}