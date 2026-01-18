package com.example;

import com.example.actor.Administrator;
import com.example.application.DeleteNoteService;
import com.example.application.command.DeleteNoteCommand;
import com.example.application.command.DeleteNoteCommandHandler;
import com.example.adapters.NoteRepositoryImpl;
import com.example.adapters.NotificationAdapter;
import com.example.adapters.SmosServerAdapter;
import com.example.external.EmailService;
import com.example.ports.SmosServerClient;
import com.example.ports.StudentRepository;
import com.example.ports.NotificationService;
import com.example.ports.NoteRepository;
import com.example.application.query.NoteDetailsQuery;
import com.example.adapters.StudentRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Disciplinary Note Deletion System");

        // Setup dependencies
        NoteRepository noteRepository = new NoteRepositoryImpl();
        StudentRepository studentRepository = new StudentRepositoryImpl();
        EmailService emailService = new EmailService("smtp.example.com");
        NotificationService notificationService = new NotificationAdapter(emailService);
        SmosServerClient smosServerClient = new SmosServerAdapter();

        // Create command handler
        DeleteNoteCommandHandler commandHandler = new DeleteNoteCommandHandler(
                noteRepository,
                notificationService,
                studentRepository,
                smosServerClient
        );

        // Create service
        DeleteNoteService deleteNoteService = new DeleteNoteService(commandHandler);

        // Create administrator and set the service
        Administrator admin = new Administrator("admin1");
        admin.setService(deleteNoteService);

        // Create a delete command
        DeleteNoteCommand command = new DeleteNoteCommand("note1", admin.getAdminId());

        // Request deletion
        String deletionResult = admin.requestNoteDeletion(command);
        System.out.println("Deletion result: " + deletionResult);

        // Simulate interruption
        String interruptionResult = admin.interruptOperation();
        System.out.println("Interruption result: " + interruptionResult);
    }
}