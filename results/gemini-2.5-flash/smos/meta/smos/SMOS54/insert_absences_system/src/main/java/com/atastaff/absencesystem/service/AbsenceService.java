package com.atastaff.absencesystem.service;

import com.atastaff.absencesystem.dto.AbsenceRequest;
import com.atastaff.absencesystem.dto.StudentAttendanceDTO;
import com.atastaff.absencesystem.model.Absence;
import com.atastaff.absencesystem.model.Class;
import com.atastaff.absencesystem.model.Student;
import com.atastaff.absencesystem.repository.AbsenceRepository;
import com.atastaff.absencesystem.repository.ClassRepository;
import com.atastaff.absencesystem.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling absence-related business logic.
 * This includes processing absence requests, saving absence records,
 * and orchestrating notifications and integration with the SMOS server.
 */
@Service
public class AbsenceService {

    private static final Logger logger = LoggerFactory.getLogger(AbsenceService.class);

    private final AbsenceRepository absenceRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final NotificationService notificationService;
    private final SmosIntegrationService smosIntegrationService;

    /**
     * Constructor for AbsenceService, injecting required repositories and serv.
     *
     * @param absenceRepository      Repository for Absence entities.
     * @param studentRepository      Repository for Student entities.
     * @param classRepository        Repository for Class entities.
     * @param notificationService    Service for sending parent notifications.
     * @param smosIntegrationService Service for integrating with the SMOS server.
     */
    public AbsenceService(AbsenceRepository absenceRepository,
                          StudentRepository studentRepository,
                          ClassRepository classRepository,
                          NotificationService notificationService,
                          SmosIntegrationService smosIntegrationService) {
        this.absenceRepository = absenceRepository;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.notificationService = notificationService;
        this.smosIntegrationService = smosIntegrationService;
    }

    /**
     * Processes an absence request, recording absences, sending notifications,
     * and integrating with the SMOS server.
     * This method is transactional to ensure data consistency.
     *
     * @param request The AbsenceRequest DTO containing class ID and student attendance data.
     * @throws IllegalArgumentException if the class or any student is not found.
     * @throws RuntimeException         if SMOS integration fails.
     */
    @Transactional
    public void processAbsences(AbsenceRequest request) {
        logger.info("Processing absence request for classId: {}", request.getClassId());

        // 1. Retrieve Class entity
        Class targetClass = classRepository.findById(request.getClassId())
                .orElseThrow(() -> {
                    logger.error("Class not found with ID: {}", request.getClassId());
                    return new IllegalArgumentException("Class not found with ID: " + request.getClassId());
                });

        // 2. Filter for absent students and retrieve their IDs
        List<String> absentStudentIds = request.getAttendanceList().stream()
                .filter(StudentAttendanceDTO::isAbsent)
                .map(StudentAttendanceDTO::getStudentId)
                .collect(Collectors.toList());

        if (absentStudentIds.isEmpty()) {
            logger.info("No students marked absent for classId: {}. No absences to record.", request.getClassId());
            return; // No absent students, nothing to do
        }

        // 3. Retrieve all relevant Student entities
        List<Student> students = studentRepository.findAllById(
                request.getAttendanceList().stream()
                        .map(StudentAttendanceDTO::getStudentId)
                        .collect(Collectors.toList())
        );

        // Map student IDs to Student objects for quick lookup
        Map<String, Student> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getStudentId, student -> student));

        List<Absence> newAbsences = new ArrayList<>();
        LocalDate today = LocalDate.now();

        // 4. Create and Save Absence Records & Send Notifications
        for (String studentId : absentStudentIds) {
            Student absentStudent = studentMap.get(studentId);
            if (absentStudent == null) {
                logger.warn("Absent student with ID {} not found in the system. Skipping.", studentId);
                // Depending on requirements, this could throw an exception or just log and continue.
                // For now, we'll log and skip.
                continue;
            }

            // Create new Absence record
            Absence absence = new Absence();
            absence.setStudent(absentStudent);
            absence.setClassEntity(targetClass);
            absence.setAbsenceDate(today);
            absence.setNotifiedParent(false); // Will be updated after notification attempt

            Absence savedAbsence = absenceRepository.save(absence);
            newAbsences.add(savedAbsence);
            logger.debug("Recorded absence for student: {} in class: {}", absentStudent.getStudentId(), targetClass.getClassId());

            // Send notification to parent
            try {
                notificationService.sendAbsenceNotification(absentStudent, targetClass, today);
                savedAbsence.setNotifiedParent(true); // Mark as notified if sending was successful
                absenceRepository.save(savedAbsence); // Update the absence record
                logger.info("Sent absence notification to parent of student: {}", absentStudent.getStudentId());
            } catch (Exception e) {
                logger.error("Failed to send absence notification for student {}: {}", absentStudent.getStudentId(), e.getMessage());
                // Continue processing other absences even if one notification fails
            }
        }

        // 5. Send Absence Data to SMOS Server
        if (!newAbsences.isEmpty()) {
            try {
                boolean smosSuccess = smosIntegrationService.sendAbsenceDataToSmos(newAbsences);
                if (smosSuccess) {
                    logger.info("Successfully sent {} absence records to SMOS server.", newAbsences.size());
                } else {
                    logger.error("Failed to send absence records to SMOS server. SMOS integration service reported failure.");
                    // Depending on requirements, this could throw an exception to rollback the transaction
                    // or trigger a retry mechanism. For now, we'll log and let the transaction commit.
                    throw new RuntimeException("SMOS integration failed for absence data.");
                }
            } catch (Exception e) {
                logger.error("Error during SMOS integration for absences: {}", e.getMessage());
                throw new RuntimeException("Error communicating with SMOS server: " + e.getMessage(), e);
            }
        } else {
            logger.info("No new absences to send to SMOS server.");
        }

        logger.info("Absence request processing completed for classId: {}", request.getClassId());
    }
}