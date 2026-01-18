package com.example;

import com.example.model.Administrator;
import com.example.model.Class;
import com.example.archive.ArchiveConnection;
import com.example.repository.ArchiveClassRepository;
import com.example.controller.ViewClassListController;
import com.example.ui.ClassManagementScreen;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Main class to demonstrate the "View List of Classes" use case.
 * This simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting View List of Classes Use Case ===\n");

        // 1. Create and log in Administrator (REQ-EC1)
        Administrator admin = new Administrator("A001", "John Doe", "Administrator");
        admin.setLoggedIn(true);

        // 2. Set up repository and controller
        ArchiveConnection archiveConn = new ArchiveConnection("http://archive.smos.example.com");
        ArchiveClassRepository repository = new ArchiveClassRepository(archiveConn);
        ViewClassListController controller = new ViewClassListController(repository);

        // 3. Create the UI screen
        ClassManagementScreen screen = new ClassManagementScreen(admin, controller);

        // 4. Simulate the sequence diagram steps
        // Step: Administrator selects Class Management
        screen.selectClassManagement();

        // Verify login (REQ-EC1)
        boolean loggedIn = screen.verifyLogin();
        if (!loggedIn) {
            System.out.println("Error: User is not logged in.");
            return;
        }

        // Simulate user input for academic year
        String selectedYear = "2023-2024";

        // Alternative: simulate interruption by administrator (REQ-EC3)
        boolean simulateInterruption = false; // Change to true to test interruption
        if (simulateInterruption) {
            screen.cancelOperation();
            return;
        }

        // Normal flow: Administrator selects academic year
        screen.selectAcademicYear(selectedYear);

        // Alternative: simulate connection failure (REQ-EC2)
        boolean simulateConnectionFailure = false; // Change to true to test connection failure
        if (simulateConnectionFailure) {
            // Force connection failure by overriding connect method
            ArchiveConnection failingConn = new ArchiveConnection("http://unreachable.server") {
                @Override
                public boolean connect() {
                    notifyConnectionLost();
                    return false;
                }
            };
            ArchiveClassRepository failingRepo = new ArchiveClassRepository(failingConn);
            ViewClassListController failingController = new ViewClassListController(failingRepo);
            ClassManagementScreen failureScreen = new ClassManagementScreen(admin, failingController);
            failureScreen.selectAcademicYear(selectedYear);
        }

        System.out.println("\n=== Use Case Completed ===");
    }
}