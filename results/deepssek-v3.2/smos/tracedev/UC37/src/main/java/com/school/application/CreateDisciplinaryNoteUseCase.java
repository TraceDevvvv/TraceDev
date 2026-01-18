
package com.school.application;

import com.school.domain.*;
import com.school.infrastructure.*;

import java.util.Date;

/**
 * Use case orchestrating the creation of a disciplinary note.
 */
public class CreateDisciplinaryNoteUseCase {
    private DisciplinaryNoteRepository noteRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private NotificationService notificationService;

    public CreateDisciplinaryNoteUseCase(
            DisciplinaryNoteRepository noteRepository,
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            NotificationService notificationService) {
        this.noteRepository = noteRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.notificationService = notificationService;
    }

    /**
     * Executes the use case as per sequence diagram.
     * Returns the created disciplinary note.
     */
    public DisciplinaryNote execute(CreateDisciplinaryNoteCommand command) {
        // Fetch required entities
        Student student = studentRepository.findById(command.getStudentId());
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        Teacher teacher = teacherRepository.findById(command.getTeacherId());
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }

        // Create and validate the note
        DisciplinaryNote note = new DisciplinaryNote(
                student,
                command.getDate(),
                teacher,
                command.getDescription()
        );
        note.validate();

        // Persist the note
        noteRepository.save(note);

        // Notify parent via email
        notifyParent(student, note);

        return note;
    }

    /**
     * Sends email notification to parent with retry logic.
     * Retries up to 3 times as per sequence diagram.
     */
    private void notifyParent(Student student, DisciplinaryNote note) {
        String to = student.getParentEmail();
        String subject = "Disciplinary Note for " + student.getName();
        String body = "A disciplinary note has been created for your child.\n"
                + "Date: " + note.getDate() + "\n"
                + "Teacher: " + note.getTeacher().getName() + "\n"
                + "Description: " + note.getDescription();

        boolean success = false;
        int maxRetries = 3;
        for (int i = 0; i < maxRetries; i++) {
            try {
                notificationService.sendEmail(to, subject, body);
                success = true;
                break;
            } catch (Exception e) {
                System.err.println("Email send attempt " + (i + 1) + " failed: " + e.getMessage());
                if (i == maxRetries - 1) {
                    System.err.println("All retry attempts failed for email to: " + to);
                }
            }
        }
        if (!success) {
            System.err.println("Notification email could not be sent after retries.");
        }
    }
}
