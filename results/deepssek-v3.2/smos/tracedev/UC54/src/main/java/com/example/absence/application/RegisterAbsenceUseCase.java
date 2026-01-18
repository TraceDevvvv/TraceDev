
package com.example.absence.application;

import com.example.absence.domain.*;
import com.example.absence.infrastructure.IAbsenceRepository;
import com.example.absence.infrastructure.INotificationService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Application layer use case for registering student absences.
 * Orchestrates domain objects and infrastructure serv.
 */
public class RegisterAbsenceUseCase {
    private IAbsenceRepository absenceRepository;
    private INotificationService notificationService;

    public RegisterAbsenceUseCase(IAbsenceRepository absenceRepository, INotificationService notificationService) {
        this.absenceRepository = absenceRepository;
        this.notificationService = notificationService;
    }

    /**
     * Executes the absence registration command.
     * @param command the command containing absence data
     * @return the result of the operation
     */
    public RegisterAbsenceResult execute(RegisterAbsenceCommand command) {
        try {
            // Load existing absences (for validation or comparison)
            List<Absence> existingAbsences = absenceRepository.findByClassAndDate(command.getClassId(), command.getDate());
            
            // Load class information
            com.example.absence.domain.Class classInfo = com.example.absence.domain.Class.loadClass(command.getClassId());
            
            // Create absence objects from command
            List<Absence> absences = createAbsenceObjects(command);
            // Save absences via repository
            absenceRepository.saveAbsences(absences);
            
            // Prepare and send notifications
            List<Notification> notifications = prepareNotifications(absences);
            int notificationCount = notificationService.sendBulkNotifications(notifications);
            
            // Create success result
            return createSuccessResult(notificationCount);
        } catch (Exception e) {
            // Handle persistence errors and other errors
            return createErrorResult("Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves students for a given class.
     * @param classId the class ID
     * @return list of students in the class
     */
    public List<Student> getStudentsForClass(String classId) {
        // Load class and return its students
        com.example.absence.domain.Class clazz = com.example.absence.domain.Class.loadClass(classId);
        return clazz.getStudents();
    }

    /**
     * Creates absence objects from the command.
     * @param command the register absence command
     * @return list of Absence objects
     */
    public List<Absence> createAbsenceObjects(RegisterAbsenceCommand command) {
        List<Absence> absences = new ArrayList<>();
        // For each entry, create an Absence object if the student is marked absent
        for (AbsenceEntry entry : command.getAbsenceEntries()) {
            if (entry.isAbsent()) {
                Student student = loadStudent(entry.getStudentId());
                if (student != null) {
                    // Load class (simplified: we just need class ID)
                    com.example.absence.domain.Class clazz = com.example.absence.domain.Class.loadClass(command.getClassId());
                    Absence absence = new Absence(student, clazz, command.getDate(), "ATA Staff");
                    absence.markAbsent();
                    absences.add(absence);
                }
            }
        }
        return absences;
    }

    /**
     * Loads a student by ID.
     * @param studentId the student ID
     * @return the Student object
     */
    public Student loadStudent(String studentId) {
        return Student.findById(studentId);
    }

    /**
     * Prepares notifications for absent students.
     * @param absences the list of absences
     * @return list of notifications to send
     */
    private List<Notification> prepareNotifications(List<Absence> absences) {
        List<Notification> notifications = new ArrayList<>();
        for (Absence absence : absences) {
            if (absence.isAbsent()) {
                Student student = absence.getStudent();
                String email = student.getParentEmail();
                if (email != null && !email.isEmpty()) {
                    String subject = "Absence Notification";
                    String body = String.format("Dear Parent, your child %s was absent on %s.",
                            student.getFullName(), absence.getDate());
                    notifications.add(new Notification(email, subject, body));
                }
            }
        }
        return notifications;
    }

    /**
     * Creates a success result with notification count.
     * @param notificationCount number of notifications sent
     * @return success result
     */
    public RegisterAbsenceResult createSuccessResult(int notificationCount) {
        return new RegisterAbsenceResult(true, "Absences registered successfully. Notifications sent: " + notificationCount, notificationCount);
    }

    /**
     * Creates an error result with message.
     * @param errorMessage the error message
     * @return error result
     */
    public RegisterAbsenceResult createErrorResult(String errorMessage) {
        return new RegisterAbsenceResult(false, errorMessage, 0);
    }
}
