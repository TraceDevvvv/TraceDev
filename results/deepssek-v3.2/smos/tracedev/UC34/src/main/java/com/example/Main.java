package com.example;

import com.example.controller.JustificationController;
import com.example.dto.JustificationViewDTO;
import com.example.entity.Absence;
import com.example.enums.JustificationStatus;
import com.example.manager.SMOSConnectionManager;
import com.example.repository.AbsenceRepositoryImpl;
import com.example.service.AuthenticationService;
import com.example.service.JustificationLogic;
import com.example.service.JustificationService;
import com.example.service.RegistrationService;
import com.example.ui.JustificationUI;
import java.util.Date;

/**
 * Main class to demonstrate the system.
 * Sets up all components and runs the use case.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting View Student Justifications Use Case ===\n");

        // 1. Setup serv and repositories
        AbsenceRepositoryImpl absenceRepository = new AbsenceRepositoryImpl();
        // Add sample data
        absenceRepository.addAbsence(new Absence("1", new Date(), "Illness", JustificationStatus.JUSTIFIED));
        absenceRepository.addAbsence(new Absence("2", new Date(), "Family emergency", JustificationStatus.TO_JUSTIFY));
        absenceRepository.addAbsence(new Absence("3", new Date(), "Doctor appointment", JustificationStatus.JUSTIFIED));

        JustificationLogic logic = new JustificationLogic();
        JustificationService justificationService = new JustificationService(absenceRepository, logic);
        AuthenticationService authService = new AuthenticationService();
        RegistrationService regService = new RegistrationService();

        // 2. Setup controller and UI
        JustificationController controller = new JustificationController(justificationService, regService, authService);
        JustificationUI ui = new JustificationUI();
        SMOSConnectionManager smosManager = new SMOSConnectionManager();
        Administrator admin = new Administrator(ui, smosManager);

        // 3. Execute normal flow (preconditions satisfied)
        System.out.println("--- Scenario 1: Normal flow (admin user, registered student) ---");
        admin.clickJustificationButton(controller, "registered", "admin", false);

        System.out.println("\n--- Scenario 2: Authentication failure (non-admin user) ---");
        controller.handleJustificationViewRequest("registered");
        System.out.println("Access denied: Administrator login required.");

        System.out.println("\n--- Scenario 3: Registration not completed ---");
        controller.handleJustificationViewRequest("notregistered");
        System.out.println("Access denied: Registration not completed.");
    }
}