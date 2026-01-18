package com.example;

import com.example.controller.LoginController;
import com.example.controller.DigitalRegisterController;
import com.example.service.DigitalRegisterService;
import com.example.repository.RegisterRepository;
import com.example.repository.RegisterRepositoryImpl;
import com.example.server.SMOSServer;
import com.example.model.Session;
import com.example.dto.RegisterDTO;
import java.util.List;

/**
 * Main class to demonstrate the flow as per sequence diagram.
 * This simulates the "Direction" actor interacting with the system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Digital Registers Demo ===\n");

        // 1. Setup components (Dependency Injection)
        SMOSServer smosServer = new SMOSServer("http://smos.example.com");
        RegisterRepository repository = new RegisterRepositoryImpl(smosServer);
        DigitalRegisterService service = new DigitalRegisterService(repository);
        DigitalRegisterController controller = new DigitalRegisterController(service);
        LoginController loginController = new LoginController();

        // 2. Entry Condition: User logs in (Direction authenticates)
        System.out.println("Step 1: Authentication");
        Session session = null;
        try {
            session = loginController.authenticate("DIR001", "password123");
            System.out.println("Login successful. Session created for: " + session.getUserId());
        } catch (Exception e) {
            System.out.println("Access denied. Redirect to login.");
            return;
        }

        // 3. Direction clicks "Digital Register" button
        System.out.println("\nStep 2: Show year selection");
        controller.showYearSelection(session);

        // 4. Direction selects an academic year
        int selectedYear = 2024;
        System.out.println("\nStep 3: Fetch registers for academic year " + selectedYear);
        try {
            List<RegisterDTO> registers = controller.getRegistersByYear(session, selectedYear);

            // 5. Display registers
            System.out.println("\nStep 4: Display registers");
            controller.displayRegisters(registers);
        } catch (Exception e) {
            // Connection error
        }
    }
}