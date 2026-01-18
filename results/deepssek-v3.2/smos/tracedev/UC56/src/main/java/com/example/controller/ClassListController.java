package com.example.controller;

import com.example.dto.AcademicYearDTO;
import com.example.dto.ClassDTO;
import com.example.service.ClassListService;
import java.util.List;

/**
 * Controller coordinating the display of academic years and classes.
 * Quality Requirement: response time < 2s.
 */
public class ClassListController {
    private ClassListService classListService;

    public ClassListController(ClassListService classListService) {
        this.classListService = classListService;
    }

    /**
     * Retrieves academic years for the currently logged‑in professor.
     * Assumes professorId is obtained from session/context; for simplicity we pass a dummy ID.
     * @return list of AcademicYearDTO
     */
    public List<AcademicYearDTO> displayAcademicYearList() {
        // In a real application, professorId would come from the authenticated session.
        String professorId = "prof-dummy"; // dummy ID for demonstration
        return classListService.getAcademicYearsForProfessor(professorId);
    }

    /**
     * Retrieves classes for a given academic year for the current professor.
     * @param academicYearId the academic year ID
     * @return list of ClassDTO
     */
    public List<ClassDTO> displayClassesForYear(String academicYearId) {
        String professorId = "prof-dummy"; // dummy ID for demonstration
        return classListService.getClassesForProfessorAndYear(professorId, academicYearId);
    }

    /**
     * Method corresponding to sequence diagram return messages (e.g., "empty list").
     * This is already covered by existing methods; we keep original logic.
     */
}