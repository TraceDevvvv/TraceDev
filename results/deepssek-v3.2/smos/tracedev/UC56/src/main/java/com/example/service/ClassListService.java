package com.example.service;

import com.example.dto.AcademicYearDTO;
import com.example.dto.ClassDTO;
import java.util.List;

/**
 * Service interface for retrieving academic years and classes for a professor.
 */
public interface ClassListService {
    /**
     * Gets academic years for a professor.
     * @param professorId the professor's ID
     * @return list of AcademicYearDTO
     */
    List<AcademicYearDTO> getAcademicYearsForProfessor(String professorId);

    /**
     * Gets classes for a professor and academic year.
     * @param professorId the professor's ID
     * @param academicYearId the academic year ID
     * @return list of ClassDTO
     */
    List<ClassDTO> getClassesForProfessorAndYear(String professorId, String academicYearId);
}