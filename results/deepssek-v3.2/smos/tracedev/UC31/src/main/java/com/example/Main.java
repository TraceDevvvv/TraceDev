package com.example;

import com.example.application.ChangeAbsenceCommand;
import com.example.application.ChangeAbsenceInteractor;
import com.example.application.ports.AbsenceRepository;
import com.example.application.ports.EmailService;
import com.example.application.ports.EventPublisher;
import com.example.application.ports.StudentRepository;
import com.example.controller.AbsenceController;
import com.example.domain.AbsenceStatus;
import com.example.infrastructure.email.EmailServiceImpl;
import com.example.infrastructure.events.EventPublisherImpl;
import com.example.infrastructure.persistence.AbsenceRepositoryImpl;
import com.example.infrastructure.persistence.StudentRepositoryImpl;
import com.example.notification.EmailNotificationHandler;
import com.example.ui.AbsenceUI;
import java.sql.Connection;
import java.util.Date;

/**
 * Main class to demonstrate the flow based on the sequence diagram.
 * This sets up the dependencies and simulates the administrator's interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Absence Management System ===\n");

        // Setup infrastructure (simulated)
        Connection dummyConnection = null; // in reality would be a real connection
        AbsenceRepository absenceRepo = new AbsenceRepositoryImpl(dummyConnection);
        StudentRepository studentRepo = new StudentRepositoryImpl(dummyConnection);

        // Setup email service and event publisher
        EmailService emailService = new EmailServiceImpl(null);
        EventPublisherImpl eventPublisher = new EventPublisherImpl();
        EmailNotificationHandler emailHandler = new EmailNotificationHandler(emailService);
        eventPublisher.addSubscriber(emailHandler);

        // Create interactor
        ChangeAbsenceInteractor interactor = new ChangeAbsenceInteractor(
                absenceRepo, studentRepo, eventPublisher);

        // Create controller and UI
        AbsenceController controller = new AbsenceController(interactor);
        AbsenceUI ui = new AbsenceUI(controller);

        // Simulate actor (Administrator) interactions
        System.out.println("1. Administrator selects an absence (from date selection)");
        ui.selectAbsence("absence-001");
        ui.displayAbsenceDetails();
        System.out.println();

        System.out.println("2. Administrator modifies absence and clicks Save");
        ui.simulateModifyAndSave("absence-001", AbsenceStatus.EXCUSED, "Doctor appointment");
        System.out.println();

        System.out.println("3. System updates screen based on selected date");
        ui.updateScreen(new Date());
        System.out.println();
    }
}