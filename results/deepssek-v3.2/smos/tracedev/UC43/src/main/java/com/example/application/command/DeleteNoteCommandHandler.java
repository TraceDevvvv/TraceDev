package com.example.application.command;

import com.example.domain.DisciplinaryNote;
import com.example.domain.Student;
import com.example.ports.NoteRepository;
import com.example.ports.NotificationService;
import com.example.ports.StudentRepository;
import com.example.ports.SmosServerClient;

public class DeleteNoteCommandHandler implements CommandHandler<DeleteNoteCommand> {
    private NoteRepository noteRepository;
    private NotificationService notificationService;
    private StudentRepository studentRepository;
    private SmosServerClient smosServerClient;

    // Constructor with dependencies
    public DeleteNoteCommandHandler(NoteRepository noteRepository, 
                                    NotificationService notificationService,
                                    StudentRepository studentRepository,
                                    SmosServerClient smosServerClient) {
        this.noteRepository = noteRepository;
        this.notificationService = notificationService;
        this.studentRepository = studentRepository;
        this.smosServerClient = smosServerClient;
    }

    @Override
    public void handle(DeleteNoteCommand command) {
        System.out.println("Handling delete note command for note ID: " + command.getNoteId());
        
        // Retrieve student information
        Student student = studentRepository.findById(command.getNoteId());
        if (student == null) {
            System.out.println("Student not found for note ID: " + command.getNoteId());
            return;
        }
        
        // Get parent email (using repository method)
        String parentEmail = studentRepository.getStudentEmail(command.getNoteId());
        
        // Send notification to parents before deletion (quality requirement)
        boolean notificationSent = notificationService.sendNotificationToParents(command.getNoteId(), "incorrect corrige");
        if (!notificationSent) {
            System.out.println("Failed to send notification. Proceeding with deletion anyway.");
        }
        
        // Delete note from SMOS archive
        boolean archiveDeleted = smosServerClient.deleteFromArchive(command.getNoteId());
        if (!archiveDeleted) {
            System.out.println("Failed to delete note from SMOS archive. Throwing exception.");
            throw new RuntimeException("Connection Failed");
        }
        
        // Delete note from repository
        noteRepository.delete(command.getNoteId());
        
        System.out.println("Note deletion process completed successfully.");
    }

    // Handle interruption by administrator
    public void handleInterruption() {
        System.out.println("Handling interruption. Saving current state...");
        // In a real scenario, we would save the current state (e.g., transaction state)
        // For simplicity, we assume the note repository can save a temporary state
        noteRepository.save(null); // Placeholder for actual state
        System.out.println("State saved. Operation interrupted.");
    }
}