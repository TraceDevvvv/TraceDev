package com.example.application;

import com.example.application.ports.AbsenceRepository;
import com.example.application.ports.EventPublisher;
import com.example.application.ports.StudentRepository;
import com.example.domain.Absence;
import com.example.domain.AbsenceStatus;
import com.example.domain.Student;
import com.example.domain.events.AbsenceModifiedEvent;

/**
 * Interactor (use case) for changing an absence record.
 */
public class ChangeAbsenceInteractor {
    private AbsenceRepository absenceRepository;
    private StudentRepository studentRepository;
    private EventPublisher eventPublisher;

    public ChangeAbsenceInteractor(AbsenceRepository absenceRepo,
                                   StudentRepository studentRepo,
                                   EventPublisher publisher) {
        this.absenceRepository = absenceRepo;
        this.studentRepository = studentRepo;
        this.eventPublisher = publisher;
    }

    public void execute(ChangeAbsenceCommand command) {
        // Step 1: retrieve absence
        Absence absence = absenceRepository.findById(command.getAbsenceId());
        if (absence == null) {
            throw new IllegalArgumentException("Absence not found: " + command.getAbsenceId());
        }
        AbsenceStatus oldStatus = absence.getStatus();

        // Step 2: modify absence
        absence.modifyAbsence(command.getNewStatus(), command.getReason());

        // Step 3: retrieve student (for event and notification)
        Student student = studentRepository.findById(absence.getStudentId());
        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + absence.getStudentId());
        }

        // Step 4: persist updated absence
        absenceRepository.save(absence);

        // Step 5: publish event (asynchronous)
        AbsenceModifiedEvent event = new AbsenceModifiedEvent(
                absence.getId(),
                absence.getStudentId(),
                absence.getDate(),
                oldStatus,
                command.getNewStatus()
        );
        eventPublisher.publish(event);
    }

    // Cancel operation (rollback) – simplified implementation.
    public void cancel() {
        // In a real application, this might rollback a transaction or clear a session.
        // For this example we just log.
        System.out.println("ChangeAbsenceInteractor: operation cancelled.");
    }

    // New method for sequence diagram message
    public void rollbackTransaction() {
        System.out.println("ChangeAbsenceInteractor: rollback transaction.");
    }

    public void operationCancelled() {
        System.out.println("ChangeAbsenceInteractor: operation cancelled.");
    }
}