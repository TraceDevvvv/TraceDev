package com.example;

import com.example.auth.Session;
import com.example.controllers.ReportController;
import com.example.domain.Report;
import com.example.gateways.SmosServerGateway;
import com.example.repositories.SmosServerRepository;
import com.example.usecases.DisplayReportInteractor;
import com.example.views.ReportView;
import java.util.Date;

/**
 * Main class to demonstrate the system functionality.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Report System ===");
        
        // Setup session (simulating logged-in student)
        Session session = new Session("student123", "student", new Date());
        
        // Setup repository and use case
        SmosServerRepository repository = new SmosServerRepository("http://smos.example.com");
        DisplayReportInteractor interactor = new DisplayReportInteractor(repository);
        
        // Setup controller
        ReportController controller = new ReportController(interactor, session);
        
        // Setup view
        ReportView view = new ReportView(controller);
        
        // Simulate user interaction
        System.out.println("\n1. User clicks 'Online report' button");
        view.onOnlineReportClicked();
        
        // Simulate fetching reports list
        System.out.println("\n2. Fetching reports list...");
        // In real flow, this would be triggered by controller
        // For demo, directly show some reports
        view.showReportsList(java.util.Arrays.asList(
            new Report("1", "Math Report", "Good progress", new Date()),
            new Report("2", "Science Report", "Excellent", new Date())
        ));
        
        System.out.println("\n3. User selects a report");
        view.onReportSelected("1");
        
        System.out.println("\n4. Displaying report details...");
        Report sampleReport = new Report("1", "Math Report", 
            "Student scored 95% in final exam. Shows excellent understanding of algebra and calculus.", 
            new Date());
        view.showReportDetails(sampleReport);
        
        System.out.println("\n5. Testing error scenario (connection interrupted)");
        // Create a gateway that will simulate connection failure
        SmosServerGateway gateway = new SmosServerGateway("http://smos.example.com");
        gateway.disconnect(); // Simulate disconnection
        
        // Try to fetch data when disconnected
        try {
            gateway.fetchData("GET /reports/student123");
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        System.out.println("\n=== Demonstration Complete ===");
    }
}