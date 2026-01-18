package com.example.justification.application;

import com.example.justification.application.dto.AbsenceDisplayDTO;
import com.example.justification.application.dto.JustificationStatus;
import com.example.justification.domain.model.Absence;
import com.example.justification.domain.repository.AbsenceRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer responsible for business logic related to student justifications.
 * It coordinates between the controller and the data access layer.
 */
public class StudentJustificationService {
    private final AbsenceRepository absenceRepository;

    /**
     * Constructs a StudentJustificationService with a given AbsenceRepository.
     * @param repo The repository for absence data.
     */
    public StudentJustificationService(AbsenceRepository repo) {
        this.absenceRepository = repo;
    }

    /**
     * Retrieves a list of absences for a specific student, along with their justification status,
     * mapped to AbsenceDisplayDTOs for presentation.
     *
     * @param studentId The ID of the student.
     * @param year The academic year to retrieve absences from.
     * @return A list of AbsenceDisplayDTOs.
     */
    public List<AbsenceDisplayDTO> getAbsencesWithJustificationStatus(String studentId, int year) {
        System.out.println("  [Service] getAbsencesWithJustificationStatus called for student: " + studentId + ", year: " + year);

        // 1. Service -> AbsenceRepo: findByStudentIdAndYear
        List<Absence> absences = absenceRepository.findByStudentIdAndYear(studentId, year);
        System.out.println("  [Service] Received " + absences.size() + " absences from repository.");

        List<AbsenceDisplayDTO> displayDTOs = new ArrayList<>();

        // 2. Loop: Iterates through absences, determines justification status, and creates DTOs.
        for (Absence absence : absences) {
            // Service -> Service: determineJustificationStatus(absence)
            JustificationStatus status = determineJustificationStatus(absence);
            System.out.println("  [Service] Determining status for absence ID " + absence.getAbsenceId() + ": " + status);

            // Service -> Service: create AbsenceDisplayDTO
            AbsenceDisplayDTO dto = new AbsenceDisplayDTO(
                absence.getAbsenceId(),
                absence.getDate(),
                absence.getReason(),
                status,
                absence.getJustificationDetails()
            );
            displayDTOs.add(dto);
        }

        System.out.println("  [Service] Prepared " + displayDTOs.size() + " DTOs.");
        return displayDTOs;
    }

    /**
     * Determines the justification status of a given Absence object.
     * This is an internal helper method for the service.
     *
     * @param absence The Absence object to check.
     * @return The JustificationStatus (JUSTIFIED or NEEDS_JUSTIFICATION).
     */
    private JustificationStatus determineJustificationStatus(Absence absence) {
        // Business logic: if isJustified is true, then status is JUSTIFIED, otherwise NEEDS_JUSTIFICATION
        if (absence.isJustified()) {
            return JustificationStatus.JUSTIFIED;
        } else {
            return JustificationStatus.NEEDS_JUSTIFICATION;
        }
    }
}